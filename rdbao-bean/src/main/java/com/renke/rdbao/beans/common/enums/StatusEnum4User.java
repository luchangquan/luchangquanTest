package com.renke.rdbao.beans.common.enums;

/**
 * @author jgshun
 * @date 2016-12-28 下午7:20:33
 * @version 1.0
 */
public enum StatusEnum4User {
	/**
	 * (short) 0, "未激活"
	 */
	NOT_ACTIVE((short) 0, "未激活"),
	/**
	 * (short) 1, "业务开通"
	 */
	BUSINESS_OPENED((short) 1, "业务开通"),
	/**
	 * (short) 2, "业务关闭"
	 */
	BUSINESS_CLOSED((short) 2, "业务关闭"),
	/**
	 * (short) 99, "不可用"
	 */
	DISABLED((short) 99, "不可用"), ;

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private StatusEnum4User(short code, String desc) {
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

	public static StatusEnum4User getStatusEnumByCode(short code) {
		for (StatusEnum4User _Enum : StatusEnum4User.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
