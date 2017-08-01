package com.renke.rdbao.beans.rdbao_v3.enums;

/**
 * 
 * @author jgshun
 * @date 2016-12-29 下午4:57:28
 * @version 1.0
 */
public enum ObjectTypeEnum {

	/**
	 * (short) 1, "个人"
	 */
	PERSONAL((short) 1, "个人"),
	/**
	 * (short) 0, "公司"
	 */
	COMPANY((short) 0, "公司");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private ObjectTypeEnum(short code, String desc) {
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

	public static ObjectTypeEnum getItemTypeEnumByCode(short code) {
		for (ObjectTypeEnum _Enum : ObjectTypeEnum.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
