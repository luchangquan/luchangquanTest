package com.renke.rdbao.beans.rdbao_v3.enums.forfaxvoicedetail;

/**
 * 是否显示虚拟号
 * 
 * @author jgshun
 * @date 2017-1-6 上午10:44:03
 * @version 1.0
 */
public enum ShowVirtualEnum4FaxVoiceDetail {

	/**
	 * (short) 0, "不显示"
	 */
	NOT_SHOW((short) 0, "不显示"),
	/**
	 * (short) 1, "显示"
	 */
	SHOW((short) 1, "显示"), ;

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private ShowVirtualEnum4FaxVoiceDetail(short code, String desc) {
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

	public static ShowVirtualEnum4FaxVoiceDetail getShowVirtualEnumByCode(short code) {
		for (ShowVirtualEnum4FaxVoiceDetail _Enum : ShowVirtualEnum4FaxVoiceDetail.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
