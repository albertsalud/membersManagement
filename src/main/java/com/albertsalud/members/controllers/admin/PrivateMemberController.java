package com.albertsalud.members.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.albertsalud.members.model.services.ActivityServices;
import com.albertsalud.members.security.UserPrincipal;

@Controller
@RequestMapping("/private/members")
public class PrivateMemberController {
	
	@Autowired
	private ActivityServices activityServices;
	
	@GetMapping(value= {"", "/"})
	public String goToMembersHome(Model model, Authentication authentication) {
		UserPrincipal user = (UserPrincipal)authentication.getPrincipal();
		model.addAttribute("member", user.getMember());
		model.addAttribute("activities", activityServices.findNextActivities());
		return "membersHome";
	}

}
