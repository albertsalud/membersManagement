package com.albertsalud.members.controllers.dto.validations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = AfterDateValidator.class)
@Retention(RUNTIME)
@Target(TYPE)
public @interface AfterDate {
	
	String message() default "Finish date value must be later than start date value";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};

	String startDateFieldName();
	String endDateFieldName();
}
