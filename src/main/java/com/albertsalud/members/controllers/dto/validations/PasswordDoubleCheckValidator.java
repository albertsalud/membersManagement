package com.albertsalud.members.controllers.dto.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

public class PasswordDoubleCheckValidator implements ConstraintValidator<PasswordDoubleCheck, Object> {
	
	private String passwordField;
	
	private String repeatedPasswordField;
	
	@Override
	public void initialize(PasswordDoubleCheck constraintAnnotation) {
		passwordField = constraintAnnotation.password();
		repeatedPasswordField = constraintAnnotation.repeatedPassword();
	}


	@Override
	public boolean isValid(Object target, ConstraintValidatorContext context) {
		
		String password = (String) new BeanWrapperImpl(target).getPropertyValue(passwordField);
		String repeatedPassword = (String) new BeanWrapperImpl(target).getPropertyValue(repeatedPasswordField);
		
		if(password == null || repeatedPassword == null) return false;
		if(!password.equals(repeatedPassword)) return false;
		
		return true;
	}

	
}
