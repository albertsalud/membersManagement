package com.albertsalud.members.controllers.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberLoginFormDTO {
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String password;

}
