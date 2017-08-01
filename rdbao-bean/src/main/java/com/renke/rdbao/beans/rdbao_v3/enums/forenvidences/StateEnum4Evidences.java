package com.renke.rdbao.beans.rdbao_v3.enums.forenvidences;

/**
 * 状态 -1 缓存、暂存 0 已存证 4 已出证
 * 
 * @author jgshun
 * @date 2016-12-30 上午11:11:06
 * @version 1.0
 */
public enum StateEnum4Evidences {

	/**
	 * (short) -1, "缓存、暂存"
	 */
	TEMPORARY((short) -1, "缓存、暂存"),
	/**
	 * (short) 0, "已存证"
	 */
	STORED_CERTIFICATE((short) 0, "已存证"),
	/**
	 * (short) 4, "已出证"
	 */
	HAS_BEEN_OUT_OF_THE_CERTIFICATE((short) 4, "已出证");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private StateEnum4Evidences(short code, String desc) {
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

	public static StateEnum4Evidences getStateEnumByCode(short code) {
		for (StateEnum4Evidences _Enum : StateEnum4Evidences.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}
}
