package com.albertsalud.members.controllers.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.albertsalud.members.controllers.dto.validations.PasswordDoubleCheck;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@PasswordDoubleCheck(password = "password", repeatedPassword = "repeatPassword")
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
	@Size(min = 6)
	private String password;
	
	@NotBlank
	private String repeatPassword;
	
}
