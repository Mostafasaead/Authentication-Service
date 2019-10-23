package com.auth.dto;

public enum ErrorCodesEnum {

	USER_ALREADY_EXIST(1001),
	
	INVALID_USER_MODEL(1002),

	INVALID_USER_NAME(1003),

	INVALID_PASSWORD(1004), 
	
	INVALID_ANSWER(1005);
	
	private final Integer errorCode;

	public Integer getErrorCode() {
		return errorCode;
	}

	private ErrorCodesEnum(final int errorCode) {
		this.errorCode = errorCode;
	}

}