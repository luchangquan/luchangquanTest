package com.renke.rdbao.beans.common.enums;

/**
 * 短信模板枚举
 * 
 * @author jgshun
 * @date 2016-12-28 下午7:16:40
 * @version 1.0
 */
public enum SmsTemplateEnum {
	/**
	 * "SMS_42820123", "找回密码", "您的验证码为：${code}，用于找回密码，3分钟内有效。如非本人操作，请勿理会。"
	 */
	RETRIEVE_PASSWORD_CODE("SMS_42820123", "找回密码", "您的验证码为：${code}，用于找回密码，3分钟内有效。如非本人操作，请勿理会。"),
	/**
	 * "SMS_55555184", "公证录音账户激活",
	 * "恭喜您，您的公证录音账号${account}已经开通！请登录平台进行激活：https://189.1010bao.com/activate"
	 */
	NOTARIZATION_RECORDING_ACTIVE_ACCOUNT("SMS_55555184", "公证录音账户激活", "恭喜您，您的公证录音账号${account}已经开通！请登录平台进行激活：https://189.1010bao.com/activate"),
	/**
	 * "SMS_56010067", "账户激活验证码",
	 * "您的验证码为：${code}，用于激活账户${account}，3分钟内有效。如非本人操作，请勿理会。"
	 */
	ACTIVE_ACCOUNT_CODE("SMS_56010067", "账户激活验证码", "您的验证码为：${code}，用于激活账户${account}，3分钟内有效。如非本人操作，请勿理会。"),
	/**
	 * "SMS_56120044", "修改密码验证码",
	 * "您的验证码为：${code}，用于修改账户${account}密码，3分钟内有效。如非本人操作，请勿理会。"
	 */
	MODIFY_PASSWORD_CODE("SMS_56120044", "修改密码验证码", "您的验证码为：${code}，用于修改账户${account}密码，3分钟内有效。如非本人操作，请勿理会。"),

	/**
	 * "SMS_58245175", "手机绑定验证码",
	 * "您的验证码为：${code}，用于绑定账户${account}，3分钟内有效。请妥善保管。"
	 */
	BIND_MOBILE_PHONE_CODE("SMS_58245175", "手机绑定验证码", "您的验证码为：${code}，用于绑定账户${account}，3分钟内有效。请妥善保管。"),

	/**
	 * "SMS_58465020", "手机更换绑定验证码",
	 * "您的验证码为：${code}，用于更换绑定账户${account}，3分钟内有效。请妥善保管。"
	 */
	MODIFY_BIND_MOBILE_PHONE_CODE("SMS_58465020", "手机更换绑定验证码", "您的验证码为：${code}，用于更换绑定账户${account}，3分钟内有效。请妥善保管。"),

	/**
	 * "SMS_67201040", "实时保账户激活通知",
	 * "恭喜您，您的实时保账户${account}已经开通！请点击下方链接激活账户，并设置密码：https://www.1010bao.com${param}"
	 */
	BAO1010_ACCOUNT_ACTIVATION_NOTIFICATION("SMS_67201040", "实时保账户激活通知", "恭喜您，您的实时保账户${account}已经开通！请点击下方链接激活账户，并设置密码：https://www.1010bao.com${param}"), ;

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

	private SmsTemplateEnum(String code, String name, String desc) {
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

	public static SmsTemplateEnum getSmsTemplateEnumByCode(String code) {
		for (SmsTemplateEnum _Enum : SmsTemplateEnum.values()) {
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
