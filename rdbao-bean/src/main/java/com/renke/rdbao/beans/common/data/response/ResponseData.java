package com.renke.rdbao.beans.common.data.response;

import java.io.Serializable;

import com.renke.rdbao.beans.common.enums.ResponseEnum;

/**
 * @author jgshun
 * @date 2017-1-20 下午5:20:22
 * @version 1.0
 */
public class ResponseData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 978775714686298686L;

	private String respCode = ResponseEnum.SUCCESS.getRespCode();
	private String respDesc = ResponseEnum.SUCCESS.getRespDesc();
	private Object data;

	public void setResponseEnum(ResponseEnum responseEnum) {
		this.respCode = responseEnum.getRespCode();
		this.respDesc = responseEnum.getRespDesc();
	}

	/**
	 * @return the respCode
	 */
	public String getRespCode() {
		return respCode;
	}

	/**
	 * @param respCode
	 *            the respCode to set
	 */
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	/**
	 * @return the respDesc
	 */
	public String getRespDesc() {
		return respDesc;
	}

	/**
	 * @param respDesc
	 *            the respDesc to set
	 */
	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
