package com.renke.rdbao.beans.rdbao_2017.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2017-4-10 下午1:37:41
 * @version 1.0
 */
@Table(name = "m_evidence")
public class MEvidence extends BasePo {
	public static final String FIELD_ID = "id";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_CODE = "code";
	public static final String FIELD_SIZE = "size";
	public static final String FIELD_DESCRIPTION = "description";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_UPDATETIME = "updateTime";
	public static final String FIELD_NPPCODE = "nppCode";
	public static final String FIELD_USERID = "userId";
	public static final String FIELD_EVIDENCESOURCEID = "evidenceSourceId";
	public static final String FIELD_SIGNATUREKEYID = "signatureKeyId";
	public static final String FIELD_COMPANYID = "companyId";
	public static final String FIELD_CATEGORYID = "categoryId";
	public static final String FIELD_STATUS = "status";
	public static final String FIELD_UPLOADSTATUS = "uploadStatus";
	public static final String FIELD_TENANTCODE = "tenantCode";

	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_CODE = "code";
	public static final String COLUMN_SIZE = "size";
	public static final String COLUMN_DESCRIPTION = "description";
	public static final String COLUMN_CREATE_TIME = "create_time";
	public static final String COLUMN_UPDATE_TIME = "update_time";
	public static final String COLUMN_NPP_CODE = "npp_code";
	public static final String COLUMN_USER_ID = "user_id";
	public static final String COLUMN_EVIDENCE_SOURCE_ID = "evidence_source_id";
	public static final String COLUMN_SIGNATURE_KEY_ID = "signature_key_id";
	public static final String COLUMN_COMPANY_ID = "company_id";
	public static final String COLUMN_CATEGORY_ID = "category_id";
	public static final String COLUMN_STATUS = "status";
	public static final String COLUMN_UPLOAD_STATUS = "upload_status";
	public static final String COLUMN_TENANT_CODE = "tenant_code";

	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "name")
	private String name;
	@Column(name = "code")
	private String code;
	@Column(name = "size")
	private Long size;
	@Column(name = "description")
	private String description;
	@Column(name = "create_time")
	private Date createTime;
	@Column(name = "update_time")
	private Date updateTime;
	@Column(name = "npp_code")
	private String nppCode;
	@Column(name = "user_id")
	private String userId;
	@Column(name = "evidence_source_id")
	private String evidenceSourceId;
	@Column(name = "signature_key_id")
	private String signatureKeyId;
	@Column(name = "company_id")
	private String companyId;
	@Column(name = "category_id")
	private Short categoryId;
	@Column(name = "status")
	private Short status;
	@Column(name = "upload_status")
	private Short uploadStatus;
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
	 * @return the categoryId
	 */
	public Short getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(Short categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the status
	 */
	public Short getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Short status) {
		this.status = status;
	}

	/**
	 * @return the uploadStatus
	 */
	public Short getUploadStatus() {
		return uploadStatus;
	}

	/**
	 * @param uploadStatus
	 *            the uploadStatus to set
	 */
	public void setUploadStatus(Short uploadStatus) {
		this.uploadStatus = uploadStatus;
	}

	public String getEvidenceSourceId() {
		return evidenceSourceId;
	}

	public void setEvidenceSourceId(String evidenceSourceId) {
		this.evidenceSourceId = evidenceSourceId;
	}

	public String getSignatureKeyId() {
		return signatureKeyId;
	}

	public void setSignatureKeyId(String signatureKeyId) {
		this.signatureKeyId = signatureKeyId;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

}
