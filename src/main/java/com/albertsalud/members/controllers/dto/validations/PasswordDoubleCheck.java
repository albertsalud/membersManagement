package com.albertsalud.members.controllers.dto.validations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = PasswordDoubleCheckValidator.class)
@Target(ElementType.TYPE)
@Retention(RUNTIME)
public @interface PasswordDoubleCheck {

	String message() default "Passwords don't match";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	
	String password();	// The name of the password field
	String repeatedPassword();	// The name of the repeated password field
	
}
