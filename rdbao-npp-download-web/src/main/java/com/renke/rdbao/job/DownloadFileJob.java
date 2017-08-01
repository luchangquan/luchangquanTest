package com.renke.rdbao.job;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.data.request.RequestData;
import com.renke.rdbao.beans.common.enums.AliOssBucketEnum;
import com.renke.rdbao.beans.common.enums.FileTypeEnum;
import com.renke.rdbao.beans.common.enums.TenantEnum;
import com.renke.rdbao.beans.common.vo.AttachEmailVo;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMREvidenceFile;
import com.renke.rdbao.controller.ApiEvidenceController;
import com.renke.rdbao.controller.base.BaseWeb;
import com.renke.rdbao.util.AliOssUtil;
import com.renke.rdbao.util.FileUtil;
import com.renke.rdbao.util.MD5Util;
import com.renke.rdbao.util.MailUtil;
import com.renke.rdbao.util.ZipCompressing;

public class DownloadFileJob {
	private static final Logger _LOGGER = LoggerFactory.getLogger(ApiEvidenceController.class);

	private static Queue<EnhancedMEvidence> needDownloadQueue = new ConcurrentLinkedQueue<EnhancedMEvidence>();
	private static Queue<RequestData> needDownloadBatchQueue = new ConcurrentLinkedQueue<RequestData>();

	static {
		start();
		startBatch();
	}

	private static void start() {
		try {
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				public void run() {
					try {
						doSchedule();
					} catch (Throwable e) {
						e.printStackTrace();
					}
				}
			}, 10 * 1000, 3 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private static void startBatch() {
		try {
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				public void run() {
					try {
						doScheduleBatch();
					} catch (Throwable e) {
						e.printStackTrace();
					}
				}
			}, 10 * 1000, 3 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private static void doSchedule() throws Throwable {
		if (needDownloadQueue.isEmpty()) {
			Thread.sleep(10 * 1000);
			return;
		}
		EnhancedMEvidence enhancedMEvidence = needDownloadQueue.poll();
		for (EnhancedMREvidenceFile _EnhancedMREvidenceFile : enhancedMEvidence.getEnhancedMREvidenceFiles()) {
			String filePath = BaseWeb.DOWNLOAD_LOCAL_PATH_PREFIX + _EnhancedMREvidenceFile.getPath();
			File curFile = new File(filePath);

			if (!curFile.exists() || curFile.length() < _EnhancedMREvidenceFile.getSize()) {
				Object[] logArgs = new Object[] { _EnhancedMREvidenceFile.getBucket(), _EnhancedMREvidenceFile.getPath(), filePath };
				if (!curFile.exists()) {
					FileUtil.createFile(filePath);
				}
				_LOGGER.info("[即将下载文件:{}--{}]", logArgs);
				AliOssUtil.downloadFile(AliOssBucketEnum.getAliOssBucketEnumByName(_EnhancedMREvidenceFile.getBucket()), _EnhancedMREvidenceFile.getPath(), filePath);
			}
		}

	}

	private static void doScheduleBatch() throws Throwable {
		if (needDownloadBatchQueue.isEmpty()) {
			Thread.sleep(10 * 1000);
			return;
		}
		_LOGGER.info("[批量下载队列当前长度:{}]", needDownloadBatchQueue.size());
		RequestData requestData = needDownloadBatchQueue.poll();
		String email = requestData.getRequest().getString("email");
		String time = requestData.getRequest().getString("time");
		String tenantCode = requestData.getRequest().getString("tenantCode");
		EnhancedMEvidence[] enhancedEvidences = requestData.getRequest().getObject("evidences", EnhancedMEvidence[].class);

		if (!checkFileExists(Arrays.asList(enhancedEvidences))) {
			addBatch(requestData);// 文件不存在再次放入队列中
		} else {
			String firstPath = BaseWeb.DOWNLOAD_LOCAL_PATH_PREFIX + enhancedEvidences[0].getEnhancedMREvidenceFiles().get(0).getPath();

			String zipFileName = firstPath.replace(FileUtil.getFileName(firstPath), "") + "/" + email + "/" + time + ".zip";
			List<File> files = getFiles(Arrays.asList(enhancedEvidences));

			try {
				if (ZipCompressing.zip(zipFileName, files)) {// 压缩到本地目录

					AttachEmailVo simpleEmailVo = new AttachEmailVo();
					simpleEmailVo.setHostName("smtp.exmail.qq.com");
					simpleEmailVo.setSslOnConnect(true);
					simpleEmailVo.setSmtpPort((short) 587);
					simpleEmailVo.setUsername("notice@renosdata.com");
					simpleEmailVo.setPassword("Renke123@qw");
					simpleEmailVo.setSubject("证据批量下载");
					simpleEmailVo.setFrom("notice@renosdata.com");
					if (tenantCode.equalsIgnoreCase(TenantEnum.TENANT_1010BAO.getCode())) {
						simpleEmailVo.setFromNickname("实时保");
					} else if (tenantCode.equalsIgnoreCase(TenantEnum.TENANT_189.getCode())) {
						simpleEmailVo.setFromNickname("公证录音");
					}

					simpleEmailVo.setTos(Arrays.asList(new String[] { email }));
					simpleEmailVo.setContent("尊敬的用户您好！<br/>  附件是您申请的文件，请妥善保管！");
					simpleEmailVo.setPaths(Arrays.asList(new String[] { zipFileName }));
					simpleEmailVo.setShownames(Arrays.asList(new String[] { "批量文件.zip" }));
					simpleEmailVo.setDescriptions(Arrays.asList(new String[] { "批量文件下载" }));

					MailUtil.send(simpleEmailVo);// 发送邮件
					_LOGGER.info("----send email--邮件发送成功-->" + email);
				}
			} finally {// 不管有没有成功本地文件都要删除
				new File(zipFileName).delete();
			}
		}
	}

	private static List<File> getFiles(List<EnhancedMEvidence> enhancedMEvidences) {
		List<File> files = Lists.newArrayList();
		for (EnhancedMEvidence _EnhancedMEvidence : enhancedMEvidences) {
			List<EnhancedMREvidenceFile> enhancedMREvidenceFiles = _EnhancedMEvidence.getEnhancedMREvidenceFiles();
			for (EnhancedMREvidenceFile _EnhancedMREvidenceFile : enhancedMREvidenceFiles) {
				if (_EnhancedMREvidenceFile.getFileType() != FileTypeEnum.SIGN_TEXT) {
					File curFile = new File(BaseWeb.DOWNLOAD_LOCAL_PATH_PREFIX + _EnhancedMREvidenceFile.getPath());
					files.add(curFile);
				}
			}
		}
		return files;
	}

	private static boolean checkFileExists(List<EnhancedMEvidence> enhancedEvidences) {
		for (EnhancedMEvidence _EnhancedMEvidence : enhancedEvidences) {
			List<EnhancedMREvidenceFile> enhancedMREvidenceFiles = _EnhancedMEvidence.getEnhancedMREvidenceFiles();
			for (EnhancedMREvidenceFile _EnhancedMREvidenceFile : enhancedMREvidenceFiles) {
				// if (_EnhancedMREvidenceFile.getFileType() !=
				// FileTypeEnum.SIGN_TEXT) {
				File curFile = new File(BaseWeb.DOWNLOAD_LOCAL_PATH_PREFIX + _EnhancedMREvidenceFile.getPath());
				if (!curFile.exists() || curFile.length() < _EnhancedMREvidenceFile.getSize()) {
					return false;
				}
				// }
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(MD5Util.MD5("abcd1234"));
	}

	public static void add(EnhancedMEvidence enhancedMEvidence) {
		needDownloadQueue.add(enhancedMEvidence);
	}

	public static void addBatch(RequestData requestData) {
		needDownloadBatchQueue.add(requestData);
	}
}
