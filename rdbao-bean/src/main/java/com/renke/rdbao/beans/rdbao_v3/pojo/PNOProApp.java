package com.renke.rdbao.beans.rdbao_v3.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2016-11-14 下午12:29:03
 * @version 1.0
 */
@Table(name = "PNOProApp")
public class PNOProApp extends BasePo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8924130924050513676L;
	public static final String FIELD_ID = "id";
	public static final String FIELD_PROAPPID = "proAppId";
	public static final String FIELD_PNOCODE = "pnoCode";
	public static final String FIELD_OPENSTATE = "openState";
	public static final String FIELD_CREATEUSER = "createUser";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_UPDATEUSER = "updateUser";
	public static final String FIELD_UPDATETIME = "updateTime";

	public static final String COLUMN_ID = "Id";
	public static final String COLUMN_PROAPPID = "ProAppId";
	public static final String COLUMN_PNOCODE = "PNOCode";
	public static final String COLUMN_OPENSTATE = "OpenState";
	public static final String COLUMN_CREATEUSER = "CreateUser";
	public static final String COLUMN_CREATETIME = "CreateTime";
	public static final String COLUMN_UPDATEUSER = "UpdateUser";
	public static final String COLUMN_UPDATETIME = "UpdateTime";

	@Id
	@Column(name = "Id")
	private String id;
	@Column(name = "ProAppId")
	private String proAppId;
	@Column(name = "PNOCode")
	private String pnoCode;
	@Column(name = "OpenState")
	private Short openState;
	@Column(name = "CreateUser")
	private String createUser;
	@Column(name = "CreateTime")
	private Date createTime;
	@Column(name = "UpdateUser")
	private String updateUser;
	@Column(name = "UpdateTime")
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
	 * @return the proAppId
	 */
	public String getProAppId() {
		return proAppId;
	}

	/**
	 * @param proAppId
	 *            the proAppId to set
	 */
	public void setProAppId(String proAppId) {
		this.proAppId = proAppId;
	}

	/**
	 * @return the pnoCode
	 */
	public String getPnoCode() {
		return pnoCode;
	}

	/**
	 * @param pnoCode
	 *            the pnoCode to set
	 */
	public void setPnoCode(String pnoCode) {
		this.pnoCode = pnoCode;
	}

	/**
	 * @return the openState
	 */
	public Short getOpenState() {
		return openState;
	}

	/**
	 * @param openState
	 *            the openState to set
	 */
	public void setOpenState(Short openState) {
		this.openState = openState;
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
