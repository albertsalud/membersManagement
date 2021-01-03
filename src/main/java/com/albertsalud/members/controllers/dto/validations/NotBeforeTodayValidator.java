package com.albertsalud.members.controllers.dto.validations;

import java.util.Calendar;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotBeforeTodayValidator implements ConstraintValidator<NotBeforeToday, Date>{

	@Override
	public boolean isValid(Date value, ConstraintValidatorContext context) {
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		
		return value.after(today.getTime());
	}

}
