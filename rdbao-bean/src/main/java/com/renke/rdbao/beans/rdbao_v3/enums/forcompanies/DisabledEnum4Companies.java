package com.renke.rdbao.beans.rdbao_v3.enums.forcompanies;

/**
 * 企业实名认证状态
 * 
 * @author jgshun
 * @date 2016-12-29 上午11:23:54
 * @version 1.0
 */
public enum DisabledEnum4Companies {

	/**
	 * (short) 0, "未关闭"
	 */
	NOT_CLOSE((short) 0, "未关闭"),
	/**
	 * (short) 1, "已关闭"
	 */
	CLOSED((short) 1, "已关闭");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private DisabledEnum4Companies(short code, String desc) {
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

	public static DisabledEnum4Companies getDisabledEnumByCode(short code) {
		for (DisabledEnum4Companies _Enum : DisabledEnum4Companies.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
