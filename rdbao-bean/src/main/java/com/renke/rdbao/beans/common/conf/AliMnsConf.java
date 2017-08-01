package com.renke.rdbao.beans.common.conf;

/**
 * @author jgshun
 * @date 2017-2-13 下午4:54:22
 * @version 1.0
 */
public class AliMnsConf {
	private String endpoint;
	private String accessKeyId;
	private String accessKeySecret;

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

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

}
