package com.albertsalud.members.controllers.exceptions;

public class MemberNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7927489751276758044L;

	public MemberNotFoundException() {
		super("No member found with introduced credentials");
	}

}
