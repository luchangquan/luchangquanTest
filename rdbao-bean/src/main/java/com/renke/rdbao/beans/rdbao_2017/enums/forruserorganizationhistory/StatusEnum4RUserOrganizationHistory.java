package com.renke.rdbao.beans.rdbao_2017.enums.forruserorganizationhistory;

/**
 * 状态
 * 
 * @author jgshun
 * @date 2016-12-29 上午11:23:54
 * @version 1.0
 */
public enum StatusEnum4RUserOrganizationHistory {
	/**
	 * (short) 1, "在组中"
	 */
	BE_IN((short) 1, "在组中"),
	/**
	 * (short) 0, "已不再组中"
	 */
	BE_OUT((short) 0, "已不再组中");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private StatusEnum4RUserOrganizationHistory(short code, String desc) {
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

	public static StatusEnum4RUserOrganizationHistory getStatusEnumByCode(short code) {
		for (StatusEnum4RUserOrganizationHistory _Enum : StatusEnum4RUserOrganizationHistory.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
