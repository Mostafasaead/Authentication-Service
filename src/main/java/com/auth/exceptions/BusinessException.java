package com.auth.exceptions;

import java.util.Map;

import org.springframework.http.HttpStatus;

import com.auth.dto.ErrorCodesEnum;

public class BusinessException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String message;
	protected Integer code;
	protected HttpStatus httpStatus;
	protected Map<String, String> extraResponseData;
	protected Throwable cause;

	public BusinessException() {

	}

	public BusinessException(String message) {
		this.message = message;
	}

	public BusinessException(String message, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
	}

	public BusinessException(int code, String message) {
		super();
		this.message = message;
		this.code = code;
	}

	public BusinessException(int code, String message, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.code = code;
		this.httpStatus = httpStatus;
	}

	public BusinessException(ErrorCodesEnum errorCode, String message, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.code = errorCode.getErrorCode();
		this.httpStatus = httpStatus;
	}

	public BusinessException(String message, int code, HttpStatus httpStatus, Map<String, String> extraResponseData) {
		super();
		this.message = message;
		this.code = code;
		this.httpStatus = httpStatus;
		this.extraResponseData = extraResponseData;
	}

	public BusinessException(ErrorCodesEnum errorCode, String message, HttpStatus httpStatus, Exception cause) {
		this(errorCode, message, httpStatus);
		this.cause = cause;
	}

	public String getMessage() {
		return message;
	}

	public Integer getCode() {
		return code;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public Map<String, String> getExtraResponseData() {
		return extraResponseData;
	}

	public Throwable getCause() {
		return cause;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public void setExtraResponseData(Map<String, String> extraResponseData) {
		this.extraResponseData = extraResponseData;
	}

	public void setCause(Throwable cause) {
		this.cause = cause;
	}
}
