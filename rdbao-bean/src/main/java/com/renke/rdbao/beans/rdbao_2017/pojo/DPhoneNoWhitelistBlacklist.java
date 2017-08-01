package com.renke.rdbao.beans.rdbao_2017.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2017-4-11 下午1:34:41
 * @version 1.0
 */
@Table(name = "d_phone_no_whitelist_blacklist")
public class DPhoneNoWhitelistBlacklist extends BasePo {

	public static final String FIELD_ID = "id";
	public static final String FIELD_PHONENO = "phoneNo";
	public static final String FIELD_TYPE = "type";
	public static final String FIELD_STATUS = "status";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_UPDATETIME = "updateTime";
	public static final String FIELD_OPERATEUSERID = "operateUserId";

	public static final String COLUMN_ID = "id";
	public static final String COLUMN_PHONE_NO = "phone_no";
	public static final String COLUMN_TYPE = "type";
	public static final String COLUMN_STATUS = "status";
	public static final String COLUMN_CREATE_TIME = "create_time";
	public static final String COLUMN_UPDATE_TIME = "update_time";
	public static final String COLUMN_OPERATE_USER_ID = "operate_user_id";

	public static final String ORDER_CREATE_TIME_DESC = " create_time desc ";

	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "phone_no")
	private String phoneNo;
	@Column(name = "type")
	private Short type;
	@Column(name = "status")
	private Short status;
	@Column(name = "create_time")
	private Date createTime;
	@Column(name = "update_time")
	private Date updateTime;
	@Column(name = "operate_user_id")
	private String operateUserId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getOperateUserId() {
		return operateUserId;
	}

	public void setOperateUserId(String operateUserId) {
		this.operateUserId = operateUserId;
	}

}
