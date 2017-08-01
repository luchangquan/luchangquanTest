package com.renke.rdbao.beans.rdbao_v3.enums.forfaxvoicedetail;

import com.renke.rdbao.beans.common.enums.OpenSourceEnum;

/**
 * 开户来源--可以理解为服务受理方 {@link OpenSourceEnum ，其为开户来源}
 * 
 * @author jgshun
 * @date 2017-1-6 上午10:44:03
 * @version 1.0
 */
public enum VoiceFromEnum4FaxVoiceDetail {

	/**
	 * (short) 1, "智恒"
	 */
	ZHIHENG((short) 1, "智恒"),
	/**
	 * (short) 2, "人科"
	 */
	RENKE((short) 2, "人科"),
	/**
	 * (short) 3, "电信SPE"
	 */
	SHANGHAI_DIANXIN_SPE((short) 3, "上海电信SPE"), ;

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private VoiceFromEnum4FaxVoiceDetail(short code, String desc) {
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

	public static VoiceFromEnum4FaxVoiceDetail getVoiceFromEnumByCode(short code) {
		for (VoiceFromEnum4FaxVoiceDetail _Enum : VoiceFromEnum4FaxVoiceDetail.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
