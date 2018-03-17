package com.skipthedishes.exceptions;

public class ExistingUserException extends RuntimeException {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 7411168565402278698L;

	public ExistingUserException(String message) {
		super(message);
	}

}
