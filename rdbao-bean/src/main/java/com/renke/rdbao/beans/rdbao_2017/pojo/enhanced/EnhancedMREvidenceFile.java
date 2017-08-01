package com.renke.rdbao.beans.rdbao_2017.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.enums.FileTypeEnum;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.StorageTypeEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.UploadStatusEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.MREvidenceFile;

/**
 * @author jgshun
 * @date 2017-4-10 下午6:02:59
 * @version 1.0
 */
public class EnhancedMREvidenceFile extends BaseEnhanced {
	public EnhancedMREvidenceFile() {
	}

	public EnhancedMREvidenceFile(MREvidenceFile mrEvidenceFile) {
		BeanUtils.copyProperties(mrEvidenceFile, this);

		if (mrEvidenceFile.getEvidenceId() != null && mrEvidenceFile.getEvidenceId().length() > 0) {
			EnhancedMEvidence _EnhancedMEvidence = new EnhancedMEvidence();
			_EnhancedMEvidence.setId(mrEvidenceFile.getEvidenceId());
			this.enhancedMEvidence = _EnhancedMEvidence;
		}
		if (mrEvidenceFile.getStorageType() != null) {
			this.storageType = StorageTypeEnum4MEvidence.getStorageTypeEnumByCode(mrEvidenceFile.getStorageType());
		}
		if (mrEvidenceFile.getNppCode() != null && mrEvidenceFile.getNppCode().length() > 0) {
			EnhancedDNpp _EnhancedDNpp = new EnhancedDNpp();
			_EnhancedDNpp.setCode(mrEvidenceFile.getNppCode());
			this.enhancedDNpp = _EnhancedDNpp;
		}
		if (mrEvidenceFile.getFileType() != null) {
			this.fileType = FileTypeEnum.getFileTypeEnumByCode(mrEvidenceFile.getFileType());
		}
		if (mrEvidenceFile.getUploadStatus() != null) {
			this.uploadStatus = UploadStatusEnum4MEvidence.getUploadStatusEnumByCode(mrEvidenceFile.getUploadStatus());
		}
	}

	private String id;
	private EnhancedMEvidence enhancedMEvidence;
	private String path;
	private String bucket;
	private StorageTypeEnum4MEvidence storageType;
	private EnhancedDNpp enhancedDNpp;
	private String md5;
	private long size;
	private FileTypeEnum fileType;
	private UploadStatusEnum4MEvidence uploadStatus;
	private double sort;
	private Date createTime;
	private Date updateTime;
	private String extra;

	private String fileUrl;// 最终可访问的文件地址

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
	 * @return the enhancedMEvidence
	 */
	public EnhancedMEvidence getEnhancedMEvidence() {
		return enhancedMEvidence;
	}

	/**
	 * @param enhancedMEvidence
	 *            the enhancedMEvidence to set
	 */
	public void setEnhancedMEvidence(EnhancedMEvidence enhancedMEvidence) {
		this.enhancedMEvidence = enhancedMEvidence;
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
	public StorageTypeEnum4MEvidence getStorageType() {
		return storageType;
	}

	/**
	 * @param storageType
	 *            the storageType to set
	 */
	public void setStorageType(StorageTypeEnum4MEvidence storageType) {
		this.storageType = storageType;
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
	 * @return the md5
	 */
	public String getMd5() {
		return md5;
	}

	/**
	 * @param md5
	 *            the md5 to set
	 */
	public void setMd5(String md5) {
		this.md5 = md5;
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

	/**
	 * @return the sort
	 */
	public double getSort() {
		return sort;
	}

	/**
	 * @param sort
	 *            the sort to set
	 */
	public void setSort(double sort) {
		this.sort = sort;
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

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

}
