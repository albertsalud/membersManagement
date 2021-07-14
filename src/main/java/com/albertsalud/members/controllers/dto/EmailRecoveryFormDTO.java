package com.albertsalud.members.controllers.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class EmailRecoveryFormDTO {

	@NotBlank
	private String email;
}
