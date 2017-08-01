package com.renke.rdbao.beans.rdbao_v3.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * 产品应用信息
 * 
 * @author jgshun
 * @date 2016-11-14 下午12:29:25
 * @version 1.0
 */
@Table(name = "ProductApp")
public class ProductApp extends BasePo {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1368472695437734840L;
	public static final String FIELD_ID = "id";
	public static final String FIELD_OBJECTID = "objectId";
	public static final String FIELD_OBJECTTYPE = "objectType";
	public static final String FIELD_PRODUCTID = "productId";
	public static final String FIELD_PRODUCTNAME = "productName";
	public static final String FIELD_DISABLED = "disabled";
	public static final String FIELD_DETAIL = "detail";
	public static final String FIELD_CREATEUSER = "createUser";
	public static final String FIELD_UPDATEUSER = "updateUser";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_UPDATETIME = "updateTime";
	public static final String FIELD_CONTRACTTYPE = "contractType";

	public static final String COLUMN_ID = "Id";
	public static final String COLUMN_OBJECTID = "ObjectId";
	public static final String COLUMN_OBJECTTYPE = "ObjectType";
	public static final String COLUMN_PRODUCTID = "ProductId";
	public static final String COLUMN_PRODUCTNAME = "ProductName";
	public static final String COLUMN_DISABLED = "Disabled";
	public static final String COLUMN_DETAIL = "Detail";
	public static final String COLUMN_CREATEUSER = "CreateUser";
	public static final String COLUMN_UPDATEUSER = "UpdateUser";
	public static final String COLUMN_CREATETIME = "CreateTime";
	public static final String COLUMN_UPDATETIME = "UpdateTime";
	public static final String COLUMN_CONTRACTTYPE = "ContractType";

	@Id
	@Column(name = "Id")
	private String id;;
	@Column(name = "ObjectId")
	private String objectId;
	@Column(name = "ObjectType")
	private Short objectType;
	@Column(name = "ProductId")
	private String productId;
	@Column(name = "ProductName")
	private String productName;
	@Column(name = "Disabled")
	private Short disabled;
	@Column(name = "Detail")
	private String detail;
	@Column(name = "CreateUser")
	private String createUser;
	@Column(name = "UpdateUser")
	private String updateUser;
	@Column(name = "CreateTime")
	private Date createTime;
	@Column(name = "UpdateTime")
	private Date updateTime;
	@Column(name = "ContractType")
	private Short contractType;

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
	 * @return the objectId
	 */
	public String getObjectId() {
		return objectId;
	}

	/**
	 * @param objectId
	 *            the objectId to set
	 */
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	/**
	 * @return the objectType
	 */
	public Short getObjectType() {
		return objectType;
	}

	/**
	 * @param objectType
	 *            the objectType to set
	 */
	public void setObjectType(Short objectType) {
		this.objectType = objectType;
	}

	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the disabled
	 */
	public Short getDisabled() {
		return disabled;
	}

	/**
	 * @param disabled
	 *            the disabled to set
	 */
	public void setDisabled(Short disabled) {
		this.disabled = disabled;
	}

	/**
	 * @return the detail
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * @param detail
	 *            the detail to set
	 */
	public void setDetail(String detail) {
		this.detail = detail;
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
	 * @return the contractType
	 */
	public Short getContractType() {
		return contractType;
	}

	/**
	 * @param contractType
	 *            the contractType to set
	 */
	public void setContractType(Short contractType) {
		this.contractType = contractType;
	}

}
