package com.renke.rdbao.beans.common.conf;

/**
 * 通知消费者服务配置
 * 
 * @author jgshun
 * @date 2017-2-28 下午3:30:40
 * @version 1.0
 */
public class NoticeConsumerConf {
	private String pnoes;// 公证处code
	private String signatureService;// 签名服务地址
	private String appVideoCustomerCount = "1";// 创建多少个APP视频消息消费者
	private String appAudioCustomerCount = "1";// 创建多少个APP音频消息消费者
	private String appPictureCustomerCount = "1";// 创建多少个APP图片消息消费者
	private String voiceCustomerCount = "1";// 创建多少个公证录音消息消费者
	private String receivedRedirectVoice4JSZHNoticeCustomerCount = "1";// 创建多少个转发公证录音消息消费者--江苏智恒

	/**
	 * @return the pnoes
	 */
	public String getPnoes() {
		return pnoes;
	}

	/**
	 * @param pnoes
	 *            the pnoes to set
	 */
	public void setPnoes(String pnoes) {
		this.pnoes = pnoes;
	}

	/**
	 * @return the signatureService
	 */
	public String getSignatureService() {
		return signatureService;
	}

	/**
	 * @param signatureService
	 *            the signatureService to set
	 */
	public void setSignatureService(String signatureService) {
		this.signatureService = signatureService;
	}

	public String getAppVideoCustomerCount() {
		return appVideoCustomerCount;
	}

	public void setAppVideoCustomerCount(String appVideoCustomerCount) {
		this.appVideoCustomerCount = appVideoCustomerCount;
	}

	/**
	 * @return the appAudioCustomerCount
	 */
	public String getAppAudioCustomerCount() {
		return appAudioCustomerCount;
	}

	/**
	 * @param appAudioCustomerCount
	 *            the appAudioCustomerCount to set
	 */
	public void setAppAudioCustomerCount(String appAudioCustomerCount) {
		this.appAudioCustomerCount = appAudioCustomerCount;
	}

	/**
	 * @return the appPictureCustomerCount
	 */
	public String getAppPictureCustomerCount() {
		return appPictureCustomerCount;
	}

	/**
	 * @param appPictureCustomerCount
	 *            the appPictureCustomerCount to set
	 */
	public void setAppPictureCustomerCount(String appPictureCustomerCount) {
		this.appPictureCustomerCount = appPictureCustomerCount;
	}

	public String getVoiceCustomerCount() {
		return voiceCustomerCount;
	}

	public void setVoiceCustomerCount(String voiceCustomerCount) {
		this.voiceCustomerCount = voiceCustomerCount;
	}

	public String getReceivedRedirectVoice4JSZHNoticeCustomerCount() {
		return receivedRedirectVoice4JSZHNoticeCustomerCount;
	}

	public void setReceivedRedirectVoice4JSZHNoticeCustomerCount(String receivedRedirectVoice4JSZHNoticeCustomerCount) {
		this.receivedRedirectVoice4JSZHNoticeCustomerCount = receivedRedirectVoice4JSZHNoticeCustomerCount;
	}

}
