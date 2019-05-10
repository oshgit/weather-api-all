package com.app.weatherapi.dto;

public class ExceptionDTO {
	
	private int code;
	private String message;
	private String stackTrace;
	
	public ExceptionDTO(int code, String message, String stackTrace) {
		super();
		this.code = code;
		this.message = message;
		this.stackTrace = stackTrace;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStackTrace() {
		return stackTrace;
	}
	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}
	
}
