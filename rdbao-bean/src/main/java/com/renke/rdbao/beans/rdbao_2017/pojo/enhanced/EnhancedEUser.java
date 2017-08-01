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
import com.renke.rdbao.beans.rdbao_2017.pojo.EUser;

/**
 * @author jgshun
 * @date 2017-4-7 下午5:54:29
 * @version 1.0
 */
public class EnhancedEUser extends BaseEnhanced {
	public EnhancedEUser() {
	}

	public EnhancedEUser(EUser eUser) {
		BeanUtils.copyProperties(eUser, this);
		if (eUser.getCredentialsType() != null) {
			this.credentialsType = CredentialsTypeEnum.getCredentialsTypeEnumByCode(eUser.getCredentialsType());
		}
		if (eUser.getType() != null) {
			this.type = UserTypeEnum.getTypeEnumByCode(eUser.getType());
		}
		if (eUser.getGender() != null) {
			this.gender = GenderEnum.getGenderEnumByCode(eUser.getGender());
		}
		if (eUser.getStatus() != null) {
			this.status = StatusEnum4User.getStatusEnumByCode(eUser.getStatus());
		}
		if (eUser.getOpenSource() != null) {
			this.openSource = OpenSourceEnum.getOpenSourceEnumByCode(eUser.getStatus());
		}
		if (eUser.getCompanyId() != null && eUser.getCompanyId().trim().length() > 0) {
			EnhancedECompany _EnhancedCompany = new EnhancedECompany();
			_EnhancedCompany.setId(eUser.getCompanyId());
			this.setEnhancedECompany(_EnhancedCompany);
		}
		if (eUser.getPhoneNoStatus() != null) {
			this.phoneNoStatus = PhoneNoStatusEnum.getPhoneNoStatusEnumByCode(eUser.getPhoneNoStatus());
		}
		if (eUser.getEmailStatus() != null) {
			this.emailStatus = EmailStatusEnum.getEmailStatusEnumByCode(eUser.getEmailStatus());
		}
		if (eUser.getNppId() != null && eUser.getNppId().length() > 0) {
			EnhancedDNpp _EnhancedDNpp = new EnhancedDNpp();
			_EnhancedDNpp.setId(eUser.getNppId());
			this.enhancedDNpp = _EnhancedDNpp;
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
	private EnhancedDNpp enhancedDNpp;
	private OpenSourceEnum openSource;
	private String nickname;
	private PhoneNoStatusEnum phoneNoStatus;
	private EmailStatusEnum emailStatus;
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
	 * @return the associatePhoneNo
	 */
	public String getAssociatePhoneNo() {
		return associatePhoneNo;
	}

	/**
	 * @param associatePhoneNo
	 *            the associatePhoneNo to set
	 */
	public void setAssociatePhoneNo(String associatePhoneNo) {
		this.associatePhoneNo = associatePhoneNo;
	}

	public EnhancedDNpp getEnhancedDNpp() {
		return enhancedDNpp;
	}

	public void setEnhancedDNpp(EnhancedDNpp enhancedDNpp) {
		this.enhancedDNpp = enhancedDNpp;
	}

	public EnhancedECompany getEnhancedECompany() {
		return enhancedECompany;
	}

	public void setEnhancedECompany(EnhancedECompany enhancedECompany) {
		this.enhancedECompany = enhancedECompany;
	}

}
