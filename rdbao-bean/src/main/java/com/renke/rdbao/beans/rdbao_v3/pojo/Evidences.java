package com.renke.rdbao.beans.rdbao_v3.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2016-11-10 下午5:56:24
 * @version 1.0
 */
@Table(name = "Evidences")
public class Evidences extends BasePo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7154827160676324720L;
	public static final String FIELD_ID = "id";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_DESCRIPTION = "description";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_LASTUPDATETIME = "lastUpdateTime";
	public static final String FIELD_PNOID = "pnoId";
	public static final String FIELD_USERID = "userId";
	public static final String FIELD_CODE = "code";
	public static final String FIELD_SIZE = "size";
	public static final String FIELD_STATE = "state";
	public static final String FIELD_APPID = "appId";
	public static final String FIELD_COMPANYID = "companyId";
	public static final String FIELD_EVIDENCEPACKAGEIID = "evidencePackageIId";
	public static final String FIELD_CATEGORYID = "categoryId";
	public static final String FIELD_FILENAME = "filename";
	public static final String FIELD_THUMBFILENAME = "thumbFilename";
	public static final String FIELD_RECEIPTSTATE = "receiptState";
	public static final String FIELD_DELETED = "deleted";
	public static final String FIELD_PARENTCODE = "parentCode";
	public static final String FIELD_EVIDRECORDVIEWURL = "evidRecordViewUrl";
	public static final String FIELD_HANDLESOURCE = "handleSource";
	public static final String FIELD_EXPRIETIME = "exprieTime";
	public static final String FIELD_UPLOADSTATUS = "uploadStatus";

	public static final String COLUMN_ID = "Id";
	public static final String COLUMN_NAME = "Name";
	public static final String COLUMN_DESCRIPTION = "Description";
	public static final String COLUMN_CREATETIME = "CreateTime";
	public static final String COLUMN_LASTUPDATETIME = "LastUpdateTime";
	public static final String COLUMN_PNO_ID = "PNO_Id";
	public static final String COLUMN_USER_ID = "User_Id";
	public static final String COLUMN_CODE = "Code";
	public static final String COLUMN_SIZE = "Size";
	public static final String COLUMN_STATE = "State";
	public static final String COLUMN_APP_ID = "App_Id";
	public static final String COLUMN_COMPANY_ID = "Company_Id";
	public static final String COLUMN_EVIDENCEPACKAGE_ID = "EvidencePackage_Id";
	public static final String COLUMN_CATEGORY_ID = "Category_Id";
	public static final String COLUMN_FILENAME = "FileName";
	public static final String COLUMN_THUMBFILENAME = "ThumbFileName";
	public static final String COLUMN_RECEIPTSTATE = "ReceiptState";
	public static final String COLUMN_DELETED = "Deleted";
	public static final String COLUMN_PARENTCODE = "ParentCode";
	public static final String COLUMN_EVIDRECORDVIEWURL = "EvidRecordViewUrl";
	public static final String COLUMN_HANDLESOURCE = "HandleSource";
	public static final String COLUMN_EXPRIETIME = "ExprieTime";
	public static final String COLUMN_UPLOAD_STATUS = "upload_status";

	@Id
	@Column(name = "Id")
	private String id;
	@Column(name = "Name")
	private String name;
	@Column(name = "Description")
	private String description;
	@Column(name = "CreateTime")
	private Date createTime;
	@Column(name = "LastUpdateTime")
	private Date lastUpdateTime;
	@Column(name = "PNO_Id")
	private String pnoId;
	@Column(name = "User_Id")
	private String userId;
	@Column(name = "Code")
	private String code;
	@Column(name = "Size")
	private Long size;
	@Column(name = "State")
	private Short state;
	@Column(name = "App_Id")
	private String appId;
	@Column(name = "Company_Id")
	private String companyId;
	@Column(name = "EvidencePackage_Id")
	private String evidencePackageIId;
	@Column(name = "Category_Id")
	private String categoryId;
	@Column(name = "FileName")
	private String filename;
	@Column(name = "ThumbFileName")
	private String thumbFilename;
	@Column(name = "ReceiptState")
	private Short receiptState;
	@Column(name = "Deleted")
	private Short deleted;
	@Column(name = "ParentCode")
	private String parentCode;
	@Column(name = "EvidRecordViewUrl")
	private String evidRecordViewUrl;
	@Column(name = "HandleSource")
	private Short handleSource;
	@Column(name = "ExprieTime")
	private Date exprieTime;
	@Column(name = "upload_status")
	private Short uploadStatus;

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
	 * @return the pnoId
	 */
	public String getPnoId() {
		return pnoId;
	}

	/**
	 * @param pnoId
	 *            the pnoId to set
	 */
	public void setPnoId(String pnoId) {
		this.pnoId = pnoId;
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
	 * @return the size
	 */
	public Long getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(Long size) {
		this.size = size;
	}

	/**
	 * @return the state
	 */
	public Short getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(Short state) {
		this.state = state;
	}

	/**
	 * @return the appId
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * @param appId
	 *            the appId to set
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}

	/**
	 * @return the companyId
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId
	 *            the companyId to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the evidencePackageIId
	 */
	public String getEvidencePackageIId() {
		return evidencePackageIId;
	}

	/**
	 * @param evidencePackageIId
	 *            the evidencePackageIId to set
	 */
	public void setEvidencePackageIId(String evidencePackageIId) {
		this.evidencePackageIId = evidencePackageIId;
	}

	/**
	 * @return the categoryId
	 */
	public String getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename
	 *            the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * @return the thumbFilename
	 */
	public String getThumbFilename() {
		return thumbFilename;
	}

	/**
	 * @param thumbFilename
	 *            the thumbFilename to set
	 */
	public void setThumbFilename(String thumbFilename) {
		this.thumbFilename = thumbFilename;
	}

	/**
	 * @return the receiptState
	 */
	public Short getReceiptState() {
		return receiptState;
	}

	/**
	 * @param receiptState
	 *            the receiptState to set
	 */
	public void setReceiptState(Short receiptState) {
		this.receiptState = receiptState;
	}

	/**
	 * @return the deleted
	 */
	public Short getDeleted() {
		return deleted;
	}

	/**
	 * @param deleted
	 *            the deleted to set
	 */
	public void setDeleted(Short deleted) {
		this.deleted = deleted;
	}

	/**
	 * @return the parentCode
	 */
	public String getParentCode() {
		return parentCode;
	}

	/**
	 * @param parentCode
	 *            the parentCode to set
	 */
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	/**
	 * @return the evidRecordViewUrl
	 */
	public String getEvidRecordViewUrl() {
		return evidRecordViewUrl;
	}

	/**
	 * @param evidRecordViewUrl
	 *            the evidRecordViewUrl to set
	 */
	public void setEvidRecordViewUrl(String evidRecordViewUrl) {
		this.evidRecordViewUrl = evidRecordViewUrl;
	}

	/**
	 * @return the handleSource
	 */
	public Short getHandleSource() {
		return handleSource;
	}

	/**
	 * @param handleSource
	 *            the handleSource to set
	 */
	public void setHandleSource(Short handleSource) {
		this.handleSource = handleSource;
	}

	/**
	 * @return the exprieTime
	 */
	public Date getExprieTime() {
		return exprieTime;
	}

	/**
	 * @param exprieTime
	 *            the exprieTime to set
	 */
	public void setExprieTime(Date exprieTime) {
		this.exprieTime = exprieTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Short getUploadStatus() {
		return uploadStatus;
	}

	public void setUploadStatus(Short uploadStatus) {
		this.uploadStatus = uploadStatus;
	}

}
