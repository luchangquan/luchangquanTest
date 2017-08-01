package com.renke.rdbao.beans.common.enums;

/**
 * 电信产品类型--套餐类型
 * 
 * @author jgshun
 * @date 2017-3-2 下午3:03:31
 * @version 1.0
 */
public enum ChinatelecomProductTypeEnum {
	/**
	 * "main", "主套餐"
	 */
	MAIN("main", "主套餐"),
	/**
	 * "additional", "附加套餐"
	 */
	ADDITIONAL("additional", "附加套餐"), ;

	/**
	 * 编码
	 */
	private String code;
	/**
	 * 描述
	 */
	private String desc;

	private ChinatelecomProductTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
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

	public static ChinatelecomProductTypeEnum getChinatelecomOptypeEnumByCode(String code) {
		for (ChinatelecomProductTypeEnum _Enum : ChinatelecomProductTypeEnum.values()) {
			if (_Enum.getCode().equals(code)) {
				return _Enum;
			}
		}
		return null;
	}

}
