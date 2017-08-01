package com.renke.rdbao.beans.rdbao_sms_2017.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.enums.SmsTypeEnum;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_sms_2017.enums.foesmsInfo.StatusEnum4ESmsInfo;
import com.renke.rdbao.beans.rdbao_sms_2017.pojo.ESmsInfo;

public class EnhancedESmsInfo extends BaseEnhanced {
	public EnhancedESmsInfo() {
	}

	public EnhancedESmsInfo(ESmsInfo smsInfo) {
		BeanUtils.copyProperties(smsInfo, this);
		if (smsInfo.getType() != null) {
			this.type = SmsTypeEnum.getSmsTypeEnumByCode(smsInfo.getType());
		}
		if (smsInfo.getStatus() != null) {
			this.status = StatusEnum4ESmsInfo.getStatusEnumByCode(smsInfo.getStatus());
		}
	}

	private String id;
	private String targetPhoneNo;
	private String content;
	private SmsTypeEnum type;
	private Date createTime;
	private Date updateTime;
	private String identificationId;
	private StatusEnum4ESmsInfo status;
	private String extra;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTargetPhoneNo() {
		return targetPhoneNo;
	}

	public void setTargetPhoneNo(String targetPhoneNo) {
		this.targetPhoneNo = targetPhoneNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public SmsTypeEnum getType() {
		return type;
	}

	public void setType(SmsTypeEnum type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getIdentificationId() {
		return identificationId;
	}

	public void setIdentificationId(String identificationId) {
		this.identificationId = identificationId;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public StatusEnum4ESmsInfo getStatus() {
		return status;
	}

	public void setStatus(StatusEnum4ESmsInfo status) {
		this.status = status;
	}

}
