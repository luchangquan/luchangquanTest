package com.renke.rdbao.beans.rdbao_v3.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2017-3-14 下午4:37:33
 * @version 1.0
 */
@Table(name = "VirtualNoUserRel")
public class VirtualNoUserRel extends BasePo {
	public static final String FIELD_ID = "id";
	public static final String FIELD_VIRTUALNO = "virtualNo";
	public static final String FIELD_OBJECTID = "objectId";
	public static final String FIELD_OBJECTTYPE = "objectType";
	public static final String FIELD_DEFAULPHONENO = "defaulPhoneNo";
	public static final String FIELD_PNOEID = "pnoeId";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_UPDATETIME = "updateTime";

	public static final String COLUMN_ID = "Id";
	public static final String COLUMN_VIRTUALNO = "VirtualNo";
	public static final String COLUMN_OBJECTID = "ObjectId";
	public static final String COLUMN_OBJECTTYPE = "ObjectType";
	public static final String COLUMN_DEFAULPHONENO = "DefaulPhoneNo";
	public static final String COLUMN_PNOEID = "PnoeId";
	public static final String COLUMN_CREATETIME = "CreateTime";
	public static final String COLUMN_UPDATETIME = "UpdateTime";

	@javax.persistence.Id
	@Column(name = "Id")
	private String id;
	@Column(name = "VirtualNo")
	private String virtualNo;
	@Column(name = "ObjectId")
	private String objectId;
	@Column(name = "ObjectType")
	private Short objectType;
	@Column(name = "DefaulPhoneNo")
	private String defaulPhoneNo;
	@Column(name = "PnoeId")
	private String pnoeId;
	@Column(name = "CreateTime")
	private Date createTime;
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
	 * @return the virtualNo
	 */
	public String getVirtualNo() {
		return virtualNo;
	}

	/**
	 * @param virtualNo
	 *            the virtualNo to set
	 */
	public void setVirtualNo(String virtualNo) {
		this.virtualNo = virtualNo;
	}

	/**
	 * @return the objectId
	 */
	public String getObjectId() {
		return objectId;
	}

	/**
	 * @param objectId
	 *            the objectId to set
	 */
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	/**
	 * @return the objectType
	 */
	public Short getObjectType() {
		return objectType;
	}

	/**
	 * @param objectType
	 *            the objectType to set
	 */
	public void setObjectType(Short objectType) {
		this.objectType = objectType;
	}

	/**
	 * @return the defaulPhoneNo
	 */
	public String getDefaulPhoneNo() {
		return defaulPhoneNo;
	}

	/**
	 * @param defaulPhoneNo
	 *            the defaulPhoneNo to set
	 */
	public void setDefaulPhoneNo(String defaulPhoneNo) {
		this.defaulPhoneNo = defaulPhoneNo;
	}

	/**
	 * @return the pnoeId
	 */
	public String getPnoeId() {
		return pnoeId;
	}

	/**
	 * @param pnoeId
	 *            the pnoeId to set
	 */
	public void setPnoeId(String pnoeId) {
		this.pnoeId = pnoeId;
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

}
