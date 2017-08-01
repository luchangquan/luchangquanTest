package com.renke.rdbao.beans.rdbao_v3.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.enums.CredentialsTypeEnum;
import com.renke.rdbao.beans.common.enums.EmailStatusEnum;
import com.renke.rdbao.beans.common.enums.PhoneNoStatusEnum;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_v3.enums.forusers.DisabledEnum4Users;
import com.renke.rdbao.beans.rdbao_v3.pojo.Users;

/**
 * @author jgshun
 * @date 2017-3-2 下午5:14:53
 * @version 1.0
 */
public class EnhancedUsers extends BaseEnhanced {

	public EnhancedUsers() {
	}

	public EnhancedUsers(Users users) {
		BeanUtils.copyProperties(users, this);
		if (users.getCredentialsType() != null) {
			this.credentialsType = CredentialsTypeEnum.getCredentialsTypeEnumByCode(users.getCredentialsType());
		}
		if (users.getUserType() != null) {
			this.userType = UserTypeEnum.getTypeEnumByCode(users.getUserType());
		}
		if (users.getCompanyId() != null && users.getCompanyId().length() > 0) {
			EnhancedCompanies _EnhancedCompanies = new EnhancedCompanies();
			_EnhancedCompanies.setId(users.getCompanyId());
			this.enhancedCompanies = _EnhancedCompanies;
		}
		if (users.getDisabled() != null) {
			this.disabled = DisabledEnum4Users.getDisabledEnumByCode(users.getDisabled());
		}

		if (users.getDefaultPnoesId() != null && users.getDefaultPnoesId().length() > 0) {
			EnhancedPNOes _EnhancedPNOes = new EnhancedPNOes();
			_EnhancedPNOes.setId(users.getDefaultPnoesId());
			this.defaultEnhancedPNOes = _EnhancedPNOes;
		}

		if (users.getMobileActive() != null) {
			this.mobileActive = PhoneNoStatusEnum.getPhoneNoStatusEnumByCode(users.getMobileActive());
		}
		if (users.getEmailActive() != null) {
			this.emailActive = EmailStatusEnum.getEmailStatusEnumByCode(users.getEmailActive());
		}
	}

	private String id;
	private String name;
	private String account;
	private String password;
	private String mobile;
	private CredentialsTypeEnum credentialsType;
	private String credentialsNumber;
	private Date createTime;
	private Date lastUpdateTime;
	private UserTypeEnum userType;
	private EnhancedCompanies enhancedCompanies;
	private String email;
	private String gender;
	private String identity;
	private String identityBack;
	private String sign;
	private String stamp;
	private String fingerPrint;
	private Short state;
	private Integer permission;
	private String avatar;
	private String credentialsPath;
	private String realName;
	private String securityQuestion;
	private String securityAnswer;
	private DisabledEnum4Users disabled;
	private PhoneNoStatusEnum mobileActive;
	private EmailStatusEnum emailActive;
	private EnhancedPNOes defaultEnhancedPNOes;
	private String createUser;
	private String lastUpdateUser;
	private String thirdID;
	private Short thirdType;
	private Short openSource;
	private Date updateTime;
	private Short infoSource;
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
	public CredentialsTypeEnum getCredentialsType() {
		return credentialsType;
	}

	/**
	 * @param credentialsType
	 *            the credentialsType to set
	 */
	public void setCredentialsType(CredentialsTypeEnum credentialsType) {
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
	public UserTypeEnum getUserType() {
		return userType;
	}

	/**
	 * @param userType
	 *            the userType to set
	 */
	public void setUserType(UserTypeEnum userType) {
		this.userType = userType;
	}

	/**
	 * @return the enhancedCompanies
	 */
	public EnhancedCompanies getEnhancedCompanies() {
		return enhancedCompanies;
	}

	/**
	 * @param enhancedCompanies
	 *            the enhancedCompanies to set
	 */
	public void setEnhancedCompanies(EnhancedCompanies enhancedCompanies) {
		this.enhancedCompanies = enhancedCompanies;
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
	public DisabledEnum4Users getDisabled() {
		return disabled;
	}

	/**
	 * @param disabled
	 *            the disabled to set
	 */
	public void setDisabled(DisabledEnum4Users disabled) {
		this.disabled = disabled;
	}

	/**
	 * @return the mobileActive
	 */
	public PhoneNoStatusEnum getMobileActive() {
		return mobileActive;
	}

	/**
	 * @param mobileActive
	 *            the mobileActive to set
	 */
	public void setMobileActive(PhoneNoStatusEnum mobileActive) {
		this.mobileActive = mobileActive;
	}

	/**
	 * @return the emailActive
	 */
	public EmailStatusEnum getEmailActive() {
		return emailActive;
	}

	/**
	 * @param emailActive
	 *            the emailActive to set
	 */
	public void setEmailActive(EmailStatusEnum emailActive) {
		this.emailActive = emailActive;
	}

	/**
	 * @return the defaultEnhancedPNOes
	 */
	public EnhancedPNOes getDefaultEnhancedPNOes() {
		return defaultEnhancedPNOes;
	}

	/**
	 * @param defaultEnhancedPNOes
	 *            the defaultEnhancedPNOes to set
	 */
	public void setDefaultEnhancedPNOes(EnhancedPNOes defaultEnhancedPNOes) {
		this.defaultEnhancedPNOes = defaultEnhancedPNOes;
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
