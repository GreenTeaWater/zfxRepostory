package com.zfx.common.exception;

public class SpringException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8296072165917283670L;
	
	
	
	private String message ;

	public SpringException() {
	}

	public SpringException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
