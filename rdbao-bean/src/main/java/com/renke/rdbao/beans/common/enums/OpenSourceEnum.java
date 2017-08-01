package com.renke.rdbao.beans.common.enums;

/**
 * 开户来源
 * 
 * @author jgshun
 * @date 2016-12-28 下午7:26:20
 * @version 1.0
 */
public enum OpenSourceEnum {
	/**
	 * (short) 1, "上海电信"
	 */
	SHANGHAI_TELECOM((short) 1, "上海电信"),
	/**
	 * (short) 2, "江苏电信"
	 */
	JIANGSU_TELECOM((short) 2, "江苏电信"),
	/**
	 * (short) 3, "人科开到上海电信"
	 */
	RENKE_2_SHANGHAI_TELECOM((short) 3, "人科开到上海电信"),
	/**
	 * (short) 4, "人科开到江苏电信"
	 */
	RENKE_2_JIANGSU_TELECOM((short) 4, "人科开到江苏电信"),
	/**
	 * (short) 99, "门户注册"
	 */
	RDBAO_GATEWAY((short) 99, "门户注册"),
	/**
	 * (short) 98, "APP注册"
	 */
	RDBAO_APP((short) 98, "APP注册"),
	/**
	 * (short) 97, "实时保后台注册"
	 */
	RDBAO_MANAGER((short) 97, "实时保后台注册"), ;

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private OpenSourceEnum(short code, String desc) {
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

	public static OpenSourceEnum getOpenSourceEnumByCode(short code) {
		for (OpenSourceEnum _Enum : OpenSourceEnum.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
