package com.renke.rdbao.beans.rdbao_v3.enums.forcompanies;

/**
 * 企业实名认证状态
 * 
 * @author jgshun
 * @date 2016-12-29 上午11:14:49
 * @version 1.0
 */
public enum StateEnum4Companies {
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
	UNDER_REVIEW((short) 2, "审核中"),
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

	private StateEnum4Companies(short code, String desc) {
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

	public static StateEnum4Companies getStateEnumByCode(short code) {
		for (StateEnum4Companies _Enum : StateEnum4Companies.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
