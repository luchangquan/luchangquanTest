package com.renke.rdbao.beans.rdbao_2017.data.app.response;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.renke.rdbao.beans.data.response.base.BaseResponseData;

public class ENotarizationReserveResponse extends BaseResponseData {
	private String id;
	private String name;
	private String description;
	private String agentName;
	private String phoneNo;
	private String email;
	private short status;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date createTime;
	private DNppResponse npp;
	private EUserResponse user;
	private List<MEvidenceResponse> items;
	private String reason;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public DNppResponse getNpp() {
		return npp;
	}

	public void setNpp(DNppResponse npp) {
		this.npp = npp;
	}

	public EUserResponse getUser() {
		return user;
	}

	public void setUser(EUserResponse user) {
		this.user = user;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<MEvidenceResponse> getItems() {
		return items;
	}

	public void setItems(List<MEvidenceResponse> items) {
		this.items = items;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
