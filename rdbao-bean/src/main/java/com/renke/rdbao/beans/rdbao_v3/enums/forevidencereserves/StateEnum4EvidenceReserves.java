package com.renke.rdbao.beans.rdbao_v3.enums.forevidencereserves;

/**
 * 预约状态
 * 
 * @author jgshun
 * @date 2017-1-6 上午10:44:03
 * @version 1.0
 */
public enum StateEnum4EvidenceReserves {

	/**
	 * (short) 1, "申请中"
	 */
	APPLY((short) 1, "申请中"),
	/**
	 * (short) 2, "预约成功"
	 */
	SUCCESS((short) 2, "预约成功"),

	/**
	 * (short) 3, "预约失败"
	 */
	FAIL((short) 3, "预约失败"),
	/**
	 * (short) 4, "预约已取消"
	 */
	CANCEL((short) 4, "预约已取消"),
	/**
	 * (short) 5, "已出证"
	 */
	OUTED((short) 5, "已出证"),
	/**
	 * (short) 7, "预约已撤销"
	 */
	REVOKE((short) 7, "预约已撤销"),
	/**
	 * (short) 8, "预约已过期"
	 */
	OVERDUE((short) 8, "预约已过期");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private StateEnum4EvidenceReserves(short code, String desc) {
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

	public static StateEnum4EvidenceReserves getStateEnumByCode(short code) {
		for (StateEnum4EvidenceReserves _Enum : StateEnum4EvidenceReserves.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}

}
