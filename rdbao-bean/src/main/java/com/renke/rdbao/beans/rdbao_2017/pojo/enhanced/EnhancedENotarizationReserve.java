package com.renke.rdbao.beans.rdbao_2017.pojo.enhanced;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.enums.TenantEnum;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_2017.enums.forenotarizationreserve.StatusEnum4ENotarizationReserve;
import com.renke.rdbao.beans.rdbao_2017.pojo.ENotarizationReserve;

/**
 * @author jgshun
 * @date 2017-4-13 下午6:02:22
 * @version 1.0
 */
public class EnhancedENotarizationReserve extends BaseEnhanced {
	public EnhancedENotarizationReserve() {
	}

	public EnhancedENotarizationReserve(ENotarizationReserve notarizationReserve) {
		BeanUtils.copyProperties(notarizationReserve, this);
		if (notarizationReserve.getStatus() != null) {
			this.status = StatusEnum4ENotarizationReserve.getStateEnumByCode(notarizationReserve.getStatus());
		}
		if (notarizationReserve.getNppCode() != null && notarizationReserve.getNppCode().length() > 0) {
			EnhancedDNpp _EnhancedDNpp = new EnhancedDNpp();
			_EnhancedDNpp.setCode(notarizationReserve.getNppCode());
			this.enhancedDNpp = _EnhancedDNpp;
		}

		if (notarizationReserve.getTenantCode() != null) {
			this.tenant = TenantEnum.getTenantEnumByCode(notarizationReserve.getTenantCode());
		}

	}

	private String id;
	private String name;
	private String description;
	private String phoneNo;
	private String email;
	private StatusEnum4ENotarizationReserve status;
	private Date createTime;
	private Date updateTime;
	private EnhancedDNpp enhancedDNpp;
	private TenantEnum tenant;
	private String userId;// 冗余字段 根据用户来源区分189用户还是实时保用户
	private BaseEnhanced enhancedUser;
	private String agentName;
	private String reason;
	private String notarySubject;

	private List<EnhancedMEvidence> enhancedMEvidences;// 预约的证据列表

	/**
	 * @return the tenant
	 */
	public TenantEnum getTenant() {
		return tenant;
	}

	/**
	 * @param tenant
	 *            the tenant to set
	 */
	public void setTenant(TenantEnum tenant) {
		this.tenant = tenant;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the enhancedUser
	 */
	public BaseEnhanced getEnhancedUser() {
		return enhancedUser;
	}

	/**
	 * @param enhancedUser
	 *            the enhancedUser to set
	 */
	public void setEnhancedUser(BaseEnhanced enhancedUser) {
		this.enhancedUser = enhancedUser;
	}

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * @param phoneNo
	 *            the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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

	public List<EnhancedMEvidence> getEnhancedMEvidences() {
		return enhancedMEvidences;
	}

	public void setEnhancedMEvidences(List<EnhancedMEvidence> enhancedMEvidences) {
		this.enhancedMEvidences = enhancedMEvidences;
	}

	public EnhancedDNpp getEnhancedDNpp() {
		return enhancedDNpp;
	}

	public void setEnhancedDNpp(EnhancedDNpp enhancedDNpp) {
		this.enhancedDNpp = enhancedDNpp;
	}

	public StatusEnum4ENotarizationReserve getStatus() {
		return status;
	}

	public void setStatus(StatusEnum4ENotarizationReserve status) {
		this.status = status;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getNotarySubject() {
		return notarySubject;
	}

	public void setNotarySubject(String notarySubject) {
		this.notarySubject = notarySubject;
	}

}
