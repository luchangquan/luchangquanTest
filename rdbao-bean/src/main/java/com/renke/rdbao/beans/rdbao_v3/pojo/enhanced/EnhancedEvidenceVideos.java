package com.renke.rdbao.beans.rdbao_v3.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_v3.pojo.EvidenceVideos;

/**
 * @author jgshun
 * @date 2017-4-1 下午5:47:14
 * @version 1.0
 */
public class EnhancedEvidenceVideos extends BaseEnhanced {
	public EnhancedEvidenceVideos() {
	}

	public EnhancedEvidenceVideos(EvidenceVideos evidenceVideos) {
		BeanUtils.copyProperties(evidenceVideos, this);
	}

	private String evidenceId;
	private int evidenceCount;
	private String md5;
	private Date createTime;
	private long duration;
	private Date beginTime;
	private Date endTime;
	private Date updateTime;

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
	 * @return the evidenceCount
	 */
	public int getEvidenceCount() {
		return evidenceCount;
	}

	/**
	 * @param evidenceCount
	 *            the evidenceCount to set
	 */
	public void setEvidenceCount(int evidenceCount) {
		this.evidenceCount = evidenceCount;
	}

	/**
	 * @return the md5
	 */
	public String getMd5() {
		return md5;
	}

	/**
	 * @param md5
	 *            the md5 to set
	 */
	public void setMd5(String md5) {
		this.md5 = md5;
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
