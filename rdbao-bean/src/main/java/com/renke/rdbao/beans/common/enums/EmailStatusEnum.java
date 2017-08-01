package com.renke.rdbao.beans.common.enums;

/**
 * @author jgshun
 * @date 2016-12-28 下午7:20:33
 * @version 1.0
 */
public enum EmailStatusEnum {
	/**
	 * (short) 1, "已激活"
	 */
	ACTIVATED((short) 1, "已激活"),
	/**
	 * (short) 0, "未激活"
	 */
	NOT_ACTIVE((short) 0, "未激活");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private EmailStatusEnum(short code, String desc) {
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

	public static EmailStatusEnum getEmailStatusEnumByCode(short code) {
		for (EmailStatusEnum _Enum : EmailStatusEnum.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
