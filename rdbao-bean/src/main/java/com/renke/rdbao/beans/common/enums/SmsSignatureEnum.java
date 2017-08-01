package com.renke.rdbao.beans.common.enums;

/**
 * 短信签名枚举
 * 
 * @author jgshun
 * @date 2016-12-28 下午7:16:40
 * @version 1.0
 */
public enum SmsSignatureEnum {
	/**
	 * "公证录音", "公证录音", "签名类型：验证码或短信通知"
	 */
	NOTARIZATION_RECORDING("公证录音", "公证录音", "签名类型：验证码或短信通知"),
	/**
	 * "实时保", "实时保", "签名类型：验证码或短信通知"
	 */
	BAO1010("实时保", "实时保", "签名类型：验证码或短信通知"), ;

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

	private SmsSignatureEnum(String code, String name, String desc) {
		this.code = code;
		this.name = name;
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

	public static SmsSignatureEnum getSmsSignatureEnumByCode(String code) {
		for (SmsSignatureEnum _Enum : SmsSignatureEnum.values()) {
			if (_Enum.getCode().equals(code)) {
				return _Enum;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
