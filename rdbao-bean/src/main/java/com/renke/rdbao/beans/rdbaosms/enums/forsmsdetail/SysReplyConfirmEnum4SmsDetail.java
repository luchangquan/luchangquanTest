package com.renke.rdbao.beans.rdbaosms.enums.forsmsdetail;

/**
 * 
 * 是否系统通知信息，是即不需要用户回复
 * 
 * @author jgshun
 * @date 2016-12-30 上午11:11:06
 * @version 1.0
 */
public enum SysReplyConfirmEnum4SmsDetail {

	/**
	 * (short) 0, "否"
	 */
	NO((short) 0, "否"),
	/**
	 * (short) 1, "是"
	 */
	YES((short) 1, "是");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private SysReplyConfirmEnum4SmsDetail(short code, String desc) {
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

	public static SysReplyConfirmEnum4SmsDetail getSysReplyConfirmEnumByCode(short code) {
		for (SysReplyConfirmEnum4SmsDetail _Enum : SysReplyConfirmEnum4SmsDetail.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}
}
