package com.renke.rdbao.beans.rdbao_v3.enums.forenvidences;

/**
 * @author jgshun
 * @date 2016-12-30 上午11:22:19
 * @version 1.0
 */
public enum ReceiptStateEnum4Envidences {

	/**
	 * (short) 0, "未开票"
	 */
	UNBILLED((short) 0, "未开票");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private ReceiptStateEnum4Envidences(short code, String desc) {
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

	public static ReceiptStateEnum4Envidences getReceiptStateEnumByCode(short code) {
		for (ReceiptStateEnum4Envidences _Enum : ReceiptStateEnum4Envidences.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}
}
