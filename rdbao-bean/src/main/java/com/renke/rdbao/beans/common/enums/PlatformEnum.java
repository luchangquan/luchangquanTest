package com.renke.rdbao.beans.common.enums;

/**
 * 平台枚举
 * 
 * @author jgshun
 * @date 2017-2-24 下午12:46:52
 * @version 1.0
 */
public enum PlatformEnum {
	/**
	 * "ANDROID", "ANDROID"
	 */
	ANDROID("ANDROID", "ANDROID"),
	/**
	 * "IOS", "IOS"
	 */
	IOS("IOS", "IOS"),

	;

	/**
	 * 编码
	 */
	private String code;
	/**
	 * 描述
	 */
	private String desc;

	private PlatformEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	public static PlatformEnum getPlatformEnumByCode(String code) {
		for (PlatformEnum _Enum : PlatformEnum.values()) {
			if (_Enum.getCode().equals(code)) {
				return _Enum;
			}
		}
		return null;
	}

}
