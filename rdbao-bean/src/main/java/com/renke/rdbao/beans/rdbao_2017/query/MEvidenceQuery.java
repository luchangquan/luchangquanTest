package com.renke.rdbao.beans.rdbao_2017.query;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.renke.rdbao.beans.common.enums.TenantEnum;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.CategoryEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.StatusEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.UploadStatusEnum4MEvidence;

/**
 * 证据查询对象
 * 
 * @author jgshun
 * @date 2017-1-4 上午11:54:23
 * @version 1.0
 */
public class MEvidenceQuery implements Serializable {
	private List<String> ids;
	private List<String> names;
	private String like_name;
	private List<String> codes;
	private String like_code;
	private List<String> descriptions;
	private String like_description;
	private String like_name_or_description;

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

	private List<String> nppCodes;
	private List<String> userIds;
	private List<String> companyIds;

	private List<StatusEnum4MEvidence> status;
	private List<String> evidenceSourceIds;
	private List<String> signatureKeyIds;

	private List<CategoryEnum4MEvidence> categories;

	private List<UploadStatusEnum4MEvidence> uploadStatus;
	private List<TenantEnum> tenants;

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
	 * @return the codes
	 */
	public List<String> getCodes() {
		return codes;
	}

	/**
	 * @param codes
	 *            the codes to set
	 */
	public void setCodes(List<String> codes) {
		this.codes = codes;
	}

	/**
	 * @return the like_code
	 */
	public String getLike_code() {
		return like_code;
	}

	/**
	 * @param like_code
	 *            the like_code to set
	 */
	public void setLike_code(String like_code) {
		this.like_code = like_code;
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
	 * @return the nppCodes
	 */
	public List<String> getNppCodes() {
		return nppCodes;
	}

	/**
	 * @param nppCodes
	 *            the nppCodes to set
	 */
	public void setNppCodes(List<String> nppCodes) {
		this.nppCodes = nppCodes;
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
	 * @return the companyIds
	 */
	public List<String> getCompanyIds() {
		return companyIds;
	}

	/**
	 * @param companyIds
	 *            the companyIds to set
	 */
	public void setCompanyIds(List<String> companyIds) {
		this.companyIds = companyIds;
	}

	/**
	 * @return the evidenceSourceIds
	 */
	public List<String> getEvidenceSourceIds() {
		return evidenceSourceIds;
	}

	/**
	 * @param evidenceSourceIds
	 *            the evidenceSourceIds to set
	 */
	public void setEvidenceSourceIds(List<String> evidenceSourceIds) {
		this.evidenceSourceIds = evidenceSourceIds;
	}

	/**
	 * @return the signatureKeyIds
	 */
	public List<String> getSignatureKeyIds() {
		return signatureKeyIds;
	}

	/**
	 * @param signatureKeyIds
	 *            the signatureKeyIds to set
	 */
	public void setSignatureKeyIds(List<String> signatureKeyIds) {
		this.signatureKeyIds = signatureKeyIds;
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

	public List<StatusEnum4MEvidence> getStatus() {
		return status;
	}

	public void setStatus(List<StatusEnum4MEvidence> status) {
		this.status = status;
	}

	public List<CategoryEnum4MEvidence> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryEnum4MEvidence> categories) {
		this.categories = categories;
	}

	public List<UploadStatusEnum4MEvidence> getUploadStatus() {
		return uploadStatus;
	}

	public void setUploadStatus(List<UploadStatusEnum4MEvidence> uploadStatus) {
		this.uploadStatus = uploadStatus;
	}

	public String getLike_name_or_description() {
		return like_name_or_description;
	}

	public void setLike_name_or_description(String like_name_or_description) {
		this.like_name_or_description = like_name_or_description;
	}

}
