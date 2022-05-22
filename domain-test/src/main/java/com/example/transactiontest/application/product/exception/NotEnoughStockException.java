package com.example.transactiontest.application.product.exception;

import com.example.transactiontest.core.exception.BusinessRuntimeException;

public class NotEnoughStockException extends BusinessRuntimeException {
	public NotEnoughStockException(String message, Object... params) {
		super(message, params);
	}
}
