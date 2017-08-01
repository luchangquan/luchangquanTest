package com.renke.rdbao.beans.rdbao_2017.enums.fordphonenowhitelistblacklist;

/**
 * 公证处可用状态
 * 
 * @author jgshun
 * @date 2016-12-29 上午11:23:54
 * @version 1.0
 */
public enum StatusEnum4DPhoneNoWhitelistBlacklist {
	/**
	 * (short) 1, "启用"
	 */
	OPEN((short) 1, "启用"),
	/**
	 * (short) 0, "关闭"
	 */
	CLOSE((short) 0, "关闭");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private StatusEnum4DPhoneNoWhitelistBlacklist(short code, String desc) {
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

	public static StatusEnum4DPhoneNoWhitelistBlacklist getStatusEnumByCode(short code) {
		for (StatusEnum4DPhoneNoWhitelistBlacklist _Enum : StatusEnum4DPhoneNoWhitelistBlacklist.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
