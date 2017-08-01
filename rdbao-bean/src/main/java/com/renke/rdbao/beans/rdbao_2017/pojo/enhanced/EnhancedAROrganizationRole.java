package com.renke.rdbao.beans.rdbao_2017.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.enums.TenantEnum;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_2017.pojo.AROrganizationRole;

/**
 * @author jgshun
 * @date 2017-4-11 下午3:43:19
 * @version 1.0
 */
public class EnhancedAROrganizationRole extends BaseEnhanced {
	public EnhancedAROrganizationRole() {
	}

	public EnhancedAROrganizationRole(AROrganizationRole organizationRole) {
		BeanUtils.copyProperties(organizationRole, this);
		if (organizationRole.getOrganizationId() != null && organizationRole.getOrganizationId().length() > 0) {
			EnhancedAOrganization _EnhancedAOrganization = new EnhancedAOrganization();
			_EnhancedAOrganization.setId(organizationRole.getOrganizationId());
			this.enhancedAOrganization = _EnhancedAOrganization;
		}
		if (organizationRole.getRoleId() != null && organizationRole.getRoleId().length() > 0) {
			EnhancedARole _EnhancedARole = new EnhancedARole();
			_EnhancedARole.setId(organizationRole.getRoleId());
			this.enhancedARole = _EnhancedARole;
		}
		if (organizationRole.getTenantCode() != null) {
			this.tenant = TenantEnum.getTenantEnumByCode(organizationRole.getTenantCode());
		}
	}

	private String id;
	private EnhancedAOrganization enhancedAOrganization;
	private EnhancedARole enhancedARole;
	private Date createTime;
	private String createUserId;
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
	 * @return the enhancedARole
	 */
	public EnhancedARole getEnhancedARole() {
		return enhancedARole;
	}

	/**
	 * @param enhancedARole
	 *            the enhancedARole to set
	 */
	public void setEnhancedARole(EnhancedARole enhancedARole) {
		this.enhancedARole = enhancedARole;
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
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId
	 *            the createUserId to set
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public TenantEnum getTenant() {
		return tenant;
	}

	public void setTenant(TenantEnum tenant) {
		this.tenant = tenant;
	}

}
