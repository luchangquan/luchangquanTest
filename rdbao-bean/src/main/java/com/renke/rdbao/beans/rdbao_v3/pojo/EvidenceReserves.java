package com.renke.rdbao.beans.rdbao_v3.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * 证据预约对象
 * 
 * @author jgshun
 * @date 2017-1-6 上午10:29:03
 * @version 1.0
 */
@Table(name = "EvidenceReserves")
public class EvidenceReserves extends BasePo {

	public static final String FIELD_ID = "id";
	public static final String FIELD_RESERVETIME = "reserveTime";
	public static final String FIELD_STATE = "state";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_LASTUPDATETIME = "lastUpdateTime";
	public static final String FIELD_PNOID = "pnoId";
	public static final String FIELD_USERID = "userId";
	public static final String FIELD_DESCRIPTION = "description";
	public static final String FIELD_ORDERNAME = "orderName";
	public static final String FIELD_MOBILE = "mobile";
	public static final String FIELD_EMAIL = "email";

	public static final String COLUMN_ID = "Id";
	public static final String COLUMN_RESERVETIME = "ReserveTime";
	public static final String COLUMN_STATE = "State";
	public static final String COLUMN_CREATETIME = "CreateTime";
	public static final String COLUMN_LASTUPDATETIME = "LastUpdateTime";
	public static final String COLUMN_PNO_ID = "PNO_Id";
	public static final String COLUMN_USER_ID = "User_Id";
	public static final String COLUMN_DESCRIPTION = "Description";
	public static final String COLUMN_ORDERNAME = "OrderName";
	public static final String COLUMN_MOBILE = "Mobile";
	public static final String COLUMN_EMAIL = "Email";

	@javax.persistence.Id
	@Column(name = "Id")
	private String id;
	@Column(name = "ReserveTime")
	private Date reserveTime;
	@Column(name = "State")
	private Short state;
	@Column(name = "CreateTime")
	private Date createTime;
	@Column(name = "LastUpdateTime")
	private Date lastUpdateTime;
	@Column(name = "PNO_Id")
	private String pnoId;
	@Column(name = "User_Id")
	private String userId;
	@Column(name = "Description")
	private String description;
	@Column(name = "OrderName")
	private String orderName;
	@Column(name = "Mobile")
	private String mobile;
	@Column(name = "Email")
	private String email;

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
	 * @return the reserveTime
	 */
	public Date getReserveTime() {
		return reserveTime;
	}

	/**
	 * @param reserveTime
	 *            the reserveTime to set
	 */
	public void setReserveTime(Date reserveTime) {
		this.reserveTime = reserveTime;
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
	 * @return the orderName
	 */
	public String getOrderName() {
		return orderName;
	}

	/**
	 * @param orderName
	 *            the orderName to set
	 */
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
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

}
