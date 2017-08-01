package com.renke.rdbao.beans.rdbao_2017.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_2017.enums.foradppversion.AppOsEnum4DAppVersion;
import com.renke.rdbao.beans.rdbao_2017.pojo.DAppVersion;

/**
 * @author jgshun
 * @date 2017-4-11 下午1:39:23
 * @version 1.0
 */
public class EnhancedDAppVersion extends BaseEnhanced {
	public EnhancedDAppVersion() {
	}

	public EnhancedDAppVersion(DAppVersion appVersion) {
		BeanUtils.copyProperties(appVersion, this);
		if (appVersion.getOs() != null && appVersion.getOs().length() > 0) {
			this.os = AppOsEnum4DAppVersion.getAppOsEnumByCode(appVersion.getOs());
		}
	}

	private String id;
	private int version;
	private AppOsEnum4DAppVersion os;
	private String desc;
	private String url;
	private Date createTime;
	private Date updateTime;
	private boolean force;

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
	 * @return the os
	 */
	public AppOsEnum4DAppVersion getOs() {
		return os;
	}

	/**
	 * @param os
	 *            the os to set
	 */
	public void setOs(AppOsEnum4DAppVersion os) {
		this.os = os;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc
	 *            the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
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

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public boolean isForce() {
		return force;
	}

	public void setForce(boolean force) {
		this.force = force;
	}

}
