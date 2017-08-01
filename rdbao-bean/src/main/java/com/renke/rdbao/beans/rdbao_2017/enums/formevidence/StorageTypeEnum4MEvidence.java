package com.renke.rdbao.beans.rdbao_2017.enums.formevidence;

/**
 * 文件存储类型
 * 
 * @author jgshun
 * @date 2016-12-30 上午11:11:06
 * @version 1.0
 */
public enum StorageTypeEnum4MEvidence {

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

	private StorageTypeEnum4MEvidence(short code, String desc) {
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

	public static StorageTypeEnum4MEvidence getStorageTypeEnumByCode(short code) {
		for (StorageTypeEnum4MEvidence _Enum : StorageTypeEnum4MEvidence.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}
}
