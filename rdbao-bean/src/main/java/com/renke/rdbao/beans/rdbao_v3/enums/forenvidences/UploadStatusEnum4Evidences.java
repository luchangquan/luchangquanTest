package com.renke.rdbao.beans.rdbao_v3.enums.forenvidences;

/**
 * 文件上传状态 0未上传 1上传中 2已上传
 * 
 * @author jgshun
 * @date 2016-12-30 上午11:11:06
 * @version 1.0
 */
public enum UploadStatusEnum4Evidences {

	/**
	 * (short) 0, "未上传"
	 */
	NOT_UPLOADED((short) 0, "未上传"),
	/**
	 * (short) 1, "上传中"
	 */
	UPLOADING((short) 1, "上传中"),
	/**
	 * (short) 2, "已上传"
	 */
	ALREADY_UPLOADED((short) 2, "已上传");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private UploadStatusEnum4Evidences(short code, String desc) {
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

	public static UploadStatusEnum4Evidences getUploadStatusEnumByCode(short code) {
		for (UploadStatusEnum4Evidences _Enum : UploadStatusEnum4Evidences.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}
}
