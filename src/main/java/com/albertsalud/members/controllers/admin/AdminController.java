package com.albertsalud.members.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.albertsalud.members.controllers.dto.AdminLoginFormDTO;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@GetMapping(value= {"", "/"})
	public String goToAdminLoginForm(Model model) {
		return goToAdminLoginForm(model, new AdminLoginFormDTO());
	}

	private String goToAdminLoginForm(Model model, AdminLoginFormDTO dto) {
		model.addAttribute("adminLoginFormDTO", dto);
		return "adminLoginForm";
	}

}
