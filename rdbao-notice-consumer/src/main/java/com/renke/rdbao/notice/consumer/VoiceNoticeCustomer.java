package com.renke.rdbao.notice.consumer;

import java.io.File;
import java.io.IOException;
import java.lang.Thread.State;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.Message;
import com.aliyun.mns.model.Message.MessageBodyType;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.renke.rdbao.beans.common.constants.AliMnsQueueTemplateConstants;
import com.renke.rdbao.beans.common.enums.AliOssBucketEnum;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.exception.AliOperateException;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.vo.notice.NoticeRequestSignatureVo;
import com.renke.rdbao.beans.common.vo.notice.NoticeRequestVo;
import com.renke.rdbao.beans.common.vo.notice.voice.VoiceNoticeRequestData;
import com.renke.rdbao.util.AesUtil;
import com.renke.rdbao.util.AliMnsUtil;
import com.renke.rdbao.util.AliOssUtil;
import com.renke.rdbao.util.FileUtil;
import com.renke.rdbao.util.HttpUtility;
import com.renke.rdbao.util.PropertiesConfUtil;
import com.renke.rdbao.util.RsaUtil;
import com.renke.rdbao.util.SFTPUtil;
import com.renke.rdbao.util.notice.VoiceNoticeRequestUtil;

/**
 * 公证录音通知消费者
 * 
 * @author jgshun
 * @date 2017-2-27 下午5:17:32
 * @version 1.0
 */
public class VoiceNoticeCustomer {
	private static final Logger _LOGGER = LoggerFactory.getLogger(VoiceNoticeCustomer.class);

	private static volatile int scheduleCount;
	private static volatile Map<Integer, Thread> curRunningSchedule = new ConcurrentHashMap<>();

	/**
	 * 启动服务
	 */
	public static void start() {
		for (int i = 0; i < Integer.valueOf(PropertiesConfUtil.PROPERTIES_CONF.getNoticeConsumerConf().getVoiceCustomerCount()); i++) {
			Thread curRunningThread = new Thread(new Schedule());
			curRunningThread.start();
			curRunningSchedule.put(i, curRunningThread);
		}
		checkScheduleStatus();
	}

	public static void start(int taskId) {
		Thread curRunningThread = new Thread(new Schedule());
		curRunningThread.start();
		curRunningSchedule.put(taskId, curRunningThread);
	}

	/**
	 * 检查启动任务的状态
	 */
	private static void checkScheduleStatus() {

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				_LOGGER.info("[公证录音通知消费者-开始检测任务运行情况....]");
				Iterator<Entry<Integer, Thread>> scheduleIterator = curRunningSchedule.entrySet().iterator();
				while (scheduleIterator.hasNext()) {
					Entry<Integer, Thread> scheduleEntry = scheduleIterator.next();
					Integer taskId = scheduleEntry.getKey();
					Thread schedule = scheduleEntry.getValue();
					if (schedule.getState() == State.TERMINATED) {
						_LOGGER.warn("[公证录音通知消费者-任务停止,重启任务:{}]", taskId);
						start(taskId);
						_LOGGER.warn("[公证录音通知消费者-任务停止,重启成功:{}]", taskId);
					} else {
						_LOGGER.info("[公证录音通知消费者-任务正常运行:{}]", taskId);
					}
				}
				_LOGGER.info("[公证录音通知消费者-检测任务运行情况完毕]");
			}
		}, 3 * 60 * 1000, 3 * 60 * 1000);
	}

	/**
	 * 处理服务
	 * 
	 * @param queue
	 * @param message
	 * @throws AliOperateException
	 * @throws IOException
	 */
	private static void dealMessage(CloudQueue queue, Message message) throws AliOperateException, IOException {
		String noticeRequest = message.getMessageBodyAsRawString();
		NoticeRequestVo noticeRequestVo = JSONObject.parseObject(noticeRequest, NoticeRequestVo.class, Feature.OrderedField);
		noticeRequestVo.setRequest(noticeRequestVo.getRequest().replace("rdbaobargin/faxVoc/speVoiceNotify", "rdbaobargin\\/faxVoc\\/speVoiceNotify"));// 解决格式化的时候去处掉的转义字符

		VoiceNoticeRequestData voiceNoticeRequestData = VoiceNoticeRequestUtil.getVoiceNoticeRequestData(noticeRequestVo.getRequest());

		// 查看本地文件是否存在
		String localPathPrefix = "/home/renke/download/";

		File localFile = new File(localPathPrefix + voiceNoticeRequestData.getFileIdentity());
		if (!localFile.exists() || localFile.length() == 0) {
			downloadRemoteFile(noticeRequestVo, localFile);
		}

		// 校验文件md5并且上传文件至阿里云
		checkAndUploadFileMd5(queue, message);

		// 删除远程文件
		deleteRemoteFile(noticeRequestVo);

		// TODO 参数校验

		Map<String, Object> signRequestMap = new HashMap<String, Object>();
		Map<String, Object> sourceMap = new HashMap<String, Object>();

		sourceMap.put("source", noticeRequest.substring(0, noticeRequest.lastIndexOf(",")) + "}");
		signRequestMap.put("request", sourceMap);
		String signRequestStr = JSONObject.toJSONString(signRequestMap);

		_LOGGER.info("[公证录音通知请求签名数据:{}", signRequestStr);

		String responseStr = null;
		try {
			responseStr = HttpUtility.httpPost(PropertiesConfUtil.PROPERTIES_CONF.getNoticeConsumerConf().getSignatureService(), signRequestStr);
		} catch (Exception ex) {
			_LOGGER.error("[调用签名服务失败:" + Thread.currentThread().getName() + "]", ex);
			return;
		}

		JSONObject responseJsonObject = JSONObject.parseObject(responseStr);
		String respCode = responseJsonObject.getString("respCode");
		String respDesc = responseJsonObject.getString("respDesc");
		JSONObject data = responseJsonObject.getJSONObject("data");
		if ("0000".equals(respCode)) {
			String sign = data.getString("sign");
			String signTime = data.getString("signTime");
			String signSerialNo = data.getString("signSerialNo");
			String signRsaNo = data.getString("signRsaNo");

			NoticeRequestSignatureVo noticeRequestSignatureVo = new NoticeRequestSignatureVo();
			noticeRequestSignatureVo.setSign(sign);
			noticeRequestSignatureVo.setSignTime(signTime);
			noticeRequestSignatureVo.setNoticeRequest(noticeRequestVo);
			noticeRequestSignatureVo.setSignSerialNo(signSerialNo);
			noticeRequestSignatureVo.setSignRsaNo(signRsaNo);
			noticeRequestSignatureVo.setPnoes(PropertiesConfUtil.PROPERTIES_CONF.getNoticeConsumerConf().getPnoes());

			addNoticeSignMns(noticeRequestSignatureVo);

			queue.deleteMessage(message.getReceiptHandle());// 删除已消费消息
		}
	}

	private static void deleteRemoteFile(NoticeRequestVo noticeRequest) {
		VoiceNoticeRequestData voiceNoticeRequestData = VoiceNoticeRequestUtil.getVoiceNoticeRequestData(noticeRequest.getRequest());
		String remotePath = PropertiesConfUtil.PROPERTIES_CONF.getNoticeRemoteSftpConf().getDefaultPath();
		if (VoiceNoticeRequestUtil.isZhihengType(noticeRequest.getRequest())) {// 处理智恒类的文件路径
			remotePath += "/" + voiceNoticeRequestData.getFileIdentity().replace(VoiceNoticeRequestUtil.getZhihengTypeFileIdentityPrefix(voiceNoticeRequestData), "")
					.replace(FileUtil.getFileName(voiceNoticeRequestData.getFileIdentity()), "");
		}

		try {
			SFTPUtil.deleteFileSftp(PropertiesConfUtil.PROPERTIES_CONF.getNoticeRemoteSftpConf().getIp(), PropertiesConfUtil.PROPERTIES_CONF.getNoticeRemoteSftpConf().getPost(),
					PropertiesConfUtil.PROPERTIES_CONF.getNoticeRemoteSftpConf().getUsername(), PropertiesConfUtil.PROPERTIES_CONF.getNoticeRemoteSftpConf().getPassword(), remotePath,
					FileUtil.getFileName(voiceNoticeRequestData.getFileIdentity()));
		} catch (JSchException | SftpException | IOException e) {// 异常可忽略
			_LOGGER.warn("[删除远程文件出错:{}--{}]", remotePath + "/" + FileUtil.getFileName(voiceNoticeRequestData.getFileIdentity()), e.getMessage());
		}

	}

	/**
	 * 下载远程文件
	 * 
	 * @param noticeRequest
	 * @param localFile
	 */
	private static void downloadRemoteFile(NoticeRequestVo noticeRequest, File localFile) {
		VoiceNoticeRequestData voiceNoticeRequestData = VoiceNoticeRequestUtil.getVoiceNoticeRequestData(noticeRequest.getRequest());

		String remotePath = PropertiesConfUtil.PROPERTIES_CONF.getNoticeRemoteSftpConf().getDefaultPath();
		if (VoiceNoticeRequestUtil.isZhihengType(noticeRequest.getRequest())) {// 处理智恒类的文件路径
			remotePath += "/" + voiceNoticeRequestData.getFileIdentity().replace(VoiceNoticeRequestUtil.getZhihengTypeFileIdentityPrefix(voiceNoticeRequestData), "")
					.replace(FileUtil.getFileName(voiceNoticeRequestData.getFileIdentity()), "");
		}
		try {
			SFTPUtil.downSftp(PropertiesConfUtil.PROPERTIES_CONF.getNoticeRemoteSftpConf().getIp(), PropertiesConfUtil.PROPERTIES_CONF.getNoticeRemoteSftpConf().getPost(),
					PropertiesConfUtil.PROPERTIES_CONF.getNoticeRemoteSftpConf().getUsername(), PropertiesConfUtil.PROPERTIES_CONF.getNoticeRemoteSftpConf().getPassword(), remotePath,
					FileUtil.getFileName(voiceNoticeRequestData.getFileIdentity()), localFile.getPath());
		} catch (JSchException | SftpException | IOException e) {
			_LOGGER.error("[下载远程文件出错:{}--{}]", remotePath + "/" + FileUtil.getFileName(voiceNoticeRequestData.getFileIdentity()), localFile.getPath(), e);
			throw new RdbaoException("[下载远程文件出错]");
		}
	}

	/**
	 * 校验md5
	 * 
	 * @param queue
	 * @param message
	 * @throws IOException
	 */
	private static void checkAndUploadFileMd5(CloudQueue queue, Message message) throws IOException {
		String noticeRequest = message.getMessageBodyAsRawString();
		NoticeRequestVo noticeRequestVo = JSONObject.parseObject(noticeRequest, NoticeRequestVo.class, Feature.OrderedField);

		VoiceNoticeRequestData voiceNoticeRequestData = VoiceNoticeRequestUtil.getVoiceNoticeRequestData(noticeRequestVo.getRequest());

		String localPathPrefix = "/home/renke/download/";
		File file = new File(localPathPrefix + voiceNoticeRequestData.getFileIdentity());

		String localMd5 = DigestUtils.md5Hex(FileUtils.readFileToByteArray(file));
		if (!voiceNoticeRequestData.getMd5().equalsIgnoreCase(localMd5)) {
			queue.deleteMessage(message.getReceiptHandle());
			_LOGGER.warn("[公证录音通知--文件摘要不一致--本机路径{},{},{}]", file.getPath(), voiceNoticeRequestData.getMd5(), localMd5);
			throw new RdbaoException(ResponseEnum.FILE_SUMMARY_INCONSISTENCY);
		}

		try {
			AliOssUtil.uploadFile(PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeyId(), PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeySecret(), null,
					FileUtils.readFileToByteArray(file), AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES, voiceNoticeRequestData.getFileIdentity(), null);
		} catch (AliOperateException ex) {
			if (ex.getResponse() != null && ex.getResponse() == ResponseEnum.ALI_OSS_FILE_EXISTED) {
				String aliMd5 = AliOssUtil.getFileMd5ToHex(PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeyId(), PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeySecret(),
						null, AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES, voiceNoticeRequestData.getFileIdentity());
				if (!voiceNoticeRequestData.getMd5().equalsIgnoreCase(aliMd5)) {
					queue.deleteMessage(message.getReceiptHandle());
					_LOGGER.warn("[公证录音通知--文件摘要不一致--阿里路径{},{},{}]", AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES.getName() + "/" + voiceNoticeRequestData.getFileIdentity(),
							voiceNoticeRequestData.getMd5(), localMd5);
					throw new RdbaoException(ResponseEnum.FILE_SUMMARY_INCONSISTENCY);
				}
			} else {
				throw ex;
			}
		}

	}

	public static void main(String[] args) throws Exception {

		String source = "{\"request\":{\"appCode\":\"AppVideo\",\"beginTime\":\"2017-04-26 17:09:44\",\"utc\":\"2016-12-14T17:50:01Z\",\"endTime\":\"2017-04-26 17:09:46\",\"location\":\"120,23.26\",\"noticeIdentities\":[{\"md5\":\"47ce2001ee9f6ee81a252b0b0c794e52\",\"fileIdentity\":\"APPVIDEO_18649809213_20170426170948915960_47ce2001ee9f6ee81a252b0b0c794e52.3gp\"}],\"userAccount\":\"18649809213\",\"duration\":2},\"sign\":\"5306b0ee54e2247421333954d7b1bf4eb51efcd41b42caba32f61e33b69cd3e576496bc5ec681c54db103f12b837e75efc7b832b41ed406623bf27b27cefd3b19f15e35a1fa25d27e85b95482c46c896b1816b5a81e21f75887fa4ea3e0bea6b1a2bbf7c566e85a36146f4bb669e2bedd93def38cb96121f1a0de36631986f1d\"}";
		source = "{\"request\":{\"location\":\"120,23.26\",\"utc\":\"2016-12-14T09:50:01Z\",\"appCode\":\"AppPicture\",\"takeTime\":\"2017-04-26 18:25:40\",\"noticeIdentities\":[{\"md5\":\"2d2bd5ff161ebbe5c88be693cc7d966a\",\"fileIdentity\":\"AppPicture_18649809213_20170426182540078381_2d2bd5ff161ebbe5c88be693cc7d966a.jpg\"}],\"userAccount\":\"18649809213\"},\"sign\":\"8b3efd54b345006e00f743a670c47da1f320c12dbaa1c54a344c1af9edeaa911763eb5f09a5df08a693b0958c5855696390f105c1d7ccb4b69819b3a1447e7c597d57ee03365d67dfa50e905770d1a88395fa5461318ba76d8368bc385a3818529563b506d14942e2214d772f7efbfb6b364884ab6739f368593daea369a1bea\"}";
		source = "{\"request\":{\"request\":{\"common\":{\"action\":\"rdbaobargin/faxVoc/speVoiceNotify\",\"reqtime\":\"20170619160539\"},\"content\":{\"accessid\":\"cc80e160ba4ad22185f7dd6a44e99638\",\"msg\":\"13721110915|02167678141|20170619160455|20170619160522|13721110915|1ce24866a76ed1f927465efb0c8ad4e7\",\"provinceCode\":\"310000\",\"requestType\":\"5\"}}},\"sign\":\"fc38572d81b2da7b05137d01e9cfa659\",\"evidenceCode\":\"11111111\"}";
		source = "{\"request\":{\"request\":{\"common\":{\"action\":\"rdbaobargin\\/faxVoc\\/speVoiceNotify\",\"reqtime\":\"20170619160539\"},\"content\":{\"accessid\":\"cc80e160ba4ad22185f7dd6a44e99638\",\"msg\":\"13721110915|02167678141|20170619160455|20170619160522|13721110915|1ce24866a76ed1f927465efb0c8ad4e7\",\"provinceCode\":\"310000\",\"requestType\":\"5\"}}},\"sign\":\"fc38572d81b2da7b05137d01e9cfa659\",\"evidenceCode\":\"SHMH7781498020203355\"}";
		source = source.substring(0, source.lastIndexOf(",")) + "}";

		Map<String, Object> signRequestMap = new HashMap<String, Object>();
		Map<String, Object> sourceMap = new HashMap<String, Object>();
		sourceMap.put("source", source);
		signRequestMap.put("request", sourceMap);

		System.out.println("signRequestMap==" + JSONObject.toJSONString(signRequestMap));
		// System.out.println(signRequest);

		String f = "{\"request\":{\"request\":{\"common\":{\"action\":\"rdbaobargin\\/faxVoc\\/speVoiceNotify\",\"reqtime\":\"20170619160539\"},\"content\":{\"accessid\":\"cc80e160ba4ad22185f7dd6a44e99638\",\"msg\":\"13721110915|02167678141|20170619160455|20170619160522|13721110915|1ce24866a76ed1f927465efb0c8ad4e7\",\"provinceCode\":\"310000\",\"requestType\":\"5\"}}},\"sign\":\"fc38572d81b2da7b05137d01e9cfa659\"}";
		String t = "";
		String responseStr = null;
		try {
			responseStr = HttpUtility.httpPost("http://192.168.9.10:4000/api/SignApi/SignData/", JSONObject.toJSONString(signRequestMap));
		} catch (Exception ex) {
			_LOGGER.error("[调用签名服务失败:" + Thread.currentThread().getName() + "]", ex);
			return;
		}
		System.out.println("==================" + responseStr);

		JSONObject responseJsonObject = JSONObject.parseObject(responseStr);
		String respCode = responseJsonObject.getString("respCode");
		String respDesc = responseJsonObject.getString("respDesc");
		JSONObject data = responseJsonObject.getJSONObject("data");
		String sign = data.getString("sign");
		String signTime = data.getString("signTime");
		String signSerialNo = data.getString("signSerialNo");

		String pubStr = "a1 f1 e4 3c a3 c1 49 95 48 50 97 31 b8 ca 09 12 31 ae 08 60 f9 a5 06 6f 3e 42 48 5b d4 0d 20 3f 0f 48 d1 45 8e 15 c1 a5 07 c9 aa 57 39 44 56 8f 21 21 3e 92 4c 98 31 00 05 07 de 55 80 7c 5c 8d cb f5 1b d9 65 27 61 c7 a4 40 df be 2a a7 fa 7c 43 19 98 d8 f9 a7 f4 71 11 f2 99 10 26 5d 2c 05 4f e2 bd 17 07 9e ad bb b1 06 3b ff 89 19 78 89 9e 94 24 38 af dc b8 47 c0 7f fb e7 28 08 65 57";
		pubStr = pubStr.replaceAll(" ", "");
		pubStr = "FVVhchAhr/oNQZkm9kuJWuY4cuDLMwxUSaLtkvsa5JfpUoBpisx5ZzUgbNri9+i4QExMEZp6g8VU58Wp7yTGdDM95AKSYYPSBDRRujU+Kb6eFyxvFuZb+sAwpJq9V9hfFSrRIu3ZjL0+pJF/yLB7B/DW9UhZtIwhMYsp4oaIBBJn7CcLFVXA2VJo5woT8Yw4IakowolRJNo5VxgoWs4A8GXM5UN01MMNTpXKFQwfOj8G9HIyzhJrJApxHpugvxOBAUNAwJVVghZBjCPez3hYBhVZG2NWCfScITCJ6GGLoOTmFAVpWUVJqQndeTCaHKYSBsRF1SalulHivC0cjRrvJGdMrJ+RYjYcMPU096QhlK4=";
		pubStr = AesUtil.decrypt(pubStr);
		pubStr = pubStr.replaceAll(" ", "");

		String pubExpStr = "00 01 00 01";
		pubExpStr = pubExpStr.replaceAll(" ", "");

		RSAPublicKey rsaPublicKey = RsaUtil.generateRSAPublicKey(pubStr, pubExpStr);
		System.out.println(rsaPublicKey);
		System.out.println("source===" + source);
		System.out.println(DigestUtils.sha1Hex(source).toUpperCase());
		if (!RsaUtil.verifySignature(DigestUtils.sha1Hex(source).toUpperCase() + signTime, sign.replaceAll(" ", ""), rsaPublicKey)) {
			System.out.println("失败");
		} else {
			System.out.println("通过");
		}

	}

	/**
	 * 签名完毕的数据放入MNS
	 * 
	 * @param noticeRequestSignatureVo
	 * @throws AliOperateException
	 */
	private static void addNoticeSignMns(NoticeRequestSignatureVo noticeRequestSignatureVo) throws AliOperateException {
		Message message = new Message();
		message.setMessageBody(JSONObject.toJSONString(noticeRequestSignatureVo), MessageBodyType.RAW_STRING);

		AliMnsUtil.sendMessage(AliMnsQueueTemplateConstants.RDBAO_EVIDENCE_VOICE_NOTICE_SIGN, message);
	}

	private static class Schedule implements Runnable {
		private int index = 0;

		@Override
		public void run() {

			index = ++scheduleCount;
			_LOGGER.info("[公证录音通知消费者启动:" + Thread.currentThread().getName() + "   ,index=" + index + "]");
			while (true) {

				try {
					doSchedule();
				} catch (Exception ex) {
					_LOGGER.error("[公证录音通知消费者未知异常:" + Thread.currentThread().getName() + "]", ex);
				}
			}
		}

		private void doSchedule() {
			MNSClient mnsClient = AliMnsUtil.getMNSClient();

			try {
				String name = AliMnsQueueTemplateConstants.RDBAO_EVIDENCE_VOICE.replace(AliMnsQueueTemplateConstants.REPLACE_LOCALE,
						PropertiesConfUtil.PROPERTIES_CONF.getNoticeConsumerConf().getPnoes().toLowerCase());
				CloudQueue queue = AliMnsUtil.getQueue(mnsClient, name);
				Message message = queue.popMessage();
				if (message == null) {
					mnsClient.close();
					try {
						Thread.sleep(10000);// 没有消息时休眠10秒钟
					} catch (InterruptedException ex) {
						_LOGGER.error("[休眠打断异常:" + Thread.currentThread().getName() + "]", ex);
					}
					return;
				}
				_LOGGER.info("[公证录音通知待签名数据:{}", message.getMessageBodyAsRawString());
				dealMessage(queue, message);
			} catch (ServiceException ex) {
				if (ex.getMessage().trim().equals("Connection closed") || ex.getMessage().trim().startsWith("Connection reset")) {
					// 此异常可忽略
					// 参见https://help.aliyun.com/knowledge_detail/39447.html
					_LOGGER.debug("[公证录音通知MNS连接异常,可忽略：{}，{}，{}]", ex.getErrorCode(), ex.getRequestId(), ex.getMessage());
				}
			} catch (Exception ex) {
				_LOGGER.error("[公证录音通知消费者未知异常:" + Thread.currentThread().getName() + "]", ex);
			} finally {
				mnsClient.close();
			}
		}
	}

}
