package com.renke.rdbao.beans.rdbao_v3.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2016-11-14 下午12:28:24
 * @version 1.0
 */
@Table(name = "FaxVoiceDetail")
public class FaxVoiceDetail extends BasePo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2691048288785862444L;
	public static final String FIELD_ID = "id";
	public static final String FIELD_FVID = "fvId";
	public static final String FIELD_PRESERVENO = "preserveNo";
	public static final String FIELD_MAINPHONE = "mainPhone";
	public static final String FIELD_ATTACHPHONE = "attachPhone";
	public static final String FIELD_CREATEUSER = "createUser";
	public static final String FIELD_UPDATEUSER = "updateUser";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_UPDATETIME = "updateTime";
	public static final String FIELD_VOICEFROM = "voiceFrom";
	public static final String FIELD_SHOWVIRTUAL = "showVirtual";
	public static final String FIELD_RECIPTTYPE = "reciptType";
	public static final String FIELD_RECIPTMOBILE = "reciptMobile";
	public static final String FIELD_RECIPTEMAIL = "reciptEmail";
	public static final String FIELD_STATE = "state";
	public static final String FIELD_RATE = "rate";

	public static final String COLUMN_ID = "Id";
	public static final String COLUMN_FVID = "FVId";
	public static final String COLUMN_PRESERVENO = "PreserveNo";
	public static final String COLUMN_MAINPHONE = "MainPhone";
	public static final String COLUMN_ATTACHPHONE = "AttachPhone";
	public static final String COLUMN_CREATEUSER = "CreateUser";
	public static final String COLUMN_UPDATEUSER = "UpdateUser";
	public static final String COLUMN_CREATETIME = "CreateTime";
	public static final String COLUMN_UPDATETIME = "UpdateTime";
	public static final String COLUMN_VOICEFROM = "VoiceFrom";
	public static final String COLUMN_SHOWVIRTUAL = "ShowVirtual";
	public static final String COLUMN_RECIPTTYPE = "ReciptType";
	public static final String COLUMN_RECIPTMOBILE = "ReciptMobile";
	public static final String COLUMN_RECIPTEMAIL = "ReciptEmail";
	public static final String COLUMN_ISSTATE = "IsState";
	public static final String COLUMN_RATE = "Rate";

	@Id
	@Column(name = "Id")
	private String id;
	@Column(name = "FVId")
	private String fvId;
	@Column(name = "PreserveNo")
	private String preserveNo;
	@Column(name = "MainPhone")
	private String mainPhone;
	@Column(name = "AttachPhone")
	private String attachPhone;
	@Column(name = "CreateUser")
	private String createUser;
	@Column(name = "UpdateUser")
	private String updateUser;
	@Column(name = "CreateTime")
	private Date createTime;
	@Column(name = "UpdateTime")
	private Date updateTime;
	@Column(name = "VoiceFrom")
	private Short voiceFrom;
	@Column(name = "ShowVirtual")
	private Short showVirtual;
	@Column(name = "ReciptType")
	private Short reciptType;
	@Column(name = "ReciptMobile")
	private String reciptMobile;
	@Column(name = "ReciptEmail")
	private String reciptEmail;
	@Column(name = "IsState")
	private Short state;
	@Column(name = "Rate")
	private String rate;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the fvId
	 */
	public String getFvId() {
		return fvId;
	}

	/**
	 * @param fvId
	 *            the fvId to set
	 */
	public void setFvId(String fvId) {
		this.fvId = fvId;
	}

	/**
	 * @return the preserveNo
	 */
	public String getPreserveNo() {
		return preserveNo;
	}

	/**
	 * @param preserveNo
	 *            the preserveNo to set
	 */
	public void setPreserveNo(String preserveNo) {
		this.preserveNo = preserveNo;
	}

	/**
	 * @return the mainPhone
	 */
	public String getMainPhone() {
		return mainPhone;
	}

	/**
	 * @param mainPhone
	 *            the mainPhone to set
	 */
	public void setMainPhone(String mainPhone) {
		this.mainPhone = mainPhone;
	}

	/**
	 * @return the attachPhone
	 */
	public String getAttachPhone() {
		return attachPhone;
	}

	/**
	 * @param attachPhone
	 *            the attachPhone to set
	 */
	public void setAttachPhone(String attachPhone) {
		this.attachPhone = attachPhone;
	}

	/**
	 * @return the createUser
	 */
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * @param createUser
	 *            the createUser to set
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	/**
	 * @return the updateUser
	 */
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * @param updateUser
	 *            the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
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
	 * @return the voiceFrom
	 */
	public Short getVoiceFrom() {
		return voiceFrom;
	}

	/**
	 * @param voiceFrom
	 *            the voiceFrom to set
	 */
	public void setVoiceFrom(Short voiceFrom) {
		this.voiceFrom = voiceFrom;
	}

	/**
	 * @return the showVirtual
	 */
	public Short getShowVirtual() {
		return showVirtual;
	}

	/**
	 * @param showVirtual
	 *            the showVirtual to set
	 */
	public void setShowVirtual(Short showVirtual) {
		this.showVirtual = showVirtual;
	}

	/**
	 * @return the reciptType
	 */
	public Short getReciptType() {
		return reciptType;
	}

	/**
	 * @param reciptType
	 *            the reciptType to set
	 */
	public void setReciptType(Short reciptType) {
		this.reciptType = reciptType;
	}

	/**
	 * @return the reciptMobile
	 */
	public String getReciptMobile() {
		return reciptMobile;
	}

	/**
	 * @param reciptMobile
	 *            the reciptMobile to set
	 */
	public void setReciptMobile(String reciptMobile) {
		this.reciptMobile = reciptMobile;
	}

	/**
	 * @return the reciptEmail
	 */
	public String getReciptEmail() {
		return reciptEmail;
	}

	/**
	 * @param reciptEmail
	 *            the reciptEmail to set
	 */
	public void setReciptEmail(String reciptEmail) {
		this.reciptEmail = reciptEmail;
	}

	/**
	 * @return the state
	 */
	public Short getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(Short state) {
		this.state = state;
	}

	/**
	 * @return the rate
	 */
	public String getRate() {
		return rate;
	}

	/**
	 * @param rate
	 *            the rate to set
	 */
	public void setRate(String rate) {
		this.rate = rate;
	}

}
