package com.albertsalud.members.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/private/members")
public class PrivateMemberController {
	
	@GetMapping(value= {"", "/"})
	public String goToMembersHome() {
		return "membersHome";
	}

}
