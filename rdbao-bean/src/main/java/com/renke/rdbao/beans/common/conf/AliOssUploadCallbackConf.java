package com.renke.rdbao.beans.common.conf;

/**
 * @author jgshun
 * @date 2017-2-13 下午4:54:22
 * @version 1.0
 */
public class AliOssUploadCallbackConf {
	private String appVideoCallbackUrl;// 视频回调地址
	private String appAudioCallbackUrl;// 音频回调地址
	private String appPictureCallbackUrl;// 图片回调地址

	/**
	 * @return the appVideoCallbackUrl
	 */
	public String getAppVideoCallbackUrl() {
		return appVideoCallbackUrl;
	}

	/**
	 * @param appVideoCallbackUrl
	 *            the appVideoCallbackUrl to set
	 */
	public void setAppVideoCallbackUrl(String appVideoCallbackUrl) {
		this.appVideoCallbackUrl = appVideoCallbackUrl;
	}

	/**
	 * @return the appAudioCallbackUrl
	 */
	public String getAppAudioCallbackUrl() {
		return appAudioCallbackUrl;
	}

	/**
	 * @param appAudioCallbackUrl
	 *            the appAudioCallbackUrl to set
	 */
	public void setAppAudioCallbackUrl(String appAudioCallbackUrl) {
		this.appAudioCallbackUrl = appAudioCallbackUrl;
	}

	/**
	 * @return the appPictureCallbackUrl
	 */
	public String getAppPictureCallbackUrl() {
		return appPictureCallbackUrl;
	}

	/**
	 * @param appPictureCallbackUrl
	 *            the appPictureCallbackUrl to set
	 */
	public void setAppPictureCallbackUrl(String appPictureCallbackUrl) {
		this.appPictureCallbackUrl = appPictureCallbackUrl;
	}

}
