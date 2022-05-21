package com.example.transactiontest.core.exception;

import java.text.MessageFormat;

public abstract class BusinessRuntimeException extends RuntimeException {

	private final String message;
	private final Object[] params;

	public BusinessRuntimeException(String message, Object... params) {
		this.message = message;
		this.params = params;
	}

	@Override
	public String getMessage() {
		return MessageFormat.format(message, params);
	}
}
