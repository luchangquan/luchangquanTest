package com.renke.rdbao.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.renke.rdbao.beans.common.data.request.RequestSignData;
import com.renke.rdbao.beans.common.data.response.ResponseData;
import com.renke.rdbao.beans.common.exception.AliOperateException;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.controller.base.BaseController;
import com.renke.rdbao.services.rdbao_2017.service.IVoiceNoticeService;

/**
 * @author jgshun
 * @date 2017-2-21 下午5:31:37
 * @version 1.0
 */
@Controller
@RequestMapping("voice")
public class VoiceController extends BaseController {
	private static final Logger _LOGGER = LoggerFactory.getLogger(VoiceController.class);

	@Autowired
	private IVoiceNoticeService voiceNoticeService;

	@RequestMapping(value = "notice", method = RequestMethod.POST)
	public @ResponseBody
	ResponseData videoNotice(@RequestBody RequestSignData requestSignData) throws UserContextException, InvalidKeyException, NoSuchAlgorithmException, SignatureException {
		ResponseData responseData = new ResponseData();

		try {
			String evidenceCode = voiceNoticeService.saveVoiceNotice(requestSignData, null);
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("evidenceCode", evidenceCode);

			responseData.setData(result);
			responseData.setRespDesc("[收取成功，待后续处理]");
		} catch (RdbaoException | AliOperateException ex) {
			super.dealResponseException(responseData, ex);
			_LOGGER.error("[语音存证出错]", ex);
		} catch (Exception ex) {
			super.dealResponseException(responseData, ex);
			_LOGGER.error("[语音存证出错]", ex);
		}
		return responseData;
	}

	/**
	 * 接受转发江苏智恒的通知
	 * 
	 * @param requestSignData
	 * @return
	 * @throws UserContextException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws SignatureException
	 */
	@RequestMapping(value = "receivedRedirectNotice4JSZH", method = RequestMethod.POST)
	public @ResponseBody
	ResponseData receivedRedirectNotice4JSZH(@RequestBody RequestSignData requestSignData) throws UserContextException, InvalidKeyException, NoSuchAlgorithmException, SignatureException {
		ResponseData responseData = new ResponseData();
		JSONObject noticeJsonObj = JSONObject.parseObject(requestSignData.getRequest());
		String sign = DigestUtils.md5Hex(noticeJsonObj.getString("AppCode") + noticeJsonObj.getString("MD5") + noticeJsonObj.getString("CallTime") + "_" + noticeJsonObj.getString("AppKey"));
		try {
			if (!sign.equalsIgnoreCase(requestSignData.getSign())) {
				throw new RdbaoException("[验签失败]");
			}
			voiceNoticeService.saveReceivedRedirectVoiceNotice4JSZH(requestSignData.getRequest(), null);
			responseData.setRespDesc("[收取成功，待后续处理]");
		} catch (Exception ex) {
			super.dealResponseException(responseData, ex);
			_LOGGER.error("[转发语音存证--江苏智恒出错]", ex);
		}
		return responseData;
	}

	public static void main(String[] args) {
		String r = "{\"AppCode\":\"NGCCNJ\",\"AppKey\":\"123!ngcc\",\"CallingNumber\":\"051683109006\",\"CalledNumber\":\"051683338896\",\"Duration\":\"3\",\"CallTime\":\"2017-05-0910:24:52\",\"Location\":\"/shoujiluyin/20170509/NGCC13970509102439028601.wav\",\"EvidenceCategoryId\":5,\"CallType\":1,\"VoiceType\":1,\"MD5\":\"b37383948fd74436a3c7381468229440\"}";

		System.out.println(r);
	}

}
