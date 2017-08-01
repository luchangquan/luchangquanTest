package com.renke.rdbao.services.rdbao_2017.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.beust.jcommander.internal.Lists;
import com.renke.rdbao.beans.common.constants.DownloadConstants;
import com.renke.rdbao.beans.common.data.request.RequestData;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.pojo.DNpp;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMEvidence;
import com.renke.rdbao.beans.rdbao_2017.vo.DownloadEvidencesVo;
import com.renke.rdbao.daos.rdbao_2017.dao.IDNppDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IEUser189Dao;
import com.renke.rdbao.services.cache.rdbao_2017.service.IDownloadCacheService;
import com.renke.rdbao.services.rdbao_2017.service.IDownloadService;
import com.renke.rdbao.services.rdbao_2017.service.IMEvidenceService;
import com.renke.rdbao.services.rdbao_2017.service.IMEvidenceTelecomVoiceService;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.util.HttpUtility;

/**
 * @author jgshun
 * @date 2017-3-27 上午11:31:32
 * @version 1.0
 */
public class DownloadService implements IDownloadService {
	@Autowired
	private IDownloadCacheService downloadCacheService;
	@Autowired
	private IMEvidenceService evidenceService;
	@Autowired
	private IDNppDao nppDao;
	@Autowired
	private IMEvidenceTelecomVoiceService evidenceFaxVoicesService;
	@Autowired
	private IEUser189Dao user189Dao;

	private static final Logger _LOGGER = LoggerFactory.getLogger(DownloadService.class);

	@Override
	public DownloadEvidencesVo saveDownloadEvidencesInfo4User(List<String> evidencesIds, UserContext userContext) {
		this.checkSave(evidencesIds, userContext);// 校验
		DownloadEvidencesVo downloadEvidencesVo = new DownloadEvidencesVo();
		@SuppressWarnings("unchecked")
		HashSet<String> evidencesIdsInCahce = (HashSet<String>) downloadCacheService.get(DownloadConstants.DOWNLOAD_EVIDENCES_PREFIX + userContext.getUserId());
		if (!Detect.notEmpty(evidencesIdsInCahce)) {
			evidencesIdsInCahce = new HashSet<String>(evidencesIds);
		} else {
			evidencesIdsInCahce.addAll(evidencesIds);
		}
		downloadCacheService.add(DownloadConstants.DOWNLOAD_EVIDENCES_PREFIX + userContext.getUserId(), evidencesIdsInCahce);
		downloadCacheService.expire(DownloadConstants.DOWNLOAD_EVIDENCES_PREFIX + userContext.getUserId(), DownloadConstants.DOWNLOAD_EVIDENCES_TIME_OUT_SECONDS_IN_CACHE);
		downloadEvidencesVo.setCount(evidencesIdsInCahce.size());
		downloadEvidencesVo.setEvidences(evidencesIdsInCahce);
		return downloadEvidencesVo;
	}

	@Override
	public DownloadEvidencesVo deleteDownloadEvidencesInfo4User(List<String> evidencesIds, UserContext userContext) throws RdbaoException {
		@SuppressWarnings("unchecked")
		HashSet<String> evidencesIdsInCahce = (HashSet<String>) downloadCacheService.get(DownloadConstants.DOWNLOAD_EVIDENCES_PREFIX + userContext.getUserId());
		if (!Detect.notEmpty(evidencesIdsInCahce)) {
			throw new RdbaoException("[空的证据列表,请刷新后重试]");
		}
		Iterator<String> evidencesIdsInCahceIterator = evidencesIdsInCahce.iterator();
		while (evidencesIdsInCahceIterator.hasNext()) {
			String _EvidencesIdInCahce = evidencesIdsInCahceIterator.next();
			for (String _EvidencesId : evidencesIds) {
				if (_EvidencesId.equals(_EvidencesIdInCahce)) {
					evidencesIdsInCahceIterator.remove();
				}
			}
		}
		if (Detect.notEmpty(evidencesIdsInCahce)) {
			downloadCacheService.add(DownloadConstants.DOWNLOAD_EVIDENCES_PREFIX + userContext.getUserId(), evidencesIdsInCahce);
			downloadCacheService.expire(DownloadConstants.DOWNLOAD_EVIDENCES_PREFIX + userContext.getUserId(), DownloadConstants.DOWNLOAD_EVIDENCES_TIME_OUT_SECONDS_IN_CACHE);
		} else {
			downloadCacheService.expire(DownloadConstants.DOWNLOAD_EVIDENCES_PREFIX + userContext.getUserId(), 0);
		}
		DownloadEvidencesVo downloadEvidencesVo = new DownloadEvidencesVo();
		downloadEvidencesVo.setCount(evidencesIdsInCahce.size());
		downloadEvidencesVo.setEvidences(evidencesIdsInCahce);
		return downloadEvidencesVo;
	}

	private void checkSave(List<String> evidencesIds, UserContext userContext) throws RdbaoException {
		@SuppressWarnings("unchecked")
		List<EnhancedMEvidence> enhancedEvidences = (List<EnhancedMEvidence>) evidenceService.getEnhanceds(Lists.newArrayList(evidencesIds), userContext);
		if (!Detect.notEmpty(enhancedEvidences)) {
			throw new RdbaoException("[证据不存在]");
		}
		List<String> containUserIds = userContext.getContainUserIds();
		for (EnhancedMEvidence _EnhancedMEvidence : enhancedEvidences) {
			if (!containUserIds.contains(_EnhancedMEvidence.getUserId())) {// 非本用户或本公司下的用户
				throw new RdbaoException(ResponseEnum.ILLEGAL_OPERATION);
			}
		}
	}

	@Override
	public DownloadEvidencesVo getDownloadEvidencesInfo4User(UserContext userContext) {
		DownloadEvidencesVo downloadEvidencesVo = new DownloadEvidencesVo();
		@SuppressWarnings("unchecked")
		HashSet<String> evidencesIdsInCahce = (HashSet<String>) downloadCacheService.get(DownloadConstants.DOWNLOAD_EVIDENCES_PREFIX + userContext.getUserId());
		if (Detect.notEmpty(evidencesIdsInCahce)) {
			downloadEvidencesVo.setCount(evidencesIdsInCahce.size());
			downloadEvidencesVo.setEvidences(evidencesIdsInCahce);
			downloadCacheService.expire(DownloadConstants.DOWNLOAD_EVIDENCES_PREFIX + userContext.getUserId(), DownloadConstants.DOWNLOAD_EVIDENCES_TIME_OUT_SECONDS_IN_CACHE);
		}
		return downloadEvidencesVo;
	}

	@Override
	public void sendCmd2Pnoes4DownloadEvidences4User(UserContext userContext) throws RdbaoException {
		@SuppressWarnings("unchecked")
		HashSet<String> evidencesIdsInCahce = (HashSet<String>) downloadCacheService.get(DownloadConstants.DOWNLOAD_EVIDENCES_PREFIX + userContext.getUserId());
		if (!Detect.notEmpty(evidencesIdsInCahce)) {
			throw new RdbaoException("[空的证据列表,请刷新后重试]");
		}
		downloadCacheService.expire(DownloadConstants.DOWNLOAD_EVIDENCES_PREFIX + userContext.getUserId(), 0);// 清空申请列表
		@SuppressWarnings("unchecked")
		List<EnhancedMEvidence> enhancedEvidences = (List<EnhancedMEvidence>) evidenceService.getEnhanceds(Lists.newArrayList(evidencesIdsInCahce), userContext);
		enhancedEvidences = evidenceService.appendEnhancedDNpp(enhancedEvidences, userContext);
		enhancedEvidences = evidenceService.appendEnhancedMREvidenceFiles(enhancedEvidences, userContext);

		this.sendCmd2Pnoes4DownloadEvidences4User(enhancedEvidences, userContext);
	}

	private void sendCmd2Pnoes4DownloadEvidences4User(List<EnhancedMEvidence> enhancedEvidences, UserContext userContext) {
		Map<String, List<EnhancedMEvidence>> pnoesEnhancedMEvidence = new HashMap<String, List<EnhancedMEvidence>>();
		for (EnhancedMEvidence _EnhancedMEvidence : enhancedEvidences) {
			if (!Detect.notEmpty(pnoesEnhancedMEvidence.get(_EnhancedMEvidence.getEnhancedDNpp().getCode()))) {
				pnoesEnhancedMEvidence.put(_EnhancedMEvidence.getEnhancedDNpp().getCode(), new ArrayList<EnhancedMEvidence>());
			}
			pnoesEnhancedMEvidence.get(_EnhancedMEvidence.getEnhancedDNpp().getCode()).add(_EnhancedMEvidence);
		}
		Set<Entry<String, List<EnhancedMEvidence>>> pnoesEnhancedMEvidenceEntries = pnoesEnhancedMEvidence.entrySet();
		for (Entry<String, List<EnhancedMEvidence>> _Entry : pnoesEnhancedMEvidenceEntries) {
			try {
				this.sendCmd2Pnoes4DownloadEvidences4User(_Entry.getKey(), _Entry.getValue(), userContext);// 发送到对应公证处
			} catch (Exception ex) {
				// 发送失败，多个公证处应该继续发送
			}
		}

	}

	private void sendCmd2Pnoes4DownloadEvidences4User(String nppCode, List<EnhancedMEvidence> enhancedEvidences, UserContext userContext) {
		DNpp pnoes = nppDao.getByCode(nppCode);
		String batchSuffix = "download/batch";
		String batchDownloadUrl = pnoes.getDownloadServerUrl() + "/" + batchSuffix;// 批量下载地址

		String time = String.valueOf(new Date().getTime());
		JSONObject requestParam = new JSONObject();
		requestParam.put("time", time);// 时间
		requestParam.put("evidences", enhancedEvidences);// 文件信息
		String email = user189Dao.getById(userContext.getUserId()).getEmail();
		requestParam.put("email", email);// 邮箱
		requestParam.put("tenantCode", userContext.getTenant().getCode());

		String sign = DigestUtils.md5Hex(time + "_" + enhancedEvidences.size() + "_" + email + "====");// 校验信息
		RequestData requestData = new RequestData();
		requestData.setRequest(requestParam);
		requestData.setSign(sign);

		try {
			HttpUtility.httpPost(batchDownloadUrl, JSONObject.toJSONString(requestData));
		} catch (Exception e) {
			_LOGGER.error("[发送下载任务到公证处失败]", e);
			throw new RdbaoException("[发送下载任务到公证处失败:(" + e.getMessage() + ")]");
		}
	}

	private List<String> getEvidencesIds(List<EnhancedMEvidence> enhancedEvidences) {
		List<String> ids = new ArrayList<String>();
		for (EnhancedMEvidence _EnhancedMEvidence : enhancedEvidences) {
			ids.add(_EnhancedMEvidence.getId());
		}
		return ids;
	}

	public static void main(String[] args) {
		String batchSuffix = "batch";
		String batchDownloadUrl = "http://localhost:8080/EvidenceDownload/EvidenceDownloadServlet/batch";// 批量下载地址
		// TODO
	}

}
