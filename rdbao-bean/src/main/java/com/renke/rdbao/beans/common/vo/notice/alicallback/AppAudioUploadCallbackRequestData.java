package com.renke.rdbao.beans.common.vo.notice.alicallback;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * app音频上传成功后阿里回调数据
 * 
 * @author jgshun
 * @date 2017-3-1 下午9:29:47
 * @version 1.0
 */
public class AppAudioUploadCallbackRequestData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9020077879788809585L;
	@JSONField(ordinal = 1)
	private String length;
	@JSONField(ordinal = 2)
	private String filetype;
	@JSONField(ordinal = 3)
	private String sort;
	@JSONField(ordinal = 4)
	private String bucketName;
	@JSONField(ordinal = 5)
	private String fileIdentity;
	@JSONField(ordinal = 6)
	private String userAccount;
	@JSONField(ordinal = 7)
	private String locationDesc;

	/**
	 * @return the length
	 */
	public String getLength() {
		return length;
	}

	/**
	 * @param length
	 *            the length to set
	 */
	public void setLength(String length) {
		this.length = length;
	}

	/**
	 * @return the filetype
	 */
	public String getFiletype() {
		return filetype;
	}

	/**
	 * @param filetype
	 *            the filetype to set
	 */
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	/**
	 * @return the sort
	 */
	public String getSort() {
		return sort;
	}

	/**
	 * @param sort
	 *            the sort to set
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}

	/**
	 * @return the bucketName
	 */
	public String getBucketName() {
		return bucketName;
	}

	/**
	 * @param bucketName
	 *            the bucketName to set
	 */
	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
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
	 * @return the userAccount
	 */
	public String getUserAccount() {
		return userAccount;
	}

	/**
	 * @param userAccount
	 *            the userAccount to set
	 */
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getLocationDesc() {
		return locationDesc;
	}

	public void setLocationDesc(String locationDesc) {
		this.locationDesc = locationDesc;
	}

}
