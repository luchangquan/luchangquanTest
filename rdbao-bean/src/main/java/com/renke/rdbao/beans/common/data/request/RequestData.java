package com.renke.rdbao.beans.common.data.request;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

/**
 * @author jgshun
 * @date 2017-2-21 下午6:04:50
 * @version 1.0
 */
public class RequestData implements Serializable {

	private JSONObject request;
	private String sign;
	private ClientMetaRequestData clientMeta;

	public JSONObject getRequest() {
		return request;
	}

	public void setRequest(JSONObject request) {
		this.request = request;
	}

	/**
	 * 获得对应的通知请求封装
	 * 
	 * @param requestClass
	 *            通知封装类
	 * @return
	 */
	public <T> T getRequestData(Class<T> noticeRequestClass) {
		return JSONObject.toJavaObject(this.request, noticeRequestClass);
	}

	public ClientMetaRequestData getClientMeta() {
		return clientMeta;
	}

	public void setClientMeta(ClientMetaRequestData clientMeta) {
		this.clientMeta = clientMeta;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
