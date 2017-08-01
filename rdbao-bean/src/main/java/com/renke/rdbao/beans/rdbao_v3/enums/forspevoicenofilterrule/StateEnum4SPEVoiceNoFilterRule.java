package com.renke.rdbao.beans.rdbao_v3.enums.forspevoicenofilterrule;

/**
 * @author jgshun
 * @date 2017-1-6 下午2:51:34
 * @version 1.0
 */
public enum StateEnum4SPEVoiceNoFilterRule {

	/**
	 * (short) 0, "启用"
	 */
	Enable((short) 0, "启用"),
	/**
	 * (short) 1, "不启用"
	 */
	Disable((short) 1, "不启用");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private StateEnum4SPEVoiceNoFilterRule(short code, String desc) {
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

	public static StateEnum4SPEVoiceNoFilterRule getStateEnumByCode(short code) {
		for (StateEnum4SPEVoiceNoFilterRule _Enum : StateEnum4SPEVoiceNoFilterRule.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
