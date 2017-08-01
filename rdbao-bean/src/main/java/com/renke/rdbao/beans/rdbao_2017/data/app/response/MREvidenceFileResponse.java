package com.renke.rdbao.beans.rdbao_2017.data.app.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.renke.rdbao.beans.data.response.base.BaseResponseData;

/**
 * @author jgshun
 * @date 2017-4-21 下午3:25:36
 * @version 1.0
 */
public class MREvidenceFileResponse extends BaseResponseData {

	private String id;
	private MEvidenceResponse evidence;
	private DNppResponse npp;
	private String fileUrl;
	private String fileIdentity;
	private long size;
	private short fileType;
	private short uploadStatusCode;
	private String md5;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date createTime;

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
	 * @return the evidence
	 */
	public MEvidenceResponse getEvidence() {
		return evidence;
	}

	/**
	 * @param evidence
	 *            the evidence to set
	 */
	public void setEvidence(MEvidenceResponse evidence) {
		this.evidence = evidence;
	}

	/**
	 * @return the npp
	 */
	public DNppResponse getNpp() {
		return npp;
	}

	/**
	 * @param npp
	 *            the npp to set
	 */
	public void setNpp(DNppResponse npp) {
		this.npp = npp;
	}

	/**
	 * @return the fileUrl
	 */
	public String getFileUrl() {
		return fileUrl;
	}

	/**
	 * @param fileUrl
	 *            the fileUrl to set
	 */
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	/**
	 * @return the fileIdentity
	 */
	public String getFileIdentity() {
		return fileIdentity;
	}

	/**
	 * @param fileIdentity
	 *            the fileIdentity to set
	 */
	public void setFileIdentity(String fileIdentity) {
		this.fileIdentity = fileIdentity;
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
	 * @return the fileType
	 */
	public short getFileType() {
		return fileType;
	}

	/**
	 * @param fileType
	 *            the fileType to set
	 */
	public void setFileType(short fileType) {
		this.fileType = fileType;
	}

	/**
	 * @return the uploadStatusCode
	 */
	public short getUploadStatusCode() {
		return uploadStatusCode;
	}

	/**
	 * @param uploadStatusCode
	 *            the uploadStatusCode to set
	 */
	public void setUploadStatusCode(short uploadStatusCode) {
		this.uploadStatusCode = uploadStatusCode;
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

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

}
