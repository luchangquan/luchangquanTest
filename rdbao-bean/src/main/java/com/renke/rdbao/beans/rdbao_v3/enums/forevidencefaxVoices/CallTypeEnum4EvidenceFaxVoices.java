package com.renke.rdbao.beans.rdbao_v3.enums.forevidencefaxVoices;

/**
 * 呼叫类型
 * 
 * @author jgshun
 * @date 2016-12-30 下午1:30:36
 * @version 1.0
 */
public enum CallTypeEnum4EvidenceFaxVoices {
	/**
	 * (short) 1, "呼出"
	 */
	CALLING((short) 1, "呼出"),
	/**
	 * (short) 2, "呼入"
	 */
	CALLED((short) 2, "呼入");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private CallTypeEnum4EvidenceFaxVoices(short code, String desc) {
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

	public static CallTypeEnum4EvidenceFaxVoices getCallTypeEnumByCode(short code) {
		for (CallTypeEnum4EvidenceFaxVoices _Enum : CallTypeEnum4EvidenceFaxVoices.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}
}
