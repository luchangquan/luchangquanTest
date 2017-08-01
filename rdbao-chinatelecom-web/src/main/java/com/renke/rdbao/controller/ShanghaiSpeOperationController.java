package com.renke.rdbao.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.renke.rdbao.beans.common.data.response.ResponseData;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.thirdparty.chinatelecom2renke.request.ShanghaiSpeOperation2renkeRequest;
import com.renke.rdbao.controller.base.BaseController;
import com.renke.rdbao.services.rdbao_2017.service.IShanghaiSpeOperationService;

/**
 * 接口地址固定，适应.net原来接口地址
 * 
 * @author jgshun
 * @date 2017-3-13 下午7:14:24
 * @version 1.0
 */
@Controller
@RequestMapping("HandlerSPE_Order.ashx")
public class ShanghaiSpeOperationController extends BaseController {
	private static final Logger _LOGGER = LoggerFactory.getLogger(ShanghaiSpeOperationController.class);
	@Autowired
	private IShanghaiSpeOperationService shanghaiSpeOperationService;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody ResponseData spe2renkeOperationAccount(HttpServletRequest request, HttpServletResponse response)
			throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException {
		// String encodeRequest = super.readRequest(request);
		// _LOGGER.info("[接受到上海电信操作,密文:{}]", encodeRequest);
		// String decodeRequest = AesUtil.decrypt(encodeRequest,
		// "2EmzvfLbjb1VolSk5TAt3A==");
		// _LOGGER.info("[接受到上海电信操作,密文:{},原文{}]", encodeRequest, decodeRequest);
		// ShanghaiSpeOperation2renkeRequest speOperation2renkeRequest =
		// JSONObject.parseObject(decodeRequest,
		// ShanghaiSpeOperation2renkeRequest.class);
		ShanghaiSpeOperation2renkeRequest speOperation2renkeRequest = JSONObject.parseObject(super.readRequest(request), ShanghaiSpeOperation2renkeRequest.class);
		String optype = speOperation2renkeRequest.getOptype().equalsIgnoreCase("add") ? "开" : "销";
		_LOGGER.info("[接受到上海电信" + optype + "户操作:(" + JSONObject.toJSONString(speOperation2renkeRequest) + ")]");
		ResponseData responseData = new ResponseData();
		try {
			shanghaiSpeOperationService.saveSpe2renkeOperation(speOperation2renkeRequest);
		} catch (RdbaoException ex) {
			ResponseEnum responseEnum = ex.getResponse();
			if (responseEnum == ResponseEnum.USER_EXISTED) {// 用户已存在抛出http状态码为201
				response.setStatus(201);
			}
			super.dealResponseException(responseData, ex);
		} catch (RemoteException | UnsupportedEncodingException ex) {
			super.dealResponseException(responseData, ex);
		}
		_LOGGER.info("[接收上海电信" + optype + "户处理结果:(" + JSONObject.toJSONString(responseData) + ")]");
		return responseData;
	}

}
