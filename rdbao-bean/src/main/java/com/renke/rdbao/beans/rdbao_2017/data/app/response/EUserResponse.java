package com.renke.rdbao.beans.rdbao_2017.data.app.response;

import com.renke.rdbao.beans.data.response.base.BaseResponseData;

/**
 * @author jgshun
 * @date 2017-3-24 下午3:21:56
 * @version 1.0
 */
public class EUserResponse extends BaseResponseData {
	private String id;
	private String account;
	private String phoneNo;
	private String name;
	private String email;
	private Short credentialsType;
	private String credentialsNo;
	private short type;
	private ECompanyResponse company;
	private short gender;
	private DNppResponse defaultNpp;
	private Short openSource;
	private String nickname;
	private short phoneNoStatus;
	private short emailStatus;

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
	public short getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(short type) {
		this.type = type;
	}

	/**
	 * @return the company
	 */
	public ECompanyResponse getCompany() {
		return company;
	}

	/**
	 * @param company
	 *            the company to set
	 */
	public void setCompany(ECompanyResponse company) {
		this.company = company;
	}

	/**
	 * @return the gender
	 */
	public short getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(short gender) {
		this.gender = gender;
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
	public short getPhoneNoStatus() {
		return phoneNoStatus;
	}

	/**
	 * @param phoneNoStatus
	 *            the phoneNoStatus to set
	 */
	public void setPhoneNoStatus(short phoneNoStatus) {
		this.phoneNoStatus = phoneNoStatus;
	}

	/**
	 * @return the emailStatus
	 */
	public short getEmailStatus() {
		return emailStatus;
	}

	/**
	 * @param emailStatus
	 *            the emailStatus to set
	 */
	public void setEmailStatus(short emailStatus) {
		this.emailStatus = emailStatus;
	}

	public DNppResponse getDefaultNpp() {
		return defaultNpp;
	}

	public void setDefaultNpp(DNppResponse defaultNpp) {
		this.defaultNpp = defaultNpp;
	}

}
