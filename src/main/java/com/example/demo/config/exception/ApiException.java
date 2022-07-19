package com.example.demo.config.exception;

public class ApiException extends RuntimeException{
	
	private static final long serialVersionUID = -7806029002430564887L;

	private String message;

	public ApiException() {}

	public ApiException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}