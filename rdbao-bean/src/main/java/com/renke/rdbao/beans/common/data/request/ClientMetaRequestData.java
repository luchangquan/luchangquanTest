package com.renke.rdbao.beans.common.data.request;

import java.io.Serializable;

/**
 * 客户端元数据
 * 
 * @author jgshun
 * @date 2017-2-24 下午12:18:12
 * @version 1.0
 */
public class ClientMetaRequestData implements Serializable {
	private String deviceId;// 设备号
	private String os;// 系统
	private String osVersion;// 系统版本
	private String appOs;// app系统
	private String appVersion;// app版本
	private String brand;// 手机品牌

	/**
	 * @return the deviceId
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId
	 *            the deviceId to set
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * @return the os
	 */
	public String getOs() {
		return os;
	}

	/**
	 * @param os
	 *            the os to set
	 */
	public void setOs(String os) {
		this.os = os;
	}

	/**
	 * @return the osVersion
	 */
	public String getOsVersion() {
		return osVersion;
	}

	/**
	 * @param osVersion
	 *            the osVersion to set
	 */
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	/**
	 * @return the appOs
	 */
	public String getAppOs() {
		return appOs;
	}

	/**
	 * @param appOs
	 *            the appOs to set
	 */
	public void setAppOs(String appOs) {
		this.appOs = appOs;
	}

	/**
	 * @return the appVersion
	 */
	public String getAppVersion() {
		return appVersion;
	}

	/**
	 * @param appVersion
	 *            the appVersion to set
	 */
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand
	 *            the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

}
