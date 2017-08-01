package com.renke.rdbao.beans.rdbao_2017.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_2017.enums.forrphonenowhitelist.StatusEnum4RPhoneNoWhitelist;
import com.renke.rdbao.beans.rdbao_2017.pojo.RPhoneNoWhitelist;

public class EnhancedRPhoneNoWhitelist extends BaseEnhanced {

	public EnhancedRPhoneNoWhitelist() {
	}

	public EnhancedRPhoneNoWhitelist(RPhoneNoWhitelist phoneNoWhitelist) {
		BeanUtils.copyProperties(phoneNoWhitelist, this);
		if (phoneNoWhitelist.getStatus() != null) {
			this.setStatus(StatusEnum4RPhoneNoWhitelist.getStatusEnumByCode(phoneNoWhitelist.getStatus()));
		}

	}

	private String id;
	private String phoneNo;
	private String targetPhoneNo;
	private StatusEnum4RPhoneNoWhitelist status;
	private Date createTime;
	private Date updateTime;
	private String operateUserId;
	private String targetUsername;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getTargetPhoneNo() {
		return targetPhoneNo;
	}

	public void setTargetPhoneNo(String targetPhoneNo) {
		this.targetPhoneNo = targetPhoneNo;
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

	public String getOperateUserId() {
		return operateUserId;
	}

	public void setOperateUserId(String operateUserId) {
		this.operateUserId = operateUserId;
	}

	public StatusEnum4RPhoneNoWhitelist getStatus() {
		return status;
	}

	public void setStatus(StatusEnum4RPhoneNoWhitelist status) {
		this.status = status;
	}

	public String getTargetUsername() {
		return targetUsername;
	}

	public void setTargetUsername(String targetUsername) {
		this.targetUsername = targetUsername;
	}

}
