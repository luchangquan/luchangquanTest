package com.renke.rdbao.beans.common.vo.notice.voice;

import java.io.Serializable;

/**
 * @author jgshun
 * @date 2017-2-21 下午5:45:42
 * @version 1.0
 */
public class VoiceNoticeRequestData implements Serializable {
	private String appCode;// 字符串 代表电信的appCode 否
	private String callingNumber;// 字符串 主叫号码 否
	private String calledNumber;// 字符串 被叫号码 否
	private String displayNumber;// 字符串 外显号码 是
	private long duration;// 整型 通话时长（秒） 否
	private String callTime;;// 字符串 通话开始的时间 例：2016-12-14 09:42:58 否
	private String fileIdentity;// 字符串 录音文件的标识 注:STS接口中返回的“阿里云OSS的文件key”
								// 当通话未建立时可以为空 否
	private short callType;// 整型 通话类型： 1主叫 2被叫 否
	private String md5;// 字符串 录音文件的md5 注:上传录音文件本身的md5值,采用HEX字符串的表示形式 否
	private String utc;// 字符串 发送通知消息的UTC时间 例： 2016-12-14T09:50:01Z 否

	/**
	 * @return the appCode
	 */
	public String getAppCode() {
		return appCode;
	}

	/**
	 * @param appCode
	 *            the appCode to set
	 */
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	/**
	 * @return the callingNumber
	 */
	public String getCallingNumber() {
		return callingNumber;
	}

	/**
	 * @param callingNumber
	 *            the callingNumber to set
	 */
	public void setCallingNumber(String callingNumber) {
		this.callingNumber = callingNumber;
	}

	/**
	 * @return the calledNumber
	 */
	public String getCalledNumber() {
		return calledNumber;
	}

	/**
	 * @param calledNumber
	 *            the calledNumber to set
	 */
	public void setCalledNumber(String calledNumber) {
		this.calledNumber = calledNumber;
	}

	/**
	 * @return the displayNumber
	 */
	public String getDisplayNumber() {
		return displayNumber;
	}

	/**
	 * @param displayNumber
	 *            the displayNumber to set
	 */
	public void setDisplayNumber(String displayNumber) {
		this.displayNumber = displayNumber;
	}

	/**
	 * @return the callTime
	 */
	public String getCallTime() {
		return callTime;
	}

	/**
	 * @param callTime
	 *            the callTime to set
	 */
	public void setCallTime(String callTime) {
		this.callTime = callTime;
	}

	/**
	 * @return the fileIdentity
	 */
	public String getFileIdentity() {
		return fileIdentity;
	}

	/**
	 * @param fileIdentity
	 *            the fileIdentity to set
	 */
	public void setFileIdentity(String fileIdentity) {
		this.fileIdentity = fileIdentity;
	}

	/**
	 * @return the callType
	 */
	public short getCallType() {
		return callType;
	}

	/**
	 * @param callType
	 *            the callType to set
	 */
	public void setCallType(short callType) {
		this.callType = callType;
	}

	/**
	 * @return the md5
	 */
	public String getMd5() {
		return md5;
	}

	/**
	 * @param md5
	 *            the md5 to set
	 */
	public void setMd5(String md5) {
		this.md5 = md5;
	}

	/**
	 * @return the utc
	 */
	public String getUtc() {
		return utc;
	}

	/**
	 * @param utc
	 *            the utc to set
	 */
	public void setUtc(String utc) {
		this.utc = utc;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

}
