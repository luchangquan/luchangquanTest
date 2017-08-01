package com.renke.rdbao.beans.common.data.request;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

/**
 * @author jgshun
 * @date 2017-2-21 下午6:04:50
 * @version 1.0
 */
public class RequestSignData implements Serializable {

	private String request;
	private String sign;
	private ClientMetaRequestData clientMeta;

	/**
	 * 获得对应的通知请求封装
	 * 
	 * @param requestClass
	 *            通知封装类
	 * @return
	 */
	public <T> T getRequestData(Class<T> noticeRequestClass) {
		return JSONObject.toJavaObject(JSONObject.parseObject(this.request), noticeRequestClass);
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	/**
	 * @return the request
	 */
	public String getRequest() {
		return request;
	}

	/**
	 * @param request
	 *            the request to set
	 */
	public void setRequest(String request) {
		this.request = request;
	}

	public ClientMetaRequestData getClientMeta() {
		return clientMeta;
	}

	public void setClientMeta(ClientMetaRequestData clientMeta) {
		this.clientMeta = clientMeta;
	}

}
