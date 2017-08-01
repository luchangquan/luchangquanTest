package com.renke.rdbao.beans.rdbao_2017.data.app.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.renke.rdbao.beans.data.response.base.BaseResponseData;

/**
 * @author jgshun
 * @date 2017-4-21 下午3:38:53
 * @version 1.0
 */
public class MEvidenceTelecomVoiceResponse extends BaseResponseData {

	private MEvidenceResponse evidence;
	private String callingNo;
	private String calledNo;
	private short callType;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date callTime;
	private long duration;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date createTime;

	/**
	 * @return the evidence
	 */
	public MEvidenceResponse getEvidence() {
		return evidence;
	}

	/**
	 * @param evidence
	 *            the evidence to set
	 */
	public void setEvidence(MEvidenceResponse evidence) {
		this.evidence = evidence;
	}

	/**
	 * @return the callingNo
	 */
	public String getCallingNo() {
		return callingNo;
	}

	/**
	 * @param callingNo
	 *            the callingNo to set
	 */
	public void setCallingNo(String callingNo) {
		this.callingNo = callingNo;
	}

	/**
	 * @return the calledNo
	 */
	public String getCalledNo() {
		return calledNo;
	}

	/**
	 * @param calledNo
	 *            the calledNo to set
	 */
	public void setCalledNo(String calledNo) {
		this.calledNo = calledNo;
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
	 * @return the callTime
	 */
	public Date getCallTime() {
		return callTime;
	}

	/**
	 * @param callTime
	 *            the callTime to set
	 */
	public void setCallTime(Date callTime) {
		this.callTime = callTime;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

}
