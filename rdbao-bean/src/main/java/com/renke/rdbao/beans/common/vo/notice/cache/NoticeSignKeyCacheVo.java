package com.renke.rdbao.beans.common.vo.notice.cache;

import java.io.Serializable;

/**
 * 通知文件放入缓存中的对象
 * 
 * @author jgshun
 * @date 2017-3-2 下午12:21:38
 * @version 1.0
 */
public class NoticeSignKeyCacheVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4482008360064523847L;
	// 证据id缓存key
	private String evidencesCache;
	// 文件是否上传成功
	private boolean upload;
	// 文件大小
	private long size;

	/**
	 * @return the upload
	 */
	public boolean isUpload() {
		return upload;
	}

	/**
	 * @param upload
	 *            the upload to set
	 */
	public void setUpload(boolean upload) {
		this.upload = upload;
	}

	public String getEvidencesCache() {
		return evidencesCache;
	}

	public void setEvidencesCache(String evidencesCache) {
		this.evidencesCache = evidencesCache;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

}
