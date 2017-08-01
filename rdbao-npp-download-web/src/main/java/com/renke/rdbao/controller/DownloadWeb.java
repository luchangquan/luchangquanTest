package com.renke.rdbao.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.renke.rdbao.beans.common.data.request.RequestData;
import com.renke.rdbao.beans.common.data.response.ResponseData;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMREvidenceFile;
import com.renke.rdbao.controller.base.BaseWeb;
import com.renke.rdbao.job.DownloadFileJob;
import com.renke.rdbao.util.HttpUtility;
import com.renke.rdbao.util.PropertiesLoadUtil;

/**
 * @author jgshun
 * @date 2017-5-8 下午12:02:27
 * @version 1.0
 */
@Controller
@RequestMapping("download")
public class DownloadWeb extends BaseWeb {
	private static final Logger _LOGGER = LoggerFactory.getLogger(DownloadWeb.class);

	@RequestMapping(value = "")
	public void downloadFile(HttpServletRequest request, HttpServletResponse response, String _t, String ec) throws UnsupportedEncodingException, IOException {
		OutputStream out = response.getOutputStream();
		_LOGGER.info("[收到下载请求，初始参数({},{})]", ec, _t);
		JSONObject requestJson = JSONObject.parseObject(new String(Base64.decodeBase64(_t)));
		_LOGGER.info("[收到下载请求，解码参数({},{})]", ec, requestJson.toJSONString());

		String _environment = PropertiesLoadUtil.getProperties().getProperty("_environment");
		PropertiesLoadUtil.load("properties/" + _environment + "/download_conf.properties");
		String url = PropertiesLoadUtil.getProperties().getProperty("download.checkUrl") + "?_t=" + _t + "&ec=" + ec;
		String responseStr = null;
		try {
			responseStr = HttpUtility.doGet(url, null);
		} catch (Exception e) {
			_LOGGER.error("[下载文件出错]", e);
			super.writeInfo2Resp(response, e.getMessage(), out);
			return;
		}
		ResponseData responseData = JSONObject.parseObject(responseStr, ResponseData.class);
		_LOGGER.info("[收到下载请求，验证结果:{}]", responseStr);
		if (!responseData.getRespCode().equals(ResponseEnum.SUCCESS.getRespCode())) {
			super.writeInfo2Resp(response, responseData.getRespDesc(), out);
			return;
		}

		String filePath = requestJson.getString("path");
		filePath = DOWNLOAD_LOCAL_PATH_PREFIX + filePath;
		_LOGGER.info("[收到下载请求，替换成本地挂载路径:{}]", filePath);

		File downFile = new File(filePath);
		response.addHeader("Content-Length", String.valueOf(downFile.length()));
		response.addHeader("Accept-Ranges", "bytes");
		response.addHeader("Content-Range", "bytes 0-" + (downFile.length() - 1) + "/" + downFile.length());
		_LOGGER.info("[开始下载文件:{}]", filePath);
		writeFileToResponse(response, new File(filePath), out);

	}

	@RequestMapping(value = "batch")
	public void downloadBatchFile(HttpServletRequest request, HttpServletResponse response, @RequestBody RequestData requestData) throws UnsupportedEncodingException, IOException {

		_LOGGER.info("----send email--param-->" + JSONObject.toJSONString(requestData));

		String email = requestData.getRequest().getString("email");
		String time = requestData.getRequest().getString("time");
		EnhancedMEvidence[] enhancedEvidences = requestData.getRequest().getObject("evidences", EnhancedMEvidence[].class);

		if (!requestData.getSign().equalsIgnoreCase(DigestUtils.md5Hex(time + "_" + enhancedEvidences.length + "_" + email + "===="))) {
			throw new RdbaoException("[非法请求]");
		}

		if (!this.checkFileExists(Arrays.asList(enhancedEvidences))) {
			for (EnhancedMEvidence _EnhancedMEvidence : enhancedEvidences) {
				DownloadFileJob.add(_EnhancedMEvidence);// 如果本地缺少文件就从远程下载文件到本地
			}
		}

		DownloadFileJob.addBatch(requestData);

	}

	private boolean checkFileExists(List<EnhancedMEvidence> enhancedEvidences) {
		for (EnhancedMEvidence _EnhancedMEvidence : enhancedEvidences) {
			List<EnhancedMREvidenceFile> enhancedMREvidenceFiles = _EnhancedMEvidence.getEnhancedMREvidenceFiles();
			for (EnhancedMREvidenceFile _EnhancedMREvidenceFile : enhancedMREvidenceFiles) {
				File curFile = new File(DOWNLOAD_LOCAL_PATH_PREFIX + _EnhancedMREvidenceFile.getPath());
				if (!curFile.exists() || curFile.length() < _EnhancedMREvidenceFile.getSize()) {
					return false;
				}
			}
		}
		return true;
	}

	// @RequestMapping(value = "test")
	// public void downloadFile(String filePath, HttpServletResponse response)
	// throws UnsupportedEncodingException, IOException {
	// OutputStream out = response.getOutputStream();
	// _LOGGER.info("[收到下载请求，初始参数({},{})]", filePath);
	//
	// filePath = DOWNLOAD_LOCAL_PATH_PREFIX + filePath;
	// _LOGGER.info("[收到下载请求，替换成本地挂载路径:{}]", filePath);
	//
	// File downFile = new File(filePath);
	// response.addHeader("Content-Length", String.valueOf(downFile.length()));
	// response.addHeader("Accept-Ranges", "bytes");
	// response.addHeader("Content-Range", "bytes 0-" + (downFile.length() - 1)
	// + "/" + downFile.length());
	// _LOGGER.info("[开始下载文件:{}]", filePath);
	// writeFileToResponse(response, new File(filePath), out);
	//
	// }

}
