package com.renke.rdbao.beans.rdbao_2017.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidenceRemotePc;

/**
 * @author jgshun
 * @date 2017-4-10 下午6:21:18
 * @version 1.0
 */
public class EnhancedMEvidenceRemotePc extends BaseEnhanced {
	public EnhancedMEvidenceRemotePc() {
	}

	public EnhancedMEvidenceRemotePc(MEvidenceRemotePc evidenceVideo) {
		BeanUtils.copyProperties(evidenceVideo, this);
		if (evidenceVideo.getEvidenceId() != null && evidenceVideo.getEvidenceId().length() > 0) {
			EnhancedMEvidence _EnhancedMEvidence = new EnhancedMEvidence();
			_EnhancedMEvidence.setId(evidenceVideo.getEvidenceId());
			this.enhancedMEvidence = _EnhancedMEvidence;
		}
	}

	private EnhancedMEvidence enhancedMEvidence;
	private int count;
	private long duration;
	private Date beginTime;
	private Date endTime;
	private Date createTime;
	private Date updateTime;

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

	public EnhancedMEvidence getEnhancedMEvidence() {
		return enhancedMEvidence;
	}

	public void setEnhancedMEvidence(EnhancedMEvidence enhancedMEvidence) {
		this.enhancedMEvidence = enhancedMEvidence;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

}
