package com.renke.rdbao.api.controller;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.data.request.RequestData;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedDNpp;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMREvidenceFile;
import com.renke.rdbao.controller.base.BaseController;
import com.renke.rdbao.services.rdbao_2017.service.IDNppService;
import com.renke.rdbao.services.rdbao_2017.service.IMEvidenceService;
import com.renke.rdbao.services.rdbao_2017.service.IMREvidenceFileService;
import com.renke.rdbao.util.Detect;

@Controller
@RequestMapping("api/evidence")
public class ApiEvidenceController extends BaseController {
	private static final Logger _LOGGER = LoggerFactory.getLogger(ApiEvidenceController.class);

	@Autowired
	private IMREvidenceFileService rEvidenceFileService;
	@Autowired
	private IMEvidenceService evidenceService;
	@Autowired
	private IDNppService nppService;

	@RequestMapping("file/check/md5")
	public String checkFileMd5(@RequestBody RequestData requestData) {
		super.checkApiSign(requestData);
		String id = requestData.getRequest().getString("id");
		String nppCode = requestData.getRequest().getString("nppCode");

		EnhancedMREvidenceFile enhancedMREvidenceFile = (EnhancedMREvidenceFile) rEvidenceFileService.getEnhanced(id, null);
		EnhancedMEvidence enhancedMEvidence = (EnhancedMEvidence) evidenceService.getEnhanced(enhancedMREvidenceFile.getEnhancedMEvidence().getId(), null);
		enhancedMEvidence = evidenceService.appendEnhancedMREvidenceFiles(enhancedMEvidence, null);

		this.excludeNotNeedData(enhancedMEvidence);

		EnhancedDNpp enhancedDNpp = nppService.getEnhancedsByCodes(Lists.newArrayList(nppCode), null).get(0);
		String param = Base64.encodeBase64URLSafeString(JSONObject.toJSONString(enhancedMEvidence).getBytes());

		String url = enhancedDNpp.getDownloadServerUrl() + "/api/evidence/file/check/md5" + "?_p=" + param + "&_c=" + id;
		_LOGGER.info("文件校验地址:{}", url);
		return super.redirect(url);
	}

	private void excludeNotNeedData(EnhancedMEvidence enhancedMEvidence) {
		enhancedMEvidence.setName(null);
		enhancedMEvidence.setCode(null);
		enhancedMEvidence.setDescription(null);
		enhancedMEvidence.setCreateTime(null);
		enhancedMEvidence.setUpdateTime(null);
		enhancedMEvidence.setEnhancedDNpp(null);
		enhancedMEvidence.setEnhancedUser(null);
		enhancedMEvidence.setEnhancedDEvidenceSource(null);
		enhancedMEvidence.setEnhancedDSignatureRecord(null);
		enhancedMEvidence.setEnhancedECompany(null);
		enhancedMEvidence.setTenant(null);
		enhancedMEvidence.setEnhancedItem(null);
		enhancedMEvidence.setCoverUrl(null);

		for (EnhancedMREvidenceFile _EnhancedMREvidenceFile : enhancedMEvidence.getEnhancedMREvidenceFiles()) {
			_EnhancedMREvidenceFile.setEnhancedMEvidence(null);
			_EnhancedMREvidenceFile.setStorageType(null);
			_EnhancedMREvidenceFile.setEnhancedDNpp(null);
			_EnhancedMREvidenceFile.setUploadStatus(null);
			_EnhancedMREvidenceFile.setCreateTime(null);
			_EnhancedMREvidenceFile.setUpdateTime(null);
			_EnhancedMREvidenceFile.setExtra(null);
			_EnhancedMREvidenceFile.setFileUrl(null);
		}

	}

	@RequestMapping("file/obtain")
	public String obtainFile(String _d) {
		_LOGGER.info("[收到下载请求,原始数据{}]", _d);
		RequestData requestData = JSONObject.parseObject(Base64.decodeBase64(_d), RequestData.class);
		_LOGGER.info("[收到下载请求,转换数据{}]", JSONObject.toJSONString(requestData));
		super.checkApiSign(requestData);
		String id = requestData.getRequest().getString("id");
		id = Detect.notEmpty(id) ? id : "";
		String evidenceId = requestData.getRequest().getString("evidenceId");
		String nppCode = requestData.getRequest().getString("nppCode");

		EnhancedMEvidence enhancedMEvidence = (EnhancedMEvidence) evidenceService.getEnhanced(evidenceId, null);
		enhancedMEvidence = evidenceService.appendEnhancedMREvidenceFiles(enhancedMEvidence, null);
		this.excludeNotNeedData(enhancedMEvidence);

		EnhancedDNpp enhancedDNpp = nppService.getEnhancedsByCodes(Lists.newArrayList(nppCode), null).get(0);
		String param = Base64.encodeBase64URLSafeString(JSONObject.toJSONString(enhancedMEvidence).getBytes());

		String url = enhancedDNpp.getDownloadServerUrl() + "/api/evidence/file/obtain" + "?_p=" + param + "&_c=" + id;
		_LOGGER.info("文件下载地址:{}", url);

		return super.redirect(url);
	}

}
