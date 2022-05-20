package com.example.transactiontest.application.product.exception;

import com.example.transactiontest.core.exception.BusinessRuntimeException;

import java.util.Arrays;

public class NotFoundItemException extends BusinessRuntimeException {

	public NotFoundItemException(String message, Object... values) {
		super(message, new String[]{requiredNotNull(values) ? parseParams(values) : ""});
	}

	private static String parseParams(Object[] values) {
		return String.join(",", Arrays.toString(values));
	}

	private static boolean requiredNotNull(Object[] values) {
		return values != null && values.length > 0;
	}
}
