package com.renke.rdbao.beans.rdbao_sms_2017.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

@Table(name = "e_sms_info")
public class ESmsInfo extends BasePo {

	public static final String FIELD_ID = "id";
	public static final String FIELD_TARGETPHONENO = "targetPhoneNo";
	public static final String FIELD_CONTENT = "content";
	public static final String FIELD_TYPE = "type";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_UPDATETIME = "updateTime";
	public static final String FIELD_IDENTIFICATIONID = "identificationId";
	public static final String FIELD_STATUS = "status";
	public static final String FIELD_EXTRA = "extra";

	public static final String COLUMN_ID = "id";
	public static final String COLUMN_TARGET_PHONE_NO = "target_phone_no";
	public static final String COLUMN_CONTENT = "content";
	public static final String COLUMN_TYPE = "type";
	public static final String COLUMN_CREATE_TIME = "create_time";
	public static final String COLUMN_UPDATE_TIME = "update_time";
	public static final String COLUMN_IDENTIFICATION_ID = "identification_id";
	public static final String COLUMN_STATUS = "status";
	public static final String COLUMN_EXTRA = "extra";

	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "target_phone_no")
	private String targetPhoneNo;
	@Column(name = "content")
	private String content;
	@Column(name = "type")
	private Short type;
	@Column(name = "create_time")
	private Date createTime;
	@Column(name = "update_time")
	private Date updateTime;
	@Column(name = "identification_id")
	private String identificationId;
	@Column(name = "status")
	private Short status;
	@Column(name = "extra")
	private String extra;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTargetPhoneNo() {
		return targetPhoneNo;
	}

	public void setTargetPhoneNo(String targetPhoneNo) {
		this.targetPhoneNo = targetPhoneNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getIdentificationId() {
		return identificationId;
	}

	public void setIdentificationId(String identificationId) {
		this.identificationId = identificationId;
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

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

}
