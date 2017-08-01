package com.renke.rdbao.beans.common.exception;

import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.exception.base.BaseException;

public class SmsException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3921905712064980515L;

	public SmsException(String message) {
		super(message);
	}

	public SmsException(ResponseEnum response) {
		super(response);
	}
}
