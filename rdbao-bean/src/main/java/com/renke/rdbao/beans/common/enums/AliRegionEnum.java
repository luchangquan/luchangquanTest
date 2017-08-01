package com.renke.rdbao.beans.common.enums;

/**
 * 阿里云region
 * 
 * @author jgshun
 * @date 2016-12-28 下午7:16:40
 * @version 1.0
 */
public enum AliRegionEnum {

	/**
	 * "cn-beijing", "北京"
	 */
	BEIJING("cn-beijing", "北京"),
	/**
	 * "cn-qingdao", "青岛"
	 */
	QINGDAO("cn-qingdao", "青岛"),
	/**
	 * "cn-hangzhou", "杭州"
	 */
	HANGZHOU("cn-hangzhou", "杭州"),
	/**
	 * "cn-hongkong", "香港"
	 */
	HONGKONG("cn-hongkong", "香港"),
	/**
	 * "cn-shenzhen", "深圳"
	 */
	SHENZHEN("cn-shenzhen", "深圳"),
	/**
	 * "cn-west-1", "west-1"
	 */
	WEST1("cn-west-1", "west-1"), ;

	/**
	 * 编码
	 */
	private String code;
	/**
	 * 描述
	 */
	private String desc;

	private AliRegionEnum(String code, String desc) {
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
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	public static AliRegionEnum getAliRegionEnumByCode(String code) {
		for (AliRegionEnum _Enum : AliRegionEnum.values()) {
			if (_Enum.getCode().equals(code)) {
				return _Enum;
			}
		}
		return null;
	}

}
