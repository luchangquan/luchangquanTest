package com.renke.rdbao.beans.rdbaoapp.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2016-11-14 下午3:57:53
 * @version 1.0
 */
@Table(name = "ArchiveCancel")
public class ArchiveCancel extends BasePo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3633822685731611361L;

	public static final String FIELD_ID = "id";
	public static final String FIELD_EVIDENCECODE = "evidenceCode";
	public static final String FIELD_APPLYUSER = "applyUser";
	public static final String FIELD_CANCELREASON = "cancelReason";
	public static final String FIELD_CANCELSTAT = "cancelStat";
	public static final String FIELD_REVIEWDESC = "reviewDesc";
	public static final String FIELD_CANCELPASSTIME = "cancelPassTime";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_UPDATETIME = "updateTime";

	public static final String COLUMN_ID = "Id";
	public static final String COLUMN_EVIDENCECODE = "EvidenceCode";
	public static final String COLUMN_APPLYUSER = "ApplyUser";
	public static final String COLUMN_CANCELREASON = "CancelReason";
	public static final String COLUMN_CANCELSTAT = "CancelStat";
	public static final String COLUMN_REVIEWDESC = "ReviewDesc";
	public static final String COLUMN_CANCELPASSTIME = "CancelPassTime";
	public static final String COLUMN_CREATETIME = "CreateTime";
	public static final String COLUMN_UPDATETIME = "UpdateTime";

	@Id
	@Column(name = "Id")
	private Integer id;
	@Column(name = "EvidenceCode")
	private String evidenceCode;
	@Column(name = "ApplyUser")
	private String applyUser;
	@Column(name = "CancelReason")
	private String cancelReason;
	@Column(name = "CancelStat")
	private Short cancelStat;
	@Column(name = "ReviewDesc")
	private String reviewDesc;
	@Column(name = "CancelPassTime")
	private Date cancelPassTime;
	@Column(name = "CreateTime")
	private Date createTime;
	@Column(name = "UpdateTime")
	private Date updateTime;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the evidenceCode
	 */
	public String getEvidenceCode() {
		return evidenceCode;
	}

	/**
	 * @param evidenceCode
	 *            the evidenceCode to set
	 */
	public void setEvidenceCode(String evidenceCode) {
		this.evidenceCode = evidenceCode;
	}

	/**
	 * @return the applyUser
	 */
	public String getApplyUser() {
		return applyUser;
	}

	/**
	 * @param applyUser
	 *            the applyUser to set
	 */
	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}

	/**
	 * @return the cancelReason
	 */
	public String getCancelReason() {
		return cancelReason;
	}

	/**
	 * @param cancelReason
	 *            the cancelReason to set
	 */
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	/**
	 * @return the cancelStat
	 */
	public Short getCancelStat() {
		return cancelStat;
	}

	/**
	 * @param cancelStat
	 *            the cancelStat to set
	 */
	public void setCancelStat(Short cancelStat) {
		this.cancelStat = cancelStat;
	}

	/**
	 * @return the reviewDesc
	 */
	public String getReviewDesc() {
		return reviewDesc;
	}

	/**
	 * @param reviewDesc
	 *            the reviewDesc to set
	 */
	public void setReviewDesc(String reviewDesc) {
		this.reviewDesc = reviewDesc;
	}

	/**
	 * @return the cancelPassTime
	 */
	public Date getCancelPassTime() {
		return cancelPassTime;
	}

	/**
	 * @param cancelPassTime
	 *            the cancelPassTime to set
	 */
	public void setCancelPassTime(Date cancelPassTime) {
		this.cancelPassTime = cancelPassTime;
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

}
