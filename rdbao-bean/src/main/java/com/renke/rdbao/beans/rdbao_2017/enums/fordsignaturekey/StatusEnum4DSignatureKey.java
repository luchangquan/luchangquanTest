package com.renke.rdbao.beans.rdbao_2017.enums.fordsignaturekey;

/**
 * 可用状态
 * 
 * @author jgshun
 * @date 2016-12-29 上午11:23:54
 * @version 1.0
 */
public enum StatusEnum4DSignatureKey {
	/**
	 * (short) 1, "启用"
	 */
	NOT_CLOSE((short) 1, "启用"),
	/**
	 * (short) 0, "禁用"
	 */
	CLOSED((short) 0, "禁用");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private StatusEnum4DSignatureKey(short code, String desc) {
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

	public static StatusEnum4DSignatureKey getStatusEnumByCode(short code) {
		for (StatusEnum4DSignatureKey _Enum : StatusEnum4DSignatureKey.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
