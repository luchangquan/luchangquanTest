package com.renke.rdbao.beans.rdbao_v3.enums.forspevoicenofilterrule;

/**
 * @author jgshun
 * @date 2017-1-6 下午2:51:34
 * @version 1.0
 */
public enum FilterRuleEnum4SPEVoiceNoFilterRule {

	/**
	 * (short) 0, "放行所有"
	 */
	NOT_FILTER((short) 0, "放行所有"),
	/**
	 * (short) 1, "放行所有黑名单除外"
	 */
	FILTERED_EXCLUDE_BLACK_LIST((short) 1, "放行所有黑名单除外"),
	/**
	 * (short) 2, "阻止所有白名单除外"
	 */
	FILTERED_ONLY_PASS_BLACK_LIST((short) 2, "阻止所有白名单除外");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private FilterRuleEnum4SPEVoiceNoFilterRule(short code, String desc) {
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

	public static FilterRuleEnum4SPEVoiceNoFilterRule getFilterRuleEnumByCode(short code) {
		for (FilterRuleEnum4SPEVoiceNoFilterRule _Enum : FilterRuleEnum4SPEVoiceNoFilterRule.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
