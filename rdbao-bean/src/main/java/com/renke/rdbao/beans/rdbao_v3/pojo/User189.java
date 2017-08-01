package com.renke.rdbao.beans.rdbao_v3.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2016-12-28 下午6:11:36
 * @version 1.0
 */
@Table(name = "e_189_user")
public class User189 extends BasePo {
	public static final String FIELD_ID = "id";
	public static final String FIELD_PHONENO = "phoneNo";
	public static final String FIELD_ACCOUNT = "account";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_EMAIL = "email";
	public static final String FIELD_PASSWORD = "password";
	public static final String FIELD_CREDENTIALSTYPE = "credentialsType";
	public static final String FIELD_CREDENTIALSNO = "credentialsNo";
	public static final String FIELD_TYPE = "type";
	public static final String FIELD_COMPANYID = "companyId";
	public static final String FIELD_GENDER = "gender";
	public static final String FIELD_STATUS = "status";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_UPDATETIME = "updateTime";
	public static final String FIELD_CREDENTIALSPATH = "credentialsPath";
	public static final String FIELD_DEFAULTPNOESID = "defaultPnoesId";
	public static final String FIELD_OPENSOURCE = "openSource";
	public static final String FIELD_NICKNAME = "nickname";
	public static final String FIELD_PHONENOSTATUS = "phoneNoStatus";
	public static final String FIELD_EMAILSTATUS = "emailStatus";
	public static final String FIELD_ASSOCIATEPHONENO = "associatePhoneNo";

	public static final String COLUMN_ID = "id";
	public static final String COLUMN_PHONE_NO = "phone_no";
	public static final String COLUMN_ACCOUNT = "account";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_EMAIL = "email";
	public static final String COLUMN_PASSWORD = "password";
	public static final String COLUMN_CREDENTIALS_TYPE = "credentials_type";
	public static final String COLUMN_CREDENTIALS_NO = "credentials_no";
	public static final String COLUMN_TYPE = "type";
	public static final String COLUMN_COMPANY_ID = "company_id";
	public static final String COLUMN_GENDER = "gender";
	public static final String COLUMN_STATUS = "status";
	public static final String COLUMN_CREATE_TIME = "create_time";
	public static final String COLUMN_UPDATE_TIME = "update_time";
	public static final String COLUMN_CREDENTIALS_PATH = "credentials_path";
	public static final String COLUMN_DEFAULT_PNOES_ID = "default_pnoes_id";
	public static final String COLUMN_OPEN_SOURCE = "open_source";
	public static final String COLUMN_NICKNAME = "nickname";
	public static final String COLUMN_PHONE_NO_STATUS = "phone_no_status";
	public static final String COLUMN_EMAIL_STATUS = "email_status";
	public static final String COLUMN_ASSOCIATE_PHONE_NO = "associate_phone_no";

	public static final String ORDER_CREATE_TIME_ASC = " " + COLUMN_CREATE_TIME + " ASC ";

	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "account")
	private String account;
	@Column(name = "phone_no")
	private String phoneNo;
	@Column(name = "name")
	private String name;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "credentials_type")
	private Short credentialsType;
	@Column(name = "credentials_no")
	private String credentialsNo;
	@Column(name = "type")
	private Short type;
	@Column(name = "company_id")
	private String companyId;
	@Column(name = "gender")
	private Short gender;
	@Column(name = "status")
	private Short status;
	@Column(name = "create_time")
	private Date createTime;
	@Column(name = "update_time")
	private Date updateTime;
	@Column(name = "credentials_path")
	private String credentialsPath;
	@Column(name = "default_pnoes_id")
	private String defaultPnoesId;
	@Column(name = "open_source")
	private Short openSource;
	@Column(name = "nickname")
	private String nickname;
	@Column(name = "phone_no_status")
	private Short phoneNoStatus;
	@Column(name = "email_status")
	private Short emailStatus;
	@Column(name = "associate_phone_no")
	private String associatePhoneNo;

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
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * @param phoneNo
	 *            the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
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

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the credentialsType
	 */
	public Short getCredentialsType() {
		return credentialsType;
	}

	/**
	 * @param credentialsType
	 *            the credentialsType to set
	 */
	public void setCredentialsType(Short credentialsType) {
		this.credentialsType = credentialsType;
	}

	/**
	 * @return the credentialsNo
	 */
	public String getCredentialsNo() {
		return credentialsNo;
	}

	/**
	 * @param credentialsNo
	 *            the credentialsNo to set
	 */
	public void setCredentialsNo(String credentialsNo) {
		this.credentialsNo = credentialsNo;
	}

	/**
	 * @return the type
	 */
	public Short getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Short type) {
		this.type = type;
	}

	/**
	 * @return the companyId
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId
	 *            the companyId to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the gender
	 */
	public Short getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(Short gender) {
		this.gender = gender;
	}

	/**
	 * @return the status
	 */
	public Short getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Short status) {
		this.status = status;
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
	 * @return the credentialsPath
	 */
	public String getCredentialsPath() {
		return credentialsPath;
	}

	/**
	 * @param credentialsPath
	 *            the credentialsPath to set
	 */
	public void setCredentialsPath(String credentialsPath) {
		this.credentialsPath = credentialsPath;
	}

	/**
	 * @return the defaultPnoesId
	 */
	public String getDefaultPnoesId() {
		return defaultPnoesId;
	}

	/**
	 * @param defaultPnoesId
	 *            the defaultPnoesId to set
	 */
	public void setDefaultPnoesId(String defaultPnoesId) {
		this.defaultPnoesId = defaultPnoesId;
	}

	/**
	 * @return the openSource
	 */
	public Short getOpenSource() {
		return openSource;
	}

	/**
	 * @param openSource
	 *            the openSource to set
	 */
	public void setOpenSource(Short openSource) {
		this.openSource = openSource;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname
	 *            the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * @return the phoneNoStatus
	 */
	public Short getPhoneNoStatus() {
		return phoneNoStatus;
	}

	/**
	 * @param phoneNoStatus
	 *            the phoneNoStatus to set
	 */
	public void setPhoneNoStatus(Short phoneNoStatus) {
		this.phoneNoStatus = phoneNoStatus;
	}

	/**
	 * @return the emailStatus
	 */
	public Short getEmailStatus() {
		return emailStatus;
	}

	/**
	 * @param emailStatus
	 *            the emailStatus to set
	 */
	public void setEmailStatus(Short emailStatus) {
		this.emailStatus = emailStatus;
	}

	public String getAssociatePhoneNo() {
		return associatePhoneNo;
	}

	public void setAssociatePhoneNo(String associatePhoneNo) {
		this.associatePhoneNo = associatePhoneNo;
	}

}
