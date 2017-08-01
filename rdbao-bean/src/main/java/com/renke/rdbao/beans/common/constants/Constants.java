package com.renke.rdbao.beans.common.constants;

/**
 * 常量
 * 
 * @author jgshun
 * @date 2017-1-3 上午11:28:36
 * @version 1.0
 */
public abstract class Constants {
	/**
	 * cookie中访问令牌前缀
	 */
	public static final String COOKIE_ACCESS_TOKEN = "C_AT_";

	/**
	 * cookie图片验证码前缀
	 */
	public static final String COOKIE_IMG_VERIFICATION_CODE_TOKEN = "C_I_VCT_";

	/**
	 * cookie短信验证码前缀
	 */
	public static final String COOKIE_SMS_VERIFICATION_CODE_TOKEN = "C_S_VCT_";

	/**
	 * cookie邮件验证码前缀
	 */
	public static final String COOKIE_EMAIL_VERIFICATION_CODE_TOKEN_STRING = "C_E_VCT_";

	/**
	 * cookie加密验证码前缀
	 */
	public static final String COOKIE_VERIFICATION_CODE_TOKEN_STRING = "C_VCT_";

	/**
	 * 加密验证码在cookie中默认有效时间为3分钟
	 */
	public static final int VERIFICATION_CODE_TIME_OUT_SECONDS_IN_COOKIE = 180;

	/**
	 * 用户上下文缓存key键前缀
	 */
	public static final String CACHE_USER_CONTEXT_PREFIX = "USER_CONTEXT_";

	/**
	 * 用户令牌在缓存中默认有效时间为30分钟
	 */
	public static final int ACCESS_TOKEN_TIME_OUT_SECONDS_IN_CACHE = 1800;

	/**
	 * 发送的短信验证码缓存key后缀
	 */
	public static final String CACHE_SMS_VERIFICATION_CODE_SUFFIX = "_SMS_VERIFICATION_CODE";

	/**
	 * 短信验证码在缓存中默认有效时间为3分钟
	 */
	public static final int SMS_VERIFICATION_CODE_TIME_OUT_SECONDS_IN_CACHE = 180;

	/**
	 * 短信验证码再次发送时间缓存key后缀
	 */
	public static final String CACHE_SMS_VERIFICATION_CODE_EXPIRE_DATE_SUFFIX = "_SMS_VERIFICATION_CODE_EXPIRE_DATE";

	/**
	 * 发送的短信验证码在缓存中默认有效时间为40秒
	 */
	public static final int SMS_VERIFICATION_CODE_EXPIRE_DATE_TIME_OUT_SECONDS_IN_CACHE = 40;

	/**
	 * 发送的邮件验证码缓存key后缀
	 */
	public static final String CACHE_EMAIL_VERIFICATION_CODE_SUFFIX = "_EMAIL_VERIFICATION_CODE";

	/**
	 * 邮件验证码在缓存中默认有效时间为3分钟
	 */
	public static final int EMAIL_VERIFICATION_CODE_TIME_OUT_SECONDS_IN_CACHE = 180;

	/**
	 * 发送的邮件验证码再次发送时间缓存key后缀
	 */
	public static final String CACHE_EMAIL_VERIFICATION_CODE_EXPIRE_DATE_SUFFIX = "_EMAIL_VERIFICATION_CODE_EXPIRE_DATE";

	/**
	 * 邮件验证码再次发送时间在缓存中默认有效时间为40秒
	 */
	public static final int EMAIL_VERIFICATION_CODE_EXPIRE_DATE_TIME_OUT_SECONDS_IN_CACHE = 40;

	/**
	 * 图片验证码缓存key后缀
	 */
	public static final String CACHE_IMG_VERIFICATION_CODE_SUFFIX = "_IMG_VERIFICATION_CODE";

	/**
	 * 图片验证码在缓存中默认有效时间为10分钟
	 */
	public static final int IMG_VERIFICATION_CODE_TIME_OUT_SECONDS_IN_CACHE = 600;

	/**
	 * 证据下载链接后缀
	 */
	public static final String CACHE_DOWNLOAD_FILE_URL_SUFFIX = "_DOWNLOAD_FILE_URL";

	/**
	 * 证据下载链接在缓存中默认有效时间为60分钟
	 */
	public static final int DOWNLOAD_FILE_URL_TIME_OUT_SECONDS_IN_CACHE = 3600;

	/**
	 * 当前登录用户 request或者session中的key
	 */
	public static final String CUR_LOGIN_USER_CONTEXT = "userContext";

}
