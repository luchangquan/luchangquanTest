package com.renke.rdbao.beans.rdbaosms.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.rdbaosms.pojo.ReplySms;

/**
 * @author jgshun
 * @date 2017-1-19 下午7:34:07
 * @version 1.0
 */
public class EnhancedReplySms extends BasePo {
	public EnhancedReplySms() {
	}

	public EnhancedReplySms(ReplySms replySms) {
		BeanUtils.copyProperties(replySms, this);
	}

	private String id;
	private String sendNumber;
	private String reciverNumber;
	private String content;
	private Date reciverTime;
	private String srcTermType;
	private String msgId;
	private Date createTime;
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
