package com.albertsalud.members.controllers.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MembersFormDTO{
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String surname;
	
	private String phone;
	
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String repeatPassword;
	
}
