package com.renke.rdbao.beans.common.vo.notice;

import java.io.Serializable;

/**
 * 文件标识
 * 
 * @author jgshun
 * @date 2017-3-1 上午11:49:42
 * @version 1.0
 */
public class NoticeIdentityRequestData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7163132115258999081L;
	private String fileIdentity;// 文件标识
	// 文件命名格式(appCode_userAccount_年月日时分秒毫米+三位随机数_文件md5.文件格式):APPVIDEO_CAILY_20170301114123000789_jghibghihfsfisbfkwdcvbnmjuigdcvr.3gp
	private String md5;// 文件的md5

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

	public String getFileIdentity() {
		return fileIdentity;
	}

	public void setFileIdentity(String fileIdentity) {
		this.fileIdentity = fileIdentity;
	}

}
