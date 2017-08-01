package com.renke.rdbao.beans.rdbao_2017.pojo.enhanced;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.enums.TenantEnum;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.CategoryEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.StatusEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.UploadStatusEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidence;

/**
 * @author jgshun
 * @date 2017-4-10 下午1:45:45
 * @version 1.0
 */
public class EnhancedMEvidence extends BaseEnhanced {
	public EnhancedMEvidence() {
	}

	public EnhancedMEvidence(MEvidence evidence) {
		BeanUtils.copyProperties(evidence, this);
		if (evidence.getNppCode() != null && evidence.getNppCode().length() > 0) {
			EnhancedDNpp _EnhancedDNpp = new EnhancedDNpp();
			_EnhancedDNpp.setCode(evidence.getNppCode());
			this.enhancedDNpp = _EnhancedDNpp;
		}

		if (evidence.getEvidenceSourceId() != null && evidence.getEvidenceSourceId().length() > 0) {
			EnhancedDEvidenceSource _EnhancedDEvidenceSource = new EnhancedDEvidenceSource();
			_EnhancedDEvidenceSource.setId(evidence.getEvidenceSourceId());
			this.setEnhancedDEvidenceSource(_EnhancedDEvidenceSource);
		}

		if (evidence.getSignatureKeyId() != null && evidence.getSignatureKeyId().length() > 0) {
			EnhancedDSignatureKey _EnhancedDSignatureRecord = new EnhancedDSignatureKey();
			_EnhancedDSignatureRecord.setId(evidence.getSignatureKeyId());
			this.enhancedDSignatureKey = _EnhancedDSignatureRecord;
		}

		if (evidence.getCompanyId() != null && evidence.getCompanyId().length() > 0) {
			EnhancedECompany _EnhancedECompany = new EnhancedECompany();
			_EnhancedECompany.setId(evidence.getCompanyId());
			this.enhancedECompany = _EnhancedECompany;
		}

		if (evidence.getCategoryId() != null) {
			this.category = CategoryEnum4MEvidence.getCategoryEnumByCode(evidence.getCategoryId());
		}

		if (evidence.getStatus() != null) {
			this.status = StatusEnum4MEvidence.getStatusEnumByCode(evidence.getStatus());
		}

		if (evidence.getUploadStatus() != null) {
			this.uploadStatus = UploadStatusEnum4MEvidence.getUploadStatusEnumByCode(evidence.getUploadStatus());
		}

		if (evidence.getTenantCode() != null) {
			this.tenant = TenantEnum.getTenantEnumByCode(evidence.getTenantCode());
		}

	}

	private String id;
	private String name;
	private String code;
	private long size;
	private String description;
	private Date createTime;
	private Date updateTime;
	private EnhancedDNpp enhancedDNpp;
	private String userId;// 冗余字段 根据用户来源区分189用户还是实时保用户
	private BaseEnhanced enhancedUser;
	private EnhancedDEvidenceSource enhancedDEvidenceSource;
	private EnhancedDSignatureKey enhancedDSignatureKey;
	private EnhancedECompany enhancedECompany;// 冗余字段
	private CategoryEnum4MEvidence category;
	private StatusEnum4MEvidence status;
	private UploadStatusEnum4MEvidence uploadStatus;
	private TenantEnum tenant;

	private BaseEnhanced enhancedItem;// 对应的证据子条目
	private List<EnhancedMREvidenceFile> enhancedMREvidenceFiles;// 证据对应的文件列表
	private String coverUrl;// 证据封面

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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return the enhancedDNpp
	 */
	public EnhancedDNpp getEnhancedDNpp() {
		return enhancedDNpp;
	}

	/**
	 * @param enhancedDNpp
	 *            the enhancedDNpp to set
	 */
	public void setEnhancedDNpp(EnhancedDNpp enhancedDNpp) {
		this.enhancedDNpp = enhancedDNpp;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the enhancedDSignatureRecord
	 */
	public EnhancedDSignatureKey getEnhancedDSignatureRecord() {
		return enhancedDSignatureKey;
	}

	/**
	 * @param enhancedDSignatureKey
	 *            the enhancedDSignatureRecord to set
	 */
	public void setEnhancedDSignatureRecord(EnhancedDSignatureKey enhancedDSignatureKey) {
		this.enhancedDSignatureKey = enhancedDSignatureKey;
	}

	/**
	 * @return the enhancedECompany
	 */
	public EnhancedECompany getEnhancedECompany() {
		return enhancedECompany;
	}

	/**
	 * @param enhancedECompany
	 *            the enhancedECompany to set
	 */
	public void setEnhancedECompany(EnhancedECompany enhancedECompany) {
		this.enhancedECompany = enhancedECompany;
	}

	/**
	 * @return the category
	 */
	public CategoryEnum4MEvidence getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(CategoryEnum4MEvidence category) {
		this.category = category;
	}

	/**
	 * @return the status
	 */
	public StatusEnum4MEvidence getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(StatusEnum4MEvidence status) {
		this.status = status;
	}

	/**
	 * @return the uploadStatus
	 */
	public UploadStatusEnum4MEvidence getUploadStatus() {
		return uploadStatus;
	}

	/**
	 * @param uploadStatus
	 *            the uploadStatus to set
	 */
	public void setUploadStatus(UploadStatusEnum4MEvidence uploadStatus) {
		this.uploadStatus = uploadStatus;
	}

	public BaseEnhanced getEnhancedItem() {
		return enhancedItem;
	}

	public void setEnhancedItem(BaseEnhanced enhancedItem) {
		this.enhancedItem = enhancedItem;
	}

	public TenantEnum getTenant() {
		return tenant;
	}

	public void setTenant(TenantEnum tenant) {
		this.tenant = tenant;
	}

	public List<EnhancedMREvidenceFile> getEnhancedMREvidenceFiles() {
		return enhancedMREvidenceFiles;
	}

	public void setEnhancedMREvidenceFiles(List<EnhancedMREvidenceFile> enhancedMREvidenceFiles) {
		this.enhancedMREvidenceFiles = enhancedMREvidenceFiles;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public BaseEnhanced getEnhancedUser() {
		return enhancedUser;
	}

	public void setEnhancedUser(BaseEnhanced enhancedUser) {
		this.enhancedUser = enhancedUser;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public EnhancedDEvidenceSource getEnhancedDEvidenceSource() {
		return enhancedDEvidenceSource;
	}

	public void setEnhancedDEvidenceSource(EnhancedDEvidenceSource enhancedDEvidenceSource) {
		this.enhancedDEvidenceSource = enhancedDEvidenceSource;
	}

}
