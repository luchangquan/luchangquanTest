package com.renke.rdbao.beans.common.exception;

import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.exception.base.BaseException;

public class MailException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6772124629367890077L;

	public MailException(String message) {
		super(message);
	}

	public MailException(ResponseEnum response) {
		super(response);
	}
}
