package com.renke.rdbao.notary.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedDNpp;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMREvidenceFile;
import com.renke.rdbao.services.rdbao_2017.service.IDNppService;
import com.renke.rdbao.services.rdbao_2017.service.IMEvidenceService;
import com.renke.rdbao.services.rdbao_2017.service.IMREvidenceFileService;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.web.base.BaseWeb;

@Controller
@RequestMapping("notary/e")
public class NotaryMEvidenceWeb extends BaseWeb {
	private static final Logger _LOGGER = LoggerFactory.getLogger(NotaryMEvidenceWeb.class);
	@Autowired
	private IMREvidenceFileService rEvidenceFileService;
	@Autowired
	private IMEvidenceService evidenceService;
	@Autowired
	private IDNppService nppService;

	/**
	 * 检查md5方法
	 * 
	 * @param id
	 *            文件id
	 * @param nppCode
	 *            公证处代码
	 */
	@RequestMapping("checkFileMd5")
	public String checkFileMd5(String id, String nppCode) {
		if (!Detect.notEmpty(nppCode)) {
			nppCode = super.getUserContext().getSourceNppCode();
		}
		// 通过文件id获取到文件的值
		EnhancedMREvidenceFile enhancedMREvidenceFile = (EnhancedMREvidenceFile) rEvidenceFileService.getEnhanced(id, null);
		EnhancedMEvidence enhancedMEvidence = (EnhancedMEvidence) evidenceService.getEnhanced(enhancedMREvidenceFile.getEnhancedMEvidence().getId(), null);
		enhancedMEvidence = evidenceService.appendEnhancedMREvidenceFiles(enhancedMEvidence, null);
		// 去除冗余数据
		this.excludeNotNeedData(enhancedMEvidence);

		EnhancedDNpp enhancedDNpp = nppService.getEnhancedsByCodes(Lists.newArrayList(nppCode), null).get(0);
		String param = Base64.encodeBase64URLSafeString(JSONObject.toJSONString(enhancedMEvidence).getBytes());

		// 定义url，包括md5的值和id
		String url = enhancedDNpp.getDownloadServerUrl() + "/api/evidence/file/check/md5" + "?_p=" + param + "&_c=" + id;
		_LOGGER.info("文件校验地址:{}", url);

		// 重定向到公证处url进行校验
		return super.redirect(url);

	}

	/**
	 * 清除冗余字段
	 */
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

	@RequestMapping("downloadFile")
	public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		id = Detect.notEmpty(id) ? id : "";
		String evidenceId = request.getParameter("evidenceId");
		String nppCode = request.getParameter("nppCode");
		if (!Detect.notEmpty(nppCode)) {
			nppCode = super.getUserContext().getSourceNppCode();
		}

		EnhancedMEvidence enhancedMEvidence = (EnhancedMEvidence) evidenceService.getEnhanced(evidenceId, null);
		enhancedMEvidence = evidenceService.appendEnhancedMREvidenceFiles(enhancedMEvidence, null);
		this.excludeNotNeedData(enhancedMEvidence);

		EnhancedDNpp enhancedDNpp = nppService.getEnhancedsByCodes(Lists.newArrayList(nppCode), null).get(0);
		String param = Base64.encodeBase64URLSafeString(JSONObject.toJSONString(enhancedMEvidence).getBytes());

		String url = enhancedDNpp.getDownloadServerUrl() + "/api/evidence/file/obtain" + "?_p=" + param + "&_c=" + id;
		/* _LOGGER.info("文件下载地址:{}", url); */

		return super.redirect(url);
	}

}
