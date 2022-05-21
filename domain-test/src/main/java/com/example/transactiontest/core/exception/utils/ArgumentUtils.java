package com.example.transactiontest.core.exception.utils;

public final class ArgumentUtils {

	private ArgumentUtils() {
	}

	public static <T> T existsParam(T param, T defaultParam) {
		return (param != null) ? param : defaultParam;
	}
}
