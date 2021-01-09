package com.albertsalud.members.controllers.dto;

import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.albertsalud.members.controllers.dto.validations.AfterDate;
import com.albertsalud.members.controllers.dto.validations.NotBeforeToday;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AfterDate(startDateFieldName = "startDate", endDateFieldName = "endDate")
public class ActivitiesFormDTO {
	
	private Long id;
	
	@NotBlank
	private String title;
	
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@NotBeforeToday
	private Date startDate;
	
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@NotBeforeToday
	private Date endDate;
	
	@NotNull
	@Min(value = 0)
	@DecimalMin(value = "0.0")
	private Float points;
	
	@NotBlank
	@Size(min = 4, max = 15)
	private String code;

}
