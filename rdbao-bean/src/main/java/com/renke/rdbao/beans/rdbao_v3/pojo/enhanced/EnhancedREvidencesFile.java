package com.renke.rdbao.beans.rdbao_v3.pojo.enhanced;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.enums.FileTypeEnum;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.StorageTypeEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.UploadStatusEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.pojo.REvidencesFile;

/**
 * @author jgshun
 * @date 2017-3-2 下午3:48:55
 * @version 1.0
 */
public class EnhancedREvidencesFile extends BaseEnhanced {
	public EnhancedREvidencesFile() {
	}

	public EnhancedREvidencesFile(REvidencesFile rEvidencesFile) {
		BeanUtils.copyProperties(rEvidencesFile, this);
		if (rEvidencesFile.getStorageType() != null) {
			this.storageType = StorageTypeEnum4Evidences.getStorageTypeEnumByCode(rEvidencesFile.getStorageType());
		}
		if (rEvidencesFile.getEvidencesId() != null && rEvidencesFile.getEvidencesId().length() > 0) {
			EnhancedEvidences _EnhancedEvidences = new EnhancedEvidences();
			_EnhancedEvidences.setId(rEvidencesFile.getEvidencesId());
			this.enhancedEvidences = _EnhancedEvidences;
		}
		if (rEvidencesFile.getPnoesId() != null && rEvidencesFile.getPnoesId().length() > 0) {
			EnhancedPNOes _EnhancedPNOes = new EnhancedPNOes();
			_EnhancedPNOes.setId(rEvidencesFile.getPnoesId());
			this.enhancedPnoes = _EnhancedPNOes;
		}
		if (rEvidencesFile.getFileType() != null) {
			this.fileType = FileTypeEnum.getFileTypeEnumByCode(rEvidencesFile.getFileType());
		}
		if (rEvidencesFile.getUploadStatus() != null) {
			this.uploadStatus = UploadStatusEnum4Evidences.getUploadStatusEnumByCode(rEvidencesFile.getUploadStatus());
		}

	}

	private String id;
	private EnhancedEvidences enhancedEvidences;
	private String path;
	private String bucket;
	private StorageTypeEnum4Evidences storageType;
	private EnhancedPNOes enhancedPnoes;
	private FileTypeEnum fileType;
	private UploadStatusEnum4Evidences uploadStatus;

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
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the bucket
	 */
	public String getBucket() {
		return bucket;
	}

	/**
	 * @param bucket
	 *            the bucket to set
	 */
	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	/**
	 * @return the storageType
	 */
	public StorageTypeEnum4Evidences getStorageType() {
		return storageType;
	}

	/**
	 * @param storageType
	 *            the storageType to set
	 */
	public void setStorageType(StorageTypeEnum4Evidences storageType) {
		this.storageType = storageType;
	}

	/**
	 * @return the enhancedPnoes
	 */
	public EnhancedPNOes getEnhancedPnoes() {
		return enhancedPnoes;
	}

	/**
	 * @param enhancedPnoes
	 *            the enhancedPnoes to set
	 */
	public void setEnhancedPnoes(EnhancedPNOes enhancedPnoes) {
		this.enhancedPnoes = enhancedPnoes;
	}

	/**
	 * @return the fileType
	 */
	public FileTypeEnum getFileType() {
		return fileType;
	}

	/**
	 * @param fileType
	 *            the fileType to set
	 */
	public void setFileType(FileTypeEnum fileType) {
		this.fileType = fileType;
	}

	/**
	 * @return the uploadStatus
	 */
	public UploadStatusEnum4Evidences getUploadStatus() {
		return uploadStatus;
	}

	/**
	 * @param uploadStatus
	 *            the uploadStatus to set
	 */
	public void setUploadStatus(UploadStatusEnum4Evidences uploadStatus) {
		this.uploadStatus = uploadStatus;
	}

	public EnhancedEvidences getEnhancedEvidences() {
		return enhancedEvidences;
	}

	public void setEnhancedEvidences(EnhancedEvidences enhancedEvidences) {
		this.enhancedEvidences = enhancedEvidences;
	}

}
