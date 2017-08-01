package com.renke.rdbao.beans.rdbaosms.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2017-1-19 下午7:19:58
 * @version 1.0
 */
@Table(name = "SMSDetail")
public class SmsDetail extends BasePo {
	public static final String FIELD_ID = "id";
	public static final String FIELD_SMSINFOID = "smsInfoId";
	public static final String FIELD_RECIVERNUMBER = "reciverNumber";
	public static final String FIELD_DELIVERSTATE = "deliverState";
	public static final String FIELD_SUBMITTIME = "submitTime";
	public static final String FIELD_DOTIME = "doTime";
	public static final String FIELD_REPLYSMSID = "replySmsId";
	public static final String FIELD_REPLAYVALIDINFO = "replayValidInfo";
	public static final String FIELD_REPLYSTAT = "replyStat";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_SYSREPLYCONFIRM = "sysReplyConfirm";
	public static final String FIELD_UPDATETIME = "updateTime";
	public static final String FIELD_DELIVERDESC = "deliverDesc";

	public static final String COLUMN_ID = "Id";
	public static final String COLUMN_SMSINFOID = "SmsInfoId";
	public static final String COLUMN_RECIVERNUMBER = "ReciverNumber";
	public static final String COLUMN_DELIVERSTATE = "DeliverState";
	public static final String COLUMN_SUBMITTIME = "SubmitTime";
	public static final String COLUMN_DOTIME = "DoTime";
	public static final String COLUMN_REPLYSMSID = "ReplySmsId";
	public static final String COLUMN_REPLAYVALIDINFO = "ReplayValidInfo";
	public static final String COLUMN_REPLYSTAT = "ReplyStat";
	public static final String COLUMN_CREATETIME = "CreateTime";
	public static final String COLUMN_SYSREPLYCONFIRM = "SysReplyConfirm";
	public static final String COLUMN_UPDATETIME = "UpdateTime";
	public static final String COLUMN_DELIVERDESC = "DeliverDesc";

	@javax.persistence.Id
	@Column(name = "Id")
	private String id;
	@Column(name = "SmsInfoId")
	private String smsInfoId;
	@Column(name = "ReciverNumber")
	private String reciverNumber;
	@Column(name = "DeliverState")
	private String deliverState;
	@Column(name = "SubmitTime")
	private Date submitTime;
	@Column(name = "DoTime")
	private Date doTime;
	@Column(name = "ReplySmsId")
	private String replySmsId;
	@Column(name = "ReplayValidInfo")
	private String replayValidInfo;
	@Column(name = "ReplyStat")
	private Short replyStat;
	@Column(name = "CreateTime")
	private Date createTime;
	@Column(name = "SysReplyConfirm")
	private Short sysReplyConfirm;
	@Column(name = "UpdateTime")
	private Date updateTime;
	@Column(name = "DeliverDesc")
	private String deliverDesc;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the smsInfoId
	 */
	public String getSmsInfoId() {
		return smsInfoId;
	}

	/**
	 * @param smsInfoId
	 *            the smsInfoId to set
	 */
	public void setSmsInfoId(String smsInfoId) {
		this.smsInfoId = smsInfoId;
	}

	/**
	 * @return the reciverNumber
	 */
	public String getReciverNumber() {
		return reciverNumber;
	}

	/**
	 * @param reciverNumber
	 *            the reciverNumber to set
	 */
	public void setReciverNumber(String reciverNumber) {
		this.reciverNumber = reciverNumber;
	}

	/**
	 * @return the deliverState
	 */
	public String getDeliverState() {
		return deliverState;
	}

	/**
	 * @param deliverState
	 *            the deliverState to set
	 */
	public void setDeliverState(String deliverState) {
		this.deliverState = deliverState;
	}

	/**
	 * @return the submitTime
	 */
	public Date getSubmitTime() {
		return submitTime;
	}

	/**
	 * @param submitTime
	 *            the submitTime to set
	 */
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	/**
	 * @return the doTime
	 */
	public Date getDoTime() {
		return doTime;
	}

	/**
	 * @param doTime
	 *            the doTime to set
	 */
	public void setDoTime(Date doTime) {
		this.doTime = doTime;
	}

	/**
	 * @return the replySmsId
	 */
	public String getReplySmsId() {
		return replySmsId;
	}

	/**
	 * @param replySmsId
	 *            the replySmsId to set
	 */
	public void setReplySmsId(String replySmsId) {
		this.replySmsId = replySmsId;
	}

	/**
	 * @return the replayValidInfo
	 */
	public String getReplayValidInfo() {
		return replayValidInfo;
	}

	/**
	 * @param replayValidInfo
	 *            the replayValidInfo to set
	 */
	public void setReplayValidInfo(String replayValidInfo) {
		this.replayValidInfo = replayValidInfo;
	}

	/**
	 * @return the replyStat
	 */
	public Short getReplyStat() {
		return replyStat;
	}

	/**
	 * @param replyStat
	 *            the replyStat to set
	 */
	public void setReplyStat(Short replyStat) {
		this.replyStat = replyStat;
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
	 * @return the sysReplyConfirm
	 */
	public Short getSysReplyConfirm() {
		return sysReplyConfirm;
	}

	/**
	 * @param sysReplyConfirm
	 *            the sysReplyConfirm to set
	 */
	public void setSysReplyConfirm(Short sysReplyConfirm) {
		this.sysReplyConfirm = sysReplyConfirm;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the deliverDesc
	 */
	public String getDeliverDesc() {
		return deliverDesc;
	}

	/**
	 * @param deliverDesc
	 *            the deliverDesc to set
	 */
	public void setDeliverDesc(String deliverDesc) {
		this.deliverDesc = deliverDesc;
	}

}
