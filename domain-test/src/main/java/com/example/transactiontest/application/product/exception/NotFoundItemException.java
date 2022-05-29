package com.example.transactiontest.application.product.exception;

import com.example.transactiontest.core.exception.BusinessRuntimeException;

import java.util.Arrays;

public class NotFoundItemException extends BusinessRuntimeException {

	public NotFoundItemException(String message, Object... values) {
		super(message, values);
	}
}
