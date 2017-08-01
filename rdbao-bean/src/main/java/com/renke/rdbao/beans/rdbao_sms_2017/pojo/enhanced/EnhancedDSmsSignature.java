package com.renke.rdbao.beans.rdbao_sms_2017.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_sms_2017.enums.fordsmssignature.StatusEnum4DSmsSignature;
import com.renke.rdbao.beans.rdbao_sms_2017.pojo.DSmsSignature;

public class EnhancedDSmsSignature extends BaseEnhanced {
	public EnhancedDSmsSignature() {
	}

	public EnhancedDSmsSignature(DSmsSignature smsSignature) {
		BeanUtils.copyProperties(smsSignature, this);
		if (smsSignature.getStatus() != null) {
			this.status = StatusEnum4DSmsSignature.getStatusEnumByCode(smsSignature.getStatus());
		}
	}

	private String id;
	private String name;
	private Date createTime;
	private Date updateTime;
	private String code;
	private StatusEnum4DSmsSignature status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
