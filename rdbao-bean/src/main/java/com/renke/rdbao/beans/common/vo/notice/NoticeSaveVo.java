package com.renke.rdbao.beans.common.vo.notice;

import java.io.Serializable;

/**
 * 通知保存成功后返回的数据
 * 
 * @author jgshun
 * @date 2017-2-28 下午8:35:45
 * @version 1.0
 */
public class NoticeSaveVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5289809015730006312L;
	private String fileIdentitiy;// 文件标识
	private String bucketName;// OSS bucket
	private String key;// OSS文件存储key

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
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	public String getFileIdentitiy() {
		return fileIdentitiy;
	}

	public void setFileIdentitiy(String fileIdentitiy) {
		this.fileIdentitiy = fileIdentitiy;
	}

}
