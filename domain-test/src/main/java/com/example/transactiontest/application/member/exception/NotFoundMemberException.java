package com.example.transactiontest.application.member.exception;

import com.example.transactiontest.core.exception.BusinessRuntimeException;

public class NotFoundMemberException extends BusinessRuntimeException {
	public NotFoundMemberException(String message, Object... params) {
		super(message, params);
	}
}
