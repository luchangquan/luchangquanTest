package com.renke.rdbao.beans.rdbao_2017.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2017-4-11 下午4:05:37
 * @version 1.0
 */
@Table(name = "r_user_npp_product")
public class RUserNppProduct extends BasePo {
	public static final String FIELD_ID = "id";
	public static final String FIELD_USERID = "userId";
	public static final String FIELD_NPPCODE = "nppCode";
	public static final String FIELD_PRODUCTCODE = "productCode";
	public static final String FIELD_PHONENO = "phoneNo";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String COLUMN_TENANTCODE = "tenantCode";

	public static final String COLUMN_ID = "id";
	public static final String COLUMN_USER_ID = "user_id";
	public static final String COLUMN_NPP_CODE = "npp_code";
	public static final String COLUMN_PRODUCT_CODE = "product_code";
	public static final String COLUMN_PHONE_NO = "phone_no";
	public static final String COLUMN_CREATE_TIME = "create_time";
	public static final String COLUMN_TENANT_CODE = "tenant_code";

	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "user_id")
	private String userId;
	@Column(name = "npp_code")
	private String nppCode;
	@Column(name = "product_code")
	private String productCode;
	@Column(name = "phone_no")
	private String phoneNo;
	@Column(name = "create_time")
	private Date createTime;
	@Column(name = "tenant_code")
	private String tenantCode;

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
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * @param phoneNo
	 *            the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
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
	 * @return the tenantCode
	 */
	public String getTenantCode() {
		return tenantCode;
	}

	/**
	 * @param tenantCode
	 *            the tenantCode to set
	 */
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

}
