package com.renke.rdbao.beans.rdbao_2017.data.app.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.renke.rdbao.beans.data.response.base.BaseResponseData;

/**
 * @author jgshun
 * @date 2017-4-21 下午3:34:01
 * @version 1.0
 */
public class MEvidenceAppPictureResponse extends BaseResponseData {
	private MEvidenceResponse evidence;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date takeTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date createTime;
	private String location;
	private String locationDesc;

	/**
	 * @return the evidence
	 */
	public MEvidenceResponse getEvidence() {
		return evidence;
	}

	/**
	 * @param evidence
	 *            the evidence to set
	 */
	public void setEvidence(MEvidenceResponse evidence) {
		this.evidence = evidence;
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
