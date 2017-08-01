package com.renke.rdbao.beans.rdbao_2017.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_2017.enums.fordconfigure.StatusEnum4DConfigure;
import com.renke.rdbao.beans.rdbao_2017.pojo.DConfigure;

/**
 * @author jgshun
 * @date 2017-4-11 下午3:50:54
 * @version 1.0
 */
public class EnhancedDConfigure extends BaseEnhanced {
	public EnhancedDConfigure() {
	}

	public EnhancedDConfigure(DConfigure configure) {
		BeanUtils.copyProperties(configure, this);
		if (configure.getStatus() != null) {
			this.status = StatusEnum4DConfigure.getStatusEnumByCode(configure.getStatus());
		}
	}

	private String id;
	private String description;
	private String key;
	private String value;
	private Date createTime;
	private Date updateTime;
	private StatusEnum4DConfigure status;

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
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
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
	 * @return the status
	 */
	public StatusEnum4DConfigure getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(StatusEnum4DConfigure status) {
		this.status = status;
	}

}
