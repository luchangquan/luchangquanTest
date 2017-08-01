package com.renke.rdbao.beans.rdbao_v3.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2017-3-2 下午3:43:56
 * @version 1.0
 */
@Table(name = "r_evidences_file")
public class REvidencesFile extends BasePo {
	public static final String FIELD_ID = "id";
	public static final String FIELD_USERID = "userId";
	public static final String FIELD_EVIDENCESID = "evidencesId";
	public static final String FIELD_PATH = "path";
	public static final String FIELD_BUCKET = "bucket";
	public static final String FIELD_STORAGETYPE = "storageType";
	public static final String FIELD_PNOESID = "pnoesId";
	public static final String FIELD_FILETYPE = "fileType";
	public static final String FIELD_UPLOADSTATUS = "uploadStatus";
	public static final String FIELD_SORT = "sort";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_UPDATETIME = "updateTime";
	public static final String FIELD_MD5 = "md5";
	public static final String FIELD_LENGTH = "length";

	public static final String COLUMN_ID = "id";
	public static final String COLUMN_USER_ID = "user_id";
	public static final String COLUMN_EVIDENCES_ID = "evidences_id";
	public static final String COLUMN_PATH = "path";
	public static final String COLUMN_BUCKET = "bucket";
	public static final String COLUMN_STORAGE_TYPE = "storage_type";
	public static final String COLUMN_PNOES_ID = "pnoes_id";
	public static final String COLUMN_FILE_TYPE = "file_type";
	public static final String COLUMN_UPLOAD_STATUS = "upload_status";
	public static final String COLUMN_SORT = "sort";
	public static final String COLUMN_CREATE_TIME = "create_time";
	public static final String COLUMN_UPDATE_TIME = "update_time";
	public static final String COLUMN_MD5 = "md5";
	public static final String COLUMN_LENGTH = "length";

	// @Id 使用通用mapper保存证据列表时如果加上了Id注解 就会保存失败：id属性为空
	@Column(name = "id")
	private String id;
	@Column(name = "user_id")
	private String userId;
	@Column(name = "evidences_id")
	private String evidencesId;
	@Column(name = "path")
	private String path;
	@Column(name = "bucket")
	private String bucket;
	@Column(name = "storage_type")
	private Short storageType;
	@Column(name = "pnoes_id")
	private String pnoesId;
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
	@Column(name = "md5")
	private String md5;
	private long length;

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
	 * @return the evidencesId
	 */
	public String getEvidencesId() {
		return evidencesId;
	}

	/**
	 * @param evidencesId
	 *            the evidencesId to set
	 */
	public void setEvidencesId(String evidencesId) {
		this.evidencesId = evidencesId;
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
	public Short getStorageType() {
		return storageType;
	}

	/**
	 * @param storageType
	 *            the storageType to set
	 */
	public void setStorageType(Short storageType) {
		this.storageType = storageType;
	}

	/**
	 * @return the pnoesId
	 */
	public String getPnoesId() {
		return pnoesId;
	}

	/**
	 * @param pnoesId
	 *            the pnoesId to set
	 */
	public void setPnoesId(String pnoesId) {
		this.pnoesId = pnoesId;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

}
