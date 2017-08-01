package com.renke.rdbao.beans.rdbao_2017.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2017-4-11 下午4:46:20
 * @version 1.0
 */
@Table(name = "r_user_organization_history")
public class RUserOrganizationHistory extends BasePo {
	public static final String FIELD_ID = "id";
	public static final String FIELD_USERID = "userId";
	public static final String FIELD_ORGANIZATIONID = "organizationId";
	public static final String FIELD_STARTTIME = "startTime";
	public static final String FIELD_ENDTIME = "endTime";
	public static final String FIELD_STATUS = "status";
	public static final String FIELD_TENANTCODE = "tenantCode";

	public static final String COLUMN_ID = "id";
	public static final String COLUMN_USER_ID = "user_id";
	public static final String COLUMN_ORGANIZATION_ID = "organization_id";
	public static final String COLUMN_START_TIME = "start_time";
	public static final String COLUMN_END_TIME = "end_time";
	public static final String COLUMN_STATUS = "status";
	public static final String COLUMN_TENANT_CODE = "tenant_code";

	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "user_id")
	private String userId;
	@Column(name = "organization_id")
	private String organizationId;
	@Column(name = "start_time")
	private Date startTime;
	@Column(name = "end_time")
	private Date endTime;
	@Column(name = "status")
	private Short status;
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
	 * @return the organizationId
	 */
	public String getOrganizationId() {
		return organizationId;
	}

	/**
	 * @param organizationId
	 *            the organizationId to set
	 */
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
