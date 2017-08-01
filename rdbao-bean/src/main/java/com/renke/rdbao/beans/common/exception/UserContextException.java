package com.renke.rdbao.beans.common.exception;

import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.exception.base.BaseException;

public class UserContextException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3532046995768413715L;

	public UserContextException(String message) {
		super(message);
	}

	public UserContextException(ResponseEnum response) {
		super(response);
	}
}
