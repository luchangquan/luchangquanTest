package com.renke.rdbao.beans.rdbao_2017.data.app.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.renke.rdbao.beans.data.response.base.BaseResponseData;

/**
 * @author jgshun
 * @date 2017-4-21 下午3:33:31
 * @version 1.0
 */
public class MEvidenceAppVideoResponse extends BaseResponseData {
	private MEvidenceResponse evidence;
	private int count;
	private long duration;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date beginTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date endTime;
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
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the duration
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(long duration) {
		this.duration = duration;
	}

	/**
	 * @return the beginTime
	 */
	public Date getBeginTime() {
		return beginTime;
	}

	/**
	 * @param beginTime
	 *            the beginTime to set
	 */
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
