package com.renke.rdbao.beans.rdbao_v3.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:02:53
 * @version 1.0
 */
@Table(name = "EvidenceFaxVoices")
public class EvidenceFaxVoices extends BasePo {

	public static final String FIELD_EVIDENCEID = "evidenceId";
	public static final String FIELD_CALLINGNUMBER = "callingNumber";
	public static final String FIELD_CALLEDNUMBER = "calledNumber";
	public static final String FIELD_DURATION = "duration";
	public static final String FIELD_CALLTIME = "callTime";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_CALLTYPE = "callType";
	public static final String FIELD_VOICETYPE = "voiceType";
	public static final String FIELD_MD5 = "md5";
	public static final String FIELD_REQUESTJSON = "requestJson";
	public static final String FIELD_CASIGN = "caSign";
	public static final String FIELD_TTSSIGN = "ttsSign";

	public static final String COLUMN_EVIDENCE_ID = "Evidence_Id";
	public static final String COLUMN_CALLINGNUMBER = "CallingNumber";
	public static final String COLUMN_CALLEDNUMBER = "CalledNumber";
	public static final String COLUMN_DURATION = "Duration";
	public static final String COLUMN_CALLTIME = "CallTime";
	public static final String COLUMN_CREATETIME = "CreateTime";
	public static final String COLUMN_CALLTYPE = "CallType";
	public static final String COLUMN_VOICETYPE = "VoiceType";
	public static final String COLUMN_MD5 = "MD5";
	public static final String COLUMN_REQUESTJSON = "RequestJson";
	public static final String COLUMN_CASIGN = "CASign";
	public static final String COLUMN_TTSSIGN = "TTSSign";

	public static final String ORDER_CREATE_TIME_DESC = " CreateTime desc ";

	@Id
	@Column(name = "Evidence_Id")
	private String evidenceId;
	@Column(name = "CallingNumber")
	private String callingNumber;
	@Column(name = "CalledNumber")
	private String calledNumber;
	@Column(name = "Duration")
	private Long duration;
	@Column(name = "CallTime")
	private Date callTime;
	@Column(name = "CreateTime")
	private Date createTime;
	@Column(name = "CallType")
	private String callType;
	@Column(name = "VoiceType")
	private String voiceType;
	@Column(name = "MD5")
	private String md5;
	@Column(name = "RequestJson")
	private String requestJson;
	@Column(name = "CASign")
	private String caSign;
	@Column(name = "TTSSign")
	private String ttsSign;

	/**
	 * @return the evidenceId
	 */
	public String getEvidenceId() {
		return evidenceId;
	}

	/**
	 * @param evidenceId
	 *            the evidenceId to set
	 */
	public void setEvidenceId(String evidenceId) {
		this.evidenceId = evidenceId;
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
	public String getCallType() {
		return callType;
	}

	/**
	 * @param callType
	 *            the callType to set
	 */
	public void setCallType(String callType) {
		this.callType = callType;
	}

	/**
	 * @return the voiceType
	 */
	public String getVoiceType() {
		return voiceType;
	}

	/**
	 * @param voiceType
	 *            the voiceType to set
	 */
	public void setVoiceType(String voiceType) {
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

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

}
