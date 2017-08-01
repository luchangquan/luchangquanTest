package com.renke.rdbao.beans.rdbaosms.enums.forsmsinfo;

/**
 * 0表示未删除，2表示进入回收站 1 表示彻底删除
 * 
 * @author jgshun
 * @date 2016-12-30 上午11:11:06
 * @version 1.0
 */
public enum DeletedEnum4SmsInfo {

	/**
	 * (short) 0, "未删除"
	 */
	NOT_DELETED((short) 0, "未删除"),
	/**
	 * (short) 2, "进入回收站"
	 */
	INTO_THE_RECYCLE_BIN((short) 2, "进入回收站"),
	/**
	 * (short) 1, "彻底删除"
	 */
	REMOVE_COMPLETELY((short) 1, "彻底删除");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private DeletedEnum4SmsInfo(short code, String desc) {
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

	public static DeletedEnum4SmsInfo getDeletedEnumByCode(short code) {
		for (DeletedEnum4SmsInfo _Enum : DeletedEnum4SmsInfo.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}
}
