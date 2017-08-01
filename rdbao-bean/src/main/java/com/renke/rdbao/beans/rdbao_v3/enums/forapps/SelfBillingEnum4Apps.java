package com.renke.rdbao.beans.rdbao_v3.enums.forapps;

/**
 * 
 * @author jgshun
 * @date 2016-12-29 下午4:57:28
 * @version 1.0
 */
public enum SelfBillingEnum4Apps {

	/**
	 * (short) 1, "人科计费"
	 */
	SELF((short) 1, "人科计费"),
	/**
	 * (short) 0, "非人科计费"
	 */
	NOT_SELF((short) 0, "非人科计费");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private SelfBillingEnum4Apps(short code, String desc) {
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

	public static SelfBillingEnum4Apps getSelfBillingByCode(short code) {
		for (SelfBillingEnum4Apps _Enum : SelfBillingEnum4Apps.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
