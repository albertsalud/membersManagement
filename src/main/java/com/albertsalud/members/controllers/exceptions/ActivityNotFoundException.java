package com.albertsalud.members.controllers.exceptions;

public class ActivityNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6524581071193304157L;
	
	public ActivityNotFoundException() {
		super("Unable to find activity");
	}

}
