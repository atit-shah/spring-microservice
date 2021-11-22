package com.learn.cloud.cart.util;

public class ApplicationResponseCode {

	public static final int SUCCESS = 1200;
	public static final int UNAUTHENTICATED = 1401;
	public static final int UNAUTHORIZED = 1403;
	public static final int RESOURCE_NOT_FOUNT = 1404;
	public static final int UNKNOWN_ERROR = 1500;
	public static final int TRANSACTION_FAILURE = 1501;

	private ApplicationResponseCode() {
	}

}
