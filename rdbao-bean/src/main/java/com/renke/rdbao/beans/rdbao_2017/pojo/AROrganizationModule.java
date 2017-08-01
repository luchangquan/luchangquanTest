package com.renke.rdbao.beans.rdbao_2017.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2017-4-11 下午3:20:43
 * @version 1.0
 */
@Table(name = "a_r_organization_module")
public class AROrganizationModule extends BasePo {
	public static final String FIELD_ID = "id";
	public static final String FIELD_ORGANIZATIONID = "organizationId";
	public static final String FIELD_MODULEID = "moduleId";
	public static final String FIELD_CREATEUSERID = "createUserId";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String COLUMN_TENANTCODE = "tenantCode";

	public static final String COLUMN_ID = "id";
	public static final String COLUMN_ORGANIZATION_ID = "organization_id";
	public static final String COLUMN_MODULE_ID = "module_id";
	public static final String COLUMN_CREATE_USER_ID = "create_user_id";
	public static final String COLUMN_CREATE_TIME = "create_time";
	public static final String COLUMN_TENANT_CODE = "tenant_code";

	@Id
	@Column(name = "id")
	private String id;;
	@Column(name = "organization_id")
	private String organizationId;
	@Column(name = "module_id")
	private String moduleId;
	@Column(name = "create_user_id")
	private String createUserId;
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
	 * @return the moduleId
	 */
	public String getModuleId() {
		return moduleId;
	}

	/**
	 * @param moduleId
	 *            the moduleId to set
	 */
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId
	 *            the createUserId to set
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
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

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

}
