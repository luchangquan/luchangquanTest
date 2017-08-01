package com.renke.rdbao.beans.rdbao_v3.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2017-2-21 下午6:32:42
 * @version 1.0
 */
@Table(name = "Users")
public class Users extends BasePo {
	public static final String FIELD_ID = "id";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_ACCOUNT = "account";
	public static final String FIELD_PASSWORD = "password";
	public static final String FIELD_MOBILE = "mobile";
	public static final String FIELD_CREDENTIALSTYPE = "credentialsType";
	public static final String FIELD_CREDENTIALSNUMBER = "credentialsNumber";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_LASTUPDATETIME = "lastUpdateTime";
	public static final String FIELD_USERTYPE = "userType";
	public static final String FIELD_COMPANYID = "companyId";
	public static final String FIELD_EMAIL = "email";
	public static final String FIELD_GENDER = "gender";
	public static final String FIELD_IDENTITY = "identity";
	public static final String FIELD_IDENTITYBACK = "identityBack";
	public static final String FIELD_SIGN = "sign";
	public static final String FIELD_STAMP = "stamp";
	public static final String FIELD_FINGERPRINT = "fingerPrint";
	public static final String FIELD_STATE = "state";
	public static final String FIELD_PERMISSION = "permission";
	public static final String FIELD_AVATAR = "avatar";
	public static final String FIELD_CREDENTIALSPATH = "credentialsPath";
	public static final String FIELD_REALNAME = "realName";
	public static final String FIELD_SECURITYQUESTION = "securityQuestion";
	public static final String FIELD_SECURITYANSWER = "securityAnswer";
	public static final String FIELD_DISABLED = "disabled";
	public static final String FIELD_MOBILEACTIVE = "mobileActive";
	public static final String FIELD_EMAILACTIVE = "emailActive";
	public static final String FIELD_DEFAULTPNOESID = "defaultPnoesId";
	public static final String FIELD_CREATEUSER = "createUser";
	public static final String FIELD_LASTUPDATEUSER = "lastUpdateUser";
	public static final String FIELD_THIRDID = "thirdID";
	public static final String FIELD_THIRDTYPE = "thirdType";
	public static final String FIELD_OPENSOURCE = "openSource";
	public static final String FIELD_UPDATETIME = "updateTime";
	public static final String FIELD_INFOSOURCE = "infoSource";
	public static final String FIELD_NICKNAME = "nickname";

	public static final String COLUMN_ID = "Id";
	public static final String COLUMN_NAME = "Name";
	public static final String COLUMN_ACCOUNT = "Account";
	public static final String COLUMN_PASSWORD = "Password";
	public static final String COLUMN_MOBILE = "Mobile";
	public static final String COLUMN_CREDENTIALS_TYPE = "Credentials_Type";
	public static final String COLUMN_CREDENTIALS_NUMBER = "Credentials_Number";
	public static final String COLUMN_CREATETIME = "CreateTime";
	public static final String COLUMN_LASTUPDATETIME = "LastUpdateTime";
	public static final String COLUMN_USERTYPE = "UserType";
	public static final String COLUMN_COMPANY_ID = "Company_Id";
	public static final String COLUMN_EMAIL = "Email";
	public static final String COLUMN_GENDER = "Gender";
	public static final String COLUMN_IDENTITY = "Identity";
	public static final String COLUMN_IDENTITYBACK = "IdentityBack";
	public static final String COLUMN_SIGN = "Sign";
	public static final String COLUMN_STAMP = "Stamp";
	public static final String COLUMN_FINGERPRINT = "FingerPrint";
	public static final String COLUMN_STATE = "State";
	public static final String COLUMN_PERMISSION = "Permission";
	public static final String COLUMN_AVATAR = "Avatar";
	public static final String COLUMN_CREDENTIALSPATH = "CredentialsPath";
	public static final String COLUMN_REALNAME = "RealName";
	public static final String COLUMN_SECURITYQUESTION = "SecurityQuestion";
	public static final String COLUMN_SECURITYANSWER = "SecurityAnswer";
	public static final String COLUMN_DISABLED = "Disabled";
	public static final String COLUMN_MOBILEACTIVE = "MobileActive";
	public static final String COLUMN_EMAILACTIVE = "EmailActive";
	public static final String COLUMN_DEFAULTPNOESID = "DefaultPnoesId";
	public static final String COLUMN_CREATEUSER = "CreateUser";
	public static final String COLUMN_LASTUPDATEUSER = "LastUpdateUser";
	public static final String COLUMN_THIRDID = "ThirdID";
	public static final String COLUMN_THIRDTYPE = "ThirdType";
	public static final String COLUMN_OPENSOURCE = "OpenSource";
	public static final String COLUMN_UPDATETIME = "UpdateTime";
	public static final String COLUMN_INFOSOURCE = "InfoSource";
	public static final String COLUMN_NICKNAME = "Nickname";

	public static final String ORDER_CREATE_TIME_ASC = " " + COLUMN_CREATETIME + " ASC ";

	@javax.persistence.Id
	@Column(name = "Id")
	private String id;
	@Column(name = "Name")
	private String name;
	@Column(name = "Account")
	private String account;
	@Column(name = "Password")
	private String password;
	@Column(name = "Mobile")
	private String mobile;
	@Column(name = "Credentials_Type")
	private Short credentialsType;
	@Column(name = "Credentials_Number")
	private String credentialsNumber;
	@Column(name = "CreateTime")
	private Date createTime;
	@Column(name = "LastUpdateTime")
	private Date lastUpdateTime;
	@Column(name = "UserType")
	private Short userType;
	@Column(name = "Company_Id")
	private String companyId;
	@Column(name = "Email")
	private String email;
	@Column(name = "Gender")
	private String gender;
	@Column(name = "Identity")
	private String identity;
	@Column(name = "IdentityBack")
	private String identityBack;
	@Column(name = "Sign")
	private String sign;
	@Column(name = "Stamp")
	private String stamp;
	@Column(name = "FingerPrint")
	private String fingerPrint;
	@Column(name = "State")
	private Short state;
	@Column(name = "Permission")
	private Integer permission;
	@Column(name = "Avatar")
	private String avatar;
	@Column(name = "CredentialsPath")
	private String credentialsPath;
	@Column(name = "RealName")
	private String realName;
	@Column(name = "SecurityQuestion")
	private String securityQuestion;
	@Column(name = "SecurityAnswer")
	private String securityAnswer;
	@Column(name = "Disabled")
	private Short disabled;
	@Column(name = "MobileActive")
	private Short mobileActive;
	@Column(name = "EmailActive")
	private Short emailActive;
	@Column(name = "DefaultPnoesId")
	private String defaultPnoesId;
	@Column(name = "CreateUser")
	private String createUser;
	@Column(name = "LastUpdateUser")
	private String lastUpdateUser;
	@Column(name = "ThirdID")
	private String thirdID;
	@Column(name = "ThirdType")
	private Short thirdType;
	@Column(name = "OpenSource")
	private Short openSource;
	@Column(name = "UpdateTime")
	private Date updateTime;
	@Column(name = "InfoSource")
	private Short infoSource;
	@Column(name = "Nickname")
	private String nickname;

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
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account
	 *            the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
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
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	 * @return the credentialsNumber
	 */
	public String getCredentialsNumber() {
		return credentialsNumber;
	}

	/**
	 * @param credentialsNumber
	 *            the credentialsNumber to set
	 */
	public void setCredentialsNumber(String credentialsNumber) {
		this.credentialsNumber = credentialsNumber;
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
	 * @return the lastUpdateTime
	 */
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	/**
	 * @param lastUpdateTime
	 *            the lastUpdateTime to set
	 */
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	/**
	 * @return the userType
	 */
	public Short getUserType() {
		return userType;
	}

	/**
	 * @param userType
	 *            the userType to set
	 */
	public void setUserType(Short userType) {
		this.userType = userType;
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
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the identity
	 */
	public String getIdentity() {
		return identity;
	}

	/**
	 * @param identity
	 *            the identity to set
	 */
	public void setIdentity(String identity) {
		this.identity = identity;
	}

	/**
	 * @return the identityBack
	 */
	public String getIdentityBack() {
		return identityBack;
	}

	/**
	 * @param identityBack
	 *            the identityBack to set
	 */
	public void setIdentityBack(String identityBack) {
		this.identityBack = identityBack;
	}

	/**
	 * @return the sign
	 */
	public String getSign() {
		return sign;
	}

	/**
	 * @param sign
	 *            the sign to set
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}

	/**
	 * @return the stamp
	 */
	public String getStamp() {
		return stamp;
	}

	/**
	 * @param stamp
	 *            the stamp to set
	 */
	public void setStamp(String stamp) {
		this.stamp = stamp;
	}

	/**
	 * @return the fingerPrint
	 */
	public String getFingerPrint() {
		return fingerPrint;
	}

	/**
	 * @param fingerPrint
	 *            the fingerPrint to set
	 */
	public void setFingerPrint(String fingerPrint) {
		this.fingerPrint = fingerPrint;
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
	 * @return the permission
	 */
	public Integer getPermission() {
		return permission;
	}

	/**
	 * @param permission
	 *            the permission to set
	 */
	public void setPermission(Integer permission) {
		this.permission = permission;
	}

	/**
	 * @return the avatar
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * @param avatar
	 *            the avatar to set
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
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
	 * @return the securityQuestion
	 */
	public String getSecurityQuestion() {
		return securityQuestion;
	}

	/**
	 * @param securityQuestion
	 *            the securityQuestion to set
	 */
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	/**
	 * @return the securityAnswer
	 */
	public String getSecurityAnswer() {
		return securityAnswer;
	}

	/**
	 * @param securityAnswer
	 *            the securityAnswer to set
	 */
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	/**
	 * @return the disabled
	 */
	public Short getDisabled() {
		return disabled;
	}

	/**
	 * @param disabled
	 *            the disabled to set
	 */
	public void setDisabled(Short disabled) {
		this.disabled = disabled;
	}

	/**
	 * @return the mobileActive
	 */
	public Short getMobileActive() {
		return mobileActive;
	}

	/**
	 * @param mobileActive
	 *            the mobileActive to set
	 */
	public void setMobileActive(Short mobileActive) {
		this.mobileActive = mobileActive;
	}

	/**
	 * @return the emailActive
	 */
	public Short getEmailActive() {
		return emailActive;
	}

	/**
	 * @param emailActive
	 *            the emailActive to set
	 */
	public void setEmailActive(Short emailActive) {
		this.emailActive = emailActive;
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
	 * @return the lastUpdateUser
	 */
	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	/**
	 * @param lastUpdateUser
	 *            the lastUpdateUser to set
	 */
	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	/**
	 * @return the thirdID
	 */
	public String getThirdID() {
		return thirdID;
	}

	/**
	 * @param thirdID
	 *            the thirdID to set
	 */
	public void setThirdID(String thirdID) {
		this.thirdID = thirdID;
	}

	/**
	 * @return the thirdType
	 */
	public Short getThirdType() {
		return thirdType;
	}

	/**
	 * @param thirdType
	 *            the thirdType to set
	 */
	public void setThirdType(Short thirdType) {
		this.thirdType = thirdType;
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
	 * @return the infoSource
	 */
	public Short getInfoSource() {
		return infoSource;
	}

	/**
	 * @param infoSource
	 *            the infoSource to set
	 */
	public void setInfoSource(Short infoSource) {
		this.infoSource = infoSource;
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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

}
