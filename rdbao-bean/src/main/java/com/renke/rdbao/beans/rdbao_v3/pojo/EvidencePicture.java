package com.renke.rdbao.beans.rdbao_v3.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2017-4-6 下午5:49:59
 * @version 1.0
 */
@Table(name = "EvidencePicture")
public class EvidencePicture extends BasePo {
	public static final String FIELD_EVIDENCEID = "evidenceId";
	public static final String FIELD_TAKETIME = "takeTime";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_UPDATETIME = "updateTime";
	public static final String FIELD_PICFROM = "picFrom";
	public static final String FIELD_IMGURL = "imgUrl";
	public static final String FIELD_NETPAGEURL = "netPageUrl";
	public static final String FIELD_LOCATION = "location";
	public static final String FIELD_LOCATIONDESC = "locationDesc";

	public static final String COLUMN_EVIDENCE_ID = "Evidence_Id";
	public static final String COLUMN_TAKETIME = "TakeTime";
	public static final String COLUMN_CREATETIME = "CreateTime";
	public static final String COLUMN_UPDATETIME = "UpdateTime";
	public static final String COLUMN_PICFROM = "PicFrom";
	public static final String COLUMN_IMGURL = "ImgUrl";
	public static final String COLUMN_NETPAGEURL = "NetPageUrl";
	public static final String COLUMN_LOCATION = "location";
	public static final String COLUMN_LOCATION_DESC = "location_desc";

	@Id
	@Column(name = "Evidence_Id")
	private String evidenceId;
	@Column(name = "TakeTime")
	private Date takeTime;
	@Column(name = "CreateTime")
	private Date createTime;
	@Column(name = "UpdateTime")
	private Date updateTime;
	@Column(name = "PicFrom")
	private Short picFrom;
	@Column(name = "ImgUrl")
	private String imgUrl;
	@Column(name = "NetPageUrl")
	private String netPageUrl;
	@Column(name = "location")
	private String location;
	@Column(name = "location_desc")
	private String locationDesc;

	/**
	 * @return the evidenceId
	 */
	public String getEvidenceId() {
		return evidenceId;
	}

	/**
	 * @param evidenceId
	 *            the evidenceId to set
	 */
	public void setEvidenceId(String evidenceId) {
		this.evidenceId = evidenceId;
	}

	/**
	 * @return the takeTime
	 */
	public Date getTakeTime() {
		return takeTime;
	}

	/**
	 * @param takeTime
	 *            the takeTime to set
	 */
	public void setTakeTime(Date takeTime) {
		this.takeTime = takeTime;
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
	 * @return the picFrom
	 */
	public Short getPicFrom() {
		return picFrom;
	}

	/**
	 * @param picFrom
	 *            the picFrom to set
	 */
	public void setPicFrom(Short picFrom) {
		this.picFrom = picFrom;
	}

	/**
	 * @return the imgUrl
	 */
	public String getImgUrl() {
		return imgUrl;
	}

	/**
	 * @param imgUrl
	 *            the imgUrl to set
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	/**
	 * @return the netPageUrl
	 */
	public String getNetPageUrl() {
		return netPageUrl;
	}

	/**
	 * @param netPageUrl
	 *            the netPageUrl to set
	 */
	public void setNetPageUrl(String netPageUrl) {
		this.netPageUrl = netPageUrl;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the locationDesc
	 */
	public String getLocationDesc() {
		return locationDesc;
	}

	/**
	 * @param locationDesc
	 *            the locationDesc to set
	 */
	public void setLocationDesc(String locationDesc) {
		this.locationDesc = locationDesc;
	}

}
