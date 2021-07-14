package com.albertsalud.members.controllers.secured;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.albertsalud.members.security.AdminPrincipal;

@Controller
@RequestMapping("/private")
public class PrivateMainController {
	
	@GetMapping(value= {"", "/"})
	public String goHome(Authentication authentication) {
		UserDetails user = (UserDetails)authentication.getPrincipal();
		
		if(user instanceof AdminPrincipal) {
			return "redirect:/admin/activities";
		} else {
			return "redirect:/private/home";
		}
		
		
	}

}
