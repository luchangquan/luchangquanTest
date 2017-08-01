package com.renke.rdbao.services.rdbao_v3.service.impl;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.mns.model.Message;
import com.aliyun.mns.model.Message.MessageBodyType;
import com.renke.rdbao.beans.common.constants.AliMnsQueueTemplateConstants;
import com.renke.rdbao.beans.common.constants.NoticeConstants;
import com.renke.rdbao.beans.common.enums.AliOssBucketEnum;
import com.renke.rdbao.beans.common.exception.AliOperateException;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.common.vo.notice.alicallback.AppVideoUploadCallbackRequestData;
import com.renke.rdbao.beans.common.vo.notice.cache.NoticeSignKeyCacheVo;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.UploadStatusEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.pojo.Evidences;
import com.renke.rdbao.beans.rdbao_v3.pojo.REvidencesFile;
import com.renke.rdbao.daos.rdbao_v3.dao.IEvidencesDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IREvidencesFileDao;
import com.renke.rdbao.services.cache.rdbao_v3.service.INoticeCacheService;
import com.renke.rdbao.services.rdbao_v3.service.IAppUploadCallbackService;
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
	private IEvidencesDao evidencesDao;
	@Autowired
	private IREvidencesFileDao rEvidencesFileDao;

	@Override
	public void updateEvidenceDetail(AppVideoUploadCallbackRequestData appVideoUploadCallbackRequestData, UserContext userContext) throws RdbaoException, AliOperateException {
		String fileCacheKey = NoticeConstants.NOTICE_IDENTITY_PREFIX + appVideoUploadCallbackRequestData.getBucketName() + "/" + appVideoUploadCallbackRequestData.getFileIdentity();
		NoticeSignKeyCacheVo noticeSignKeyCacheVo = (NoticeSignKeyCacheVo) noticeCacheService.get(fileCacheKey);
		String evidenceCacheKey = noticeSignKeyCacheVo.getEvidencesCache();
		Evidences evidences = evidencesDao.getById(evidenceCacheKey.replace(NoticeConstants.NOTICE_SIGN_KEY_PREFIX, ""));
		if (evidences == null) {
			_LOGGER.info(JSONObject.toJSONString(appVideoUploadCallbackRequestData));
			throw new RdbaoException("[证据不存在:(" + evidenceCacheKey.replace(NoticeConstants.NOTICE_SIGN_KEY_PREFIX, "") + ")]");
		}
		this.updateREvidencesFileDetail(evidences.getId(), appVideoUploadCallbackRequestData.getFileIdentity(), Short.valueOf(appVideoUploadCallbackRequestData.getFiletype()),
				Double.valueOf(appVideoUploadCallbackRequestData.getSort()), Long.valueOf(appVideoUploadCallbackRequestData.getLength()));// 更新证据文件详情

		this.updateFileCacheUploadedStatus(fileCacheKey, noticeSignKeyCacheVo, Long.valueOf(appVideoUploadCallbackRequestData.getLength()));

		this.updateEvidencesDetail(evidences.getId());

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
		Evidences evidences = evidencesDao.getById(evidencesId);
		evidences.setSize(size);
		evidences.setLastUpdateTime(new Date());
		if (uploadAllStatus) {
			evidences.setUploadStatus(UploadStatusEnum4Evidences.ALREADY_UPLOADED.getCode());
		} else {
			evidences.setUploadStatus(UploadStatusEnum4Evidences.UPLOADING.getCode());
		}
		evidencesDao.updateByPrimaryKey(evidences);// 更新文件上传状态

		if (uploadAllStatus) {// 全部上传成功后删除缓存
			noticeCacheService.expire(NoticeConstants.NOTICE_SIGN_KEY_PREFIX + evidencesId, 0);
			for (String _FileKeyCache : fileKeyCaches) {
				noticeCacheService.expire(_FileKeyCache, 0);
			}
		}
	}

	/**
	 * 更新证据详情
	 * 
	 * @param evidencesId
	 * @param fileIdentity
	 * @param filetype
	 * @param sort
	 * @param length
	 * @throws AliOperateException
	 * @throws RdbaoException
	 */
	private void updateREvidencesFileDetail(String evidencesId, String fileIdentity, short filetype, double sort, long length) throws AliOperateException, RdbaoException {
		REvidencesFile rEvidencesFile = new REvidencesFile();
		rEvidencesFile.setEvidencesId(evidencesId);
		rEvidencesFile.setPath(fileIdentity);
		rEvidencesFile = rEvidencesFileDao.getOneByRecord(rEvidencesFile);
		if (rEvidencesFile == null) {
			throw new RdbaoException("[证据文件不存在:(" + evidencesId + ")]");
		}

		byte[] fileByte = AliOssUtil.getFile(PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeyId(), PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeySecret(), null,
				AliOssBucketEnum.getAliOssBucketEnumByName(rEvidencesFile.getBucket()), rEvidencesFile.getPath());
		String fileMd5 = DigestUtils.md5Hex(fileByte);
		if (!fileMd5.equals(rEvidencesFile.getMd5())) {// 校验证据通知中文件的md5与上传文件的md5是否匹配
			throw new RdbaoException("[证据文件MD5不匹配:(" + evidencesId + ",   " + fileMd5 + ",   " + rEvidencesFile.getMd5() + ")]");
		}
		DateTime curDateTime = new DateTime();

		rEvidencesFile.setUploadStatus(UploadStatusEnum4Evidences.ALREADY_UPLOADED.getCode());
		rEvidencesFile.setFileType(filetype);
		rEvidencesFile.setSort(sort);
		rEvidencesFile.setUpdateTime(curDateTime.toDate());
		rEvidencesFile.setLength(length);

		rEvidencesFileDao.updateByPrimaryKey(rEvidencesFile);// 更新证据详细数据

	}

	@Override
	public void sendCallbackInfoToMns(AppVideoUploadCallbackRequestData appVideoUploadCallbackRequestData, UserContext userContext) {
		Message message = new Message();
		message.setMessageBody(JSONObject.toJSONString(appVideoUploadCallbackRequestData), MessageBodyType.RAW_STRING);
		AliMnsUtil.sendMessage(AliMnsQueueTemplateConstants.RDBAO_EVIDENCE_APP_VIDEO_NOTICE_CALLBACK_INFO, message);
	}
}
