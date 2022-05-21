package com.example.transactiontest.application.category.exception;

import com.example.transactiontest.core.exception.BusinessRuntimeException;

public class NotFoundCategoryException extends BusinessRuntimeException {
	public NotFoundCategoryException(String message, Object... params) {
		super(message, params);
	}
}
