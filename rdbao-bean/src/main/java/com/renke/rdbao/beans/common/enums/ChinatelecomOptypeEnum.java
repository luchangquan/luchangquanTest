package com.renke.rdbao.beans.common.enums;

/**
 * 语音开户操作类型
 * 
 * @author jgshun
 * @date 2017-3-2 下午3:03:31
 * @version 1.0
 */
public enum ChinatelecomOptypeEnum {
	/**
	 * "add", "业务开通"
	 */
	ADD("add", "业务开通"),
	/**
	 * "delete", "关闭账户"
	 */
	DELETE("delete", "业务关闭"),
	/**
	 * "disassembleNumber", "号码拆机"
	 */
	DISASSEMBLE_NUMBER("disassembleNumber", "号码拆机"),
	/**
	 * "numberTransfer", "号码过户"
	 */
	NUMBER_TRANSFER("numberTransfer", "号码过户"),
	/**
	 * "becomeDelinquentUsers", "将正常用户变为欠费用户"
	 */
	BECOME_DELINQUENT_USERS("becomeDelinquentUsers", "将正常用户变为欠费用户"),
	/**
	 * "becomeNormalUsers", "将欠费用户变为正常用户"
	 */
	BECOME_NORMAL_USERS("becomeNormalUsers", "将欠费用户变为正常用户"),
	/**
	 * "modifyDisplayNumber", "修改外显号码"
	 */
	MODIFY_DISPLAY_NUMBER("modifyDisplayNumber", "修改外显号码"),
	/**
	 * "modifyCallbackNumber", "修改回拨号码"
	 */
	MODIFY_CALLBACK_NUMBER("modifyCallbackNumber", "修改回拨号码"), ;

	/**
	 * 编码
	 */
	private String code;
	/**
	 * 描述
	 */
	private String desc;

	private ChinatelecomOptypeEnum(String code, String desc) {
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

	public static ChinatelecomOptypeEnum getChinatelecomOptypeEnumByCode(String code) {
		for (ChinatelecomOptypeEnum _Enum : ChinatelecomOptypeEnum.values()) {
			if (_Enum.getCode().equals(code)) {
				return _Enum;
			}
		}
		return null;
	}

}
