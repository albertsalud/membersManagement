package com.albertsalud.members.controllers.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MembersFormDTO {
	
	private Long id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String surname;
	
	@Email
	@NotBlank
	private String email;
	private String phone;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String repeatPassword;
	
}
