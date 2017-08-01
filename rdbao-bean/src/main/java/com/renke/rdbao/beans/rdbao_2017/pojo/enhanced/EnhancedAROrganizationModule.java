package com.renke.rdbao.beans.rdbao_2017.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.enums.TenantEnum;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_2017.pojo.AROrganizationModule;

/**
 * @author jgshun
 * @date 2017-4-11 下午3:24:13
 * @version 1.0
 */
public class EnhancedAROrganizationModule extends BaseEnhanced {
	public EnhancedAROrganizationModule() {
	}

	public EnhancedAROrganizationModule(AROrganizationModule organizationModule) {
		BeanUtils.copyProperties(organizationModule, this);
		if (organizationModule.getModuleId() != null && organizationModule.getModuleId().length() > 0) {
			EnhancedAModule _EnhancedAModule = new EnhancedAModule();
			_EnhancedAModule.setId(organizationModule.getModuleId());
			this.enhancedAModule = _EnhancedAModule;
		}
		if (organizationModule.getOrganizationId() != null && organizationModule.getOrganizationId().length() > 0) {
			EnhancedAOrganization _EnhancedAOrganization = new EnhancedAOrganization();
			_EnhancedAOrganization.setId(organizationModule.getOrganizationId());
			this.enhancedAOrganization = _EnhancedAOrganization;
		}
		if (organizationModule.getTenantCode() != null) {
			this.tenant = TenantEnum.getTenantEnumByCode(organizationModule.getTenantCode());
		}
	}

	private String id;;
	private EnhancedAOrganization enhancedAOrganization;
	private EnhancedAModule enhancedAModule;
	private String createUserId;
	private Date createTime;
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
	 * @return the enhancedAModule
	 */
	public EnhancedAModule getEnhancedAModule() {
		return enhancedAModule;
	}

	/**
	 * @param enhancedAModule
	 *            the enhancedAModule to set
	 */
	public void setEnhancedAModule(EnhancedAModule enhancedAModule) {
		this.enhancedAModule = enhancedAModule;
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

	public TenantEnum getTenant() {
		return tenant;
	}

	public void setTenant(TenantEnum tenant) {
		this.tenant = tenant;
	}

}
