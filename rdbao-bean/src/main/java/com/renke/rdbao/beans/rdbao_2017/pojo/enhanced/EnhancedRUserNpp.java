package com.renke.rdbao.beans.rdbao_2017.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.enums.TenantEnum;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_2017.enums.forrusenpp.StatusEnum4RUserNpp;
import com.renke.rdbao.beans.rdbao_2017.pojo.RUserNpp;

/**
 * @author jgshun
 * @date 2017-4-18 上午11:40:05
 * @version 1.0
 */
public class EnhancedRUserNpp extends BaseEnhanced {
	public EnhancedRUserNpp() {
	}

	public EnhancedRUserNpp(RUserNpp rUserNpp) {
		BeanUtils.copyProperties(rUserNpp, this);
		if (rUserNpp.getNppCode() != null && rUserNpp.getNppCode().length() > 0) {
			EnhancedDNpp _EnhancedDNpp = new EnhancedDNpp();
			_EnhancedDNpp.setCode(rUserNpp.getNppCode());
			this.enhancedDNpp = _EnhancedDNpp;
		}
		if (rUserNpp.getStatus() != null) {
			this.status = StatusEnum4RUserNpp.getStatusEnumByCode(rUserNpp.getStatus());
		}
		if (rUserNpp.getTenantCode() != null) {
			this.tenant = TenantEnum.getTenantEnumByCode(rUserNpp.getTenantCode());
		}
	}

	private String id;
	private String userId;
	private BaseEnhanced enhancedUser;
	private EnhancedDNpp enhancedDNpp;
	private StatusEnum4RUserNpp status;
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
	 * @return the enhancedDNpp
	 */
	public EnhancedDNpp getEnhancedDNpp() {
		return enhancedDNpp;
	}

	/**
	 * @param enhancedDNpp
	 *            the enhancedDNpp to set
	 */
	public void setEnhancedDNpp(EnhancedDNpp enhancedDNpp) {
		this.enhancedDNpp = enhancedDNpp;
	}

	/**
	 * @return the status
	 */
	public StatusEnum4RUserNpp getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(StatusEnum4RUserNpp status) {
		this.status = status;
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
