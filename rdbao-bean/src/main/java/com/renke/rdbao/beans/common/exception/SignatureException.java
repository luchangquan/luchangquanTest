package com.renke.rdbao.beans.common.exception;

import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.exception.base.BaseException;

public class SignatureException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1317978986680550298L;

	public SignatureException(String message) {
		super(message);
	}

	public SignatureException(ResponseEnum response) {
		super(response);
	}
}
