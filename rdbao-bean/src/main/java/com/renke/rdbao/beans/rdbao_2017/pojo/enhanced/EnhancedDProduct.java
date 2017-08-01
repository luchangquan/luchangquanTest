package com.renke.rdbao.beans.rdbao_2017.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_2017.enums.fordproduct.StatusEnum4DProduct;
import com.renke.rdbao.beans.rdbao_2017.pojo.DProduct;

/**
 * @author jgshun
 * @date 2017-4-10 下午5:34:13
 * @version 1.0
 */
public class EnhancedDProduct extends BaseEnhanced {
	public EnhancedDProduct() {
	}

	public EnhancedDProduct(DProduct product) {
		BeanUtils.copyProperties(product, this);
		if (product.getStatus() != null) {
			this.status = StatusEnum4DProduct.getStatusEnumByCode(product.getStatus());
		}
	}

	private String id;
	private String code;
	private String name;
	private String description;
	private Date createTime;
	private Date updateTime;
	private StatusEnum4DProduct status;

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
	public StatusEnum4DProduct getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(StatusEnum4DProduct status) {
		this.status = status;
	}

}
