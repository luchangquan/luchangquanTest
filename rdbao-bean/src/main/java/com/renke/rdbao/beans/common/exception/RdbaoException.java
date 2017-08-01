package com.renke.rdbao.beans.common.exception;

import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.exception.base.BaseException;

public class RdbaoException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8493365126066473462L;

	public RdbaoException(String message) {
		super(message);
	}

	public RdbaoException(ResponseEnum response) {
		super(response);
	}

	public RdbaoException(Exception e) {
		super(e);
	}

	public RdbaoException(String message, Throwable e) {
		super(message, e);
	}
}
