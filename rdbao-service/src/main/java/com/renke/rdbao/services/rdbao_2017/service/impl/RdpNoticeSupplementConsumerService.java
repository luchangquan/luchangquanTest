package com.renke.rdbao.services.rdbao_2017.service.impl;

import java.util.ArrayList;
import java.util.Date;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.StorageTypeEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.UploadStatusEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.MREvidenceFile;
import com.renke.rdbao.daos.rdbao_2017.dao.IMEvidenceDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IMREvidenceFileDao;
import com.renke.rdbao.services.cache.rdbao_2017.service.INoticeCacheService;
import com.renke.rdbao.services.rdbao_2017.service.IRdpNoticeSupplementConsumerService;
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
	private IMEvidenceDao evidenceDao;
	@Autowired
	private IMREvidenceFileDao rEvidenceFileDao;
	private static final Logger _LOGGER = LoggerFactory.getLogger(RdpNoticeSupplementConsumerService.class);

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
			_LOGGER.warn("[通知任务缓存不存在:{}]", NoticeConstants.NOTICE_TASK_PREFIX + rdpNoticeSupplementVo.getTaskId());
			throw new RdbaoException(ResponseEnum.NOTICE_TASK_CACHE_NOT_EXIST);
		}
		String evidenceId = evidencesCache.replace(NoticeConstants.NOTICE_SIGN_KEY_PREFIX, "");
		MEvidence evidence = evidenceDao.getById(evidenceId);
		if (evidence == null) {
			throw new RdbaoException("[证据不存在:(" + evidenceId + "," + rdpNoticeSupplementVo.getTaskId() + ")]");
		}
		evidence.setName(rdpNoticeSupplementVo.getRemarkSupplement().getEvidenceName());
		evidence.setDescription(rdpNoticeSupplementVo.getRemarkSupplement().getEvidenceRemark());
		evidence.setUpdateTime(new Date());
		evidenceDao.updateByPrimaryKey(evidence);
	}

	private void updateEvidencesSupplement4File(RdpNoticeSupplementVo rdpNoticeSupplementVo, UserContext userContext) throws AliOperateException, RdbaoException {
		String evidencesCache = (String) noticeCacheService.get(NoticeConstants.NOTICE_TASK_PREFIX + rdpNoticeSupplementVo.getTaskId());
		if (!Detect.notEmpty(evidencesCache)) {
			_LOGGER.warn("[通知任务缓存不存在:{}]", NoticeConstants.NOTICE_TASK_PREFIX + rdpNoticeSupplementVo.getTaskId());
			throw new RdbaoException(ResponseEnum.NOTICE_TASK_CACHE_NOT_EXIST);
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
		MEvidence evidences = evidenceDao.getById(evidencesId);
		evidences.setSize(size);
		evidences.setUpdateTime(new Date());
		if (uploadAllStatus) {
			evidences.setUploadStatus(UploadStatusEnum4MEvidence.ALREADY_UPLOADED.getCode());
		} else {
			evidences.setUploadStatus(UploadStatusEnum4MEvidence.UPLOADING.getCode());
		}
		evidenceDao.updateByPrimaryKey(evidences);// 更新文件上传状态

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
		MREvidenceFile rEvidencesFile = new MREvidenceFile();
		rEvidencesFile.setEvidenceId(evidencesId);
		rEvidencesFile.setPath(fileIdentity);
		rEvidencesFile = rEvidenceFileDao.getOneByRecord(rEvidencesFile);
		if (rEvidencesFile == null) {
			throw new RdbaoException("[证据文件不存在:(" + evidencesId + ")]");
		}

		String fileMd5Hex = AliOssUtil.getFileMd5ToHex(PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeyId(), PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeySecret(),
				null, AliOssBucketEnum.getAliOssBucketEnumByName(rEvidencesFile.getBucket()), rEvidencesFile.getPath());

		if (!fileMd5Hex.equalsIgnoreCase(rEvidencesFile.getMd5())) {// 校验证据通知中文件的md5与上传文件的md5是否匹配
			_LOGGER.error("[证据文件MD5不匹配:(" + evidencesId + ",   " + fileMd5Hex + ",   " + rEvidencesFile.getMd5() + ")]");
			throw new RdbaoException(ResponseEnum.FILE_SUMMARY_INCONSISTENCY);
		}
		DateTime curDateTime = new DateTime();

		rEvidencesFile.setUploadStatus(UploadStatusEnum4MEvidence.ALREADY_UPLOADED.getCode());
		rEvidencesFile.setFileType(filetype);
		rEvidencesFile.setSort(sort);
		rEvidencesFile.setUpdateTime(curDateTime.toDate());
		rEvidencesFile.setSize(length);
		rEvidencesFile.setStorageType(StorageTypeEnum4MEvidence.ALI_OSS.getCode());
		rEvidencesFile.setBucket(AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES.getName());

		rEvidenceFileDao.updateByPrimaryKey(rEvidencesFile);// 更新证据详细数据
	}

}
