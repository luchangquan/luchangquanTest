package com.renke.rdbao.beans.rdbao_2017.enums.fordproduct;

/**
 * 可用状态
 * 
 * @author jgshun
 * @date 2016-12-29 上午11:23:54
 * @version 1.0
 */
public enum StatusEnum4DProduct {
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

	private StatusEnum4DProduct(short code, String desc) {
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

	public static StatusEnum4DProduct getStatusEnumByCode(short code) {
		for (StatusEnum4DProduct _Enum : StatusEnum4DProduct.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
