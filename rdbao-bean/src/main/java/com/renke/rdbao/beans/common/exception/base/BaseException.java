package com.renke.rdbao.beans.common.exception.base;

import com.renke.rdbao.beans.common.enums.ResponseEnum;

public class BaseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7731640045439513370L;
	private ResponseEnum response = null;

	public BaseException(String message) {
		super(message);
	}

	public BaseException(Exception e) {
		super(e);
	}

	public BaseException(String message, Throwable e) {
		super(message, e);
	}

	public BaseException(ResponseEnum response) {
		super(response.getRespDesc());
		this.setResponse(response);
	}

	public ResponseEnum getResponse() {
		return response;
	}

	public void setResponse(ResponseEnum response) {
		this.response = response;
	}

	public void setException(Exception exception) {
		if (exception instanceof BaseException) {
			BaseException baseException = (BaseException) exception;
			if (baseException.getResponse() != null) {
				this.setResponse(baseException.getResponse());
			}
		}
		this.setStackTrace(exception.getStackTrace());
	}

}
