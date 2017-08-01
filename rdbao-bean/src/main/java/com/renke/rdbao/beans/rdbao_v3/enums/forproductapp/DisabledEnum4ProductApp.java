package com.renke.rdbao.beans.rdbao_v3.enums.forproductapp;

/**
 * 可用状态
 * 
 * @author jgshun
 * @date 2016-12-29 上午11:23:54
 * @version 1.0
 */
public enum DisabledEnum4ProductApp {
	/**
	 * (short) 0, "未禁用"
	 */
	NOT_CLOSE((short) 0, "未禁用"),
	/**
	 * (short) 1, "已禁用"
	 */
	CLOSED((short) 1, "已禁用");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private DisabledEnum4ProductApp(short code, String desc) {
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

	public static DisabledEnum4ProductApp getDisabledEnumByCode(short code) {
		for (DisabledEnum4ProductApp _Enum : DisabledEnum4ProductApp.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
