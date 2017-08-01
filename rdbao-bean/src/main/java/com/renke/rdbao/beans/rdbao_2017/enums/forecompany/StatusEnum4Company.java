package com.renke.rdbao.beans.rdbao_2017.enums.forecompany;

/**
 * @author jgshun
 * @date 2017-4-7 下午6:32:13
 * @version 1.0
 */
public enum StatusEnum4Company {

	/**
	 * (short) 0, "未认证"
	 */
	UNAUTHORIZED((short) 0, "未认证"),
	/**
	 * (short) 1, "已认证"
	 */
	AUTHENTICATED((short) 1, "已认证"),
	/**
	 * (short) 2, "审核中"
	 */
	UNDER_AUDIT((short) 2, "审核中"),
	/**
	 * (short) -1, "审核失败"
	 */
	AUDIT_FAILURE((short) -1, "审核失败");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private StatusEnum4Company(short code, String desc) {
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

	public static StatusEnum4Company getStatusEnumByCode(short code) {
		for (StatusEnum4Company _Enum : StatusEnum4Company.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
