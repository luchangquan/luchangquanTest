package com.renke.rdbao.services.rdbao_2017.service.impl;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import shaded.org.apache.commons.codec.binary.Hex;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.mns.model.Message;
import com.aliyun.mns.model.Message.MessageBodyType;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.renke.rdbao.beans.common.constants.AliMnsQueueTemplateConstants;
import com.renke.rdbao.beans.common.constants.NoticeConstants;
import com.renke.rdbao.beans.common.enums.AliOssBucketEnum;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.exception.AliOperateException;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.common.vo.notice.alicallback.AppAudioUploadCallbackRequestData;
import com.renke.rdbao.beans.common.vo.notice.alicallback.AppPictureUploadCallbackRequestData;
import com.renke.rdbao.beans.common.vo.notice.alicallback.AppVideoUploadCallbackRequestData;
import com.renke.rdbao.beans.common.vo.notice.cache.NoticeSignKeyCacheVo;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.UploadStatusEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidenceAppPicture;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidenceAppVideo;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidenceAppVoice;
import com.renke.rdbao.beans.rdbao_2017.pojo.MREvidenceFile;
import com.renke.rdbao.daos.rdbao_2017.dao.IMEvidenceAppPictureDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IMEvidenceAppVideoDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IMEvidenceAppVoiceDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IMEvidenceDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IMREvidenceFileDao;
import com.renke.rdbao.services.cache.rdbao_2017.service.INoticeCacheService;
import com.renke.rdbao.services.rdbao_2017.service.IAppUploadCallbackService;
import com.renke.rdbao.util.AliMnsUtil;
import com.renke.rdbao.util.AliOssUtil;
import com.renke.rdbao.util.PropertiesConfUtil;

/**
 * @author jgshun
 * @date 2017-3-7 下午2:33:40
 * @version 1.0
 */
public class AppUploadCallbackService implements IAppUploadCallbackService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(AppUploadCallbackService.class);

	@Autowired
	private INoticeCacheService noticeCacheService;
	@Autowired
	private IMEvidenceDao evidenceDao;
	@Autowired
	private IMREvidenceFileDao rEvidenceFileDao;
	@Autowired
	private IMEvidenceAppVoiceDao evidenceAppVoiceDao;
	@Autowired
	private IMEvidenceAppVideoDao evidenceAppVideoDao;
	@Autowired
	private IMEvidenceAppPictureDao evidenceAppPictureDao;

	@Override
	public void updateEvidenceDetail(AppVideoUploadCallbackRequestData appVideoUploadCallbackRequestData, UserContext userContext) throws RdbaoException, AliOperateException {
		String fileCacheKey = NoticeConstants.NOTICE_IDENTITY_PREFIX + appVideoUploadCallbackRequestData.getBucketName() + "/" + appVideoUploadCallbackRequestData.getFileIdentity();
		NoticeSignKeyCacheVo noticeSignKeyCacheVo = (NoticeSignKeyCacheVo) noticeCacheService.get(fileCacheKey);
		if (noticeSignKeyCacheVo == null) {
			_LOGGER.warn("[证据缓存不存在:{}]", fileCacheKey);
			MREvidenceFile rEvidenceFile = new MREvidenceFile();
			rEvidenceFile.setBucket(appVideoUploadCallbackRequestData.getBucketName());
			rEvidenceFile.setPath(appVideoUploadCallbackRequestData.getFileIdentity());
			rEvidenceFile = rEvidenceFileDao.getOneByRecord(rEvidenceFile);
			if (rEvidenceFile == null) {
				throw new RdbaoException("[证据缓存不存在，且尚未入库:" + fileCacheKey + "]");
			} else {
				return;
			}
		}
		String evidenceCacheKey = noticeSignKeyCacheVo.getEvidencesCache();
		MEvidence evidence = evidenceDao.getById(evidenceCacheKey.replace(NoticeConstants.NOTICE_SIGN_KEY_PREFIX, ""));
		if (evidence == null) {
			_LOGGER.info(JSONObject.toJSONString(appVideoUploadCallbackRequestData));
			throw new RdbaoException("[证据不存在:(" + evidenceCacheKey.replace(NoticeConstants.NOTICE_SIGN_KEY_PREFIX, "") + ")]");
		}

		MEvidenceAppVideo evidenceAppVideo = new MEvidenceAppVideo();
		evidenceAppVideo.setEvidenceId(evidence.getId());
		evidenceAppVideo = evidenceAppVideoDao.getOneByRecord(evidenceAppVideo);
		evidenceAppVideo.setLocationDesc(appVideoUploadCallbackRequestData.getLocationDesc());
		evidenceAppVideoDao.updateByPrimaryKey(evidenceAppVideo);

		this.updateREvidencesFileDetail(evidence.getId(), appVideoUploadCallbackRequestData.getFileIdentity(), Short.valueOf(appVideoUploadCallbackRequestData.getFiletype()),
				Double.valueOf(appVideoUploadCallbackRequestData.getSort()), Long.valueOf(appVideoUploadCallbackRequestData.getLength()));// 更新证据文件详情

		this.updateFileCacheUploadedStatus(fileCacheKey, noticeSignKeyCacheVo, Long.valueOf(appVideoUploadCallbackRequestData.getLength()));

		this.updateEvidencesDetail(evidence.getId());

	}

	@Override
	public void updateEvidenceDetail(AppAudioUploadCallbackRequestData appAudioUploadCallbackRequestData, UserContext userContext) throws RdbaoException, AliOperateException {
		String fileCacheKey = NoticeConstants.NOTICE_IDENTITY_PREFIX + appAudioUploadCallbackRequestData.getBucketName() + "/" + appAudioUploadCallbackRequestData.getFileIdentity();
		NoticeSignKeyCacheVo noticeSignKeyCacheVo = (NoticeSignKeyCacheVo) noticeCacheService.get(fileCacheKey);
		if (noticeSignKeyCacheVo == null) {
			_LOGGER.warn("[证据缓存不存在:{}]", fileCacheKey);
			MREvidenceFile rEvidenceFile = new MREvidenceFile();// 证据文件信息已入库，说明内容已被更新，缓存被删除
																// 兼容重复通知的情况
			rEvidenceFile.setBucket(appAudioUploadCallbackRequestData.getBucketName());
			rEvidenceFile.setPath(appAudioUploadCallbackRequestData.getFileIdentity());
			rEvidenceFile = rEvidenceFileDao.getOneByRecord(rEvidenceFile);
			if (rEvidenceFile == null) {
				throw new RdbaoException("[证据缓存不存在，且尚未入库:" + fileCacheKey + "]");
			} else {
				return;
			}
		}

		String evidenceCacheKey = noticeSignKeyCacheVo.getEvidencesCache();
		MEvidence evidence = evidenceDao.getById(evidenceCacheKey.replace(NoticeConstants.NOTICE_SIGN_KEY_PREFIX, ""));
		if (evidence == null) {
			_LOGGER.info(JSONObject.toJSONString(appAudioUploadCallbackRequestData));
			throw new RdbaoException("[证据不存在:(" + evidenceCacheKey.replace(NoticeConstants.NOTICE_SIGN_KEY_PREFIX, "") + ")]");
		}
		MEvidenceAppVoice evidenceAppVoice = new MEvidenceAppVoice();
		evidenceAppVoice.setEvidenceId(evidence.getId());
		evidenceAppVoice = evidenceAppVoiceDao.getOneByRecord(evidenceAppVoice);
		evidenceAppVoice.setLocationDesc(appAudioUploadCallbackRequestData.getLocationDesc());
		evidenceAppVoiceDao.updateByPrimaryKey(evidenceAppVoice);

		this.updateREvidencesFileDetail(evidence.getId(), appAudioUploadCallbackRequestData.getFileIdentity(), Short.valueOf(appAudioUploadCallbackRequestData.getFiletype()),
				Double.valueOf(appAudioUploadCallbackRequestData.getSort()), Long.valueOf(appAudioUploadCallbackRequestData.getLength()));// 更新证据文件详情

		this.updateFileCacheUploadedStatus(fileCacheKey, noticeSignKeyCacheVo, Long.valueOf(appAudioUploadCallbackRequestData.getLength()));

		this.updateEvidencesDetail(evidence.getId());
	}

	@Override
	public void updateEvidenceDetail(AppPictureUploadCallbackRequestData appPictureUploadCallbackRequestData, UserContext userContext) throws RdbaoException, AliOperateException {
		String fileCacheKey = NoticeConstants.NOTICE_IDENTITY_PREFIX + appPictureUploadCallbackRequestData.getBucketName() + "/" + appPictureUploadCallbackRequestData.getFileIdentity();
		NoticeSignKeyCacheVo noticeSignKeyCacheVo = (NoticeSignKeyCacheVo) noticeCacheService.get(fileCacheKey);
		if (noticeSignKeyCacheVo == null) {
			_LOGGER.warn("[证据缓存不存在:{}]", fileCacheKey);
			MREvidenceFile rEvidenceFile = new MREvidenceFile();// 证据文件信息已入库，说明内容已被更新，缓存被删除
																// 兼容重复通知的情况
			rEvidenceFile.setBucket(appPictureUploadCallbackRequestData.getBucketName());
			rEvidenceFile.setPath(appPictureUploadCallbackRequestData.getFileIdentity());
			rEvidenceFile = rEvidenceFileDao.getOneByRecord(rEvidenceFile);
			if (rEvidenceFile == null) {
				throw new RdbaoException("[证据缓存不存在，且尚未入库:" + fileCacheKey + "]");
			} else {
				return;
			}
		}

		String evidenceCacheKey = noticeSignKeyCacheVo.getEvidencesCache();
		MEvidence evidence = evidenceDao.getById(evidenceCacheKey.replace(NoticeConstants.NOTICE_SIGN_KEY_PREFIX, ""));
		if (evidence == null) {
			_LOGGER.info(JSONObject.toJSONString(appPictureUploadCallbackRequestData));
			throw new RdbaoException("[证据不存在:(" + evidenceCacheKey.replace(NoticeConstants.NOTICE_SIGN_KEY_PREFIX, "") + ")]");
		}

		MEvidenceAppPicture evidenceAppPicture = new MEvidenceAppPicture();
		evidenceAppPicture.setEvidenceId(evidence.getId());
		evidenceAppPicture = evidenceAppPictureDao.getOneByRecord(evidenceAppPicture);
		evidenceAppPicture.setLocationDesc(appPictureUploadCallbackRequestData.getLocationDesc());
		evidenceAppPictureDao.updateByPrimaryKey(evidenceAppPicture);

		this.updateREvidencesFileDetail(evidence.getId(), appPictureUploadCallbackRequestData.getFileIdentity(), Short.valueOf(appPictureUploadCallbackRequestData.getFiletype()),
				Double.valueOf(appPictureUploadCallbackRequestData.getSort()), Long.valueOf(appPictureUploadCallbackRequestData.getLength()));// 更新证据文件详情

		this.updateFileCacheUploadedStatus(fileCacheKey, noticeSignKeyCacheVo, Long.valueOf(appPictureUploadCallbackRequestData.getLength()));

		this.updateEvidencesDetail(evidence.getId());
	}

	/**
	 * 更新缓存中文件状态为已上传
	 * 
	 * @param fileCacheKey
	 * @param noticeSignKeyCacheVo
	 * @param length
	 *            文件大小
	 */
	private void updateFileCacheUploadedStatus(String fileCacheKey, NoticeSignKeyCacheVo noticeSignKeyCacheVo, long length) {
		noticeSignKeyCacheVo.setUpload(true);
		noticeSignKeyCacheVo.setSize(length);
		noticeCacheService.add(fileCacheKey, noticeSignKeyCacheVo);
		noticeCacheService.expire(fileCacheKey, 365 * 24 * 60 * 60);
	}

	/**
	 * 更新证据状态
	 * 
	 * @param evidencesId
	 * @param noticeSignKeyCacheVo
	 */
	private void updateEvidencesDetail(String evidencesId) {
		ArrayList<String> fileKeyCaches = (ArrayList<String>) noticeCacheService.get(NoticeConstants.NOTICE_SIGN_KEY_PREFIX + evidencesId);
		boolean uploadAllStatus = true;// 全部证据文件上传状态
		long size = 0;// 全部证据文件大小
		for (String _FileKeyCache : fileKeyCaches) {
			NoticeSignKeyCacheVo _NoticeSignKeyCacheVo = (NoticeSignKeyCacheVo) noticeCacheService.get(_FileKeyCache);
			if (_NoticeSignKeyCacheVo != null) {
				if (!_NoticeSignKeyCacheVo.isUpload()) {
					uploadAllStatus = false;// 有一个未上传就是上传中的状态
				}
				size += _NoticeSignKeyCacheVo.getSize();
			}
		}
		MEvidence evidence = evidenceDao.getById(evidencesId);
		evidence.setSize(size);
		evidence.setUpdateTime(new Date());
		if (uploadAllStatus) {
			evidence.setUploadStatus(UploadStatusEnum4MEvidence.ALREADY_UPLOADED.getCode());
		} else {
			evidence.setUploadStatus(UploadStatusEnum4MEvidence.UPLOADING.getCode());
		}
		evidenceDao.updateByPrimaryKey(evidence);// 更新文件上传状态

		if (uploadAllStatus) {// 全部上传成功后删除缓存
			noticeCacheService.expire(NoticeConstants.NOTICE_SIGN_KEY_PREFIX + evidencesId, 0);
			for (String _FileKeyCache : fileKeyCaches) {
				noticeCacheService.expire(_FileKeyCache, 0);
			}
		}
	}

	public static void main(String[] args) {
		// 0a0ad39b442e65fe8ed5872d3f826c70
		// 70a8c1fb1f6f9a97923e2955c11a166a
		OSSClient client = AliOssUtil.createOSSClient(PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeyId(), PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeySecret(),
				null);
		ObjectMetadata metadata = client.getObjectMetadata("rdbao-evidence-resources-test",
				"appvoice/18649809213/20161214T175001Z/APPVOICE_18649809213_20170502141658821623_0a0ad39b442e65fe8ed5872d3f826c70.amr");
		client.shutdown();// 关闭资源
		String fileMd5Hex = null;
		String aliFileMd5 = metadata.getContentMD5();
		fileMd5Hex = Hex.encodeHexString(Base64.decodeBase64(aliFileMd5));
		System.out.println(fileMd5Hex);
		System.out.println(metadata.getContentLength());
	}

	/**
	 * 更新证据详情
	 * 
	 * @param evidenceId
	 * @param fileIdentity
	 * @param filetype
	 * @param sort
	 * @param length
	 * @throws AliOperateException
	 * @throws RdbaoException
	 */
	private void updateREvidencesFileDetail(String evidenceId, String fileIdentity, short filetype, double sort, long length) throws AliOperateException, RdbaoException {
		MREvidenceFile rEvidenceFile = new MREvidenceFile();
		rEvidenceFile.setEvidenceId(evidenceId);
		rEvidenceFile.setPath(fileIdentity);
		rEvidenceFile = rEvidenceFileDao.getOneByRecord(rEvidenceFile);
		if (rEvidenceFile == null) {
			throw new RdbaoException("[证据文件不存在:(" + evidenceId + "," + fileIdentity + ")]");
		}
		String fileMd5Hex = AliOssUtil.getFileMd5ToHex(PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeyId(), PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeySecret(),
				null, AliOssBucketEnum.getAliOssBucketEnumByName(rEvidenceFile.getBucket()), rEvidenceFile.getPath());

		if (!fileMd5Hex.equalsIgnoreCase(rEvidenceFile.getMd5())) {// 校验证据通知中文件的md5与上传文件的md5是否匹配
			_LOGGER.error("[证据文件MD5不匹配:(" + evidenceId + ",   " + fileMd5Hex + ",   " + rEvidenceFile.getMd5() + ")]");
			throw new RdbaoException(ResponseEnum.FILE_SUMMARY_INCONSISTENCY);
		}
		DateTime curDateTime = new DateTime();

		rEvidenceFile.setUploadStatus(UploadStatusEnum4MEvidence.ALREADY_UPLOADED.getCode());
		rEvidenceFile.setFileType(filetype);
		rEvidenceFile.setSort(sort);
		rEvidenceFile.setUpdateTime(curDateTime.toDate());
		rEvidenceFile.setSize(length);

		rEvidenceFileDao.updateByPrimaryKey(rEvidenceFile);// 更新证据详细数据

	}

	@Override
	public void sendCallbackInfoToMns(AppVideoUploadCallbackRequestData appVideoUploadCallbackRequestData, UserContext userContext) {
		Message message = new Message();
		message.setMessageBody(JSONObject.toJSONString(appVideoUploadCallbackRequestData), MessageBodyType.RAW_STRING);
		AliMnsUtil.sendMessage(AliMnsQueueTemplateConstants.RDBAO_EVIDENCE_APP_VIDEO_NOTICE_CALLBACK_INFO, message);
	}

	@Override
	public void sendCallbackInfoToMns(AppAudioUploadCallbackRequestData appAudioUploadCallbackRequestData, Object userContext) {
		Message message = new Message();
		message.setMessageBody(JSONObject.toJSONString(appAudioUploadCallbackRequestData), MessageBodyType.RAW_STRING);
		AliMnsUtil.sendMessage(AliMnsQueueTemplateConstants.RDBAO_EVIDENCE_APP_AUDIO_NOTICE_CALLBACK_INFO, message);
	}

	@Override
	public void sendCallbackInfoToMns(AppPictureUploadCallbackRequestData appPictureUploadCallbackRequestData, Object userContext) {
		Message message = new Message();
		message.setMessageBody(JSONObject.toJSONString(appPictureUploadCallbackRequestData), MessageBodyType.RAW_STRING);
		AliMnsUtil.sendMessage(AliMnsQueueTemplateConstants.RDBAO_EVIDENCE_APP_PICTURE_NOTICE_CALLBACK_INFO, message);
	}

}
