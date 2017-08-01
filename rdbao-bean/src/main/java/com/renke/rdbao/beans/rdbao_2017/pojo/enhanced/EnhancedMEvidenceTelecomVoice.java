package com.renke.rdbao.beans.rdbao_2017.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidencetelecomvoice.CallTypeEnum4MEvidenceTelecomVoice;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidenceTelecomVoice;

/**
 * @author jgshun
 * @date 2017-4-10 下午5:46:57
 * @version 1.0
 */
public class EnhancedMEvidenceTelecomVoice extends BaseEnhanced {
	public EnhancedMEvidenceTelecomVoice() {
	}

	public EnhancedMEvidenceTelecomVoice(MEvidenceTelecomVoice evidenceVoice) {
		BeanUtils.copyProperties(evidenceVoice, this);
		if (evidenceVoice.getCallType() != null) {
			this.callType = CallTypeEnum4MEvidenceTelecomVoice.getCallTypeEnumByCode(evidenceVoice.getCallType());
		}
		if (evidenceVoice.getEvidenceId() != null && evidenceVoice.getEvidenceId().length() > 0) {
			EnhancedMEvidence _EnhancedMEvidence = new EnhancedMEvidence();
			_EnhancedMEvidence.setId(evidenceVoice.getEvidenceId());
			this.setEnhancedMEvidence(_EnhancedMEvidence);
		}

	}

	private EnhancedMEvidence enhancedMEvidence;
	private String callingNo;
	private String calledNo;
	private CallTypeEnum4MEvidenceTelecomVoice callType;
	private Date callTime;
	private long duration;
	private Date createTime;

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
	public CallTypeEnum4MEvidenceTelecomVoice getCallType() {
		return callType;
	}

	/**
	 * @param callType
	 *            the callType to set
	 */
	public void setCallType(CallTypeEnum4MEvidenceTelecomVoice callType) {
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

	public EnhancedMEvidence getEnhancedMEvidence() {
		return enhancedMEvidence;
	}

	public void setEnhancedMEvidence(EnhancedMEvidence enhancedMEvidence) {
		this.enhancedMEvidence = enhancedMEvidence;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

}
