package com.renke.rdbao.beans.rdbao_v3.enums.forcompanies;

/**
 * 信息来源(0.门户1.开户,2微信,3微博，4网信,5现场开通)
 * 
 * @author jgshun
 * @date 2016-12-29 上午11:31:44
 * @version 1.0
 */
public enum InfoSourceEnum4Companies {

	/**
	 * (short) 0, "门户"
	 */
	GATEWAY((short) 0, "门户"),
	/**
	 * (short) 1, "开户"
	 */
	OPEN_ACCOUNT((short) 1, "开户"),
	/**
	 * (short) 2, "微信"
	 */
	WECHAT((short) 2, "微信"),
	/**
	 * (short) 3, "微博"
	 */
	MICRO_BLOG((short) 3, "微博"),
	/**
	 * (short) 4, "网信"
	 */
	WANG_XIN((short) 4, "网信"),
	/**
	 * (short) 5, "现场开通"
	 */
	SCENE((short) 5, "现场开通");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private InfoSourceEnum4Companies(short code, String desc) {
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

	public static InfoSourceEnum4Companies getInfoSourceEnumByCode(short code) {
		for (InfoSourceEnum4Companies _Enum : InfoSourceEnum4Companies.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
