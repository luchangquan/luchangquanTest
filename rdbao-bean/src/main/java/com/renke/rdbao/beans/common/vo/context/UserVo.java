package com.renke.rdbao.beans.common.vo.context;

import java.util.Date;

import com.renke.rdbao.beans.common.enums.TenantEnum;
import com.renke.rdbao.beans.common.vo.BaseVo;

/**
 * @author jgshun
 * @date 2017-2-22 上午10:37:39
 * @version 1.0
 */
public class UserVo extends BaseVo {
	private String id;
	private String account;
	private String phoneNo;
	private String name;
	private String email;
	private String password;
	private Short credentialsType;
	private String credentialsNo;
	private Short type;
	private String companyId;
	private Short gender;
	private Short status;
	private Short disabled;// 实时保验证此状态+status 公证录音用户验证status即可
	private Date createTime;
	private Date updateTime;
	private String credentialsPath;
	private String defaultPnoesId;
	private Short openSource;
	private String nickname;
	private Short phoneNoStatus;
	private Short emailStatus;
	private String associatePhoneNo;// 关联手机号（固话接收短信）

	private TenantEnum tenant;// 租户类型

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

	public Short getDisabled() {
		return disabled;
	}

	public void setDisabled(Short disabled) {
		this.disabled = disabled;
	}

	public String getAssociatePhoneNo() {
		return associatePhoneNo;
	}

	public void setAssociatePhoneNo(String associatePhoneNo) {
		this.associatePhoneNo = associatePhoneNo;
	}

	public TenantEnum getTenant() {
		return tenant;
	}

	public void setTenant(TenantEnum tenant) {
		this.tenant = tenant;
	}

}
