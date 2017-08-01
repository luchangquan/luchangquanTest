package com.renke.rdbao.beans.rdbao_2017.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2017-4-10 下午6:27:16
 * @version 1.0
 */
@Table(name = "m_evidence_app_picture")
public class MEvidenceAppPicture extends BasePo {
	public static final String FIELD_EVIDENCEID = "evidenceId";
	public static final String FIELD_TAKETIME = "takeTime";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_UPDATETIME = "updateTime";
	public static final String FIELD_LOCATION = "location";
	public static final String FIELD_LOCATIONDESC = "locationDesc";

	public static final String COLUMN_EVIDENCE_ID = "evidence_id";
	public static final String COLUMN_TAKE_TIME = "take_time";
	public static final String COLUMN_CREATE_TIME = "create_time";
	public static final String COLUMN_UPDATE_TIME = "update_time";
	public static final String COLUMN_LOCATION = "location";
	public static final String COLUMN_LOCATION_DESC = "location_desc";

	@Id
	@Column(name = "evidence_id")
	private String evidenceId;
	@Column(name = "take_time")
	private Date takeTime;
	@Column(name = "create_time")
	private Date createTime;
	@Column(name = "update_time")
	private Date updateTime;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocationDesc() {
		return locationDesc;
	}

	public void setLocationDesc(String locationDesc) {
		this.locationDesc = locationDesc;
	}

}
