package com.renke.rdbao.beans.rdbao_v3.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2017-1-6 下午3:33:24
 * @version 1.0
 */
@Table(name = "SPEVoiceNoBlackWhiteList")
public class SPEVoiceNoBlackWhiteList extends BasePo {
	public static final String FIELD_ID = "id";
	public static final String FIELD_SPEVOICENOFILTERRULEID = "speVoiceNoFilterRuleId";
	public static final String FIELD_TARGETPHONENO = "targetPhoneNo";
	public static final String FIELD_BLACKORWHITE = "blackOrWhite";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_NAME = "name";

	public static final String COLUMN_ID = "Id";
	public static final String COLUMN_SPEVOICENOFILTERRULEID = "SPEVoiceNoFilterRuleId";
	public static final String COLUMN_TARGETPHONENO = "TargetPhoneNo";
	public static final String COLUMN_BLACKORWHITE = "BlackOrWhite";
	public static final String COLUMN_CREATETIME = "CreateTime";
	public static final String COLUMN_NAME = "Name";

	@javax.persistence.Id
	@Column(name = "Id")
	private Integer id;
	@Column(name = "SPEVoiceNoFilterRuleId")
	private Integer speVoiceNoFilterRuleId;
	@Column(name = "TargetPhoneNo")
	private String targetPhoneNo;
	@Column(name = "BlackOrWhite")
	private Short blackOrWhite;
	@Column(name = "CreateTime")
	private Date createTime;
	@Column(name = "Name")
	private String name;

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
	 * @return the speVoiceNoFilterRuleId
	 */
	public Integer getSpeVoiceNoFilterRuleId() {
		return speVoiceNoFilterRuleId;
	}

	/**
	 * @param speVoiceNoFilterRuleId
	 *            the speVoiceNoFilterRuleId to set
	 */
	public void setSpeVoiceNoFilterRuleId(Integer speVoiceNoFilterRuleId) {
		this.speVoiceNoFilterRuleId = speVoiceNoFilterRuleId;
	}

	/**
	 * @return the targetPhoneNo
	 */
	public String getTargetPhoneNo() {
		return targetPhoneNo;
	}

	/**
	 * @param targetPhoneNo
	 *            the targetPhoneNo to set
	 */
	public void setTargetPhoneNo(String targetPhoneNo) {
		this.targetPhoneNo = targetPhoneNo;
	}

	/**
	 * @return the blackOrWhite
	 */
	public Short getBlackOrWhite() {
		return blackOrWhite;
	}

	/**
	 * @param blackOrWhite
	 *            the blackOrWhite to set
	 */
	public void setBlackOrWhite(Short blackOrWhite) {
		this.blackOrWhite = blackOrWhite;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
