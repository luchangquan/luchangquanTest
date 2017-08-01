package com.renke.rdbao.beans.rdbao_2017.enums.forrphonenoblacklist;

/**
 * 公证处可用状态
 * 
 * @author jgshun
 * @date 2016-12-29 上午11:23:54
 * @version 1.0
 */
public enum StatusEnum4RPhoneNoBlacklist {
	/**
	 * (short) 1, "启用"
	 */
	OPEN((short) 1, "启用"),
	/**
	 * (short) 0, "关闭"
	 */
	CLOSE((short) 0, "关闭");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private StatusEnum4RPhoneNoBlacklist(short code, String desc) {
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

	public static StatusEnum4RPhoneNoBlacklist getStatusEnumByCode(short code) {
		for (StatusEnum4RPhoneNoBlacklist _Enum : StatusEnum4RPhoneNoBlacklist.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
