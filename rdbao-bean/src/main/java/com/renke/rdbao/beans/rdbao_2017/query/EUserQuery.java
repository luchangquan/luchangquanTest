package com.renke.rdbao.beans.rdbao_2017.query;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.renke.rdbao.beans.common.enums.CredentialsTypeEnum;
import com.renke.rdbao.beans.common.enums.EmailStatusEnum;
import com.renke.rdbao.beans.common.enums.GenderEnum;
import com.renke.rdbao.beans.common.enums.OpenSourceEnum;
import com.renke.rdbao.beans.common.enums.PhoneNoStatusEnum;
import com.renke.rdbao.beans.common.enums.StatusEnum4User;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;

/**
 * @author jgshun
 * @date 2017-4-12 下午6:48:18
 * @version 1.0
 */
public class EUserQuery implements Serializable {
	private List<String> ids;
	private List<String> accounts;
	private String like_account;
	private List<String> phoneNos;
	private String like_phoneNo;

	private List<String> names;
	private String like_name;
	private List<String> emails;
	private String like_email;

	private List<CredentialsTypeEnum> credentialsTypes;
	private List<String> credentialsNos;
	private String like_credentialsNo;

	private List<UserTypeEnum> userTypes;
	private List<GenderEnum> genders;
	private List<StatusEnum4User> statuses;

	private Date equalCreateTime;
	private Date equalAndBeforCreateTime;
	private Date equalAndAfterCreateTime;
	private Date beforCreateTime;
	private Date afterCreateTime;

	private Date equalUpdateTime;
	private Date equalAndBeforUpdateTime;
	private Date equalAndAfterUpdateTime;
	private Date beforUpdateTime;
	private Date afterUpdateTime;

	private List<String> nppIds;
	private List<OpenSourceEnum> openSources;
	private List<String> nicknames;
	private String like_nicknames;
	private List<PhoneNoStatusEnum> phoneNoStatuses;
	private List<EmailStatusEnum> emailStatuses;

	private List<String> associatePhoneNos;
	private String like_associatePhoneNo;

	/**
	 * @return the ids
	 */
	public List<String> getIds() {
		return ids;
	}

	/**
	 * @param ids
	 *            the ids to set
	 */
	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	/**
	 * @return the accounts
	 */
	public List<String> getAccounts() {
		return accounts;
	}

	/**
	 * @param accounts
	 *            the accounts to set
	 */
	public void setAccounts(List<String> accounts) {
		this.accounts = accounts;
	}

	/**
	 * @return the like_account
	 */
	public String getLike_account() {
		return like_account;
	}

	/**
	 * @param like_account
	 *            the like_account to set
	 */
	public void setLike_account(String like_account) {
		this.like_account = like_account;
	}

	/**
	 * @return the phoneNos
	 */
	public List<String> getPhoneNos() {
		return phoneNos;
	}

	/**
	 * @param phoneNos
	 *            the phoneNos to set
	 */
	public void setPhoneNos(List<String> phoneNos) {
		this.phoneNos = phoneNos;
	}

	/**
	 * @return the like_phoneNo
	 */
	public String getLike_phoneNo() {
		return like_phoneNo;
	}

	/**
	 * @param like_phoneNo
	 *            the like_phoneNo to set
	 */
	public void setLike_phoneNo(String like_phoneNo) {
		this.like_phoneNo = like_phoneNo;
	}

	/**
	 * @return the names
	 */
	public List<String> getNames() {
		return names;
	}

	/**
	 * @param names
	 *            the names to set
	 */
	public void setNames(List<String> names) {
		this.names = names;
	}

	/**
	 * @return the like_name
	 */
	public String getLike_name() {
		return like_name;
	}

	/**
	 * @param like_name
	 *            the like_name to set
	 */
	public void setLike_name(String like_name) {
		this.like_name = like_name;
	}

	/**
	 * @return the emails
	 */
	public List<String> getEmails() {
		return emails;
	}

	/**
	 * @param emails
	 *            the emails to set
	 */
	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	/**
	 * @return the like_email
	 */
	public String getLike_email() {
		return like_email;
	}

	/**
	 * @param like_email
	 *            the like_email to set
	 */
	public void setLike_email(String like_email) {
		this.like_email = like_email;
	}

	/**
	 * @return the credentialsTypes
	 */
	public List<CredentialsTypeEnum> getCredentialsTypes() {
		return credentialsTypes;
	}

	/**
	 * @param credentialsTypes
	 *            the credentialsTypes to set
	 */
	public void setCredentialsTypes(List<CredentialsTypeEnum> credentialsTypes) {
		this.credentialsTypes = credentialsTypes;
	}

	/**
	 * @return the credentialsNos
	 */
	public List<String> getCredentialsNos() {
		return credentialsNos;
	}

	/**
	 * @param credentialsNos
	 *            the credentialsNos to set
	 */
	public void setCredentialsNos(List<String> credentialsNos) {
		this.credentialsNos = credentialsNos;
	}

	/**
	 * @return the like_credentialsNo
	 */
	public String getLike_credentialsNo() {
		return like_credentialsNo;
	}

	/**
	 * @param like_credentialsNo
	 *            the like_credentialsNo to set
	 */
	public void setLike_credentialsNo(String like_credentialsNo) {
		this.like_credentialsNo = like_credentialsNo;
	}

	/**
	 * @return the userTypes
	 */
	public List<UserTypeEnum> getUserTypes() {
		return userTypes;
	}

	/**
	 * @param userTypes
	 *            the userTypes to set
	 */
	public void setUserTypes(List<UserTypeEnum> userTypes) {
		this.userTypes = userTypes;
	}

	/**
	 * @return the genders
	 */
	public List<GenderEnum> getGenders() {
		return genders;
	}

	/**
	 * @param genders
	 *            the genders to set
	 */
	public void setGenders(List<GenderEnum> genders) {
		this.genders = genders;
	}

	/**
	 * @return the statuses
	 */
	public List<StatusEnum4User> getStatuses() {
		return statuses;
	}

	/**
	 * @param statuses
	 *            the statuses to set
	 */
	public void setStatuses(List<StatusEnum4User> statuses) {
		this.statuses = statuses;
	}

	/**
	 * @return the equalCreateTime
	 */
	public Date getEqualCreateTime() {
		return equalCreateTime;
	}

	/**
	 * @param equalCreateTime
	 *            the equalCreateTime to set
	 */
	public void setEqualCreateTime(Date equalCreateTime) {
		this.equalCreateTime = equalCreateTime;
	}

	/**
	 * @return the equalAndBeforCreateTime
	 */
	public Date getEqualAndBeforCreateTime() {
		return equalAndBeforCreateTime;
	}

	/**
	 * @param equalAndBeforCreateTime
	 *            the equalAndBeforCreateTime to set
	 */
	public void setEqualAndBeforCreateTime(Date equalAndBeforCreateTime) {
		this.equalAndBeforCreateTime = equalAndBeforCreateTime;
	}

	/**
	 * @return the equalAndAfterCreateTime
	 */
	public Date getEqualAndAfterCreateTime() {
		return equalAndAfterCreateTime;
	}

	/**
	 * @param equalAndAfterCreateTime
	 *            the equalAndAfterCreateTime to set
	 */
	public void setEqualAndAfterCreateTime(Date equalAndAfterCreateTime) {
		this.equalAndAfterCreateTime = equalAndAfterCreateTime;
	}

	/**
	 * @return the beforCreateTime
	 */
	public Date getBeforCreateTime() {
		return beforCreateTime;
	}

	/**
	 * @param beforCreateTime
	 *            the beforCreateTime to set
	 */
	public void setBeforCreateTime(Date beforCreateTime) {
		this.beforCreateTime = beforCreateTime;
	}

	/**
	 * @return the afterCreateTime
	 */
	public Date getAfterCreateTime() {
		return afterCreateTime;
	}

	/**
	 * @param afterCreateTime
	 *            the afterCreateTime to set
	 */
	public void setAfterCreateTime(Date afterCreateTime) {
		this.afterCreateTime = afterCreateTime;
	}

	/**
	 * @return the equalUpdateTime
	 */
	public Date getEqualUpdateTime() {
		return equalUpdateTime;
	}

	/**
	 * @param equalUpdateTime
	 *            the equalUpdateTime to set
	 */
	public void setEqualUpdateTime(Date equalUpdateTime) {
		this.equalUpdateTime = equalUpdateTime;
	}

	/**
	 * @return the equalAndBeforUpdateTime
	 */
	public Date getEqualAndBeforUpdateTime() {
		return equalAndBeforUpdateTime;
	}

	/**
	 * @param equalAndBeforUpdateTime
	 *            the equalAndBeforUpdateTime to set
	 */
	public void setEqualAndBeforUpdateTime(Date equalAndBeforUpdateTime) {
		this.equalAndBeforUpdateTime = equalAndBeforUpdateTime;
	}

	/**
	 * @return the equalAndAfterUpdateTime
	 */
	public Date getEqualAndAfterUpdateTime() {
		return equalAndAfterUpdateTime;
	}

	/**
	 * @param equalAndAfterUpdateTime
	 *            the equalAndAfterUpdateTime to set
	 */
	public void setEqualAndAfterUpdateTime(Date equalAndAfterUpdateTime) {
		this.equalAndAfterUpdateTime = equalAndAfterUpdateTime;
	}

	/**
	 * @return the beforUpdateTime
	 */
	public Date getBeforUpdateTime() {
		return beforUpdateTime;
	}

	/**
	 * @param beforUpdateTime
	 *            the beforUpdateTime to set
	 */
	public void setBeforUpdateTime(Date beforUpdateTime) {
		this.beforUpdateTime = beforUpdateTime;
	}

	/**
	 * @return the afterUpdateTime
	 */
	public Date getAfterUpdateTime() {
		return afterUpdateTime;
	}

	/**
	 * @param afterUpdateTime
	 *            the afterUpdateTime to set
	 */
	public void setAfterUpdateTime(Date afterUpdateTime) {
		this.afterUpdateTime = afterUpdateTime;
	}

	/**
	 * @return the nppIds
	 */
	public List<String> getNppIds() {
		return nppIds;
	}

	/**
	 * @param nppIds
	 *            the nppIds to set
	 */
	public void setNppIds(List<String> nppIds) {
		this.nppIds = nppIds;
	}

	/**
	 * @return the openSources
	 */
	public List<OpenSourceEnum> getOpenSources() {
		return openSources;
	}

	/**
	 * @param openSources
	 *            the openSources to set
	 */
	public void setOpenSources(List<OpenSourceEnum> openSources) {
		this.openSources = openSources;
	}

	/**
	 * @return the nicknames
	 */
	public List<String> getNicknames() {
		return nicknames;
	}

	/**
	 * @param nicknames
	 *            the nicknames to set
	 */
	public void setNicknames(List<String> nicknames) {
		this.nicknames = nicknames;
	}

	/**
	 * @return the like_nicknames
	 */
	public String getLike_nicknames() {
		return like_nicknames;
	}

	/**
	 * @param like_nicknames
	 *            the like_nicknames to set
	 */
	public void setLike_nicknames(String like_nicknames) {
		this.like_nicknames = like_nicknames;
	}

	/**
	 * @return the phoneNoStatuses
	 */
	public List<PhoneNoStatusEnum> getPhoneNoStatuses() {
		return phoneNoStatuses;
	}

	/**
	 * @param phoneNoStatuses
	 *            the phoneNoStatuses to set
	 */
	public void setPhoneNoStatuses(List<PhoneNoStatusEnum> phoneNoStatuses) {
		this.phoneNoStatuses = phoneNoStatuses;
	}

	/**
	 * @return the emailStatuses
	 */
	public List<EmailStatusEnum> getEmailStatuses() {
		return emailStatuses;
	}

	/**
	 * @param emailStatuses
	 *            the emailStatuses to set
	 */
	public void setEmailStatuses(List<EmailStatusEnum> emailStatuses) {
		this.emailStatuses = emailStatuses;
	}

	/**
	 * @return the associatePhoneNos
	 */
	public List<String> getAssociatePhoneNos() {
		return associatePhoneNos;
	}

	/**
	 * @param associatePhoneNos
	 *            the associatePhoneNos to set
	 */
	public void setAssociatePhoneNos(List<String> associatePhoneNos) {
		this.associatePhoneNos = associatePhoneNos;
	}

	/**
	 * @return the like_associatePhoneNo
	 */
	public String getLike_associatePhoneNo() {
		return like_associatePhoneNo;
	}

	/**
	 * @param like_associatePhoneNo
	 *            the like_associatePhoneNo to set
	 */
	public void setLike_associatePhoneNo(String like_associatePhoneNo) {
		this.like_associatePhoneNo = like_associatePhoneNo;
	}

}
