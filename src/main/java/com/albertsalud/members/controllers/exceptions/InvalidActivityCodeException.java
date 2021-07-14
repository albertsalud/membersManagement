package com.albertsalud.members.controllers.exceptions;

public class InvalidActivityCodeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1711245891727805841L;
	
	public InvalidActivityCodeException() {
		super("Invalid activity code.");
	}

}
