package com.renke.rdbao.beans.rdbao_2017.enums.formevidence;

/**
 * 证据类型
 * 
 * @author jgshun
 * @date 2016-12-30 上午11:35:26
 * @version 1.0
 */
public enum CategoryEnum4MEvidence {

	/**
	 * (short) 1, "文件"
	 */
	FILE((short) 1, "文件"),
	/**
	 * (short) 6, "邮件"
	 */
	EMAIL((short) 6, "邮件"),
	/**
	 * (short) 5, "传真 语音"
	 */
	FAX((short) 5, "传真 语音"),
	/**
	 * (short) 4, "视频"
	 */
	VIDEO((short) 4, "视频"),
	/**
	 * (short) 10, "电子借条"
	 */
	IOU((short) 10, "电子借条"),
	/**
	 * (short) 11, "合同邮"
	 */
	CONTRACT((short) 11, "合同邮"),
	/**
	 * (short) 12, "通知邮"
	 */
	NOTICE((short) 12, "通知邮"),
	/**
	 * (short) 9, "短信"
	 */
	SMS((short) 9, "短信"),
	/**
	 * (short) 14, "存档邮"
	 */
	ARCHIVE((short) 14, "存档邮"),
	/**
	 * (short) 15, "记账宝账"
	 */
	CHARGEACCOUNT((short) 15, "记账宝账"),
	/**
	 * (short) 16, "通知函"
	 */
	NOTICETZ((short) 16, "通知函"),
	/**
	 * (short) 20, "实时保APP照片"
	 */
	APPPICTURE((short) 20, "实时保APP照片"),
	/**
	 * (short) 21, "实时保APP录音"
	 */
	APPVOICE((short) 21, "实时保APP录音"),
	/**
	 * (short) 22, "实时保APP录像"
	 */
	APPVIDEO((short) 22, "实时保APP录像"),
	/**
	 * (short) 23, "实时保APP文件"
	 */
	APPFILE((short) 23, "实时保APP文件"),

	;

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private CategoryEnum4MEvidence(short code, String desc) {
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

	public static CategoryEnum4MEvidence getCategoryEnumByCode(short code) {
		for (CategoryEnum4MEvidence _Enum : CategoryEnum4MEvidence.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
