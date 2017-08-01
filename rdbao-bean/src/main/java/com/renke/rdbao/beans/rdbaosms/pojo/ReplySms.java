package com.renke.rdbao.beans.rdbaosms.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2017-1-19 下午7:30:37
 * @version 1.0
 */
@Table(name = "ReplySms")
public class ReplySms extends BasePo {

	public static final String FIELD_ID = "id";
	public static final String FIELD_SENDNUMBER = "sendNumber";
	public static final String FIELD_RECIVERNUMBER = "reciverNumber";
	public static final String FIELD_CONTENT = "content";
	public static final String FIELD_RECIVERTIME = "reciverTime";
	public static final String FIELD_SRCTERMTYPE = "srcTermType";
	public static final String FIELD_MSGID = "msgId";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_UPDATETIME = "updateTime";

	public static final String COLUMN_ID = "Id";
	public static final String COLUMN_SENDNUMBER = "SendNumber";
	public static final String COLUMN_RECIVERNUMBER = "ReciverNumber";
	public static final String COLUMN_CONTENT = "Content";
	public static final String COLUMN_RECIVERTIME = "ReciverTime";
	public static final String COLUMN_SRCTERMTYPE = "SrcTermType";
	public static final String COLUMN_MSGID = "MsgId";
	public static final String COLUMN_CREATETIME = "CreateTime";
	public static final String COLUMN_UPDATETIME = "UpdateTime";

	@javax.persistence.Id
	@Column(name = "Id")
	private String id;
	@Column(name = "SendNumber")
	private String sendNumber;
	@Column(name = "ReciverNumber")
	private String reciverNumber;
	@Column(name = "Content")
	private String content;
	@Column(name = "ReciverTime")
	private Date reciverTime;
	@Column(name = "SrcTermType")
	private String srcTermType;
	@Column(name = "MsgId")
	private String msgId;
	@Column(name = "CreateTime")
	private Date createTime;
	@Column(name = "UpdateTime")
	private Date updateTime;

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
	 * @return the sendNumber
	 */
	public String getSendNumber() {
		return sendNumber;
	}

	/**
	 * @param sendNumber
	 *            the sendNumber to set
	 */
	public void setSendNumber(String sendNumber) {
		this.sendNumber = sendNumber;
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
	 * @return the reciverTime
	 */
	public Date getReciverTime() {
		return reciverTime;
	}

	/**
	 * @param reciverTime
	 *            the reciverTime to set
	 */
	public void setReciverTime(Date reciverTime) {
		this.reciverTime = reciverTime;
	}

	/**
	 * @return the srcTermType
	 */
	public String getSrcTermType() {
		return srcTermType;
	}

	/**
	 * @param srcTermType
	 *            the srcTermType to set
	 */
	public void setSrcTermType(String srcTermType) {
		this.srcTermType = srcTermType;
	}

	/**
	 * @return the msgId
	 */
	public String getMsgId() {
		return msgId;
	}

	/**
	 * @param msgId
	 *            the msgId to set
	 */
	public void setMsgId(String msgId) {
		this.msgId = msgId;
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

}
