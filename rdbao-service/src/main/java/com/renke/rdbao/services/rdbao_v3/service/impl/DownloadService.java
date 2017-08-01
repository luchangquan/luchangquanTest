package com.renke.rdbao.services.rdbao_v3.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.beust.jcommander.internal.Lists;
import com.renke.rdbao.beans.common.constants.DownloadConstants;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_v3.enums.forevidencefaxVoices.CallTypeEnum4EvidenceFaxVoices;
import com.renke.rdbao.beans.rdbao_v3.pojo.PNOes;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedEvidenceFaxVoices;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedEvidences;
import com.renke.rdbao.beans.rdbao_v3.vo.DownloadEvidencesVo;
import com.renke.rdbao.daos.rdbao_v3.dao.IPNOesDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IUser189Dao;
import com.renke.rdbao.services.cache.rdbao_v3.service.IDownloadCacheService;
import com.renke.rdbao.services.rdbao_v3.service.IDownloadService;
import com.renke.rdbao.services.rdbao_v3.service.IEvidenceFaxVoicesService;
import com.renke.rdbao.services.rdbao_v3.service.IEvidencesService;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.util.HttpUtility;
import com.renke.rdbao.util.MD5Util;

/**
 * @author jgshun
 * @date 2017-3-27 上午11:31:32
 * @version 1.0
 */
public class DownloadService implements IDownloadService {
	@Autowired
	private IDownloadCacheService downloadCacheService;
	@Autowired
	private IEvidencesService evidencesService;
	@Autowired
	private IPNOesDao pnoesDao;
	@Autowired
	private IEvidenceFaxVoicesService evidenceFaxVoicesService;
	@Autowired
	private IUser189Dao user189Dao;

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
		List<EnhancedEvidences> enhancedEvidences = (List<EnhancedEvidences>) evidencesService.getEnhanceds(Lists.newArrayList(evidencesIds), userContext);
		if (!Detect.notEmpty(enhancedEvidences)) {
			throw new RdbaoException("[证据不存在]");
		}
		List<String> containUserIds = userContext.getContainUserIds();
		for (EnhancedEvidences _EnhancedEvidences : enhancedEvidences) {
			if (!containUserIds.contains(_EnhancedEvidences.getEnhancedUser189().getId())) {// 非本用户或本公司下的用户
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
		List<EnhancedEvidences> enhancedEvidences = (List<EnhancedEvidences>) evidencesService.getEnhanceds(Lists.newArrayList(evidencesIdsInCahce), userContext);
		this.sendCmd2Pnoes4DownloadEvidences4User(enhancedEvidences, userContext);
	}

	private void sendCmd2Pnoes4DownloadEvidences4User(List<EnhancedEvidences> enhancedEvidences, UserContext userContext) {
		Map<String, List<EnhancedEvidences>> pnoesEnhancedEvidences = new HashMap<String, List<EnhancedEvidences>>();
		for (EnhancedEvidences _EnhancedEvidences : enhancedEvidences) {
			if (!Detect.notEmpty(pnoesEnhancedEvidences.get(_EnhancedEvidences.getEnhancedPNOes().getId()))) {
				pnoesEnhancedEvidences.put(_EnhancedEvidences.getEnhancedPNOes().getId(), new ArrayList<EnhancedEvidences>());
			}
			pnoesEnhancedEvidences.get(_EnhancedEvidences.getEnhancedPNOes().getId()).add(_EnhancedEvidences);
		}
		Set<Entry<String, List<EnhancedEvidences>>> pnoesEnhancedEvidencesEntries = pnoesEnhancedEvidences.entrySet();
		for (Entry<String, List<EnhancedEvidences>> _Entry : pnoesEnhancedEvidencesEntries) {
			try {
				this.sendCmd2Pnoes4DownloadEvidences4User(_Entry.getKey(), _Entry.getValue(), userContext);// 发送到对应公证处
			} catch (Exception ex) {
				// 发送失败，多个公证处应该继续发送
			}
		}

	}

	private void sendCmd2Pnoes4DownloadEvidences4User(String pnoesId, List<EnhancedEvidences> enhancedEvidences, UserContext userContext) {
		PNOes pnoes = pnoesDao.getById(pnoesId);
		String batchSuffix = "batch";
		String batchDownloadUrl = pnoes.getEvidenceDownloadUrl() + "/" + batchSuffix;// 批量下载地址

		List<String> ids = this.getEvidencesIds(enhancedEvidences);
		@SuppressWarnings("unchecked")
		List<EnhancedEvidenceFaxVoices> enhancedEvidenceFaxVoices = (List<EnhancedEvidenceFaxVoices>) evidenceFaxVoicesService.getEnhanceds(ids, userContext);
		evidenceFaxVoicesService.appendEnhancedEvidence(enhancedEvidenceFaxVoices, userContext);

		List<Map<String, String>> fileInfos = new ArrayList<Map<String, String>>();// 文件信息列表
		for (EnhancedEvidenceFaxVoices _EnhancedEvidenceFaxVoices : enhancedEvidenceFaxVoices) {
			String mainPhone = null;// 开户号码
			String targetPhone = null;// 通话号码
			if (_EnhancedEvidenceFaxVoices.getCallType() == CallTypeEnum4EvidenceFaxVoices.CALLING) {// 主叫
				mainPhone = _EnhancedEvidenceFaxVoices.getCallingNumber();
				targetPhone = _EnhancedEvidenceFaxVoices.getCalledNumber();
			} else {// 被叫
				mainPhone = _EnhancedEvidenceFaxVoices.getCalledNumber();
				targetPhone = _EnhancedEvidenceFaxVoices.getCallingNumber();
			}

			DateTime callTime = new DateTime(_EnhancedEvidenceFaxVoices.getCallTime());
			String showname = callTime.toString("yyyyMMdd") + "/" + mainPhone + "/" + callTime.toString("yyyyMMddHHmm") + "_" + _EnhancedEvidenceFaxVoices.getDuration() + "_" + mainPhone + "_"
					+ _EnhancedEvidenceFaxVoices.getCallType().getDesc() + "_" + targetPhone
					+ _EnhancedEvidenceFaxVoices.getEnhancedEvidences().getFilename().substring(_EnhancedEvidenceFaxVoices.getEnhancedEvidences().getFilename().lastIndexOf("."));

			Map<String, String> fileInfo = new HashMap<String, String>();
			fileInfo.put("path", _EnhancedEvidenceFaxVoices.getEnhancedEvidences().getFilename());
			fileInfo.put("showname", showname);
			fileInfos.add(fileInfo);
		}

		String time = String.valueOf(new Date().getTime());
		Map<String, Object> requestParamMap = new HashMap<String, Object>();
		requestParamMap.put("time", time);// 时间
		requestParamMap.put("fileInfos", fileInfos);// 文件
		String email = user189Dao.getById(userContext.getUserId()).getEmail();
		requestParamMap.put("email", email);// 邮箱

		String sign = MD5Util.MD5(time + "_" + fileInfos.size() + "_" + email + "====");// 校验信息
		Map<String, String> headerParamMap = new HashMap<String, String>();
		headerParamMap.put("sign", sign);
		try {
			HttpUtility.httpPost(batchDownloadUrl, JSONObject.toJSONString(requestParamMap), headerParamMap);
		} catch (Exception e) {
			_LOGGER.error("[发送下载任务到公证处失败]", e);
			throw new RdbaoException("[发送下载任务到公证处失败:(" + e.getMessage() + ")]");
		}
	}

	private List<String> getEvidencesIds(List<EnhancedEvidences> enhancedEvidences) {
		List<String> ids = new ArrayList<String>();
		for (EnhancedEvidences _EnhancedEvidences : enhancedEvidences) {
			ids.add(_EnhancedEvidences.getId());
		}
		return ids;
	}

	public static void main(String[] args) {
		String batchSuffix = "batch";
		String batchDownloadUrl = "http://localhost:8080/EvidenceDownload/EvidenceDownloadServlet/batch";// 批量下载地址

		List<Map<String, String>> fileInfos = new ArrayList<Map<String, String>>();// 文件信息列表

		List<EnhancedEvidenceFaxVoices> enhancedEvidenceFaxVoices = new ArrayList<EnhancedEvidenceFaxVoices>();
		EnhancedEvidenceFaxVoices enhancedEvidenceFaxVoices2 = new EnhancedEvidenceFaxVoices();
		enhancedEvidenceFaxVoices2.setCallingNumber("13636621498");
		enhancedEvidenceFaxVoices2.setCalledNumber("13721110915");
		enhancedEvidenceFaxVoices2.setCallType(CallTypeEnum4EvidenceFaxVoices.CALLING);
		enhancedEvidenceFaxVoices2.setCallTime(new Date());
		enhancedEvidenceFaxVoices2.setDuration(101);
		EnhancedEvidences enhancedEvidences = new EnhancedEvidences();
		enhancedEvidences.setFilename("E:/人科数据/189项目/189上线步骤.txt");
		enhancedEvidenceFaxVoices2.setEnhancedEvidences(enhancedEvidences);
		enhancedEvidenceFaxVoices.add(enhancedEvidenceFaxVoices2);

		for (EnhancedEvidenceFaxVoices _EnhancedEvidenceFaxVoices : enhancedEvidenceFaxVoices) {
			String mainPhone = null;// 开户号码
			String targetPhone = null;// 通话号码
			if (_EnhancedEvidenceFaxVoices.getCallType() == CallTypeEnum4EvidenceFaxVoices.CALLING) {// 主叫
				mainPhone = _EnhancedEvidenceFaxVoices.getCallingNumber();
				targetPhone = _EnhancedEvidenceFaxVoices.getCalledNumber();
			} else {// 被叫
				mainPhone = _EnhancedEvidenceFaxVoices.getCalledNumber();
				targetPhone = _EnhancedEvidenceFaxVoices.getCallingNumber();
			}
			DateTime callTime = new DateTime(_EnhancedEvidenceFaxVoices.getCallTime());
			String showname = callTime.toString("yyyyMMdd") + "/" + mainPhone + "/" + callTime.toString("yyyyMMddHHmm") + "_" + _EnhancedEvidenceFaxVoices.getDuration() + "_" + mainPhone + "_"
					+ _EnhancedEvidenceFaxVoices.getCallType().getDesc() + "_" + targetPhone
					+ _EnhancedEvidenceFaxVoices.getEnhancedEvidences().getFilename().substring(_EnhancedEvidenceFaxVoices.getEnhancedEvidences().getFilename().lastIndexOf("."));

			Map<String, String> fileInfo = new HashMap<String, String>();
			fileInfo.put("path", _EnhancedEvidenceFaxVoices.getEnhancedEvidences().getFilename());
			fileInfo.put("showname", showname);
			fileInfos.add(fileInfo);
		}
		String email = "gsjiang@renosdata.com";
		String time = String.valueOf(new Date().getTime());
		Map<String, Object> requestParamMap = new HashMap<String, Object>();
		requestParamMap.put("time", time);// 时间
		requestParamMap.put("fileInfos", fileInfos);// 文件
		requestParamMap.put("email", email);// 邮箱

		String sign = MD5Util.MD5(time + "_" + fileInfos.size() + "_" + email + "====");// 校验信息
		Map<String, String> headerParamMap = new HashMap<String, String>();
		headerParamMap.put("sign", sign);
		try {
			HttpUtility.httpPost(batchDownloadUrl, JSONObject.toJSONString(requestParamMap), headerParamMap);
		} catch (Exception e) {
			_LOGGER.error("[发送下载任务到公证处失败]", e);
			throw new RdbaoException("[发送下载任务到公证处失败:(" + e.getMessage() + ")]");
		}
	}

}
