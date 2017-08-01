package com.renke.rdbao.beans.rdbao_2017.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2017-4-13 下午5:59:17
 * @version 1.0
 */
@Table(name = "e_notarization_reserve")
public class ENotarizationReserve extends BasePo {
	public static final String FIELD_ID = "id";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_DESCRIPTION = "description";
	public static final String FIELD_PHONENO = "phoneNo";
	public static final String FIELD_EMAIL = "email";
	public static final String FIELD_STATUS = "status";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_UPDATETIME = "updateTime";
	public static final String FIELD_NPPCODE = "nppCode";
	public static final String FIELD_USERID = "userId";
	public static final String FIELD_TENANTCODE = "tenantCode";
	public static final String FIELD_AGENTNAME = "agentName";
	public static final String FIELD_REASON = "reason";
	public static final String FIELD_NOTARYSUBJECT = "notarySubject";

	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_DESCRIPTION = "description";
	public static final String COLUMN_PHONE_NO = "phone_no";
	public static final String COLUMN_EMAIL = "email";
	public static final String COLUMN_STATUS = "status";
	public static final String COLUMN_CREATE_TIME = "create_time";
	public static final String COLUMN_UPDATE_TIME = "update_time";
	public static final String COLUMN_NPP_CODE = "npp_code";
	public static final String COLUMN_USER_ID = "user_id";
	public static final String COLUMN_TENANT_CODE = "tenant_code";
	public static final String COLUMN_AGENT_NAME = "agent_name";
	public static final String COLUMN_REASON = "reason";
	public static final String COLUMN_NOTARY_SUBJECT = "notary_subject";

	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "phone_no")
	private String phoneNo;
	@Column(name = "email")
	private String email;
	@Column(name = "status")
	private Short status;
	@Column(name = "create_time")
	private Date createTime;
	@Column(name = "update_time")
	private Date updateTime;
	@Column(name = "npp_code")
	private String nppCode;
	@Column(name = "user_id")
	private String userId;
	@Column(name = "tenant_code")
	private String tenantCode;
	@Column(name = "agent_name")
	private String agentName;
	@Column(name = "reason")
	private String reason;
	@Column(name = "notary_subject")
	private String notarySubject;

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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public String getNppCode() {
		return nppCode;
	}

	public void setNppCode(String nppCode) {
		this.nppCode = nppCode;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getNotarySubject() {
		return notarySubject;
	}

	public void setNotarySubject(String notarySubject) {
		this.notarySubject = notarySubject;
	}

}
