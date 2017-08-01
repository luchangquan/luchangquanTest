package com.renke.rdbao.beans.rdbao_v3.enums.forusers;

/**
 * 账号可用状态
 * 
 * @author jgshun
 * @date 2016-12-29 上午11:23:54
 * @version 1.0
 */
public enum DisabledEnum4Users {
	/**
	 * (short) 0, "用户可用"
	 */
	NOT_CLOSE((short) 0, "用户可用"),
	/**
	 * (short) 1, "用户被禁"
	 */
	CLOSED((short) 1, "用户被禁");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private DisabledEnum4Users(short code, String desc) {
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

	public static DisabledEnum4Users getDisabledEnumByCode(short code) {
		for (DisabledEnum4Users _Enum : DisabledEnum4Users.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
