package com.renke.rdbao.beans.rdbao_v3.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_v3.pojo.PNOProApp;

/**
 * @author jgshun
 * @date 2017-3-14 下午1:40:34
 * @version 1.0
 */
public class EnhancedPNOProApp extends BaseEnhanced {
	public EnhancedPNOProApp() {
	}

	public EnhancedPNOProApp(PNOProApp pnoProApp) {
		BeanUtils.copyProperties(pnoProApp, this);
		if (pnoProApp.getProAppId() != null && pnoProApp.getProAppId().length() > 0) {
			EnhancedProductApp _EnhancedProductApp = new EnhancedProductApp();
			_EnhancedProductApp.setId(pnoProApp.getProAppId());
			this.enhancedProductApp = _EnhancedProductApp;
		}
		if (pnoProApp.getPnoCode() != null && pnoProApp.getPnoCode().length() > 0) {
			EnhancedPNOes _EnhancedPNOes = new EnhancedPNOes();
			_EnhancedPNOes.setCode(pnoProApp.getPnoCode());
			this.enhancedPnoes = _EnhancedPNOes;
		}
	}

	private String id;
	private EnhancedProductApp enhancedProductApp;
	private EnhancedPNOes enhancedPnoes;
	private Short openState;
	private String createUser;
	private Date createTime;
	private String updateUser;
	private Date updateTime;

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
	 * @return the enhancedProductApp
	 */
	public EnhancedProductApp getEnhancedProductApp() {
		return enhancedProductApp;
	}

	/**
	 * @param enhancedProductApp
	 *            the enhancedProductApp to set
	 */
	public void setEnhancedProductApp(EnhancedProductApp enhancedProductApp) {
		this.enhancedProductApp = enhancedProductApp;
	}

	/**
	 * @return the enhancedPnoes
	 */
	public EnhancedPNOes getEnhancedPnoes() {
		return enhancedPnoes;
	}

	/**
	 * @param enhancedPnoes
	 *            the enhancedPnoes to set
	 */
	public void setEnhancedPnoes(EnhancedPNOes enhancedPnoes) {
		this.enhancedPnoes = enhancedPnoes;
	}

	/**
	 * @return the openState
	 */
	public Short getOpenState() {
		return openState;
	}

	/**
	 * @param openState
	 *            the openState to set
	 */
	public void setOpenState(Short openState) {
		this.openState = openState;
	}

	/**
	 * @return the createUser
	 */
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * @param createUser
	 *            the createUser to set
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
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
	 * @return the updateUser
	 */
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * @param updateUser
	 *            the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
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

}
