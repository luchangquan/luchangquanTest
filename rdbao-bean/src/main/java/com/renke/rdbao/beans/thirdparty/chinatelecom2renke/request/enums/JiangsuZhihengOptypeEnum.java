package com.renke.rdbao.beans.thirdparty.chinatelecom2renke.request.enums;

import com.renke.rdbao.beans.common.enums.ChinatelecomOptypeEnum;

/**
 * 智恒语音开户操作类型
 * 
 * @author jgshun
 * @date 2017-3-2 下午3:03:31
 * @version 1.0
 */
public enum JiangsuZhihengOptypeEnum {
	/**
	 * "1", "add", "业务开通"
	 */
	ADD("1", ChinatelecomOptypeEnum.ADD, "业务开通"),
	/**
	 * "2", "delete", "业务关闭"
	 */
	DELETE("2", ChinatelecomOptypeEnum.DELETE, "业务关闭"),
	/**
	 * "3", "disassembleNumber", "号码拆机"
	 */
	DISASSEMBLE_NUMBER("3", ChinatelecomOptypeEnum.DISASSEMBLE_NUMBER, "号码拆机"),
	/**
	 * "4", "numberTransfer", "号码过户"
	 */
	NUMBER_TRANSFER("4", ChinatelecomOptypeEnum.NUMBER_TRANSFER, "号码过户"),
	/**
	 * "5", "becomeDelinquentUsers", "将正常用户变为欠费用户"
	 */
	BECOME_DELINQUENT_USERS("5", ChinatelecomOptypeEnum.BECOME_DELINQUENT_USERS, "将正常用户变为欠费用户"),
	/**
	 * "6", "becomeNormalUsers", "将欠费用户变为正常用户"
	 */
	BECOME_NORMAL_USERS("6", ChinatelecomOptypeEnum.BECOME_NORMAL_USERS, "将欠费用户变为正常用户"),
	/**
	 * "7", "modifyDisplayNumber", "修改外显号码"
	 */
	MODIFY_DISPLAY_NUMBER("7", ChinatelecomOptypeEnum.MODIFY_DISPLAY_NUMBER, "修改外显号码"),
	/**
	 * "8", "modifyCallbackNumber", "修改回拨号码"
	 */
	MODIFY_CALLBACK_NUMBER("8", ChinatelecomOptypeEnum.MODIFY_CALLBACK_NUMBER, "修改回拨号码"), ;

	/**
	 * 编码
	 */
	private String code;
	/**
	 * 人科对应的操作
	 */
	private ChinatelecomOptypeEnum chinatelecomOptype;
	/**
	 * 描述
	 */
	private String desc;

	private JiangsuZhihengOptypeEnum(String code, ChinatelecomOptypeEnum chinatelecomOptype, String desc) {
		this.code = code;
		this.chinatelecomOptype = chinatelecomOptype;
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

	public static JiangsuZhihengOptypeEnum getJiangsuZhihengOptypeEnumByCode(String code) {
		for (JiangsuZhihengOptypeEnum _Enum : JiangsuZhihengOptypeEnum.values()) {
			if (_Enum.getCode().equals(code)) {
				return _Enum;
			}
		}
		return null;
	}

	/**
	 * @return the chinatelecomOptype
	 */
	public ChinatelecomOptypeEnum getChinatelecomOptype() {
		return chinatelecomOptype;
	}

}
