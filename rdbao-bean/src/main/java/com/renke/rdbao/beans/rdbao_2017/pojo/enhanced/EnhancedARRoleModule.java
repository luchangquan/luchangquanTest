package com.renke.rdbao.beans.rdbao_2017.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.enums.TenantEnum;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_2017.pojo.ARRoleModule;

/**
 * @author jgshun
 * @date 2017-4-11 下午3:35:44
 * @version 1.0
 */
public class EnhancedARRoleModule extends BaseEnhanced {
	public EnhancedARRoleModule() {
	}

	public EnhancedARRoleModule(ARRoleModule roleModule) {
		BeanUtils.copyProperties(roleModule, this);
		if (roleModule.getRoleId() != null && roleModule.getRoleId().length() > 0) {
			EnhancedARole _EnhancedARole = new EnhancedARole();
			_EnhancedARole.setId(roleModule.getRoleId());
			this.enhancedARole = _EnhancedARole;
		}
		if (roleModule.getModuleId() != null && roleModule.getModuleId().length() > 0) {
			EnhancedAModule _EnhancedAModule = new EnhancedAModule();
			_EnhancedAModule.setId(roleModule.getModuleId());
			this.enhancedAModule = _EnhancedAModule;
		}
		if (roleModule.getTenantCode() != null) {
			this.tenant = TenantEnum.getTenantEnumByCode(roleModule.getTenantCode());
		}
	}

	private String id;
	private EnhancedARole enhancedARole;
	private EnhancedAModule enhancedAModule;
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
