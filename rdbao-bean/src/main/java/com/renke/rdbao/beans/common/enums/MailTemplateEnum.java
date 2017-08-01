package com.renke.rdbao.beans.common.enums;

/**
 * @author jgshun
 * @date 2017-1-20 下午7:50:32
 * @version 1.0
 */
public enum MailTemplateEnum {
	/**
	 * "retrieve_password", "重置密码", "尊敬的用户您好！<br/>
	 * 您的验证码为：${code}，用于找回账户${account}密码，3分钟内有效。如非本人操作，请勿理会。"
	 */
	RETRIEVE_PASSWORD("retrieve_password", "重置密码", "尊敬的用户您好！<br/>  您的验证码为：${code}，用于找回账户${account}密码，3分钟内有效。如非本人操作，请勿理会。"),
	/**
	 * "active_account_code", "账户激活验证码", "尊敬的用户您好！<br/>
	 * 您的验证码为：${code}，用于激活账户${account}，3分钟内有效。如非本人操作，请勿理会。"
	 */
	ACTIVE_ACCOUNT_CODE("active_account_code", "账户激活验证码", "尊敬的用户您好！<br/>  您的验证码为：${code}，用于激活账户${account}，3分钟内有效。如非本人操作，请勿理会。"),
	/**
	 * "modify_password_code", "修改密码验证码", "尊敬的用户您好！<br/>
	 * 您的验证码为：${code}，用于修改账户${account}密码，3分钟内有效。如非本人操作，请勿理会。"
	 */
	MODIFY_PASSWORD_CODE("modify_password_code", "修改密码验证码", "尊敬的用户您好！<br/>  您的验证码为：${code}，用于修改账户${account}密码，3分钟内有效。如非本人操作，请勿理会。"),

	/**
	 * "download_file_code", "文件下载验证码", "尊敬的用户您好！<br/>
	 * 您的验证码为：${code}，用于申请文件下载，3分钟内有效。请妥善保管。"
	 */
	DOWNLOAD_FILE_CODE("download_file_code", "文件下载验证码", "尊敬的用户您好！<br/>  您的验证码为：${code}，用于申请文件下载，3分钟内有效。请妥善保管。"),

	/**
	 * "bind_email_code", "邮箱绑定验证码", "尊敬的用户您好！<br/>
	 * 您的验证码为：${code}，用于邮箱绑定，3分钟内有效。请妥善保管。"
	 */
	BIND_EMAIL_CODE("bind_email_code", "邮箱绑定验证码", "尊敬的用户您好！<br/>  您的验证码为：${code}，用于邮箱绑定，3分钟内有效。请妥善保管。"),

	/**
	 * "modify_bind_email_code", "邮箱更换绑定验证码", "尊敬的用户您好！<br/>
	 * 您的验证码为：${code}，用于邮箱更换绑定，3分钟内有效。请妥善保管。"
	 */
	MODIFY_BIND_EMAIL_CODE("modify_bind_email_code", "邮箱更换绑定验证码", "尊敬的用户您好！<br/>  您的验证码为：${code}，用于邮箱更换绑定，3分钟内有效。请妥善保管。"),

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

	private MailTemplateEnum(String code, String name, String desc) {
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

	public static MailTemplateEnum getMailTemplateEnumByCode(String code) {
		for (MailTemplateEnum _Enum : MailTemplateEnum.values()) {
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
