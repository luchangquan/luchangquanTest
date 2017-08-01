package com.renke.rdbao.beans.rdbao_2017.query;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.renke.rdbao.beans.common.enums.TenantEnum;
import com.renke.rdbao.beans.rdbao_2017.enums.forenotarizationreserve.StatusEnum4ENotarizationReserve;

/**
 * @author jgshun
 * @date 2017-4-14 下午2:21:27
 * @version 1.0
 */
public class ENotarizationReserveQuery implements Serializable {
	private List<String> ids;
	private List<String> names;
	private String like_name;
	private List<String> descriptions;
	private String like_description;
	private String like_name_or_description;
	private List<String> phoneNos;
	private String like_phoneNo;
	private List<String> emails;
	private String like_email;
	private List<String> agentNames;
	private String like_agentName;
	private List<StatusEnum4ENotarizationReserve> statuses;
	private List<String> nppCodes;
	private List<String> userIds;
	private List<TenantEnum> tenants;

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
	 * @return the descriptions
	 */
	public List<String> getDescriptions() {
		return descriptions;
	}

	/**
	 * @param descriptions
	 *            the descriptions to set
	 */
	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}

	/**
	 * @return the like_description
	 */
	public String getLike_description() {
		return like_description;
	}

	/**
	 * @param like_description
	 *            the like_description to set
	 */
	public void setLike_description(String like_description) {
		this.like_description = like_description;
	}

	/**
	 * @return the like_name_or_description
	 */
	public String getLike_name_or_description() {
		return like_name_or_description;
	}

	/**
	 * @param like_name_or_description
	 *            the like_name_or_description to set
	 */
	public void setLike_name_or_description(String like_name_or_description) {
		this.like_name_or_description = like_name_or_description;
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
	 * @return the statuses
	 */
	public List<StatusEnum4ENotarizationReserve> getStatuses() {
		return statuses;
	}

	/**
	 * @param statuses
	 *            the statuses to set
	 */
	public void setStatuses(List<StatusEnum4ENotarizationReserve> statuses) {
		this.statuses = statuses;
	}

	/**
	 * @return the userIds
	 */
	public List<String> getUserIds() {
		return userIds;
	}

	/**
	 * @param userIds
	 *            the userIds to set
	 */
	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	}

	/**
	 * @return the tenants
	 */
	public List<TenantEnum> getTenants() {
		return tenants;
	}

	/**
	 * @param tenants
	 *            the tenants to set
	 */
	public void setTenants(List<TenantEnum> tenants) {
		this.tenants = tenants;
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

	public List<String> getNppCodes() {
		return nppCodes;
	}

	public void setNppCodes(List<String> nppCodes) {
		this.nppCodes = nppCodes;
	}

	public String getLike_agentName() {
		return like_agentName;
	}

	public void setLike_agentName(String like_agentName) {
		this.like_agentName = like_agentName;
	}

	public List<String> getAgentNames() {
		return agentNames;
	}

	public void setAgentNames(List<String> agentNames) {
		this.agentNames = agentNames;
	}

}
