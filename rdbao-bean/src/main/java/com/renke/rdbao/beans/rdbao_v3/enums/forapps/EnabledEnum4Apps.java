package com.renke.rdbao.beans.rdbao_v3.enums.forapps;

/**
 * 
 * @author jgshun
 * @date 2016-12-29 下午4:57:28
 * @version 1.0
 */
public enum EnabledEnum4Apps {

	/**
	 * (short) 1, "启用"
	 */
	enabled((short) 1, "启用"),
	/**
	 * (short) 0, "禁用"
	 */
	disabled((short) 0, "禁用");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private EnabledEnum4Apps(short code, String desc) {
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

	public static EnabledEnum4Apps getEnabledEnumByCode(short code) {
		for (EnabledEnum4Apps _Enum : EnabledEnum4Apps.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
