package com.albertsalud.members.controllers.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.albertsalud.members.model.entities.Activity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ActivitiesCheckFormDTO {

	@NotNull
	private Activity activity;
	
	@NotBlank
	private String code;
}
