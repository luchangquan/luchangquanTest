package com.renke.rdbao.beans.rdbao_v3.enums.forspevoicenoblackwhitelist;

/**
 * @author jgshun
 * @date 2017-1-6 下午3:38:16
 * @version 1.0
 */
public enum BlackOrWhiteEnum4SPEVoiceNoBlackWhiteList {

	/**
	 * (short) 0, "黑名单"
	 */
	BLACK_LIST((short) 0, "黑名单"),
	/**
	 * (short) 1, "白名单"
	 */
	WHITE_LIST((short) 1, "白名单");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private BlackOrWhiteEnum4SPEVoiceNoBlackWhiteList(short code, String desc) {
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

	public static BlackOrWhiteEnum4SPEVoiceNoBlackWhiteList getBlackOrWhiteEnumByCode(short code) {
		for (BlackOrWhiteEnum4SPEVoiceNoBlackWhiteList _Enum : BlackOrWhiteEnum4SPEVoiceNoBlackWhiteList.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
