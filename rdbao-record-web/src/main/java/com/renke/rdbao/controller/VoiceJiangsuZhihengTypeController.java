package com.renke.rdbao.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.renke.rdbao.beans.common.data.request.RequestSignData;
import com.renke.rdbao.beans.common.exception.AliOperateException;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.controller.base.BaseController;
import com.renke.rdbao.services.rdbao_2017.service.IVoiceNoticeService;

/**
 * 江苏智恒类的通知参数处理控制器
 * 
 * @author guoshunjiang
 *
 */
@Controller
@RequestMapping("renkehandle/notice")
public class VoiceJiangsuZhihengTypeController extends BaseController {
	private static final Logger _LOGGER = LoggerFactory.getLogger(VoiceJiangsuZhihengTypeController.class);

	@Autowired
	private IVoiceNoticeService voiceNoticeService;

	@RequestMapping("")
	public @ResponseBody String notice(HttpServletRequest request) throws IOException {
		String requestStr = super.readRequest();
		_LOGGER.info("[接收到电信通知参数:{}]", requestStr);
		requestStr = requestStr.replace(" \"EvidenceCategoryId\"", "\"EvidenceCategoryId\"").trim();// 去处json
		// 中的空格，不然验签的时候会通不过

		RequestSignData requestSignData = new RequestSignData();
		requestSignData.setRequest(requestStr);

		try {
			voiceNoticeService.saveVoiceNotice(requestSignData, null);
		} catch (InvalidKeyException | AliOperateException | RdbaoException | NoSuchAlgorithmException | SignatureException e) {
			_LOGGER.error("[接收电信通知失败]", e);
			return "{\"resultNo\":0,\"resultMsg\":\"" + e.getMessage() + "\"}";
		}

		return "{\"resultNo\":1,\"resultMsg\":\"成功\"}";
	}

}
