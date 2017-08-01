package com.renke.rdbao.beans.rdbao_2017.enums.forephonenoproductInterimrecord;

/**
 * 公证处可用状态
 * 
 * @author jgshun
 * @date 2016-12-29 上午11:23:54
 * @version 1.0
 */
public enum StatusEnum4EPhoneNoProductInterimRecord {
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

	private StatusEnum4EPhoneNoProductInterimRecord(short code, String desc) {
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

	public static StatusEnum4EPhoneNoProductInterimRecord getStatusEnumByCode(short code) {
		for (StatusEnum4EPhoneNoProductInterimRecord _Enum : StatusEnum4EPhoneNoProductInterimRecord.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
