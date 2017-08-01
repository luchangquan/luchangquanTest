package com.renke.rdbao.beans.rdbao_v3.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_v3.enums.forevidencefaxVoices.CallTypeEnum4EvidenceFaxVoices;
import com.renke.rdbao.beans.rdbao_v3.enums.forevidencefaxVoices.VoiceTypeEnum4EvidenceFaxVoices;
import com.renke.rdbao.beans.rdbao_v3.pojo.EvidenceFaxVoices;

/**
 * @author jgshun
 * @date 2016-12-30 下午1:28:39
 * @version 1.0
 */
public class EnhancedEvidenceFaxVoices extends BaseEnhanced {
	public EnhancedEvidenceFaxVoices() {
	}

	public EnhancedEvidenceFaxVoices(EvidenceFaxVoices evidenceFaxVoices) {
		BeanUtils.copyProperties(evidenceFaxVoices, this);
		if (evidenceFaxVoices.getEvidenceId() != null && evidenceFaxVoices.getEvidenceId().trim().length() > 0) {
			EnhancedEvidences _EnhancedEvidences = new EnhancedEvidences();
			_EnhancedEvidences.setId(evidenceFaxVoices.getEvidenceId());
			this.enhancedEvidences = _EnhancedEvidences;
		}

		if (evidenceFaxVoices.getCallType() != null && evidenceFaxVoices.getCallType().trim().length() > 0) {
			try {
				this.callType = CallTypeEnum4EvidenceFaxVoices.getCallTypeEnumByCode(Short.valueOf(evidenceFaxVoices.getCallType()));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		if (evidenceFaxVoices.getVoiceType() != null && evidenceFaxVoices.getVoiceType().trim().length() > 0) {
			try {
				this.voiceType = VoiceTypeEnum4EvidenceFaxVoices.getVoiceTypeEnumByCode(Short.valueOf(evidenceFaxVoices.getVoiceType()));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	private EnhancedEvidences enhancedEvidences;
	private String callingNumber;
	private String calledNumber;
	private long duration;
	private Date callTime;
	private Date createTime;
	private CallTypeEnum4EvidenceFaxVoices callType;
	private VoiceTypeEnum4EvidenceFaxVoices voiceType;
	private String md5;
	private String requestJson;
	private String caSign;
	private String ttsSign;

	/**
	 * @return the enhancedEvidences
	 */
	public EnhancedEvidences getEnhancedEvidences() {
		return enhancedEvidences;
	}

	/**
	 * @param enhancedEvidences
	 *            the enhancedEvidences to set
	 */
	public void setEnhancedEvidences(EnhancedEvidences enhancedEvidences) {
		this.enhancedEvidences = enhancedEvidences;
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

	/**
	 * @return the callType
	 */
	public CallTypeEnum4EvidenceFaxVoices getCallType() {
		return callType;
	}

	/**
	 * @param callType
	 *            the callType to set
	 */
	public void setCallType(CallTypeEnum4EvidenceFaxVoices callType) {
		this.callType = callType;
	}

	/**
	 * @return the voiceType
	 */
	public VoiceTypeEnum4EvidenceFaxVoices getVoiceType() {
		return voiceType;
	}

	/**
	 * @param voiceType
	 *            the voiceType to set
	 */
	public void setVoiceType(VoiceTypeEnum4EvidenceFaxVoices voiceType) {
		this.voiceType = voiceType;
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
	 * @return the requestJson
	 */
	public String getRequestJson() {
		return requestJson;
	}

	/**
	 * @param requestJson
	 *            the requestJson to set
	 */
	public void setRequestJson(String requestJson) {
		this.requestJson = requestJson;
	}

	/**
	 * @return the caSign
	 */
	public String getCaSign() {
		return caSign;
	}

	/**
	 * @param caSign
	 *            the caSign to set
	 */
	public void setCaSign(String caSign) {
		this.caSign = caSign;
	}

	/**
	 * @return the ttsSign
	 */
	public String getTtsSign() {
		return ttsSign;
	}

	/**
	 * @param ttsSign
	 *            the ttsSign to set
	 */
	public void setTtsSign(String ttsSign) {
		this.ttsSign = ttsSign;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

}
