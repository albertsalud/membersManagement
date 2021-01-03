package com.albertsalud.members.controllers.dto.validations;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

public class AfterDateValidator implements ConstraintValidator<AfterDate, Object> {

	private String startDateFieldName;
	private String endDateFieldName;
	
	@Override
	public void initialize(AfterDate constraintAnnotation) {
		startDateFieldName = constraintAnnotation.startDateFieldName();
		endDateFieldName = constraintAnnotation.endDateFieldName();
	}

	@Override
	public boolean isValid(Object target, ConstraintValidatorContext context) {
		Date startDate = (Date) new BeanWrapperImpl(target).getPropertyValue(startDateFieldName);
		Date endDate = (Date) new BeanWrapperImpl(target).getPropertyValue(endDateFieldName);
		
		if(endDate == null) return false;
		
		return endDate.after(startDate);
	}

}
