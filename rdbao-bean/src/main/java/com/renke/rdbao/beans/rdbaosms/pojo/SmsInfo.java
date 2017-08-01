package com.renke.rdbao.beans.rdbaosms.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2017-1-19 下午6:45:31
 * @version 1.0
 */
@Table(name = "SMSInfo")
public class SmsInfo extends BasePo {
	public static final String FIELD_ID = "id";
	public static final String FIELD_ACCOUNTID = "accountId";
	public static final String FIELD_CONTENT = "content";
	public static final String FIELD_SENDTIME = "sendTime";
	public static final String FIELD_SEQUENCENUMBER = "sequenceNumber";
	public static final String FIELD_MSGID = "msgId";
	public static final String FIELD_SUBMITMSGRESPRESULT = "submitMsgRespResult";
	public static final String FIELD_APPSERINUMBER = "appSeriNumber";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_UPDATETIME = "updateTime";
	public static final String FIELD_DELETED = "deleted";
	public static final String FIELD_SDKSUBCODE = "sdkSubCode";
	public static final String FIELD_AUTOREPLYSMSY = "autoReplySmsY";
	public static final String FIELD_AUTOREPLYSMSN = "autoReplySmsN";
	public static final String FIELD_REPLYOVERDUETIME = "replyOverdueTime";
	public static final String FIELD_REPLYSMSCALLBACKURL = "replySmsCallbackUrl";

	public static final String COLUMN_ID = "Id";
	public static final String COLUMN_ACCOUNTID = "AccountId";
	public static final String COLUMN_CONTENT = "Content";
	public static final String COLUMN_SENDTIME = "SendTime";
	public static final String COLUMN_SEQUENCENUMBER = "SequenceNumber";
	public static final String COLUMN_MSGID = "MsgId";
	public static final String COLUMN_SUBMITMSGRESPRESULT = "SubmitMsgRespResult";
	public static final String COLUMN_APPSERINUMBER = "AppSeriNumber";
	public static final String COLUMN_CREATETIME = "CreateTime";
	public static final String COLUMN_UPDATETIME = "UpdateTime";
	public static final String COLUMN_DELETED = "Deleted";
	public static final String COLUMN_SDKSUBCODE = "SdkSubCode";
	public static final String COLUMN_AUTOREPLYSMSY = "AutoReplySmsY";
	public static final String COLUMN_AUTOREPLYSMSN = "AutoReplySmsN";
	public static final String COLUMN_REPLYOVERDUETIME = "ReplyOverdueTime";
	public static final String COLUMN_REPLYSMSCALLBACKURL = "ReplySmsCallbackUrl";

	@javax.persistence.Id
	@Column(name = "Id")
	private String id;
	@Column(name = "AccountId")
	private String accountId;
	@Column(name = "Content")
	private String content;
	@Column(name = "SendTime")
	private Date sendTime;
	@Column(name = "SequenceNumber")
	private Integer sequenceNumber;
	@Column(name = "MsgId")
	private Long msgId;
	@Column(name = "SubmitMsgRespResult")
	private Short submitMsgRespResult;
	@Column(name = "AppSeriNumber")
	private String appSeriNumber;
	@Column(name = "CreateTime")
	private Date createTime;
	@Column(name = "UpdateTime")
	private Date updateTime;
	@Column(name = "Deleted")
	private Short deleted;
	@Column(name = "SdkSubCode")
	private String sdkSubCode;
	@Column(name = "AutoReplySmsY")
	private String autoReplySmsY;
	@Column(name = "AutoReplySmsN")
	private String autoReplySmsN;
	@Column(name = "ReplyOverdueTime")
	private Date replyOverdueTime;
	@Column(name = "ReplySmsCallbackUrl")
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
	public Short getDeleted() {
		return deleted;
	}

	/**
	 * @param deleted
	 *            the deleted to set
	 */
	public void setDeleted(Short deleted) {
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
