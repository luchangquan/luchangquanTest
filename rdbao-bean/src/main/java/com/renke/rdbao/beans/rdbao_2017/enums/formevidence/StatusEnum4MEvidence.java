package com.renke.rdbao.beans.rdbao_2017.enums.formevidence;

/**
 * 状态
 * 
 * @author jgshun
 * @date 2016-12-29 上午11:23:54
 * @version 1.0
 */
public enum StatusEnum4MEvidence {
	/**
	 * (short) 1, "可用"
	 */
	AVAILABLE((short) 1, "可用"),
	/**
	 * (short) 0, "删除"
	 */
	DELETE((short) 0, "删除");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private StatusEnum4MEvidence(short code, String desc) {
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

	public static StatusEnum4MEvidence getStatusEnumByCode(short code) {
		for (StatusEnum4MEvidence _Enum : StatusEnum4MEvidence.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
