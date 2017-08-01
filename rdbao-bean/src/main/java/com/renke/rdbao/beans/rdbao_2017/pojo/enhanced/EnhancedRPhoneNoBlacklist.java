package com.renke.rdbao.beans.rdbao_2017.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_2017.enums.forrphonenoblacklist.StatusEnum4RPhoneNoBlacklist;
import com.renke.rdbao.beans.rdbao_2017.pojo.RPhoneNoBlacklist;

public class EnhancedRPhoneNoBlacklist extends BaseEnhanced {

	public EnhancedRPhoneNoBlacklist() {
	}

	public EnhancedRPhoneNoBlacklist(RPhoneNoBlacklist phoneNoBlacklist) {
		BeanUtils.copyProperties(phoneNoBlacklist, this);
		if (phoneNoBlacklist.getStatus() != null) {
			this.status = StatusEnum4RPhoneNoBlacklist.getStatusEnumByCode(phoneNoBlacklist.getStatus());
		}

	}

	private String id;
	private String phoneNo;
	private String targetPhoneNo;
	private StatusEnum4RPhoneNoBlacklist status;
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

	public StatusEnum4RPhoneNoBlacklist getStatus() {
		return status;
	}

	public void setStatus(StatusEnum4RPhoneNoBlacklist status) {
		this.status = status;
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

	public String getTargetUsername() {
		return targetUsername;
	}

	public void setTargetUsername(String targetUsername) {
		this.targetUsername = targetUsername;
	}

}
