package com.renke.rdbao.beans.rdbao_2017.enums.foradppversion;

/**
 * @author jgshun
 * @date 2017-5-11 下午8:44:10
 * @version 1.0
 */
public enum AppOsEnum4DAppVersion {

	/**
	 * "android", "安卓"
	 */
	ANDROID("android", "安卓"),
	/**
	 * "iphone", "苹果"
	 */
	IPHONE("iphone", "苹果"),

	;

	/**
	 * 编码
	 */
	private String code;
	/**
	 * 描述
	 */
	private String desc;

	private AppOsEnum4DAppVersion(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static AppOsEnum4DAppVersion getAppOsEnumByCode(String code) {
		for (AppOsEnum4DAppVersion _Enum : AppOsEnum4DAppVersion.values()) {
			if (_Enum.getCode().equals(code)) {
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
