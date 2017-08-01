package com.renke.rdbao.beans.rdbao_2017.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.enums.TenantEnum;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_2017.enums.forruserorganizationhistory.StatusEnum4RUserOrganizationHistory;
import com.renke.rdbao.beans.rdbao_2017.pojo.RUserOrganizationHistory;

/**
 * @author jgshun
 * @date 2017-4-11 下午4:49:37
 * @version 1.0
 */
public class EnhancedRUserOrganizationHistory extends BaseEnhanced {
	public EnhancedRUserOrganizationHistory() {
	}

	public EnhancedRUserOrganizationHistory(RUserOrganizationHistory userOrganizationHistory) {
		BeanUtils.copyProperties(userOrganizationHistory, this);
		if (userOrganizationHistory.getOrganizationId() != null && userOrganizationHistory.getOrganizationId().length() > 0) {
			EnhancedAOrganization _EnhancedAOrganization = new EnhancedAOrganization();
			_EnhancedAOrganization.setId(userOrganizationHistory.getOrganizationId());
			this.enhancedAOrganization = _EnhancedAOrganization;
		}
		if (userOrganizationHistory.getStatus() != null) {
			this.status = StatusEnum4RUserOrganizationHistory.getStatusEnumByCode(userOrganizationHistory.getStatus());
		}
		if (userOrganizationHistory.getTenantCode() != null) {
			this.tenant = TenantEnum.getTenantEnumByCode(userOrganizationHistory.getTenantCode());
		}
	}

	private String id;
	private String userId;
	private EnhancedEUser enhancedEUser;// 实时保用户
	private EnhancedAOrganization enhancedAOrganization;
	private Date startTime;
	private Date endTime;
	private StatusEnum4RUserOrganizationHistory status;
	private TenantEnum tenant;

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
	 * @return the enhancedEUser
	 */
	public EnhancedEUser getEnhancedEUser() {
		return enhancedEUser;
	}

	/**
	 * @param enhancedEUser
	 *            the enhancedEUser to set
	 */
	public void setEnhancedEUser(EnhancedEUser enhancedEUser) {
		this.enhancedEUser = enhancedEUser;
	}

	/**
	 * @return the enhancedAOrganization
	 */
	public EnhancedAOrganization getEnhancedAOrganization() {
		return enhancedAOrganization;
	}

	/**
	 * @param enhancedAOrganization
	 *            the enhancedAOrganization to set
	 */
	public void setEnhancedAOrganization(EnhancedAOrganization enhancedAOrganization) {
		this.enhancedAOrganization = enhancedAOrganization;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the status
	 */
	public StatusEnum4RUserOrganizationHistory getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(StatusEnum4RUserOrganizationHistory status) {
		this.status = status;
	}

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

}
