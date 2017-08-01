package com.renke.rdbao.beans.rdbao_v3.enums.forfaxvoicedetail;

/**
 * 账户状态
 * 
 * @author jgshun
 * @date 2017-1-6 上午10:44:03
 * @version 1.0
 */
public enum StateEnum4FaxVoiceDetail {

	/**
	 * (short) 0, "审核"
	 */
	REVIEW((short) 0, "审核"),
	/**
	 * (short) 1, "可用"
	 */
	AVAILABLE((short) 1, "可用"), ;

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private StateEnum4FaxVoiceDetail(short code, String desc) {
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

	public static StateEnum4FaxVoiceDetail getStateEnumByCode(short code) {
		for (StateEnum4FaxVoiceDetail _Enum : StateEnum4FaxVoiceDetail.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
