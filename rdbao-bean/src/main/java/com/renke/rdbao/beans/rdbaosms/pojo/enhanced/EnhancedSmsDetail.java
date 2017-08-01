package com.renke.rdbao.beans.rdbaosms.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbaosms.enums.forsmsdetail.SysReplyConfirmEnum4SmsDetail;
import com.renke.rdbao.beans.rdbaosms.pojo.SmsDetail;

/**
 * @author jgshun
 * @date 2017-1-19 下午7:26:19
 * @version 1.0
 */
public class EnhancedSmsDetail extends BaseEnhanced {
	public EnhancedSmsDetail() {
	}

	public EnhancedSmsDetail(SmsDetail smsDetail) {
		BeanUtils.copyProperties(smsDetail, this);
		if (smsDetail.getSmsInfoId() != null && smsDetail.getSmsInfoId().length() > 0) {
			EnhancedSmsInfo _EnhancedSmsInfo = new EnhancedSmsInfo();
			this.enhancedSmsInfo = _EnhancedSmsInfo;
		}
		if (smsDetail.getReplySmsId() != null && smsDetail.getReplySmsId().length() > 0) {
			EnhancedReplySms _EnhancedReplySms = new EnhancedReplySms();
			this.enhancedReplySms = _EnhancedReplySms;
		}
		if (smsDetail.getSysReplyConfirm() != null) {
			this.sysReplyConfirm = SysReplyConfirmEnum4SmsDetail.getSysReplyConfirmEnumByCode(smsDetail.getSysReplyConfirm());
		}
	}

	private String id;
	private EnhancedSmsInfo enhancedSmsInfo;
	private String reciverNumber;
	private String deliverState;
	private Date submitTime;
	private Date doTime;
	private EnhancedReplySms enhancedReplySms;
	private String replayValidInfo;
	private Short replyStat;
	private Date createTime;
	private SysReplyConfirmEnum4SmsDetail sysReplyConfirm;
	private Date updateTime;
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
	 * @return the enhancedSmsInfo
	 */
	public EnhancedSmsInfo getEnhancedSmsInfo() {
		return enhancedSmsInfo;
	}

	/**
	 * @param enhancedSmsInfo
	 *            the enhancedSmsInfo to set
	 */
	public void setEnhancedSmsInfo(EnhancedSmsInfo enhancedSmsInfo) {
		this.enhancedSmsInfo = enhancedSmsInfo;
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
	 * @return the enhancedReplySms
	 */
	public EnhancedReplySms getEnhancedReplySms() {
		return enhancedReplySms;
	}

	/**
	 * @param enhancedReplySms
	 *            the enhancedReplySms to set
	 */
	public void setEnhancedReplySms(EnhancedReplySms enhancedReplySms) {
		this.enhancedReplySms = enhancedReplySms;
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
	public SysReplyConfirmEnum4SmsDetail getSysReplyConfirm() {
		return sysReplyConfirm;
	}

	/**
	 * @param sysReplyConfirm
	 *            the sysReplyConfirm to set
	 */
	public void setSysReplyConfirm(SysReplyConfirmEnum4SmsDetail sysReplyConfirm) {
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
