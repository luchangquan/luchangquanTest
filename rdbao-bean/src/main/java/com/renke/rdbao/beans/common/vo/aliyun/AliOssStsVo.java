package com.renke.rdbao.beans.common.vo.aliyun;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.renke.rdbao.beans.common.vo.BaseVo;

/**
 * 阿里云 OSS STS相关数据
 * 
 * @author jgshun
 * @date 2017-2-21 下午1:42:43
 * @version 1.0
 */
public class AliOssStsVo extends BaseVo {
	private String endpoint;
	private String accessKeyId;
	private String accessKeySecret;
	private String securityToken;
	private String callbackUrl;
	/**
	 * 当前服务器时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date curServerTime;

	/**
	 * 失效时间 秒
	 */
	private long expirationSeconds;
	/**
	 * 上传到OSS的文件与key列表
	 */
	private List<AliOssIdentityVo> aliOssIdentities;

	/**
	 * 文件与对应的oss存储key
	 * 
	 * @author jgshun
	 * 
	 */
	public static class AliOssIdentityVo extends BaseVo {
		private String filename;// 文件名
		private String bucketName;// OSS bucket
		private String key;// OSS文件存储key

		/**
		 * @return the filename
		 */
		public String getFilename() {
			return filename;
		}

		/**
		 * @param filename
		 *            the filename to set
		 */
		public void setFilename(String filename) {
			this.filename = filename;
		}

		public String getBucketName() {
			return bucketName;
		}

		public void setBucketName(String bucketName) {
			this.bucketName = bucketName;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

	}

	/**
	 * @return the accessKeyId
	 */
	public String getAccessKeyId() {
		return accessKeyId;
	}

	/**
	 * @param accessKeyId
	 *            the accessKeyId to set
	 */
	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	/**
	 * @return the accessKeySecret
	 */
	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	/**
	 * @param accessKeySecret
	 *            the accessKeySecret to set
	 */
	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}

	/**
	 * @return the securityToken
	 */
	public String getSecurityToken() {
		return securityToken;
	}

	/**
	 * @param securityToken
	 *            the securityToken to set
	 */
	public void setSecurityToken(String securityToken) {
		this.securityToken = securityToken;
	}

	/**
	 * @return the curServerTime
	 */
	public Date getCurServerTime() {
		return curServerTime;
	}

	/**
	 * @param curServerTime
	 *            the curServerTime to set
	 */
	public void setCurServerTime(Date curServerTime) {
		this.curServerTime = curServerTime;
	}

	/**
	 * @return the expirationSeconds
	 */
	public long getExpirationSeconds() {
		return expirationSeconds;
	}

	/**
	 * @param expirationSeconds
	 *            the expirationSeconds to set
	 */
	public void setExpirationSeconds(long expirationSeconds) {
		this.expirationSeconds = expirationSeconds;
	}

	public List<AliOssIdentityVo> getAliOssIdentities() {
		return aliOssIdentities;
	}

	public void setAliOssIdentities(List<AliOssIdentityVo> aliOssIdentities) {
		this.aliOssIdentities = aliOssIdentities;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

}
