package com.renke.rdbao.beans.rdbao_2017.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2017-4-10 下午5:57:38
 * @version 1.0
 */
@Table(name = "m_r_evidence_file")
public class MREvidenceFile extends BasePo {
	public static final String FIELD_ID = "id";
	public static final String FIELD_EVIDENCEID = "evidenceId";
	public static final String FIELD_PATH = "path";
	public static final String FIELD_BUCKET = "bucket";
	public static final String FIELD_STORAGETYPE = "storageType";
	public static final String FIELD_NPPCODE = "nppCode";
	public static final String FIELD_MD5 = "md5";
	public static final String FIELD_SIZE = "size";
	public static final String FIELD_FILETYPE = "fileType";
	public static final String FIELD_UPLOADSTATUS = "uploadStatus";
	public static final String FIELD_SORT = "sort";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_UPDATETIME = "updateTime";
	public static final String FIELD_EXTRA = "extra";

	public static final String COLUMN_ID = "id";
	public static final String COLUMN_EVIDENCE_ID = "evidence_id";
	public static final String COLUMN_PATH = "path";
	public static final String COLUMN_BUCKET = "bucket";
	public static final String COLUMN_STORAGE_TYPE = "storage_type";
	public static final String COLUMN_NPP_CODE = "npp_code";
	public static final String COLUMN_MD5 = "md5";
	public static final String COLUMN_SIZE = "size";
	public static final String COLUMN_FILE_TYPE = "file_type";
	public static final String COLUMN_UPLOAD_STATUS = "upload_status";
	public static final String COLUMN_SORT = "sort";
	public static final String COLUMN_CREATE_TIME = "create_time";
	public static final String COLUMN_UPDATE_TIME = "update_time";
	public static final String COLUMN_EXTRA = "extra";

	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "evidence_id")
	private String evidenceId;
	@Column(name = "path")
	private String path;
	@Column(name = "bucket")
	private String bucket;
	@Column(name = "storage_type")
	private Short storageType;
	@Column(name = "npp_code")
	private String nppCode;
	@Column(name = "md5")
	private String md5;
	@Column(name = "size")
	private Long size;
	@Column(name = "file_type")
	private Short fileType;
	@Column(name = "upload_status")
	private Short uploadStatus;
	@Column(name = "sort")
	private Double sort;
	@Column(name = "create_time")
	private Date createTime;
	@Column(name = "update_time")
	private Date updateTime;
	@Column(name = "extra")
	private String extra;

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
	 * @return the nppCode
	 */
	public String getNppCode() {
		return nppCode;
	}

	/**
	 * @param nppCode
	 *            the nppCode to set
	 */
	public void setNppCode(String nppCode) {
		this.nppCode = nppCode;
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
	public Short getFileType() {
		return fileType;
	}

	/**
	 * @param fileType
	 *            the fileType to set
	 */
	public void setFileType(Short fileType) {
		this.fileType = fileType;
	}

	/**
	 * @return the uploadStatus
	 */
	public Short getUploadStatus() {
		return uploadStatus;
	}

	/**
	 * @param uploadStatus
	 *            the uploadStatus to set
	 */
	public void setUploadStatus(Short uploadStatus) {
		this.uploadStatus = uploadStatus;
	}

	/**
	 * @return the sort
	 */
	public Double getSort() {
		return sort;
	}

	/**
	 * @param sort
	 *            the sort to set
	 */
	public void setSort(Double sort) {
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

	public Short getStorageType() {
		return storageType;
	}

	public void setStorageType(Short storageType) {
		this.storageType = storageType;
	}

	public String getEvidenceId() {
		return evidenceId;
	}

	public void setEvidenceId(String evidenceId) {
		this.evidenceId = evidenceId;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

}
