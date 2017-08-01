package com.renke.rdbao.beans.rdbao_2017.pojo.enhanced;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.enums.TenantEnum;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_2017.pojo.AModule;

/**
 * @author jgshun
 * @date 2017-4-11 下午1:39:23
 * @version 1.0
 */
public class EnhancedAModule extends BaseEnhanced {
	public EnhancedAModule() {
	}

	public EnhancedAModule(AModule module) {
		BeanUtils.copyProperties(module, this);
		if (module.getParentId() != null && module.getParentId().length() > 0) {
			EnhancedAModule _EnhancedAModule = new EnhancedAModule();
			_EnhancedAModule.setId(module.getParentId());
			this.enhancedParentAModule = _EnhancedAModule;
		}
		if (module.getTenantCode() != null) {
			this.tenant = TenantEnum.getTenantEnumByCode(module.getTenantCode());
		}
	}

	private String id;
	private String name;
	private String code;
	private EnhancedAModule enhancedParentAModule;
	private String path;
	private String createUserId;
	private Date createTime;
	private Date updateTime;
	private TenantEnum tenant;

	private List<EnhancedAModule> enhancedChildAModules;// 包含的下级资源

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
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
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

	public EnhancedAModule getEnhancedParentAModule() {
		return enhancedParentAModule;
	}

	public void setEnhancedParentAModule(EnhancedAModule enhancedParentAModule) {
		this.enhancedParentAModule = enhancedParentAModule;
	}

	public List<EnhancedAModule> getEnhancedChildAModules() {
		return enhancedChildAModules;
	}

	public void setEnhancedChildAModules(List<EnhancedAModule> enhancedChildAModules) {
		this.enhancedChildAModules = enhancedChildAModules;
	}

	public TenantEnum getTenant() {
		return tenant;
	}

	public void setTenant(TenantEnum tenant) {
		this.tenant = tenant;
	}

}
