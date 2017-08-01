package com.renke.rdbao.beans.common.enums;

/**
 * @author jgshun
 * @date 2017-2-21 下午12:46:49
 * @version 1.0
 */
public enum AliOssBucketEnum {
	/**
	 * "OSS0001", "rdbao-acs-logs", "日志存储"
	 */
	RDBAO_ACS_LOGS("OSS0001", "rdbao-acs-logs-test", "日志存储"),
	/**
	 * "OSS0002", "rdbao-app-store", "APP版本存储"
	 */
	RDBAO_APP_STORE("OSS0002", "rdbao-app-store-test", "APP版本存储"),
	/**
	 * "OSS0003", "rdbao-evidence-resources", "证据存储"
	 */
	RDBAO_EVIDENCE_RESOURCES("OSS0003", "rdbao-evidence-resources-test", "证据存储"),
	/**
	 * "OSS0004", "rdbao-common-resource", "其它资源存储"
	 */
	RDBAO_COMMON_RESOURCES("OSS0004", "rdbao-common-resource-test", "其它资源存储"),

	;

	/**
	 * 编码
	 */
	private String code;
	/**
	 * 模板名称
	 */
	private String name;
	/**
	 * 描述
	 */
	private String desc;

	private AliOssBucketEnum(String code, String name, String desc) {
		this.code = code;
		this.name = name;
		this.desc = desc;
	}

	public static AliOssBucketEnum getAliOssBucketEnumByCode(String code) {
		for (AliOssBucketEnum _Enum : AliOssBucketEnum.values()) {
			if (_Enum.getCode().equals(code)) {
				return _Enum;
			}
		}
		return null;
	}

	public static AliOssBucketEnum getAliOssBucketEnumByName(String name) {
		for (AliOssBucketEnum _Enum : AliOssBucketEnum.values()) {
			if (_Enum.getName().equals(name)) {
				return _Enum;
			}
		}
		return null;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc
	 *            the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

}
