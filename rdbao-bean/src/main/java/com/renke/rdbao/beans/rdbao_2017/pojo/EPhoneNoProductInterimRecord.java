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
@Table(name = "e_phone_no_product_interim_record")
public class EPhoneNoProductInterimRecord extends BasePo {

	public static final String FIELD_ID = "id";
	public static final String FIELD_PHONENO = "phoneNo";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_UPDATETIME = "updateTime";
	public static final String FIELD_PRODUCTCODE = "productCode";
	public static final String FIELD_OPENSOURCE = "openSource";
	public static final String FIELD_STATUS = "status";

	public static final String COLUMN_ID = "id";
	public static final String COLUMN_PHONE_NO = "phone_no";
	public static final String COLUMN_CREATE_TIME = "create_time";
	public static final String COLUMN_UPDATE_TIME = "update_time";
	public static final String COLUMN_PRODUCT_CODE = "product_code";
	public static final String COLUMN_OPEN_SOURCE = "open_source";
	public static final String COLUMN_STATUS = "status";

	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "phone_no")
	private String phoneNo;
	@Column(name = "create_time")
	private Date createTime;
	@Column(name = "update_time")
	private Date updateTime;
	@Column(name = "product_code")
	private String productCode;
	@Column(name = "open_source")
	private Short openSource;
	@Column(name = "status")
	private Short status;

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

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Short getOpenSource() {
		return openSource;
	}

	public void setOpenSource(Short openSource) {
		this.openSource = openSource;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

}
