package com.albertsalud.members.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.albertsalud.members.controllers.dto.ActivitiesFormDTO;

@Controller
@RequestMapping("/activities")
public class ActivityController {

	@GetMapping("/new")
	public String newActivityForm(Model model) {
		ActivitiesFormDTO dto = new ActivitiesFormDTO();
		return goToActivitiesForm(model, dto);
		
	}

	private String goToActivitiesForm(Model model, ActivitiesFormDTO dto) {
		model.addAttribute("activitiesFormDTO", dto);
		return "activitiesForm";
		
	}
}
