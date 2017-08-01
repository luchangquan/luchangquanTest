package com.renke.rdbao.beans.common.vo.notice;

import java.io.Serializable;

/**
 * @author jgshun
 * @date 2017-2-28 下午2:00:17
 * @version 1.0
 */
public class NoticeRequestSignatureVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5510347203500229619L;
	private NoticeRequestVo noticeRequest;
	private String sign;
	private String signTime;
	private String signSerialNo;// 签名usbKey版本号 对应签名的公钥
	private String signRsaNo;// RSA签名版本
	private String pnoes;// 公证处编码
	private String taskId;// 任务编码 RDP使用

	/**
	 * @return the noticeRequest
	 */
	public NoticeRequestVo getNoticeRequest() {
		return noticeRequest;
	}

	/**
	 * @param noticeRequest
	 *            the noticeRequest to set
	 */
	public void setNoticeRequest(NoticeRequestVo noticeRequest) {
		this.noticeRequest = noticeRequest;
	}

	/**
	 * @return the sign
	 */
	public String getSign() {
		return sign;
	}

	/**
	 * @param sign
	 *            the sign to set
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSignTime() {
		return signTime;
	}

	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}

	public String getSignSerialNo() {
		return signSerialNo;
	}

	public void setSignSerialNo(String signSerialNo) {
		this.signSerialNo = signSerialNo;
	}

	public String getPnoes() {
		return pnoes;
	}

	public void setPnoes(String pnoes) {
		this.pnoes = pnoes;
	}

	public String getSignRsaNo() {
		return signRsaNo;
	}

	public void setSignRsaNo(String signRsaNo) {
		this.signRsaNo = signRsaNo;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

}
