package com.renke.rdbao.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
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
 * 上海电信类的通知参数处理控制器
 * 
 * @author guoshunjiang
 *
 */
@Controller
@RequestMapping("rdbaobargin/faxVoc/speVoiceNotify")
public class VoiceShanghaiSpeTypeController extends BaseController {
	private static final Logger _LOGGER = LoggerFactory.getLogger(VoiceShanghaiSpeTypeController.class);

	@Autowired
	private IVoiceNoticeService voiceNoticeService;

	// {"response":{"info":{"code":"100000","msg":"请求成功"}}}

	@RequestMapping("")
	public @ResponseBody String speVoiceNotify(HttpServletRequest request) throws IOException {
		String requestStr = super.readRequest();
		String sign = request.getHeader("sign");
		_LOGGER.info("[接收到电信通知参数:{}--{}]", sign, requestStr);

		if (!DigestUtils.md5Hex(requestStr).equalsIgnoreCase(sign)) {
			return "{\"response\":{\"info\":{\"code\":\"200000\",\"msg\":\"签名验证失败\"}}}";
		}
		RequestSignData requestSignData = new RequestSignData();
		requestSignData.setRequest(requestStr);
		requestSignData.setSign(sign);

		try {
			voiceNoticeService.saveVoiceNotice(requestSignData, null);
		} catch (InvalidKeyException | AliOperateException | RdbaoException | NoSuchAlgorithmException | SignatureException e) {
			_LOGGER.error("[接收电信通知失败]", e);
			return "{\"response\":{\"info\":{\"code\":\"200000\",\"msg\":\"" + e.getMessage() + "\"}}}";
		}

		return "{\"response\":{\"info\":{\"code\":\"100000\",\"msg\":\"请求成功\"}}}";
	}

}
