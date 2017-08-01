package com.renke.rdbao.beans.common.conf;

/**
 * 通用配置
 * 
 * @author jgshun
 * @date 2017-2-13 上午11:03:23
 * @version 1.0
 */
public class PropertiesConf {
	private MailConf noticeMailConf;
	private AliSmsConf aliSmsConf;
	private AliStsConf aliStsConf;
	private AliOssConf aliOssConf;
	private AliMnsConf aliMnsConf;
	private CommonConf commonConf;
	private MnsConsumerConf mnsConsumerConf;
	private NoticeConsumerConf noticeConsumerConf;
	private AliOssUploadCallbackConf aliOssUploadCallbackConf;
	private NoticeRemoteSftpConf noticeRemoteSftpConf;

	/**
	 * @return the noticeMailConf
	 */
	public MailConf getNoticeMailConf() {
		return noticeMailConf;
	}

	/**
	 * @param noticeMailConf
	 *            the noticeMailConf to set
	 */
	public void setNoticeMailConf(MailConf noticeMailConf) {
		this.noticeMailConf = noticeMailConf;
	}

	public AliSmsConf getAliSmsConf() {
		return aliSmsConf;
	}

	public void setAliSmsConf(AliSmsConf aliSmsConf) {
		this.aliSmsConf = aliSmsConf;
	}

	public AliStsConf getAliStsConf() {
		return aliStsConf;
	}

	public void setAliStsConf(AliStsConf aliStsConf) {
		this.aliStsConf = aliStsConf;
	}

	public AliOssConf getAliOssConf() {
		return aliOssConf;
	}

	public void setAliOssConf(AliOssConf aliOssConf) {
		this.aliOssConf = aliOssConf;
	}

	public AliMnsConf getAliMnsConf() {
		return aliMnsConf;
	}

	public void setAliMnsConf(AliMnsConf aliMnsConf) {
		this.aliMnsConf = aliMnsConf;
	}

	public NoticeConsumerConf getNoticeConsumerConf() {
		return noticeConsumerConf;
	}

	public void setNoticeConsumerConf(NoticeConsumerConf noticeConsumerConf) {
		this.noticeConsumerConf = noticeConsumerConf;
	}

	public CommonConf getCommonConf() {
		return commonConf;
	}

	public void setCommonConf(CommonConf commonConf) {
		this.commonConf = commonConf;
	}

	public MnsConsumerConf getMnsConsumerConf() {
		return mnsConsumerConf;
	}

	public void setMnsConsumerConf(MnsConsumerConf mnsConsumerConf) {
		this.mnsConsumerConf = mnsConsumerConf;
	}

	public AliOssUploadCallbackConf getAliOssUploadCallbackConf() {
		return aliOssUploadCallbackConf;
	}

	public void setAliOssUploadCallbackConf(AliOssUploadCallbackConf aliOssUploadCallbackConf) {
		this.aliOssUploadCallbackConf = aliOssUploadCallbackConf;
	}

	public NoticeRemoteSftpConf getNoticeRemoteSftpConf() {
		return noticeRemoteSftpConf;
	}

	public void setNoticeRemoteSftpConf(NoticeRemoteSftpConf noticeRemoteSftpConf) {
		this.noticeRemoteSftpConf = noticeRemoteSftpConf;
	}

}
