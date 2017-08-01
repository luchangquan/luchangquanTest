package com.renke.rdbao.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.renke.rdbao.beans.common.data.request.RequestData;
import com.renke.rdbao.beans.common.data.response.ResponseData;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.rdbao_sms_2017.pojo.ESmsInfo;
import com.renke.rdbao.controller.base.BaseController;
import com.renke.rdbao.services.rdbao_sms_2017.service.IESmsInfoService;

@Controller
@RequestMapping("sms")
public class SmsController extends BaseController {
	@Autowired
	private IESmsInfoService smsInfoService;

	public static void main(String[] args) {
		String sourceCode = "ZHONGZHENGTONG";
		String requestTime = "20170717113201";
		String key = "c26d5a1d36c2405e83d851cbfefca11a==";
		String sign = DigestUtils.md5Hex(sourceCode + "_" + requestTime + "_" + key);
		System.out.println(sign);
	}

	@RequestMapping("send")
	public @ResponseBody ResponseData send(@RequestBody RequestData requestData) {
		super.checkApiSign(requestData);

		ResponseData responseData = new ResponseData();

		@SuppressWarnings("unchecked")
		JSONObject jsonParams = requestData.getRequest().getJSONObject("args");
		String signatureCode = requestData.getRequest().getString("signatureCode");
		String templateCode = requestData.getRequest().getString("templateCode");
		String[] phoneNos = requestData.getRequest().getObject("phoneNos", String[].class);

		Map<String, String> params = new HashMap<String, String>();
		if (jsonParams != null) {
			params = JSONObject.parseObject(jsonParams.toJSONString(), HashMap.class);
			if (jsonParams.toJSONString().length() >= 550) {
				throw new RdbaoException("[短信长度暂不支持500字符以上]");
			}
		}

		// 校验发送者
		List<ESmsInfo> smsInfos = smsInfoService.send(signatureCode, templateCode, params, Arrays.asList(phoneNos), null);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("identificationId", smsInfos.get(0).getIdentificationId());
		responseData.setData(resultMap);
		return responseData;
	}

}
