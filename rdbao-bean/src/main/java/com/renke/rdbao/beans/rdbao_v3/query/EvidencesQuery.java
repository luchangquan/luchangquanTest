package com.renke.rdbao.beans.rdbao_v3.query;

import java.util.Date;
import java.util.List;

import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.CategoryEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.DeletedEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.HandleSourceEnum4Envidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.ReceiptStateEnum4Envidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.StateEnum4Evidences;

/**
 * 证据查询对象
 * 
 * @author jgshun
 * @date 2017-1-4 上午11:54:23
 * @version 1.0
 */
public class EvidencesQuery {
	private List<String> ids;
	private List<String> names;
	private String like_name;
	private List<String> descriptions;
	private String like_description;

	private Date equalCreateTime;
	private Date equalAndBeforCreateTime;
	private Date equalAndAfterCreateTime;
	private Date beforCreateTime;
	private Date afterCreateTime;

	private Date equalLastUpdateTime;
	private Date equalAndBeforLastUpdateTime;
	private Date equalAndAfterLastUpdateTime;
	private Date beforLastUpdateTime;
	private Date afterLastUpdateTime;

	private Date equalExprieTime;
	private Date equalAndBeforExprieTime;
	private Date equalAndAfterExprieTime;
	private Date beforExprieTime;
	private Date afterExprieTime;

	private List<String> pnoesIds;
	private List<String> userIds;
	private List<String> companyIds;

	private List<String> codes;
	private String like_code;

	private List<StateEnum4Evidences> states;
	private List<String> appIds;
	private List<String> evidencePackageIIds;

	private List<CategoryEnum4Evidences> categories;
	private List<String> filenames;
	private String like_filename;

	private List<String> thumbFilenames;
	private String like_thumbFilename;

	private List<ReceiptStateEnum4Envidences> receiptStates;
	private List<DeletedEnum4Evidences> deleteds;
	private List<String> parentCodes;
	private List<String> evidRecordViewUrls;
	private String like_evidRecordViewUrl;
	private List<HandleSourceEnum4Envidences> handleSources;

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
	 * @return the equalLastUpdateTime
	 */
	public Date getEqualLastUpdateTime() {
		return equalLastUpdateTime;
	}

	/**
	 * @param equalLastUpdateTime
	 *            the equalLastUpdateTime to set
	 */
	public void setEqualLastUpdateTime(Date equalLastUpdateTime) {
		this.equalLastUpdateTime = equalLastUpdateTime;
	}

	/**
	 * @return the equalAndBeforLastUpdateTime
	 */
	public Date getEqualAndBeforLastUpdateTime() {
		return equalAndBeforLastUpdateTime;
	}

	/**
	 * @param equalAndBeforLastUpdateTime
	 *            the equalAndBeforLastUpdateTime to set
	 */
	public void setEqualAndBeforLastUpdateTime(Date equalAndBeforLastUpdateTime) {
		this.equalAndBeforLastUpdateTime = equalAndBeforLastUpdateTime;
	}

	/**
	 * @return the equalAndAfterLastUpdateTime
	 */
	public Date getEqualAndAfterLastUpdateTime() {
		return equalAndAfterLastUpdateTime;
	}

	/**
	 * @param equalAndAfterLastUpdateTime
	 *            the equalAndAfterLastUpdateTime to set
	 */
	public void setEqualAndAfterLastUpdateTime(Date equalAndAfterLastUpdateTime) {
		this.equalAndAfterLastUpdateTime = equalAndAfterLastUpdateTime;
	}

	/**
	 * @return the beforLastUpdateTime
	 */
	public Date getBeforLastUpdateTime() {
		return beforLastUpdateTime;
	}

	/**
	 * @param beforLastUpdateTime
	 *            the beforLastUpdateTime to set
	 */
	public void setBeforLastUpdateTime(Date beforLastUpdateTime) {
		this.beforLastUpdateTime = beforLastUpdateTime;
	}

	/**
	 * @return the afterLastUpdateTime
	 */
	public Date getAfterLastUpdateTime() {
		return afterLastUpdateTime;
	}

	/**
	 * @param afterLastUpdateTime
	 *            the afterLastUpdateTime to set
	 */
	public void setAfterLastUpdateTime(Date afterLastUpdateTime) {
		this.afterLastUpdateTime = afterLastUpdateTime;
	}

	/**
	 * @return the equalExprieTime
	 */
	public Date getEqualExprieTime() {
		return equalExprieTime;
	}

	/**
	 * @param equalExprieTime
	 *            the equalExprieTime to set
	 */
	public void setEqualExprieTime(Date equalExprieTime) {
		this.equalExprieTime = equalExprieTime;
	}

	/**
	 * @return the equalAndBeforExprieTime
	 */
	public Date getEqualAndBeforExprieTime() {
		return equalAndBeforExprieTime;
	}

	/**
	 * @param equalAndBeforExprieTime
	 *            the equalAndBeforExprieTime to set
	 */
	public void setEqualAndBeforExprieTime(Date equalAndBeforExprieTime) {
		this.equalAndBeforExprieTime = equalAndBeforExprieTime;
	}

	/**
	 * @return the equalAndAfterExprieTime
	 */
	public Date getEqualAndAfterExprieTime() {
		return equalAndAfterExprieTime;
	}

	/**
	 * @param equalAndAfterExprieTime
	 *            the equalAndAfterExprieTime to set
	 */
	public void setEqualAndAfterExprieTime(Date equalAndAfterExprieTime) {
		this.equalAndAfterExprieTime = equalAndAfterExprieTime;
	}

	/**
	 * @return the beforExprieTime
	 */
	public Date getBeforExprieTime() {
		return beforExprieTime;
	}

	/**
	 * @param beforExprieTime
	 *            the beforExprieTime to set
	 */
	public void setBeforExprieTime(Date beforExprieTime) {
		this.beforExprieTime = beforExprieTime;
	}

	/**
	 * @return the afterExprieTime
	 */
	public Date getAfterExprieTime() {
		return afterExprieTime;
	}

	/**
	 * @param afterExprieTime
	 *            the afterExprieTime to set
	 */
	public void setAfterExprieTime(Date afterExprieTime) {
		this.afterExprieTime = afterExprieTime;
	}

	/**
	 * @return the pnoesIds
	 */
	public List<String> getPnoesIds() {
		return pnoesIds;
	}

	/**
	 * @param pnoesIds
	 *            the pnoesIds to set
	 */
	public void setPnoesIds(List<String> pnoesIds) {
		this.pnoesIds = pnoesIds;
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
	 * @return the states
	 */
	public List<StateEnum4Evidences> getStates() {
		return states;
	}

	/**
	 * @param states
	 *            the states to set
	 */
	public void setStates(List<StateEnum4Evidences> states) {
		this.states = states;
	}

	/**
	 * @return the appIds
	 */
	public List<String> getAppIds() {
		return appIds;
	}

	/**
	 * @param appIds
	 *            the appIds to set
	 */
	public void setAppIds(List<String> appIds) {
		this.appIds = appIds;
	}

	/**
	 * @return the evidencePackageIIds
	 */
	public List<String> getEvidencePackageIIds() {
		return evidencePackageIIds;
	}

	/**
	 * @param evidencePackageIIds
	 *            the evidencePackageIIds to set
	 */
	public void setEvidencePackageIIds(List<String> evidencePackageIIds) {
		this.evidencePackageIIds = evidencePackageIIds;
	}

	/**
	 * @return the categories
	 */
	public List<CategoryEnum4Evidences> getCategories() {
		return categories;
	}

	/**
	 * @param categories
	 *            the categories to set
	 */
	public void setCategories(List<CategoryEnum4Evidences> categories) {
		this.categories = categories;
	}

	/**
	 * @return the filenames
	 */
	public List<String> getFilenames() {
		return filenames;
	}

	/**
	 * @param filenames
	 *            the filenames to set
	 */
	public void setFilenames(List<String> filenames) {
		this.filenames = filenames;
	}

	/**
	 * @return the like_filename
	 */
	public String getLike_filename() {
		return like_filename;
	}

	/**
	 * @param like_filename
	 *            the like_filename to set
	 */
	public void setLike_filename(String like_filename) {
		this.like_filename = like_filename;
	}

	/**
	 * @return the thumbFilenames
	 */
	public List<String> getThumbFilenames() {
		return thumbFilenames;
	}

	/**
	 * @param thumbFilenames
	 *            the thumbFilenames to set
	 */
	public void setThumbFilenames(List<String> thumbFilenames) {
		this.thumbFilenames = thumbFilenames;
	}

	/**
	 * @return the receiptStates
	 */
	public List<ReceiptStateEnum4Envidences> getReceiptStates() {
		return receiptStates;
	}

	/**
	 * @param receiptStates
	 *            the receiptStates to set
	 */
	public void setReceiptStates(List<ReceiptStateEnum4Envidences> receiptStates) {
		this.receiptStates = receiptStates;
	}

	/**
	 * @return the deleteds
	 */
	public List<DeletedEnum4Evidences> getDeleteds() {
		return deleteds;
	}

	/**
	 * @param deleteds
	 *            the deleteds to set
	 */
	public void setDeleteds(List<DeletedEnum4Evidences> deleteds) {
		this.deleteds = deleteds;
	}

	/**
	 * @return the parentCodes
	 */
	public List<String> getParentCodes() {
		return parentCodes;
	}

	/**
	 * @param parentCodes
	 *            the parentCodes to set
	 */
	public void setParentCodes(List<String> parentCodes) {
		this.parentCodes = parentCodes;
	}

	/**
	 * @return the evidRecordViewUrls
	 */
	public List<String> getEvidRecordViewUrls() {
		return evidRecordViewUrls;
	}

	/**
	 * @param evidRecordViewUrls
	 *            the evidRecordViewUrls to set
	 */
	public void setEvidRecordViewUrls(List<String> evidRecordViewUrls) {
		this.evidRecordViewUrls = evidRecordViewUrls;
	}

	/**
	 * @return the like_evidRecordViewUrl
	 */
	public String getLike_evidRecordViewUrl() {
		return like_evidRecordViewUrl;
	}

	/**
	 * @param like_evidRecordViewUrl
	 *            the like_evidRecordViewUrl to set
	 */
	public void setLike_evidRecordViewUrl(String like_evidRecordViewUrl) {
		this.like_evidRecordViewUrl = like_evidRecordViewUrl;
	}

	/**
	 * @return the handleSources
	 */
	public List<HandleSourceEnum4Envidences> getHandleSources() {
		return handleSources;
	}

	/**
	 * @param handleSources
	 *            the handleSources to set
	 */
	public void setHandleSources(List<HandleSourceEnum4Envidences> handleSources) {
		this.handleSources = handleSources;
	}

	public String getLike_thumbFilename() {
		return like_thumbFilename;
	}

	public void setLike_thumbFilename(String like_thumbFilename) {
		this.like_thumbFilename = like_thumbFilename;
	}

}
