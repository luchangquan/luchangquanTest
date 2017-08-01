package com.renke.rdbao.beans.rdbao_v3.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_v3.pojo.EvidenceAppVideo;

/**
 * @author jgshun
 * @date 2017-3-2 下午6:59:51
 * @version 1.0
 */
public class EnhancedEvidenceAppVideo extends BaseEnhanced {

	public EnhancedEvidenceAppVideo() {
	}

	public EnhancedEvidenceAppVideo(EvidenceAppVideo evidenceAppVideo) {
		BeanUtils.copyProperties(evidenceAppVideo, this);
		if (evidenceAppVideo.getEvidenceId() != null && evidenceAppVideo.getEvidenceId().length() > 0) {
			EnhancedEvidences _EnhancedEvidences = new EnhancedEvidences();
			_EnhancedEvidences.setId(evidenceAppVideo.getEvidenceId());
			this.enhancedEvidences = _EnhancedEvidences;
		}
	}

	private EnhancedEvidences enhancedEvidences;
	private Integer duration;
	private Date beginTime;
	private Date endTime;
	private Date createTime;
	private Date updateTime;

	/**
	 * @return the duration
	 */
	public Integer getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(Integer duration) {
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

	public EnhancedEvidences getEnhancedEvidences() {
		return enhancedEvidences;
	}

	public void setEnhancedEvidences(EnhancedEvidences enhancedEvidences) {
		this.enhancedEvidences = enhancedEvidences;
	}

}
