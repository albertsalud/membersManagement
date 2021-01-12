package com.albertsalud.members.controllers.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChangeMemberPasswordDTO {
	
	@NotBlank
	private String email;
	
	@NotBlank
	protected String password;
	
	@NotBlank
	protected String newPassword;
	
	@NotBlank
	protected String repeatPassword;

}
