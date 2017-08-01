package com.renke.rdbao.beans.common.enums;

/**
 * @author jgshun
 * @date 2017-3-13 下午6:04:53
 * @version 1.0
 */
public enum PhoneNoTypeEnum {
	/**
	 * 手机
	 */
	CELLPHONE((short) 1, "手机"),
	/**
	 * 固定电话
	 */
	FIXEDPHONE((short) 2, "固定电话"),
	/**
	 * 非法格式号码
	 */
	INVALIDPHONE((short) 99, "非法格式号码");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private PhoneNoTypeEnum(short code, String desc) {
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

	public static PhoneNoTypeEnum getPhoneNoTypeEnumByCode(short code) {
		for (PhoneNoTypeEnum _Enum : PhoneNoTypeEnum.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
