package com.renke.rdbao.beans.rdbao_2017.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.enums.CredentialsTypeEnum;
import com.renke.rdbao.beans.common.enums.EmailStatusEnum;
import com.renke.rdbao.beans.common.enums.GenderEnum;
import com.renke.rdbao.beans.common.enums.OpenSourceEnum;
import com.renke.rdbao.beans.common.enums.PhoneNoStatusEnum;
import com.renke.rdbao.beans.common.enums.StatusEnum4User;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_2017.pojo.EUser189;

/**
 * @author jgshun
 * @date 2016-12-28 下午6:53:30
 * @version 1.0
 */
public class EnhancedEUser189 extends BaseEnhanced {
	public EnhancedEUser189() {
	}

	public EnhancedEUser189(EUser189 user189) {
		BeanUtils.copyProperties(user189, this);
		if (user189.getCredentialsType() != null) {
			this.credentialsType = CredentialsTypeEnum.getCredentialsTypeEnumByCode(user189.getCredentialsType());
		}
		if (user189.getType() != null) {
			this.type = UserTypeEnum.getTypeEnumByCode(user189.getType());
		}
		if (user189.getGender() != null) {
			this.gender = GenderEnum.getGenderEnumByCode(user189.getGender());
		}
		if (user189.getStatus() != null) {
			this.status = StatusEnum4User.getStatusEnumByCode(user189.getStatus());
		}
		if (user189.getOpenSource() != null) {
			this.openSource = OpenSourceEnum.getOpenSourceEnumByCode(user189.getStatus());
		}
		if (user189.getCompanyId() != null && user189.getCompanyId().trim().length() > 0) {
			EnhancedECompany _EnhancedCompany = new EnhancedECompany();
			_EnhancedCompany.setId(user189.getCompanyId());
			this.setEnhancedECompany(_EnhancedCompany);
		}
		if (user189.getPhoneNoStatus() != null) {
			this.phoneNoStatus = PhoneNoStatusEnum.getPhoneNoStatusEnumByCode(user189.getPhoneNoStatus());
		}
		if (user189.getEmailStatus() != null) {
			this.emailStatus = EmailStatusEnum.getEmailStatusEnumByCode(user189.getEmailStatus());
		}
	}

	private String id;
	private String phoneNo;
	private String account;
	private String name;
	private String email;
	private String password;
	private CredentialsTypeEnum credentialsType;
	private String credentialsNo;
	private UserTypeEnum type;
	private EnhancedECompany enhancedECompany;
	private GenderEnum gender;
	private StatusEnum4User status;
	private Date createTime;
	private Date updateTime;
	private String credentialsPath;
	private String defaultPnoesId;
	private OpenSourceEnum openSource;
	private String nickname;
	private PhoneNoStatusEnum phoneNoStatus;
	private EmailStatusEnum emailStatus;
	private String associatePhoneNo;

	/**
	 * @return the phoneNoStatus
	 */
	public PhoneNoStatusEnum getPhoneNoStatus() {
		return phoneNoStatus;
	}

	/**
	 * @param phoneNoStatus
	 *            the phoneNoStatus to set
	 */
	public void setPhoneNoStatus(PhoneNoStatusEnum phoneNoStatus) {
		this.phoneNoStatus = phoneNoStatus;
	}

	/**
	 * @return the emailStatus
	 */
	public EmailStatusEnum getEmailStatus() {
		return emailStatus;
	}

	/**
	 * @param emailStatus
	 *            the emailStatus to set
	 */
	public void setEmailStatus(EmailStatusEnum emailStatus) {
		this.emailStatus = emailStatus;
	}

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
	public UserTypeEnum getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(UserTypeEnum type) {
		this.type = type;
	}

	/**
	 * @return the gender
	 */
	public GenderEnum getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}

	/**
	 * @return the status
	 */
	public StatusEnum4User getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(StatusEnum4User status) {
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
	public OpenSourceEnum getOpenSource() {
		return openSource;
	}

	/**
	 * @param openSource
	 *            the openSource to set
	 */
	public void setOpenSource(OpenSourceEnum openSource) {
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

	public String getAssociatePhoneNo() {
		return associatePhoneNo;
	}

	public void setAssociatePhoneNo(String associatePhoneNo) {
		this.associatePhoneNo = associatePhoneNo;
	}

	public EnhancedECompany getEnhancedECompany() {
		return enhancedECompany;
	}

	public void setEnhancedECompany(EnhancedECompany enhancedECompany) {
		this.enhancedECompany = enhancedECompany;
	}

}
