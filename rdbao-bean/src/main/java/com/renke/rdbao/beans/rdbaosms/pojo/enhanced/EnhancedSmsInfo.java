package com.renke.rdbao.beans.rdbaosms.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.rdbaosms.enums.forsmsinfo.DeletedEnum4SmsInfo;
import com.renke.rdbao.beans.rdbaosms.pojo.SmsInfo;

/**
 * @author jgshun
 * @date 2017-1-19 下午6:58:43
 * @version 1.0
 */
public class EnhancedSmsInfo extends BasePo {
	public EnhancedSmsInfo() {
	}

	public EnhancedSmsInfo(SmsInfo smsInfo) {
		BeanUtils.copyProperties(smsInfo, this);
		if (smsInfo.getDeleted() != null) {
			this.deleted = DeletedEnum4SmsInfo.getDeletedEnumByCode(smsInfo.getDeleted());
		}
	}

	private String id;
	private String accountId;
	private String content;
	private Date sendTime;
	private Integer sequenceNumber;
	private Long msgId;
	private Short submitMsgRespResult;
	private String appSeriNumber;
	private Date createTime;
	private Date updateTime;
	private DeletedEnum4SmsInfo deleted;
	private String sdkSubCode;
	private String autoReplySmsY;
	private String autoReplySmsN;
	private Date replyOverdueTime;
	private String replySmsCallbackUrl;

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
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId
	 *            the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the sendTime
	 */
	public Date getSendTime() {
		return sendTime;
	}

	/**
	 * @param sendTime
	 *            the sendTime to set
	 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	/**
	 * @return the sequenceNumber
	 */
	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * @param sequenceNumber
	 *            the sequenceNumber to set
	 */
	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	/**
	 * @return the msgId
	 */
	public Long getMsgId() {
		return msgId;
	}

	/**
	 * @param msgId
	 *            the msgId to set
	 */
	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	/**
	 * @return the submitMsgRespResult
	 */
	public Short getSubmitMsgRespResult() {
		return submitMsgRespResult;
	}

	/**
	 * @param submitMsgRespResult
	 *            the submitMsgRespResult to set
	 */
	public void setSubmitMsgRespResult(Short submitMsgRespResult) {
		this.submitMsgRespResult = submitMsgRespResult;
	}

	/**
	 * @return the appSeriNumber
	 */
	public String getAppSeriNumber() {
		return appSeriNumber;
	}

	/**
	 * @param appSeriNumber
	 *            the appSeriNumber to set
	 */
	public void setAppSeriNumber(String appSeriNumber) {
		this.appSeriNumber = appSeriNumber;
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
	 * @return the deleted
	 */
	public DeletedEnum4SmsInfo getDeleted() {
		return deleted;
	}

	/**
	 * @param deleted
	 *            the deleted to set
	 */
	public void setDeleted(DeletedEnum4SmsInfo deleted) {
		this.deleted = deleted;
	}

	/**
	 * @return the sdkSubCode
	 */
	public String getSdkSubCode() {
		return sdkSubCode;
	}

	/**
	 * @param sdkSubCode
	 *            the sdkSubCode to set
	 */
	public void setSdkSubCode(String sdkSubCode) {
		this.sdkSubCode = sdkSubCode;
	}

	/**
	 * @return the autoReplySmsY
	 */
	public String getAutoReplySmsY() {
		return autoReplySmsY;
	}

	/**
	 * @param autoReplySmsY
	 *            the autoReplySmsY to set
	 */
	public void setAutoReplySmsY(String autoReplySmsY) {
		this.autoReplySmsY = autoReplySmsY;
	}

	/**
	 * @return the autoReplySmsN
	 */
	public String getAutoReplySmsN() {
		return autoReplySmsN;
	}

	/**
	 * @param autoReplySmsN
	 *            the autoReplySmsN to set
	 */
	public void setAutoReplySmsN(String autoReplySmsN) {
		this.autoReplySmsN = autoReplySmsN;
	}

	/**
	 * @return the replyOverdueTime
	 */
	public Date getReplyOverdueTime() {
		return replyOverdueTime;
	}

	/**
	 * @param replyOverdueTime
	 *            the replyOverdueTime to set
	 */
	public void setReplyOverdueTime(Date replyOverdueTime) {
		this.replyOverdueTime = replyOverdueTime;
	}

	/**
	 * @return the replySmsCallbackUrl
	 */
	public String getReplySmsCallbackUrl() {
		return replySmsCallbackUrl;
	}

	/**
	 * @param replySmsCallbackUrl
	 *            the replySmsCallbackUrl to set
	 */
	public void setReplySmsCallbackUrl(String replySmsCallbackUrl) {
		this.replySmsCallbackUrl = replySmsCallbackUrl;
	}

}
