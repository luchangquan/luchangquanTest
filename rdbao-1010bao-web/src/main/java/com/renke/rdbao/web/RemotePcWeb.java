package com.renke.rdbao.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.renke.rdbao.beans.common.data.response.ResponseData;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.pojo.RUserNppProduct;
import com.renke.rdbao.services.rdbao_2017.service.IRUserNppProductService;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.web.base.BaseWeb;

/**
 * @author jgshun
 * @date 2017-5-3 下午12:15:10
 * @version 1.0
 */
@Controller
@RequestMapping
public class RemotePcWeb extends BaseWeb {
	// private static final String REMOTE_PC_BASE_URL =
	// "http://rdptest.renosdata.cn:8088/PortAPI/Login";//测试

	private static final String REMOTE_PC_BASE_URL = "http://remote.1010bao.com:8088/PortAPI/Login";
	private static final String REMOTE_PC_KEY = "1234567890";
	@Autowired
	private IRUserNppProductService rUserNppProductService;

	@RequestMapping("getRemotePcUrl")
	public @ResponseBody
	ResponseData getRemotePcUrl(String nppCode) throws UnsupportedEncodingException {
		ResponseData responseData = new ResponseData();
		UserContext userContext = super.getUserContext();

		Map<String, String> inputMap = new HashMap<String, String>();
		String account = userContext.getUser().getAccount();
		String balance = "1";
		RUserNppProduct record = new RUserNppProduct();
		record.setUserId(userContext.getUserId());
		record.setNppCode(userContext.getSourceNppCode());
		record.setProductCode("RDP");

		List<RUserNppProduct> rUserNppProducts = rUserNppProductService.getListByRecord(record, userContext);
		if (!Detect.notEmpty(rUserNppProducts)) {
			responseData.setResponseEnum(ResponseEnum.NOT_OPEN_PRODUCT);
			return responseData;
		}
		String pnoCode = nppCode;
		if (!Detect.notEmpty(pnoCode)) {
			pnoCode = rUserNppProducts.get(0).getNppCode();// 为空，随机取第一个
		}
		String signTime = new DateTime().toString("yyyy-MM-dd HH:mm:ss");

		inputMap.put("account", account);
		inputMap.put("balance", balance);
		inputMap.put("pnoCode", pnoCode);
		inputMap.put("signTime", signTime);

		String inputStr = JSONObject.toJSONString(inputMap);
		String signStr = DigestUtils.md5Hex(inputStr + REMOTE_PC_KEY);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("url", REMOTE_PC_BASE_URL + "?inputStr=" + URLEncoder.encode(inputStr, "utf-8") + "&signStr=" + signStr);
		responseData.setData(resultMap);

		return responseData;
	}
}
