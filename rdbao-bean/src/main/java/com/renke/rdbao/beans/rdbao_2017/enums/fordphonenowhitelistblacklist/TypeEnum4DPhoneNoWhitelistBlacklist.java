package com.renke.rdbao.beans.rdbao_2017.enums.fordphonenowhitelistblacklist;

/**
 * 公证处可用状态
 * 
 * @author jgshun
 * @date 2016-12-29 上午11:23:54
 * @version 1.0
 */
public enum TypeEnum4DPhoneNoWhitelistBlacklist {
	/**
	 * (short) 1, "开启白名单"
	 */
	OPEN_WHITELIST((short) 1, "开启白名单"),
	/**
	 * (short) 2, "开启黑名单"
	 */
	OPEN_BLACKLIST((short) 2, "开启黑名单"),
	/**
	 * (short) 0, "不启用"
	 */
	NOT_OPEN((short) 0, "不启用");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private TypeEnum4DPhoneNoWhitelistBlacklist(short code, String desc) {
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

	public static TypeEnum4DPhoneNoWhitelistBlacklist getTypeEnumByCode(short code) {
		for (TypeEnum4DPhoneNoWhitelistBlacklist _Enum : TypeEnum4DPhoneNoWhitelistBlacklist.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
