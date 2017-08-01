package com.renke.rdbao.beans.common.enums;

/**
 * 用户类型
 * 
 * @author jgshun
 * @date 2016-12-28 下午7:11:52
 * @version 1.0
 */
public enum UserTypeEnum {
	/**
	 * (short) 1, "公司管理员"
	 */
	MANAGER((short) 1, "公司管理员"),
	/**
	 * (short)2,"个人或员工"
	 */
	PERSONAL((short) 2, "个人或员工"),
	/**
	 * (short) 3, "公证处管理员"
	 */
	NOTARY_MANAGER((short) 3, "公证处管理员"),
	/**
	 * (short) 4, "公证处员工"
	 */
	NOTARY_PERSONAL((short) 4, "公证处员工");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private UserTypeEnum(short code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	/**
	 * @return the code
	 */
	public short getCode() {
		return code;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	public static UserTypeEnum getTypeEnumByCode(short code) {
		for (UserTypeEnum _Enum : UserTypeEnum.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}
}
