package com.renke.rdbao.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.renke.rdbao.beans.common.exception.RdbaoException;

/**
 * @author jgshun
 * @date 2017-3-2 下午1:23:48
 * @version 1.0
 */
public class TtsSignUtil {
	private static final Logger _LOGGER = LoggerFactory.getLogger(TtsSignUtil.class);

	public static Map<String, String> ttsSign(List<String> datas) throws RdbaoException {
		String requestString = JSONObject.toJSONString(datas);
		String responseString = null;
		try {
			responseString = HttpUtility.httpPost(PropertiesConfUtil.PROPERTIES_CONF.getCommonConf().getTtsService() + "/CreateTTSServlet", requestString);
		} catch (Exception ex) {
			_LOGGER.error("[调用时间戳签名服务异常]", ex);
			throw new RdbaoException("[调用时间戳签名服务异常:(" + ex.getMessage() + ")]");
		}
		@SuppressWarnings("unchecked")
		Map<String, String> responseMap = JSONObject.parseObject(responseString, LinkedHashMap.class);
		// 验证数据
		for (Entry<String, String> entry : responseMap.entrySet()) {
			if (StringUtils.isBlank(entry.getValue())) {
				throw new RdbaoException("[时间戳签名失败]");
			}
		}
		return responseMap;
	}

	public static void verifySign(Map<String, String> map) throws RdbaoException {
		// json化数据
		String requeString = JSONObject.toJSONString(map);
		String responseString = null;
		try {
			responseString = HttpUtility.httpPost(PropertiesConfUtil.PROPERTIES_CONF.getCommonConf().getTtsService() + "/VerifyTTSServlet", requeString);
		} catch (Exception ex) {
			_LOGGER.error("[调用时间戳签名服务异常]", ex);
			throw new RdbaoException("[调用时间戳签名服务异常:(" + ex.getMessage() + ")]");
		}
		// 对象化返回数据
		@SuppressWarnings("unchecked")
		Map<String, Boolean> responseMap = JSONObject.parseObject(responseString, LinkedHashMap.class);
		if (Detect.notEmpty(responseMap)) {
			for (Entry<String, Boolean> entry : responseMap.entrySet()) {
				if (!entry.getValue()) {
					throw new RdbaoException("[时间戳签名验证失败]");
				}
			}
		} else {
			throw new RdbaoException("[时间戳签名验证数据返回空:(入参=" + JSONObject.toJSONString(map) + ")]");
		}
	}

	public static void main(String[] args) throws Exception {
		List<String> datas = com.google.common.collect.Lists.newArrayList("ddddddddddddddddddddddddddddddddddddddddddddddddddd");
		Map<String, String> signData = ttsSign(datas);
		System.out.println(signData);
		String sign = signData.get(datas.get(0));
		System.out.println(sign);
		// sign = null;
		Map<String, String> map = new HashMap<String, String>();
		map.put(datas.get(0), sign);
		verifySign(map);
	}

}
