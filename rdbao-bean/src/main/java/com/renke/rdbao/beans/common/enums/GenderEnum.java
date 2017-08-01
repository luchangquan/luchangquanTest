package com.renke.rdbao.beans.common.enums;

/**
 * 性别
 * 
 * @author jgshun
 * @date 2016-12-28 下午7:16:40
 * @version 1.0
 */
public enum GenderEnum {
	/**
	 * (short) 1, "男性")
	 */
	MALE((short) 1, "男性"),
	/**
	 * (short) 2, "女性"
	 */
	FEMALE((short) 2, "女性"),
	/**
	 * (short) 0, "未知"
	 */
	UNKNOWN((short) 0, "未知");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private GenderEnum(short code, String desc) {
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

	public static GenderEnum getGenderEnumByCode(short code) {
		for (GenderEnum _Enum : GenderEnum.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
