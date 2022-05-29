package com.example.transactiontest.core.exception;

import java.text.MessageFormat;
import java.util.Arrays;

public abstract class BusinessRuntimeException extends RuntimeException {

	private final String message;
	private final Object[] params;

	public BusinessRuntimeException(String message, Object... params) {
		this.message = message;
		this.params = params;
	}

	@Override
	public String getMessage() {
		return MessageFormat.format(message, requiredNotNull(params) ? parseParams(params) : "");
	}

	private String parseParams(Object[] values) {
		return String.join(",", Arrays.toString(values));
	}

	private boolean requiredNotNull(Object[] values) {
		return values != null && values.length > 0;
	}
}
