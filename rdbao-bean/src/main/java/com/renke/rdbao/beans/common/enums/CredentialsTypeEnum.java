package com.renke.rdbao.beans.common.enums;

/**
 * 证件类型枚举
 * 
 * @author jgshun
 * @date 2016-12-28 下午6:55:44
 * @version 1.0
 */
public enum CredentialsTypeEnum {
	/**
	 * (short) 1, "身份证"
	 */
	IDENTITY_CARD((short) 1, "身份证"),
	/**
	 * (short) 2, "台胞证"
	 */
	MTP((short) 2, "台胞证"),
	/**
	 * (short) 3, "港澳身份证"
	 */
	HONG_KONG_OR_MACAO_IDENTITY_CARD((short) 3, "港澳身份证"),
	/**
	 * (short) 4, "护照"
	 */
	PASSPORT((short) 4, "护照"),
	/**
	 * (short) 99, "未知"
	 */
	UNKNOWN((short) 99, "未知");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private CredentialsTypeEnum(short code, String desc) {
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

	public static CredentialsTypeEnum getCredentialsTypeEnumByCode(short code) {
		for (CredentialsTypeEnum _Enum : CredentialsTypeEnum.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
