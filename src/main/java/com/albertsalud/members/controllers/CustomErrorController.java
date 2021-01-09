package com.albertsalud.members.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class CustomErrorController implements ErrorController {
	
	@GetMapping(value = {"", "/"})
	public String goToErrorPage(Authentication authentication,
			Model model) {
		model.addAttribute("user", authentication);
		return "errorPage";
		
	}

	@Override
	public String getErrorPath() {
		return "errorPage";
	}

}
