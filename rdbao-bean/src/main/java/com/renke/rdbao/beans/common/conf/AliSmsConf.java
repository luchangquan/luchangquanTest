package com.renke.rdbao.beans.common.conf;

/**
 * 短信配置
 * 
 * @author jgshun
 * @date 2017-2-13 上午10:51:24
 * @version 1.0
 */
public class AliSmsConf {
	private String accessKeyId;
	private String accessKeySecret;
	private String mnsEndpoint;
	private String mnsTopic;

	/**
	 * @return the accessKeyId
	 */
	public String getAccessKeyId() {
		return accessKeyId;
	}

	/**
	 * @param accessKeyId
	 *            the accessKeyId to set
	 */
	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	/**
	 * @return the accessKeySecret
	 */
	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	/**
	 * @param accessKeySecret
	 *            the accessKeySecret to set
	 */
	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}

	public String getMnsEndpoint() {
		return mnsEndpoint;
	}

	public void setMnsEndpoint(String mnsEndpoint) {
		this.mnsEndpoint = mnsEndpoint;
	}

	public String getMnsTopic() {
		return mnsTopic;
	}

	public void setMnsTopic(String mnsTopic) {
		this.mnsTopic = mnsTopic;
	}

}
