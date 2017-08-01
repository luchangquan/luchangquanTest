package com.renke.rdbao.beans.rdbao_2017.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2017-4-10 下午6:17:35
 * @version 1.0
 */
@Table(name = "m_evidence_remote_pc")
public class MEvidenceRemotePc extends BasePo {
	public static final String FIELD_EVIDENCEID = "evidenceId";
	public static final String FIELD_COUNT = "count";
	public static final String FIELD_DURATION = "duration";
	public static final String FIELD_BEGINTIME = "beginTime";
	public static final String FIELD_ENDTIME = "endTime";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_UPDATETIME = "updateTime";

	public static final String COLUMN_EVIDENCE_ID = "evidence_id";
	public static final String COLUMN_COUNT = "count";
	public static final String COLUMN_DURATION = "duration";
	public static final String COLUMN_BEGIN_TIME = "begin_time";
	public static final String COLUMN_END_TIME = "end_time";
	public static final String COLUMN_CREATE_TIME = "create_time";
	public static final String COLUMN_UPDATE_TIME = "update_time";

	@Id
	@Column(name = "evidence_id")
	private String evidenceId;
	@Column(name = "count")
	private Integer count;
	@Column(name = "duration")
	private Long duration;
	@Column(name = "begin_time")
	private Date beginTime;
	@Column(name = "end_time")
	private Date endTime;
	@Column(name = "create_time")
	private Date createTime;
	@Column(name = "update_time")
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
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(Integer count) {
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

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

}
