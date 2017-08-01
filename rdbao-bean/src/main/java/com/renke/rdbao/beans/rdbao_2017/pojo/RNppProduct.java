package com.renke.rdbao.beans.rdbao_2017.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2017-4-11 下午3:58:36
 * @version 1.0
 */
@Table(name = "r_npp_product")
public class RNppProduct extends BasePo {
	public static final String FIELD_ID = "id";
	public static final String FIELD_NPPCODE = "nppCode";
	public static final String FIELD_PRODUCTCODE = "productCode";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_UPDATETIME = "updateTime";

	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NPP_CODE = "npp_code";
	public static final String COLUMN_PRODUCT_CODE = "product_code";
	public static final String COLUMN_CREATE_TIME = "create_time";
	public static final String COLUMN_UPDATE_TIME = "update_time";

	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "npp_code")
	private String nppCode;
	@Column(name = "product_code")
	private String productCode;
	@Column(name = "create_time")
	private Date createTime;
	@Column(name = "update_time")
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
	 * @return the nppCode
	 */
	public String getNppCode() {
		return nppCode;
	}

	/**
	 * @param nppCode
	 *            the nppCode to set
	 */
	public void setNppCode(String nppCode) {
		this.nppCode = nppCode;
	}

	/**
	 * @return the productCode
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * @param productCode
	 *            the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
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

}
