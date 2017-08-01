package com.renke.rdbao.beans.common.enums;

/**
 * @author jgshun
 * @date 2017-4-11 下午4:51:36
 * @version 1.0
 */
public enum SmsTypeEnum {
	/**
	 * (short)1, "验证码"
	 */
	VERIFICATION_CODE((short) 1, "验证码"),
	/**
	 * (short)2, "短信通知"
	 */
	NOTICE((short) 2, "短信通知"),
	/**
	 * (short)3, "需回复的短信"
	 */
	NEED_REPLY((short) 3, "需回复的短信"),

	;

	/**
	 * 编码
	 */
	private Short code;
	/**
	 * 描述
	 */
	private String desc;

	private SmsTypeEnum(Short code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static SmsTypeEnum getSmsTypeEnumByCode(short code) {
		for (SmsTypeEnum _Enum : SmsTypeEnum.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

	public Short getCode() {
		return code;
	}

	public void setCode(Short code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
