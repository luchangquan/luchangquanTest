package com.renke.rdbao.beans.rdbao_2017.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2017-4-10 下午5:44:11
 * @version 1.0
 */
@Table(name = "m_evidence_telecom_voice")
public class MEvidenceTelecomVoice extends BasePo {
	public static final String FIELD_EVIDENCEID = "evidenceId";
	public static final String FIELD_CALLINGNO = "callingNo";
	public static final String FIELD_CALLEDNO = "calledNo";
	public static final String FIELD_CALLTYPE = "callType";
	public static final String FIELD_CALLTIME = "callTime";
	public static final String FIELD_DURATION = "duration";
	public static final String FIELD_CREATETIME = "createTime";

	public static final String COLUMN_EVIDENCE_ID = "evidence_id";
	public static final String COLUMN_CALLING_NO = "calling_no";
	public static final String COLUMN_CALLED_NO = "called_no";
	public static final String COLUMN_CALL_TYPE = "call_type";
	public static final String COLUMN_CALL_TIME = "call_time";
	public static final String COLUMN_DURATION = "duration";
	public static final String COLUMN_CREATE_TIME = "create_time";

	public static final String ORDER_CREATE_TIME_DESC = " create_time desc ";

	@Id
	@Column(name = "evidence_id")
	private String evidenceId;
	@Column(name = "calling_no")
	private String callingNo;
	@Column(name = "called_no")
	private String calledNo;
	@Column(name = "call_type")
	private Short callType;
	@Column(name = "call_time")
	private Date callTime;
	@Column(name = "duration")
	private Long duration;
	@Column(name = "create_time")
	private Date createTime;

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
	public Short getCallType() {
		return callType;
	}

	/**
	 * @param callType
	 *            the callType to set
	 */
	public void setCallType(Short callType) {
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

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

}
