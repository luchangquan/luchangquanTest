package com.renke.rdbao.beans.rdbao_v3.data.web.response;

import java.util.Date;

import com.renke.rdbao.beans.data.response.base.BaseResponseData;

/**
 * 通话记录数据
 * 
 * @author wy
 * 
 */
public class CallLogData extends BaseResponseData {

	private String callType; // 呼叫类型

	private String callOutPhone; // 呼出电话号码
	private String callIncomingPhone; // 呼入电话号码
	private Date recordIngTime; // 录音开始时间
	private Date recordEndTime; // 录音结束时间
	private String conversationTime; // 通话时间
	private String notaryName; // 公证处名称
	private String fileUrl; // 录音文件地址
	private String id; // id

	private String recordIngTimeStr; // 录音开始时间
	private String recordEndTimeStr; // 录音结束时间

	private String evidRecordViewUrl;// 调阅链接

	public String getCallType() {
		return callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}

	public String getCallOutPhone() {
		return callOutPhone;
	}

	public void setCallOutPhone(String callOutPhone) {
		this.callOutPhone = callOutPhone;
	}

	public String getCallIncomingPhone() {
		return callIncomingPhone;
	}

	public void setCallIncomingPhone(String callIncomingPhone) {
		this.callIncomingPhone = callIncomingPhone;
	}

	public Date getRecordIngTime() {
		return recordIngTime;
	}

	public void setRecordIngTime(Date recordIngTime) {
		this.recordIngTime = recordIngTime;
	}

	public Date getRecordEndTime() {
		return recordEndTime;
	}

	public void setRecordEndTime(Date recordEndTime) {
		this.recordEndTime = recordEndTime;
	}

	public String getConversationTime() {
		return conversationTime;
	}

	public void setConversationTime(String conversationTime) {
		this.conversationTime = conversationTime;
	}

	public String getNotaryName() {
		return notaryName;
	}

	public void setNotaryName(String notaryName) {
		this.notaryName = notaryName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRecordIngTimeStr() {
		return recordIngTimeStr;
	}

	public void setRecordIngTimeStr(String recordIngTimeStr) {
		this.recordIngTimeStr = recordIngTimeStr;
	}

	public String getRecordEndTimeStr() {
		return recordEndTimeStr;
	}

	public void setRecordEndTimeStr(String recordEndTimeStr) {
		this.recordEndTimeStr = recordEndTimeStr;
	}

	public String getEvidRecordViewUrl() {
		return evidRecordViewUrl;
	}

	public void setEvidRecordViewUrl(String evidRecordViewUrl) {
		this.evidRecordViewUrl = evidRecordViewUrl;
	}

}
