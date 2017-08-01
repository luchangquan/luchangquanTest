package com.renke.rdbao.beans.rdbao_v3.enums.forenvidences;

/**
 * 文件存储类型
 * 
 * @author jgshun
 * @date 2016-12-30 上午11:11:06
 * @version 1.0
 */
public enum StorageTypeEnum4Evidences {

	/**
	 * (short) 1, "公证处"
	 */
	PNOES((short) 1, "公证处"),
	/**
	 * (short) 2, "OSS"
	 */
	ALI_OSS((short) 2, "OSS");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private StorageTypeEnum4Evidences(short code, String desc) {
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

	public static StorageTypeEnum4Evidences getStorageTypeEnumByCode(short code) {
		for (StorageTypeEnum4Evidences _Enum : StorageTypeEnum4Evidences.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}
}
