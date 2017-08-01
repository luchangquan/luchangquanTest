package com.renke.rdbao.beans.rdbao_v3.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_v3.enums.forapps.EnabledEnum4Apps;
import com.renke.rdbao.beans.rdbao_v3.enums.forapps.SelfBillingEnum4Apps;
import com.renke.rdbao.beans.rdbao_v3.pojo.Apps;

/**
 * @author jgshun
 * @date 2017-3-14 下午12:37:09
 * @version 1.0
 */
public class EnhancedApps extends BaseEnhanced {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6248147278879413202L;

	public EnhancedApps() {
	}

	public EnhancedApps(Apps apps) {
		BeanUtils.copyProperties(apps, this);
		if (apps.getEnabled() != null) {
			this.enabled = EnabledEnum4Apps.getEnabledEnumByCode(apps.getEnabled());
		}
		if (apps.getSelfBilling() != null) {
			this.selfBilling = SelfBillingEnum4Apps.getSelfBillingByCode(apps.getSelfBilling());
		}
		if (apps.getParentAppId() != null && apps.getParentAppId().length() > 0) {
			EnhancedApps _EnhancedApps = new EnhancedApps();
			_EnhancedApps.setId(apps.getParentAppId());
			this.enhancedParentApp = _EnhancedApps;
		}
	}

	private String id;
	private String code;;
	private String key;
	private String name;
	private String company;
	private String description;
	private Date createTime;
	private Date lastUpdateTime;
	private EnabledEnum4Apps enabled;
	private SelfBillingEnum4Apps selfBilling;
	private Short pnoHandle;
	private String defaultCategoryId;
	private EnhancedApps enhancedParentApp;

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
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
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
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company
	 *            the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
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
	 * @return the lastUpdateTime
	 */
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	/**
	 * @param lastUpdateTime
	 *            the lastUpdateTime to set
	 */
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	/**
	 * @return the enabled
	 */
	public EnabledEnum4Apps getEnabled() {
		return enabled;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(EnabledEnum4Apps enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the selfBilling
	 */
	public SelfBillingEnum4Apps getSelfBilling() {
		return selfBilling;
	}

	/**
	 * @param selfBilling
	 *            the selfBilling to set
	 */
	public void setSelfBilling(SelfBillingEnum4Apps selfBilling) {
		this.selfBilling = selfBilling;
	}

	/**
	 * @return the pnoHandle
	 */
	public Short getPnoHandle() {
		return pnoHandle;
	}

	/**
	 * @param pnoHandle
	 *            the pnoHandle to set
	 */
	public void setPnoHandle(Short pnoHandle) {
		this.pnoHandle = pnoHandle;
	}

	/**
	 * @return the defaultCategoryId
	 */
	public String getDefaultCategoryId() {
		return defaultCategoryId;
	}

	/**
	 * @param defaultCategoryId
	 *            the defaultCategoryId to set
	 */
	public void setDefaultCategoryId(String defaultCategoryId) {
		this.defaultCategoryId = defaultCategoryId;
	}

	/**
	 * @return the enhancedParentApp
	 */
	public EnhancedApps getEnhancedParentApp() {
		return enhancedParentApp;
	}

	/**
	 * @param enhancedParentApp
	 *            the enhancedParentApp to set
	 */
	public void setEnhancedParentApp(EnhancedApps enhancedParentApp) {
		this.enhancedParentApp = enhancedParentApp;
	}

}
