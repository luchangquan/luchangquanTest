package com.renke.rdbao.beans.rdbao_sms_2017.enums.foesmsInfo;

/**
 * @author jgshun
 * @date 2016-12-28 下午7:20:33
 * @version 1.0
 */
public enum StatusEnum4ESmsInfo {
	/**
	 * (short) 1, "发送中"
	 */
	SENDING((short) 1, "发送中"),
	/**
	 * (short) 2, "发送成功"
	 */
	SEND_SUCCESSFUL((short) 2, "发送成功"),
	/**
	 * (short) 0, "发送失败"
	 */
	SEND_FAILED((short) 0, "发送失败"),

	;

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private StatusEnum4ESmsInfo(short code, String desc) {
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

	public static StatusEnum4ESmsInfo getStatusEnumByCode(short code) {
		for (StatusEnum4ESmsInfo _Enum : StatusEnum4ESmsInfo.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
