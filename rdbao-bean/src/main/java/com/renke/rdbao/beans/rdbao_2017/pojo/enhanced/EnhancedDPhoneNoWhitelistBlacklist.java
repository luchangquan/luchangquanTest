package com.renke.rdbao.beans.rdbao_2017.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_2017.enums.fordphonenowhitelistblacklist.StatusEnum4DPhoneNoWhitelistBlacklist;
import com.renke.rdbao.beans.rdbao_2017.enums.fordphonenowhitelistblacklist.TypeEnum4DPhoneNoWhitelistBlacklist;
import com.renke.rdbao.beans.rdbao_2017.pojo.DPhoneNoWhitelistBlacklist;

public class EnhancedDPhoneNoWhitelistBlacklist extends BaseEnhanced {

	public EnhancedDPhoneNoWhitelistBlacklist() {
	}

	public EnhancedDPhoneNoWhitelistBlacklist(DPhoneNoWhitelistBlacklist phoneNoWhitelistBlacklist) {
		BeanUtils.copyProperties(phoneNoWhitelistBlacklist, this);
		if (phoneNoWhitelistBlacklist.getType() != null) {
			this.type = TypeEnum4DPhoneNoWhitelistBlacklist.getTypeEnumByCode(phoneNoWhitelistBlacklist.getType());
		}
		if (phoneNoWhitelistBlacklist.getStatus() != null) {
			this.status = StatusEnum4DPhoneNoWhitelistBlacklist.getStatusEnumByCode(phoneNoWhitelistBlacklist.getStatus());
		}

	}

	private String id;
	private String phoneNo;
	private TypeEnum4DPhoneNoWhitelistBlacklist type;
	private StatusEnum4DPhoneNoWhitelistBlacklist status;
	private Date createTime;
	private Date updateTime;
	private String operateUserId;

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

	public TypeEnum4DPhoneNoWhitelistBlacklist getType() {
		return type;
	}

	public void setType(TypeEnum4DPhoneNoWhitelistBlacklist type) {
		this.type = type;
	}

	public StatusEnum4DPhoneNoWhitelistBlacklist getStatus() {
		return status;
	}

	public void setStatus(StatusEnum4DPhoneNoWhitelistBlacklist status) {
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

}
