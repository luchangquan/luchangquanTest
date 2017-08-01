package com.renke.rdbao.beans.common.enums;

public enum ResponseEnum {
	/**
	 * "999", "未知错误"
	 */
	UNKNOWN("9999", "未知错误"),
	/**
	 * "0000","成功"
	 */
	SUCCESS("0000", "成功"),
	/**
	 * "0001", "签名验证失败"
	 */
	SIGNATURE_VERIFICATION_FAILED("0001", "签名验证失败"),
	/**
	 * "0002", "用户不存在"
	 */
	USER_DOES_NOT_EXIST("0002", "用户不存在"),
	/**
	 * "0003", "套餐不存在"
	 */
	PACKAGE_DOES_NOT_EXIST("0003", "套餐不存在"),
	/**
	 * "0004", "参数有误"
	 */
	PARAMETER_ERROR("0004", "参数有误"),
	/**
	 * "0005", "令牌已过期"
	 */
	TOKEN_HAS_EXPIRED("0005", "令牌已过期"),
	/**
	 * "0006", "令牌不存在"
	 */
	TOKEN_DOES_NOT_EXIST("0006", "令牌不存在"),
	/**
	 * "0007", "令牌已被使用"
	 */
	TOKEN_HAS_BEEN_USED("0007", "令牌已被使用"),
	/**
	 * "0008", "令牌无效"
	 */
	TOKEN_INVALID("0008", "令牌无效"),
	/**
	 * "0009", "缺少用户上下文"
	 */
	LACK_OF_USER_CONTEXT("0009", "缺少用户上下文"),
	/**
	 * "0010", "缺少所属用户"
	 */
	LACK_OF_USERS("0010", "缺少所属用户"),

	/**
	 * "0011", "密码错误"
	 */
	PASSWORD_ERROR("0011", "密码错误"),
	/**
	 * "0012", "用户上下文不能为空"
	 */
	USER_CONTEXT_CANNOT_BE_EMPTY("0012", "用户上下文不能为空"),

	/**
	 * "0013", "公司不存在"
	 */
	COMPANY_DOES_NOT_EXIST("0013", "公司不存在"),

	/**
	 * "0014", "账户不可用"
	 */
	ACCOUNT_IS_NOT_AVAILABLE("0014", "账户不可用"),

	/**
	 * "0015", "图片验证码不能为空"
	 */
	PICTURE_VALIDATE_CODE_CAN_NOT_BE_EMPTY("0015", "图片验证码不能为空"),

	/**
	 * "0016", "图片验证码有误"
	 */
	PICTURE_VALIDATE_CODE_IS_WRONG("0016", "图片验证码有误"),

	/**
	 * "0017", "证据不存在"
	 */
	EVIDENCE_DOES_NOT_EXIST("0017", "证据不存在"),

	/**
	 * "0018", "无权操作"
	 */
	UNAUTHORIZED_OPERATION("0018", "无权操作"),
	/**
	 * "0019", "手机号未激活"
	 */
	PHONENO_STATUS_NOT_ACTIVE("0019", "手机号未激活"),
	/**
	 * "0020", "邮箱未激活"
	 */
	EMAIL_STATUS_NOT_ACTIVE("0020", "邮箱未激活"),
	/**
	 * "0021", "短信验证码校验失败"
	 */
	SMS_VERIFICATION_CODE_VERIFICATION_FAILURE("0021", "短信验证码校验失败"),
	/**
	 * "0022", "图片验证码校验失败"
	 */
	IMG_VERIFICATION_CODE_VERIFICATION_FAILURE("0022", "图片验证码校验失败"),
	/**
	 * "0023", "邮件验证码校验失败"
	 */
	EMAIL_VERIFICATION_CODE_VERIFICATION_FAILURE("0023", "邮件验证码校验失败"),
	/**
	 * "0024", "OSS文件不存在"
	 */
	ALI_OSS_FILE_NOT_EXIST("0024", "OSS文件不存在"),
	/**
	 * "0026", "OSS文件已存在"
	 */
	ALI_OSS_FILE_EXISTED("0026", "OSS文件已存在"),
	/**
	 * "0027", "非法操作"
	 */
	ILLEGAL_OPERATION("0027", "非法操作"),
	/**
	 * "0028", "通知任务缓存不存在"
	 */
	NOTICE_TASK_CACHE_NOT_EXIST("0028", "通知任务缓存不存在"),
	/**
	 * "0029","证据尚未入库"
	 */
	EVIDENCE_NOT_STORAGED("0029", "证据尚未入库"),
	/**
	 * "0030", "用户已存在"
	 */
	USER_EXISTED("0030", "用户已存在"),
	/**
	 * "0031", "未知操作"
	 */
	UNKNOWN_OPERATION("0031", "未知操作"),
	/**
	 * "0032", "文件摘要不一致"
	 */
	FILE_SUMMARY_INCONSISTENCY("0032", "文件摘要不一致"),
	/**
	 * "0033", "未开通此产品"
	 */
	NOT_OPEN_PRODUCT("0033", "未开通此产品"),
	/**
	 * "0034", "签名密钥不存在"
	 */
	SIGNATURE_KEY_NOT_EXIST("0034", "签名密钥不存在"),

	/**
	 * "0035", "签名密钥不在有效期内"
	 */
	SIGNATURE_KEY_NOT_WITHIN_THE_VALIDITY_PERIOD("0035", "签名密钥不在有效期内"),

	/**
	 * "0036", "签名密钥不可用"
	 */
	SIGNATURE_KEY_NOT_AVAILABLE("0036", "签名密钥不可用"),

	/**
	 * "0037", "已开通此产品"
	 */
	OPENED_PRODUCT("0037", "已开通此产品"),;

	private String respCode;
	private String respDesc;

	private ResponseEnum(String respCode, String respDesc) {
		this.respCode = respCode;
		this.respDesc = respDesc;
	}

	/**
	 * @return the respCode
	 */
	public String getRespCode() {
		return respCode;
	}

	/**
	 * @return the respDesc
	 */
	public String getRespDesc() {
		return respDesc;
	}

}
