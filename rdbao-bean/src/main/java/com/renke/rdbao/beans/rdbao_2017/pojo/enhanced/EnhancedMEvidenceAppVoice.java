package com.renke.rdbao.beans.rdbao_2017.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidenceAppVoice;

/**
 * @author jgshun
 * @date 2017-4-12 下午6:12:40
 * @version 1.0
 */
public class EnhancedMEvidenceAppVoice extends BaseEnhanced {
	public EnhancedMEvidenceAppVoice() {
	}

	public EnhancedMEvidenceAppVoice(MEvidenceAppVoice evidenceAppVoice) {
		BeanUtils.copyProperties(evidenceAppVoice, this);
		if (evidenceAppVoice.getEvidenceId() != null && evidenceAppVoice.getEvidenceId().length() > 0) {
			EnhancedMEvidence _EnhancedMEvidence = new EnhancedMEvidence();
			_EnhancedMEvidence.setId(evidenceAppVoice.getEvidenceId());
			this.enhancedMEvidence = _EnhancedMEvidence;
		}
	}

	private EnhancedMEvidence enhancedMEvidence;
	private long duration;
	private Date beginTime;
	private Date endTime;
	private Date createTime;
	private Date updateTime;
	private String location;
	private String locationDesc;

	/**
	 * @return the enhancedMEvidence
	 */
	public EnhancedMEvidence getEnhancedMEvidence() {
		return enhancedMEvidence;
	}

	/**
	 * @param enhancedMEvidence
	 *            the enhancedMEvidence to set
	 */
	public void setEnhancedMEvidence(EnhancedMEvidence enhancedMEvidence) {
		this.enhancedMEvidence = enhancedMEvidence;
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

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

}
