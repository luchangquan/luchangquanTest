package com.renke.rdbao.beans.rdbao_2017.pojo.enhanced;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.enums.TenantEnum;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_2017.pojo.AOrganization;

/**
 * @author jgshun
 * @date 2017-4-7 下午6:03:27
 * @version 1.0
 */
public class EnhancedAOrganization extends BaseEnhanced {
	public EnhancedAOrganization() {
	}

	public EnhancedAOrganization(AOrganization organization) {
		BeanUtils.copyProperties(organization, this);
		if (organization.getParentId() != null && organization.getParentId().trim().length() > 0) {
			EnhancedAOrganization _EnhancedAOrganization = new EnhancedAOrganization();
			_EnhancedAOrganization.setId(organization.getParentId());
			this.enhancedParentAOrganization = _EnhancedAOrganization;
		}
		if (organization.getCompanyId() != null && organization.getCompanyId().length() > 0) {
			EnhancedECompany _EnhancedCompany = new EnhancedECompany();
			_EnhancedCompany.setId(organization.getCompanyId());
			this.enhancedECompany = _EnhancedCompany;
		}
		if (organization.getTenantCode() != null) {
			this.tenant = TenantEnum.getTenantEnumByCode(organization.getTenantCode());
		}
	}

	private String id;
	private String name;
	private String code;
	private EnhancedAOrganization enhancedParentAOrganization;
	private EnhancedECompany enhancedECompany;
	private String userId;// 冗余字段 用来补充用户属于189用户还是实时保用户
	private EnhancedEUser enhancedEUser;// 实时保用户
	private EnhancedEUser189 enhancedEUser189;// 189用户
	private Date createTime;
	private Date updateTime;
	private String createUserId;
	private TenantEnum tenant;
	private String allName;// 冗余字段组织的全名

	private List<EnhancedAOrganization> enhancedChildAOrganizations;
	private List<EnhancedARole> enhancedARoles;
	private List<EnhancedAModule> enhancedAModules;

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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the enhancedParentAOrganization
	 */
	public EnhancedAOrganization getEnhancedParentAOrganization() {
		return enhancedParentAOrganization;
	}

	/**
	 * @param enhancedParentAOrganization
	 *            the enhancedParentAOrganization to set
	 */
	public void setEnhancedParentAOrganization(EnhancedAOrganization enhancedParentAOrganization) {
		this.enhancedParentAOrganization = enhancedParentAOrganization;
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

	public EnhancedEUser189 getEnhancedEUser189() {
		return enhancedEUser189;
	}

	public void setEnhancedEUser189(EnhancedEUser189 enhancedEUser189) {
		this.enhancedEUser189 = enhancedEUser189;
	}

	public List<EnhancedAOrganization> getEnhancedChildAOrganizations() {
		return enhancedChildAOrganizations;
	}

	public void setEnhancedChildAOrganizations(List<EnhancedAOrganization> enhancedChildAOrganizations) {
		this.enhancedChildAOrganizations = enhancedChildAOrganizations;
	}

	public EnhancedEUser getEnhancedEUser() {
		return enhancedEUser;
	}

	public void setEnhancedEUser(EnhancedEUser enhancedEUser) {
		this.enhancedEUser = enhancedEUser;
	}

	public EnhancedECompany getEnhancedECompany() {
		return enhancedECompany;
	}

	public void setEnhancedECompany(EnhancedECompany enhancedECompany) {
		this.enhancedECompany = enhancedECompany;
	}

	public List<EnhancedARole> getEnhancedARoles() {
		return enhancedARoles;
	}

	public void setEnhancedARoles(List<EnhancedARole> enhancedARoles) {
		this.enhancedARoles = enhancedARoles;
	}

	public List<EnhancedAModule> getEnhancedAModules() {
		return enhancedAModules;
	}

	public void setEnhancedAModules(List<EnhancedAModule> enhancedAModules) {
		this.enhancedAModules = enhancedAModules;
	}

	public String getAllName() {
		return allName;
	}

	public void setAllName(String allName) {
		this.allName = allName;
	}

}
