package com.renke.rdbao.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.data.response.ResponseData;
import com.renke.rdbao.beans.common.enums.FileTypeEnum;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMREvidenceFile;
import com.renke.rdbao.controller.base.BaseWeb;
import com.renke.rdbao.job.DownloadFileJob;
import com.renke.rdbao.service.IMEvidenceService;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.util.FileUtil;
import com.renke.rdbao.util.ZipCompressing;

@Controller
@RequestMapping("api/evidence")
public class ApiEvidenceController extends BaseWeb {
	private static final Logger _LOGGER = LoggerFactory.getLogger(ApiEvidenceController.class);

	@Autowired
	private IMEvidenceService evidenceService;

	@RequestMapping("file/check/md5")
	public @ResponseBody ResponseData checkFileMd5(String _p, String _c) {
		_LOGGER.info("[接收到文件校验请求,原始请求:{}--{}]", _p, _c);
		ResponseData responseData = new ResponseData();
		EnhancedMEvidence enhancedMEvidence = JSONObject.parseObject(Base64.decodeBase64(_p), EnhancedMEvidence.class);
		_LOGGER.info("[接收到文件校验请求,转换请求:{}--{}]", JSONObject.toJSONString(enhancedMEvidence), _c);
		String rEvidenceFileId = _c;
		try {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("pass", evidenceService.checkFileMd5(enhancedMEvidence, rEvidenceFileId));
			responseData.setData(resultMap);
		} catch (Exception exception) {
			super.dealResponseException(responseData, exception);
		}
		return responseData;
	}

	@RequestMapping("file/obtain")
	public void obtainFile(String _p, String _c, HttpServletResponse response) throws IOException {
		_LOGGER.info("[接收到文件下载请求,原始请求:{}--{}]", _p, _c);

		OutputStream out = response.getOutputStream();

		EnhancedMEvidence enhancedMEvidence = JSONObject.parseObject(Base64.decodeBase64(_p), EnhancedMEvidence.class);
		_LOGGER.info("[接收到文件下载请求,转换请求:{}--{}]", JSONObject.toJSONString(enhancedMEvidence), _c);

		String rEvidenceFileId = _c;

		if (!Detect.notEmpty(_c)) {// 文件id为空的话，默认下载图片压缩包
			this.obtainFilePicturesZip(enhancedMEvidence, response, out);
			return;
		}

		EnhancedMREvidenceFile cuEnhancedMREvidenceFile = null;
		for (EnhancedMREvidenceFile _EnhancedMREvidenceFile : enhancedMEvidence.getEnhancedMREvidenceFiles()) {
			if (_EnhancedMREvidenceFile.getId().equals(rEvidenceFileId)) {
				cuEnhancedMREvidenceFile = _EnhancedMREvidenceFile;
				break;
			}
		}

		String filePath = "/home/renke/download/" + cuEnhancedMREvidenceFile.getPath();
		_LOGGER.info("[收到下载请求，替换成本地挂载路径:{}]", filePath);

		File downFile = new File(filePath);
		if (!downFile.exists() || downFile.length() < cuEnhancedMREvidenceFile.getSize()) {
			DownloadFileJob.add(enhancedMEvidence);
			super.writeInfo2Resp(response, "[文件正在准备中，请稍后再试...]", out);
			return;
		}

		response.addHeader("Content-Length", String.valueOf(downFile.length()));
		response.addHeader("Accept-Ranges", "bytes");
		response.addHeader("Content-Range", "bytes 0-" + (downFile.length() - 1) + "/" + downFile.length());
		_LOGGER.info("[开始下载文件:{}]", filePath);
		writeFileToResponse(response, downFile, out);
	}

	private void obtainFilePicturesZip(EnhancedMEvidence enhancedMEvidence, HttpServletResponse response, OutputStream out) {
		List<EnhancedMREvidenceFile> pictureEnhancedMREvidenceFiles = new ArrayList<EnhancedMREvidenceFile>();
		for (EnhancedMREvidenceFile _EnhancedMREvidenceFile : enhancedMEvidence.getEnhancedMREvidenceFiles()) {
			if (_EnhancedMREvidenceFile.getFileType() == FileTypeEnum.IMG) {
				pictureEnhancedMREvidenceFiles.add(_EnhancedMREvidenceFile);
			}
		}
		if (!Detect.notEmpty(pictureEnhancedMREvidenceFiles)) {
			super.writeInfo2Resp(response, "[没有图片压缩包]", out);
			return;
		}

		// List<File> pictureFiles = new ArrayList<File>();
		JSONArray fileInfoJsonArray = new JSONArray();
		List<String> shownames = Lists.newArrayList();

		for (EnhancedMREvidenceFile _EnhancedMREvidenceFile : pictureEnhancedMREvidenceFiles) {
			String filePath = "/home/renke/download/" + _EnhancedMREvidenceFile.getPath();
			_LOGGER.info("[收到下载请求，替换成本地挂载路径:{}]", filePath);

			File downFile = new File(filePath);
			if (!downFile.exists() || downFile.length() < _EnhancedMREvidenceFile.getSize()) {
				DownloadFileJob.add(enhancedMEvidence);
				super.writeInfo2Resp(response, "[文件正在准备中，请稍后再试...]", out);
				return;
			}
			// pictureFiles.add(downFile);
			JSONObject fileInfoJson = new JSONObject();
			fileInfoJson.put("path", filePath);
			String showname = _EnhancedMREvidenceFile.getSort() + _EnhancedMREvidenceFile.getPath().substring(_EnhancedMREvidenceFile.getPath().lastIndexOf("."));
			if (shownames.contains(showname)) {
				showname = FileUtil.getFileName(_EnhancedMREvidenceFile.getPath());
			}
			shownames.add(showname);
			fileInfoJson.put("showname", showname);
			fileInfoJsonArray.add(fileInfoJson);
		}

		String firstFilePath = "/home/renke/download/" + pictureEnhancedMREvidenceFiles.get(0).getPath();
		String zipFilePath = firstFilePath.replace(FileUtil.getFileName(firstFilePath), "PICTURE.zip");
		File downFile = new File(zipFilePath);
		if (downFile.exists()) {
			downFile.delete();
		}

		// if (ZipCompressing.zip(zipFilePath, pictureFiles)) {
		if (ZipCompressing.zip(zipFilePath, fileInfoJsonArray)) {
			response.addHeader("Content-Length", String.valueOf(downFile.length()));
			response.addHeader("Accept-Ranges", "bytes");
			response.addHeader("Content-Range", "bytes 0-" + (downFile.length() - 1) + "/" + downFile.length());
			_LOGGER.info("[开始下载文件:{}]", zipFilePath);
			writeFileToResponse(response, downFile, out);
		} else {
			super.writeInfo2Resp(response, "[文件压缩失败，请重新下载...]", out);
		}

	}

}
