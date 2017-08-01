package com.renke.rdbao.services.rdbao_v3.service.impl;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.renke.rdbao.beans.common.constants.NoticeConstants;
import com.renke.rdbao.beans.common.enums.AliOssBucketEnum;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.exception.AliOperateException;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.common.vo.notice.cache.NoticeSignKeyCacheVo;
import com.renke.rdbao.beans.common.vo.notice.rdp.RdpNoticeSupplementVo;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.StorageTypeEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.UploadStatusEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.pojo.Evidences;
import com.renke.rdbao.beans.rdbao_v3.pojo.REvidencesFile;
import com.renke.rdbao.daos.rdbao_v3.dao.IEvidencesDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IREvidencesFileDao;
import com.renke.rdbao.services.cache.rdbao_v3.service.INoticeCacheService;
import com.renke.rdbao.services.rdbao_v3.service.IRdpNoticeSupplementConsumerService;
import com.renke.rdbao.util.AliOssUtil;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.util.PropertiesConfUtil;

/**
 * @author jgshun
 * @date 2017-3-9 下午5:47:46
 * @version 1.0
 */
public class RdpNoticeSupplementConsumerService implements IRdpNoticeSupplementConsumerService {
	@Autowired
	private INoticeCacheService noticeCacheService;
	@Autowired
	private IEvidencesDao evidencesDao;
	@Autowired
	private IREvidencesFileDao rEvidencesFileDao;

	@Override
	public void updateEvidencesSupplement(String message, UserContext userContext) throws RdbaoException, AliOperateException {
		RdpNoticeSupplementVo rdpNoticeSupplementVo = JSONObject.parseObject(message, RdpNoticeSupplementVo.class);
		short supplementType = rdpNoticeSupplementVo.getSupplementType();
		if (supplementType == 2) {// 证据补充信息
			this.updateEvidencesSupplement4Remark(rdpNoticeSupplementVo, userContext);
		} else if (supplementType == 1) {// 文件补充信息
			this.updateEvidencesSupplement4File(rdpNoticeSupplementVo, userContext);
		}
	}

	private void updateEvidencesSupplement4Remark(RdpNoticeSupplementVo rdpNoticeSupplementVo, UserContext userContext) throws RdbaoException {
		String evidencesCache = (String) noticeCacheService.get(NoticeConstants.NOTICE_TASK_PREFIX + rdpNoticeSupplementVo.getTaskId());
		if (!Detect.notEmpty(evidencesCache)) {
			throw new RdbaoException(ResponseEnum.NOTICE_TASK_CACHE_NOT_EXIST);
		}
		String evidencesId = evidencesCache.replace(NoticeConstants.NOTICE_SIGN_KEY_PREFIX, "");
		Evidences evidences = evidencesDao.getById(evidencesId);
		if (evidences == null) {
			throw new RdbaoException("[证据不存在:(" + evidencesId + "," + rdpNoticeSupplementVo.getTaskId() + ")]");
		}
		evidences.setName(rdpNoticeSupplementVo.getRemarkSupplement().getEvidenceName());
		evidences.setDescription(rdpNoticeSupplementVo.getRemarkSupplement().getEvidenceRemark());
		evidences.setLastUpdateTime(new Date());
		evidencesDao.updateByPrimaryKey(evidences);
	}

	private void updateEvidencesSupplement4File(RdpNoticeSupplementVo rdpNoticeSupplementVo, UserContext userContext) throws AliOperateException, RdbaoException {
		String evidencesCache = (String) noticeCacheService.get(NoticeConstants.NOTICE_TASK_PREFIX + rdpNoticeSupplementVo.getTaskId());
		if (!Detect.notEmpty(evidencesCache)) {
			return;// 证据缓存不存在当做更新成功处理---消息会被删除
		}
		String evidencesId = evidencesCache.replace(NoticeConstants.NOTICE_SIGN_KEY_PREFIX, "");
		String fileIdentity = rdpNoticeSupplementVo.getFileSupplement().getFileIdentity();
		double sort = rdpNoticeSupplementVo.getFileSupplement().getSort();
		short fileType = rdpNoticeSupplementVo.getFileSupplement().getFileType();
		long length = rdpNoticeSupplementVo.getFileSupplement().getLength();
		this.updateREvidencesFileDetail(evidencesId, fileIdentity, fileType, sort, length);
		this.updateFileCacheUploadedStatus(NoticeConstants.NOTICE_IDENTITY_PREFIX + AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES.getName() + fileIdentity, length);
		this.updateEvidencesDetail(evidencesId);
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
	 * 更新缓存中文件状态为已上传
	 * 
	 * @param fileCacheKey
	 * @param length
	 *            文件大小
	 */
	private void updateFileCacheUploadedStatus(String fileCacheKey, long length) {
		NoticeSignKeyCacheVo noticeSignKeyCacheVo = (NoticeSignKeyCacheVo) noticeCacheService.get(fileCacheKey);
		noticeSignKeyCacheVo.setUpload(true);
		noticeSignKeyCacheVo.setSize(length);
		noticeCacheService.add(fileCacheKey, noticeSignKeyCacheVo);
		noticeCacheService.expire(fileCacheKey, 365 * 24 * 60 * 60);
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
		rEvidencesFile.setStorageType(StorageTypeEnum4Evidences.ALI_OSS.getCode());
		rEvidencesFile.setBucket(AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES.getName());

		rEvidencesFileDao.updateByPrimaryKey(rEvidencesFile);// 更新证据详细数据

	}

}
