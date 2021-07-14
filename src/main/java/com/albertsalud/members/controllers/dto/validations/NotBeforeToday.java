package com.albertsalud.members.controllers.dto.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = NotBeforeTodayValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotBeforeToday {
	String message() default "Activity can not be declared before today";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
