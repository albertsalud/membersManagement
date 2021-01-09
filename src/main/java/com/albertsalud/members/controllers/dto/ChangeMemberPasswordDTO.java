package com.albertsalud.members.controllers.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChangeMemberPasswordDTO {
	
	protected Long id;
	
	@NotBlank
	protected String password;
	
	@NotBlank
	protected String repeatPassword;

}
