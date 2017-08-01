package com.renke.rdbao.beans.rdbao_v3.pojo.enhanced;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.CategoryEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.DeletedEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.HandleSourceEnum4Envidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.ReceiptStateEnum4Envidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.StateEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.UploadStatusEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.pojo.Evidences;

/**
 * @author jgshun
 * @date 2016-11-10 下午5:57:10
 * @version 1.0
 */
public class EnhancedEvidences extends BaseEnhanced {

	public EnhancedEvidences() {
	}

	public EnhancedEvidences(Evidences evidences) {
		BeanUtils.copyProperties(evidences, this);
		if (evidences.getPnoId() != null && evidences.getPnoId().trim().length() > 0) {
			EnhancedPNOes _EnhancedPNOes = new EnhancedPNOes();
			_EnhancedPNOes.setId(evidences.getPnoId());
			this.enhancedPNOes = _EnhancedPNOes;
		}

		if (evidences.getUserId() != null && evidences.getUserId().trim().length() > 0) {
			EnhancedUser189 _EnhancedUser189 = new EnhancedUser189();
			_EnhancedUser189.setId(evidences.getUserId());
			this.enhancedUser189 = _EnhancedUser189;
		}

		if (evidences.getCompanyId() != null && evidences.getCompanyId().trim().length() > 0) {
			EnhancedCompanies _EnhancedCompanies = new EnhancedCompanies();
			_EnhancedCompanies.setId(evidences.getCompanyId());
			this.enhancedCompanies = _EnhancedCompanies;
		}

		if (evidences.getState() != null) {
			this.state = StateEnum4Evidences.getStateEnumByCode(evidences.getState());
		}

		if (evidences.getCategoryId() != null && evidences.getCategoryId().trim().length() > 0) {
			try {
				this.category = CategoryEnum4Evidences.getCategoryEnumByCode(Short.valueOf(evidences.getCategoryId()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (evidences.getReceiptState() != null) {
			this.receiptState = ReceiptStateEnum4Envidences.getReceiptStateEnumByCode(evidences.getReceiptState());
		}

		if (evidences.getDeleted() != null) {
			this.deleted = DeletedEnum4Evidences.getDeletedEnumByCode(evidences.getDeleted());
		}

		if (evidences.getHandleSource() != null) {
			this.handleSource = HandleSourceEnum4Envidences.getHandleSourceEnumByCode(evidences.getHandleSource());
		}
		if (evidences.getUploadStatus() != null) {
			this.setUploadStatus(UploadStatusEnum4Evidences.getUploadStatusEnumByCode(evidences.getUploadStatus()));
		}
	}

	private String id;
	private String name;
	private String description;
	private Date createTime;
	private Date lastUpdateTime;
	private EnhancedPNOes enhancedPNOes;
	private EnhancedUser189 enhancedUser189;
	// TODO 待添加实时保用户
	private String code;
	private long size;
	private StateEnum4Evidences state;
	private String appId;
	private EnhancedCompanies enhancedCompanies;
	private String evidencePackageIId;
	private CategoryEnum4Evidences category;
	private String filename;
	private String thumbFilename;
	private ReceiptStateEnum4Envidences receiptState;
	private DeletedEnum4Evidences deleted;
	private String parentCode;
	private String evidRecordViewUrl;
	private HandleSourceEnum4Envidences handleSource;
	private Date exprieTime;
	private UploadStatusEnum4Evidences uploadStatus;

	/**
	 * 证据文件列表
	 */
	private List<EnhancedREvidencesFile> enhancedREvidencesFiles;
	/**
	 * 子证据详情
	 */
	private BaseEnhanced enhancedItem;

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
	 * @return the enhancedPNOes
	 */
	public EnhancedPNOes getEnhancedPNOes() {
		return enhancedPNOes;
	}

	/**
	 * @param enhancedPNOes
	 *            the enhancedPNOes to set
	 */
	public void setEnhancedPNOes(EnhancedPNOes enhancedPNOes) {
		this.enhancedPNOes = enhancedPNOes;
	}

	/**
	 * @return the enhancedUser189
	 */
	public EnhancedUser189 getEnhancedUser189() {
		return enhancedUser189;
	}

	/**
	 * @param enhancedUser189
	 *            the enhancedUser189 to set
	 */
	public void setEnhancedUser189(EnhancedUser189 enhancedUser189) {
		this.enhancedUser189 = enhancedUser189;
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
	 * @return the size
	 */
	public long getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(long size) {
		this.size = size;
	}

	/**
	 * @return the state
	 */
	public StateEnum4Evidences getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(StateEnum4Evidences state) {
		this.state = state;
	}

	/**
	 * @return the appId
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * @param appId
	 *            the appId to set
	 */
	public void setAppId(String appId) {
		this.appId = appId;
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
	 * @return the evidencePackageIId
	 */
	public String getEvidencePackageIId() {
		return evidencePackageIId;
	}

	/**
	 * @param evidencePackageIId
	 *            the evidencePackageIId to set
	 */
	public void setEvidencePackageIId(String evidencePackageIId) {
		this.evidencePackageIId = evidencePackageIId;
	}

	/**
	 * @return the category
	 */
	public CategoryEnum4Evidences getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(CategoryEnum4Evidences category) {
		this.category = category;
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename
	 *            the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * @return the thumbFilename
	 */
	public String getThumbFilename() {
		return thumbFilename;
	}

	/**
	 * @param thumbFilename
	 *            the thumbFilename to set
	 */
	public void setThumbFilename(String thumbFilename) {
		this.thumbFilename = thumbFilename;
	}

	/**
	 * @return the receiptState
	 */
	public ReceiptStateEnum4Envidences getReceiptState() {
		return receiptState;
	}

	/**
	 * @param receiptState
	 *            the receiptState to set
	 */
	public void setReceiptState(ReceiptStateEnum4Envidences receiptState) {
		this.receiptState = receiptState;
	}

	/**
	 * @return the deleted
	 */
	public DeletedEnum4Evidences getDeleted() {
		return deleted;
	}

	/**
	 * @param deleted
	 *            the deleted to set
	 */
	public void setDeleted(DeletedEnum4Evidences deleted) {
		this.deleted = deleted;
	}

	/**
	 * @return the parentCode
	 */
	public String getParentCode() {
		return parentCode;
	}

	/**
	 * @param parentCode
	 *            the parentCode to set
	 */
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	/**
	 * @return the evidRecordViewUrl
	 */
	public String getEvidRecordViewUrl() {
		return evidRecordViewUrl;
	}

	/**
	 * @param evidRecordViewUrl
	 *            the evidRecordViewUrl to set
	 */
	public void setEvidRecordViewUrl(String evidRecordViewUrl) {
		this.evidRecordViewUrl = evidRecordViewUrl;
	}

	/**
	 * @return the handleSource
	 */
	public HandleSourceEnum4Envidences getHandleSource() {
		return handleSource;
	}

	/**
	 * @param handleSource
	 *            the handleSource to set
	 */
	public void setHandleSource(HandleSourceEnum4Envidences handleSource) {
		this.handleSource = handleSource;
	}

	/**
	 * @return the exprieTime
	 */
	public Date getExprieTime() {
		return exprieTime;
	}

	/**
	 * @param exprieTime
	 *            the exprieTime to set
	 */
	public void setExprieTime(Date exprieTime) {
		this.exprieTime = exprieTime;
	}

	public UploadStatusEnum4Evidences getUploadStatus() {
		return uploadStatus;
	}

	public void setUploadStatus(UploadStatusEnum4Evidences uploadStatus) {
		this.uploadStatus = uploadStatus;
	}

	public List<EnhancedREvidencesFile> getEnhancedREvidencesFiles() {
		return enhancedREvidencesFiles;
	}

	public void setEnhancedREvidencesFiles(List<EnhancedREvidencesFile> enhancedREvidencesFiles) {
		this.enhancedREvidencesFiles = enhancedREvidencesFiles;
	}

	public BaseEnhanced getEnhancedItem() {
		return enhancedItem;
	}

	public void setEnhancedItem(BaseEnhanced enhancedItem) {
		this.enhancedItem = enhancedItem;
	}

}
