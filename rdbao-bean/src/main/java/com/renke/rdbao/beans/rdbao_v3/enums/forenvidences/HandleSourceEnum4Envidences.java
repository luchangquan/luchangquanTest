package com.renke.rdbao.beans.rdbao_v3.enums.forenvidences;

/**
 * 证据处理程序来源
 * 
 * @author jgshun
 * @date 2016-12-30 上午11:22:19
 * @version 1.0
 */
public enum HandleSourceEnum4Envidences {

	/**
	 * (short) 1, "JAVA"
	 */
	JAVA((short) 1, "JAVA"),
	/**
	 * (short)2,".NET"
	 */
	DOTNET((short) 2, ".NET");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private HandleSourceEnum4Envidences(short code, String desc) {
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

	public static HandleSourceEnum4Envidences getHandleSourceEnumByCode(short code) {
		for (HandleSourceEnum4Envidences _Enum : HandleSourceEnum4Envidences.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}
}
