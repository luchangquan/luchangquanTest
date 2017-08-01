package com.renke.rdbao.beans.rdbao_2017.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.enums.TenantEnum;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_2017.pojo.ARole;

/**
 * @author jgshun
 * @date 2017-4-11 下午3:31:39
 * @version 1.0
 */
public class EnhancedARole extends BaseEnhanced {
	public EnhancedARole() {
	}

	public EnhancedARole(ARole role) {
		BeanUtils.copyProperties(role, this);
		if (role.getTenantCode() != null) {
			this.tenant = TenantEnum.getTenantEnumByCode(role.getTenantCode());
		}
	}

	private String id;
	private String name;
	private String code;
	private String createUserId;
	private Date createTime;
	private Date updateTime;
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

	public TenantEnum getTenant() {
		return tenant;
	}

	public void setTenant(TenantEnum tenant) {
		this.tenant = tenant;
	}

}
