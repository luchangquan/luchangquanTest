package com.renke.rdbao.beans.rdbao_2017.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidenceAppPicture;

/**
 * @author jgshun
 * @date 2017-4-10 下午6:33:18
 * @version 1.0
 */
public class EnhancedMEvidenceAppPicture extends BaseEnhanced {
	public EnhancedMEvidenceAppPicture() {
	}

	public EnhancedMEvidenceAppPicture(MEvidenceAppPicture evidenceAppPicture) {
		BeanUtils.copyProperties(evidenceAppPicture, this);
		if (evidenceAppPicture.getEvidenceId() != null && evidenceAppPicture.getEvidenceId().length() > 0) {
			EnhancedMEvidence _EnhancedMEvidence = new EnhancedMEvidence();
			_EnhancedMEvidence.setId(evidenceAppPicture.getEvidenceId());
			this.enhancedMEvidence = _EnhancedMEvidence;
		}
	}

	private EnhancedMEvidence enhancedMEvidence;
	private Date takeTime;
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
