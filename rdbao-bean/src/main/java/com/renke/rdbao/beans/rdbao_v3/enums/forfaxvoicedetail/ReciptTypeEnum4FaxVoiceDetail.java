package com.renke.rdbao.beans.rdbao_v3.enums.forfaxvoicedetail;

/**
 * 回执类型
 * 
 * @author jgshun
 * @date 2017-1-6 上午10:44:03
 * @version 1.0
 */
public enum ReciptTypeEnum4FaxVoiceDetail {

	/**
	 * (short) 0, "手机短信"
	 */
	PHONE_SMS((short) 0, "手机短信"),
	/**
	 * (short) 1, "邮件"
	 */
	EMAIL((short) 1, "邮件"),
	/**
	 * (short) -1, "不需要"
	 */
	NOT_NEED((short) -1, "不需要"), ;

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private ReciptTypeEnum4FaxVoiceDetail(short code, String desc) {
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

	public static ReciptTypeEnum4FaxVoiceDetail getReciptTypeEnumByCode(short code) {
		for (ReciptTypeEnum4FaxVoiceDetail _Enum : ReciptTypeEnum4FaxVoiceDetail.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
