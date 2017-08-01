package com.renke.rdbao.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.renke.rdbao.beans.common.data.request.RequestData;
import com.renke.rdbao.beans.common.data.response.ResponseData;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.exception.AliOperateException;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.vo.notice.alicallback.AppAudioUploadCallbackRequestData;
import com.renke.rdbao.beans.common.vo.notice.alicallback.AppPictureUploadCallbackRequestData;
import com.renke.rdbao.beans.common.vo.notice.alicallback.AppVideoUploadCallbackRequestData;
import com.renke.rdbao.controller.base.NoticeController;
import com.renke.rdbao.services.rdbao_2017.service.IAppUploadCallbackService;
///import com.aliyun.oss.OSSClient;
//import com.aliyun.oss.common.utils.BinaryUtil;
//import org.apache.commons.codec.binary.Base64;

/**
 * @author jgshun
 * @date 2017-3-1 下午7:37:38
 * @version 1.0
 */
@Controller
@RequestMapping("app/upload/callback")
public class AppUploadCallbackController extends NoticeController {
	private static final Logger _LOGGER = LoggerFactory.getLogger(AppUploadCallbackController.class);

	@Autowired
	private IAppUploadCallbackService appUploadCallbackService;

	@RequestMapping(value = "video")
	public @ResponseBody
	ResponseData appVideoNoticeCallback(HttpServletRequest request, @RequestBody RequestData requestData) throws NumberFormatException, IOException, AliOperateException, RdbaoException, JAXBException {
		ResponseData responseData = new ResponseData();

		AppVideoUploadCallbackRequestData appVideoUploadCallbackRequestData = JSONObject.toJavaObject(requestData.getRequest(), AppVideoUploadCallbackRequestData.class);
		Map<String, Object> ossRequestMap = new HashMap<String, Object>();
		ossRequestMap.put("request", appVideoUploadCallbackRequestData);
		_LOGGER.info("收到回调:" + JSONObject.toJSONString(ossRequestMap));
		if (super.verifyOSSCallbackRequest(request, JSONObject.toJSONString(ossRequestMap))) {
			_LOGGER.info("收到来自阿里云OSS的回调");
		} else {
			_LOGGER.info("来自未验证来源的调用");
			responseData.setResponseEnum(ResponseEnum.ILLEGAL_OPERATION);
			return responseData;
		}
		appUploadCallbackService.sendCallbackInfoToMns(appVideoUploadCallbackRequestData, null);
		// appUploadCallbackService.updateEvidenceDetail(appVideoUploadCallbackRequestData,
		// null);// 更新文件详情
		return responseData;
	}

	@RequestMapping(value = "audio")
	public @ResponseBody
	ResponseData appVoiceNoticeCallback(HttpServletRequest request, @RequestBody RequestData requestData) throws NumberFormatException, IOException, AliOperateException, RdbaoException, JAXBException {
		ResponseData responseData = new ResponseData();

		AppAudioUploadCallbackRequestData appAudioUploadCallbackRequestData = JSONObject.toJavaObject(requestData.getRequest(), AppAudioUploadCallbackRequestData.class);
		Map<String, Object> ossRequestMap = new HashMap<String, Object>();
		ossRequestMap.put("request", appAudioUploadCallbackRequestData);
		_LOGGER.info("收到回调:" + JSONObject.toJSONString(ossRequestMap));
		if (super.verifyOSSCallbackRequest(request, JSONObject.toJSONString(ossRequestMap))) {
			_LOGGER.info("收到来自阿里云OSS的回调");
		} else {
			_LOGGER.info("来自未验证来源的调用");
			responseData.setResponseEnum(ResponseEnum.ILLEGAL_OPERATION);
			return responseData;
		}
		appUploadCallbackService.sendCallbackInfoToMns(appAudioUploadCallbackRequestData, null);
		// appUploadCallbackService.updateEvidenceDetail(appVideoUploadCallbackRequestData,
		// null);// 更新文件详情
		return responseData;
	}

	@RequestMapping(value = "img")
	public @ResponseBody
	ResponseData appPictureNoticeCallback(HttpServletRequest request, @RequestBody RequestData requestData) throws NumberFormatException, IOException, AliOperateException, RdbaoException,
			JAXBException {
		ResponseData responseData = new ResponseData();

		AppPictureUploadCallbackRequestData appPictureUploadCallbackRequestData = JSONObject.toJavaObject(requestData.getRequest(), AppPictureUploadCallbackRequestData.class);
		Map<String, Object> ossRequestMap = new HashMap<String, Object>();
		ossRequestMap.put("request", appPictureUploadCallbackRequestData);
		_LOGGER.info("收到回调:" + JSONObject.toJSONString(ossRequestMap));
		if (super.verifyOSSCallbackRequest(request, JSONObject.toJSONString(ossRequestMap))) {
			_LOGGER.info("收到来自阿里云OSS的回调");
		} else {
			_LOGGER.info("来自未验证来源的调用");
			responseData.setResponseEnum(ResponseEnum.ILLEGAL_OPERATION);
			return responseData;
		}
		appUploadCallbackService.sendCallbackInfoToMns(appPictureUploadCallbackRequestData, null);
		// appUploadCallbackService.updateEvidenceDetail(appVideoUploadCallbackRequestData,
		// null);// 更新文件详情
		return responseData;
	}

}
