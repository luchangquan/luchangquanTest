package com.renke.rdbao.beans.rdbao_v3.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2017-3-14 下午12:28:26
 * @version 1.0
 */
@Table(name = "Apps")
public class Apps extends BasePo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8357211870253847173L;
	public static final String FIELD_ID = "id";
	public static final String FIELD_CODE = "code";
	public static final String FIELD_KEY = "key";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_COMPANY = "company";
	public static final String FIELD_DESCRIPTION = "description";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_LASTUPDATETIME = "lastUpdateTime";
	public static final String FIELD_ENABLED = "enabled";
	public static final String FIELD_SELFBILLING = "selfBilling";
	public static final String FIELD_PNOHANDLE = "pnoHandle";
	public static final String FIELD_DEFAULTCATEGORYID = "defaultCategoryId";
	public static final String FIELD_PARENTAPPID = "parentAppId";

	public static final String COLUMN_ID = "Id";
	public static final String COLUMN_CODE = "Code";
	public static final String COLUMN_KEY = "Key";
	public static final String COLUMN_NAME = "Name";
	public static final String COLUMN_COMPANY = "Company";
	public static final String COLUMN_DESCRIPTION = "Description";
	public static final String COLUMN_CREATETIME = "CreateTime";
	public static final String COLUMN_LASTUPDATETIME = "LastUpdateTime";
	public static final String COLUMN_ISENABLED = "IsEnabled";
	public static final String COLUMN_ISSELFBILLING = "IsSelfbilling";
	public static final String COLUMN_ISPNOHANDLE = "IsPNOHandle";
	public static final String COLUMN_DEFAULTCATEGORY_ID = "DefaultCategory_Id";
	public static final String COLUMN_PARENTAPP_ID = "ParentApp_Id";

	@javax.persistence.Id
	@Column(name = "Id")
	private String id;
	@Column(name = "Code")
	private String code;
	@Column(name = "'Key'")
	private String key;
	@Column(name = "Name")
	private String name;
	@Column(name = "Company")
	private String company;
	@Column(name = "Description")
	private String description;
	@Column(name = "CreateTime")
	private Date createTime;
	@Column(name = "LastUpdateTime")
	private Date lastUpdateTime;
	@Column(name = "IsEnabled")
	private Short enabled;
	@Column(name = "IsSelfbilling")
	private Short selfBilling;
	@Column(name = "IsPNOHandle")
	private Short pnoHandle;
	@Column(name = "DefaultCategory_Id")
	private String defaultCategoryId;
	@Column(name = "ParentApp_Id")
	private String parentAppId;

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
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company
	 *            the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
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
	 * @return the lastUpdateTime
	 */
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	/**
	 * @param lastUpdateTime
	 *            the lastUpdateTime to set
	 */
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	/**
	 * @return the enabled
	 */
	public Short getEnabled() {
		return enabled;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the selfBilling
	 */
	public Short getSelfBilling() {
		return selfBilling;
	}

	/**
	 * @param selfBilling
	 *            the selfBilling to set
	 */
	public void setSelfBilling(Short selfBilling) {
		this.selfBilling = selfBilling;
	}

	/**
	 * @return the pnoHandle
	 */
	public Short getPnoHandle() {
		return pnoHandle;
	}

	/**
	 * @param pnoHandle
	 *            the pnoHandle to set
	 */
	public void setPnoHandle(Short pnoHandle) {
		this.pnoHandle = pnoHandle;
	}

	/**
	 * @return the defaultCategoryId
	 */
	public String getDefaultCategoryId() {
		return defaultCategoryId;
	}

	/**
	 * @param defaultCategoryId
	 *            the defaultCategoryId to set
	 */
	public void setDefaultCategoryId(String defaultCategoryId) {
		this.defaultCategoryId = defaultCategoryId;
	}

	/**
	 * @return the parentAppId
	 */
	public String getParentAppId() {
		return parentAppId;
	}

	/**
	 * @param parentAppId
	 *            the parentAppId to set
	 */
	public void setParentAppId(String parentAppId) {
		this.parentAppId = parentAppId;
	}

}
