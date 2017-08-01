package com.renke.rdbao.beans.common.exception;

import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.exception.base.BaseException;

public class AliOperateException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7962921742433285133L;

	public AliOperateException(String message) {
		super(message);
	}

	public AliOperateException(ResponseEnum response) {
		super(response);
	}
}
