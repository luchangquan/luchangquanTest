package com.renke.rdbao.beans.rdbao_v3.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2017-4-1 下午5:43:04
 * @version 1.0
 */
@Table(name = "EvidenceVideos")
public class EvidenceVideos extends BasePo {
	public static final String FIELD_EVIDENCEID = "evidenceId";
	public static final String FIELD_EVIDENCECOUNT = "evidenceCount";
	public static final String FIELD_MD5 = "md5";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_DURATION = "duration";
	public static final String FIELD_BEGINTIME = "beginTime";
	public static final String FIELD_ENDTIME = "endTime";
	public static final String FIELD_UPDATETIME = "updateTime";

	public static final String COLUMN_EVIDENCE_ID = "Evidence_Id";
	public static final String COLUMN_EVIDENCECOUNT = "EvidenceCount";
	public static final String COLUMN_MD5 = "MD5";
	public static final String COLUMN_CREATETIME = "CreateTime";
	public static final String COLUMN_DURATION = "Duration";
	public static final String COLUMN_BEGINTIME = "BeginTime";
	public static final String COLUMN_ENDTIME = "EndTime";
	public static final String COLUMN_UPDATETIME = "UpdateTime";

	@Id
	@Column(name = "Evidence_Id")
	private String evidenceId;
	@Column(name = "EvidenceCount")
	private Integer evidenceCount;
	@Column(name = "MD5")
	private String md5;
	@Column(name = "CreateTime")
	private Date createTime;
	@Column(name = "Duration")
	private Integer duration;
	@Column(name = "BeginTime")
	private Date beginTime;
	@Column(name = "EndTime")
	private Date endTime;
	@Column(name = "UpdateTime")
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
	public Integer getEvidenceCount() {
		return evidenceCount;
	}

	/**
	 * @param evidenceCount
	 *            the evidenceCount to set
	 */
	public void setEvidenceCount(Integer evidenceCount) {
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
