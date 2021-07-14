package com.albertsalud.members.controllers.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.albertsalud.members.controllers.dto.validations.PasswordDoubleCheck;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@PasswordDoubleCheck(password = "newPassword", repeatedPassword = "repeatPassword")
public class ChangeMemberPasswordDTO {
	
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	private String password;
	
	@NotBlank
	@Size(min = 6)
	private String newPassword;
	
	@NotBlank
	private String repeatPassword;

}
