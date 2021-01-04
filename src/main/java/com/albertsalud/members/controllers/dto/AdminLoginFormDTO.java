package com.albertsalud.members.controllers.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AdminLoginFormDTO {
	
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;

}
