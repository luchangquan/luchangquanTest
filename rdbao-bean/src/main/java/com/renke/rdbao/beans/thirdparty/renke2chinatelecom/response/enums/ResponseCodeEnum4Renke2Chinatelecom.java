package com.renke.rdbao.beans.thirdparty.renke2chinatelecom.response.enums;

/**
 * @author jgshun
 * @date 2016-11-11 上午10:37:45
 * @version 1.0
 */
public enum ResponseCodeEnum4Renke2Chinatelecom {
	/**
	 * "100000", "请求成功" 表示存在订购关系
	 */
	SUCCESS("100000", "请求成功"),
	/**
	 * "200000", "不存在订购关系"
	 */
	NO_ORDER_RELATIONSHIP_EXISTS("200000", "不存在订购关系"),
	/**
	 * "300000", "未知错误"
	 */
	UNKNOWN("300000", "未知错误");

	private String code;
	private String msg;

	private ResponseCodeEnum4Renke2Chinatelecom(String code, String msg) {
		this.code = code;
		this.msg = msg;
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
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
