package com.renke.rdbao.beans.rdbao_sms_2017.enums.fordsmssignature;

/**
 * @author jgshun
 * @date 2016-12-28 下午7:20:33
 * @version 1.0
 */
public enum StatusEnum4DSmsSignature {
	/**
	 * (short) 0, "不可用"
	 */
	DISABLED((short) 0, "不可用"),
	/**
	 * (short) 1, "业务开通"
	 */
	AVAILABLE((short) 1, "可用"),;

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private StatusEnum4DSmsSignature(short code, String desc) {
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

	public static StatusEnum4DSmsSignature getStatusEnumByCode(short code) {
		for (StatusEnum4DSmsSignature _Enum : StatusEnum4DSmsSignature.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
