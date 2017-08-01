package com.renke.rdbao.beans.common.enums;

/**
 * @author jgshun
 * @date 2017-4-11 下午4:51:36
 * @version 1.0
 */
public enum TenantEnum {
	/**
	 * "189", "189公证录音"
	 */
	TENANT_189("189", "189公证录音"),
	/**
	 * "1010bao", "实时保"
	 */
	TENANT_1010BAO("1010bao", "实时保"),

	;

	/**
	 * 编码
	 */
	private String code;
	/**
	 * 描述
	 */
	private String desc;

	private TenantEnum(String code, String desc) {
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

	public static TenantEnum getTenantEnumByCode(String code) {
		for (TenantEnum _Enum : TenantEnum.values()) {
			if (_Enum.getCode().equals(code)) {
				return _Enum;
			}
		}
		return null;
	}

}
