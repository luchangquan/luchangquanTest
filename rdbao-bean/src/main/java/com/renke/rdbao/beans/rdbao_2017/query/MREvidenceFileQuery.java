package com.renke.rdbao.beans.rdbao_2017.query;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.renke.rdbao.beans.common.enums.AliOssBucketEnum;
import com.renke.rdbao.beans.common.enums.FileTypeEnum;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.StorageTypeEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.UploadStatusEnum4MEvidence;

/**
 * @author jgshun
 * @date 2017-4-13 下午3:02:44
 * @version 1.0
 */
public class MREvidenceFileQuery implements Serializable {
	private List<String> ids;
	private List<String> evidenceIds;
	private List<String> paths;
	private String like_path;
	private List<AliOssBucketEnum> buckets;
	private List<StorageTypeEnum4MEvidence> storageTypes;
	private List<String> nppCodes;
	private List<FileTypeEnum> fileTypes;
	private List<UploadStatusEnum4MEvidence> uploadStatuses;

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
	 * @return the evidenceIds
	 */
	public List<String> getEvidenceIds() {
		return evidenceIds;
	}

	/**
	 * @param evidenceIds
	 *            the evidenceIds to set
	 */
	public void setEvidenceIds(List<String> evidenceIds) {
		this.evidenceIds = evidenceIds;
	}

	/**
	 * @return the paths
	 */
	public List<String> getPaths() {
		return paths;
	}

	/**
	 * @param paths
	 *            the paths to set
	 */
	public void setPaths(List<String> paths) {
		this.paths = paths;
	}

	/**
	 * @return the like_path
	 */
	public String getLike_path() {
		return like_path;
	}

	/**
	 * @param like_path
	 *            the like_path to set
	 */
	public void setLike_path(String like_path) {
		this.like_path = like_path;
	}

	/**
	 * @return the buckets
	 */
	public List<AliOssBucketEnum> getBuckets() {
		return buckets;
	}

	/**
	 * @param buckets
	 *            the buckets to set
	 */
	public void setBuckets(List<AliOssBucketEnum> buckets) {
		this.buckets = buckets;
	}

	/**
	 * @return the storageTypes
	 */
	public List<StorageTypeEnum4MEvidence> getStorageTypes() {
		return storageTypes;
	}

	/**
	 * @param storageTypes
	 *            the storageTypes to set
	 */
	public void setStorageTypes(List<StorageTypeEnum4MEvidence> storageTypes) {
		this.storageTypes = storageTypes;
	}


	/**
	 * @return the nppCodes
	 */
	public List<String> getNppCodes() {
		return nppCodes;
	}

	/**
	 * @param nppCodes the nppCodes to set
	 */
	public void setNppCodes(List<String> nppCodes) {
		this.nppCodes = nppCodes;
	}

	/**
	 * @return the fileTypes
	 */
	public List<FileTypeEnum> getFileTypes() {
		return fileTypes;
	}

	/**
	 * @param fileTypes
	 *            the fileTypes to set
	 */
	public void setFileTypes(List<FileTypeEnum> fileTypes) {
		this.fileTypes = fileTypes;
	}

	/**
	 * @return the uploadStatuses
	 */
	public List<UploadStatusEnum4MEvidence> getUploadStatuses() {
		return uploadStatuses;
	}

	/**
	 * @param uploadStatuses
	 *            the uploadStatuses to set
	 */
	public void setUploadStatuses(List<UploadStatusEnum4MEvidence> uploadStatuses) {
		this.uploadStatuses = uploadStatuses;
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

}
