package com.renke.rdbao.beans.common.enums;

/**
 * 所属用户表
 * 
 * @author jgshun
 * 
 */
public enum UserTableEnum {
	/**
	 * "e_189_user", "公证录音用户表"
	 */
	E_189_USER("e_189_user", "公证录音用户表"),
	/**
	 * "Users", "实时保用户表"
	 */
	USERS("Users", "实时保用户表"),

	;

	/**
	 * 编码
	 */
	private String code;
	/**
	 * 描述
	 */
	private String desc;

	private UserTableEnum(String code, String desc) {
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

	public static UserTableEnum getUserTableEnumByCode(String code) {
		for (UserTableEnum _Enum : UserTableEnum.values()) {
			if (_Enum.getCode().equals(code)) {
				return _Enum;
			}
		}
		return null;
	}

}
