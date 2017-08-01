package com.renke.rdbao.beans.rdbao_2017.enums.forrusenpp;

/**
 * @author jgshun
 * @date 2016-12-28 下午7:20:33
 * @version 1.0
 */
public enum StatusEnum4RUserNpp {
	/**
	 * (short) 0, "不可用"
	 */
	DISABLED((short) 0, "不可用"),
	/**
	 * (short) 1, "业务开通"
	 */
	BUSINESS_OPENED((short) 1, "业务开通"),
	/**
	 * (short) 2, "业务关闭"
	 */
	BUSINESS_CLOSED((short) 2, "业务关闭");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private StatusEnum4RUserNpp(short code, String desc) {
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

	public static StatusEnum4RUserNpp getStatusEnumByCode(short code) {
		for (StatusEnum4RUserNpp _Enum : StatusEnum4RUserNpp.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
