package com.renke.rdbao.beans.rdbao_2017.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_2017.enums.fordevidencesource.StatusEnum4DEvidenceSource;
import com.renke.rdbao.beans.rdbao_2017.pojo.DEvidenceSource;

/**
 * @author jgshun
 * @date 2017-4-10 下午4:55:20
 * @version 1.0
 */
public class EnhancedDEvidenceSource extends BaseEnhanced {
	public EnhancedDEvidenceSource() {
	}

	public EnhancedDEvidenceSource(DEvidenceSource evidenceSource) {
		BeanUtils.copyProperties(evidenceSource, this);
		if (evidenceSource.getStatus() != null) {
			this.status = StatusEnum4DEvidenceSource.getStatusEnumByCode(evidenceSource.getStatus());
		}
	}

	private String id;
	private String code;
	private String key;
	private String name;
	private String description;
	private Date createTime;
	private Date updateTime;
	private StatusEnum4DEvidenceSource status;

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
	public StatusEnum4DEvidenceSource getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(StatusEnum4DEvidenceSource status) {
		this.status = status;
	}

}
