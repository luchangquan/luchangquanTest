package com.renke.rdbao.beans.common.enums;

/**
 * 文件类型
 * 
 * @author jgshun
 * @date 2017-3-2 下午3:03:31
 * @version 1.0
 */
public enum FileTypeEnum {
	/**
	 * (short) 0, "图片"
	 */
	IMG((short) 0, "图片"),
	/**
	 * (short) 1, "视频"
	 */
	VIDEO((short) 1, "视频"),
	/**
	 * (short) 2, "ZIP"
	 */
	ZIP((short) 2, "ZIP"),
	/**
	 * (short) 3, "音频"
	 */
	AUDIO((short) 3, "音频"),
	/**
	 * (short) 4, "文本"
	 */
	TEXT((short) 4, "文本"),
	/**
	 * (short) 5, "签名文本"
	 */
	SIGN_TEXT((short) 5, "签名文本"),
	/**
	 * (short) 6, "移动轨迹文本"
	 */
	ORBIT_TEXT((short) 6, "移动轨迹文本"),
	/**
	 * (short) 99, "未知"
	 */
	UNKNOWN((short) 99, "未知");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private FileTypeEnum(short code, String desc) {
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

	public static FileTypeEnum getFileTypeEnumByCode(short code) {
		for (FileTypeEnum _Enum : FileTypeEnum.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
