package com.albertsalud.members.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.albertsalud.members.controllers.dto.ActivitiesCheckFormDTO;
import com.albertsalud.members.controllers.exceptions.InvalidActivityCodeException;
import com.albertsalud.members.model.entities.Member;
import com.albertsalud.members.model.services.ActivityServices;
import com.albertsalud.members.model.services.MemberServices;
import com.albertsalud.members.model.services.result.MemberServicesResultBean;
import com.albertsalud.members.security.UserPrincipal;

@Controller
@RequestMapping("/activities")
public class ActivityController {
	
	@Autowired
	private ActivityServices activityServices;
	
	@Autowired
	private MemberServices memberService;
	
	@GetMapping("/check")
	public String goToActivitiesCheckForm(Model model) {
		
		return goToActivitiesCheckFormDTO(model, new ActivitiesCheckFormDTO());
	}

	private String goToActivitiesCheckFormDTO(Model model, ActivitiesCheckFormDTO activitiesCheckFormDTO) {
		model.addAttribute("activities", activityServices.getCurrentActivities());
		
		model.addAttribute("activitiesCheckFormDTO", activitiesCheckFormDTO);
		return "activitiesCheckForm";
	}
	
	@PostMapping("/check")
	public String checkActivity(
			Authentication authentication,
			@Valid @ModelAttribute ActivitiesCheckFormDTO dto,
			BindingResult result,
			Model model) {
		
		if(result.hasErrors()) return goToActivitiesCheckFormDTO(model, dto);
		
		try {
			if(!dto.getActivity().getCode().equals(dto.getCode())) throw new InvalidActivityCodeException();
			
			Member member = getMemberFromSecurityContext(authentication);
			
			MemberServicesResultBean resultBean = memberService.addActivity(member, dto.getActivity());
			if(!resultBean.isOk()) throw new Exception(resultBean.getError());
			
			model.addAttribute("activity", dto.getActivity());
		
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return goToActivitiesCheckFormDTO(model, dto);
		}
		
		return "activityRegistration";
		
	}

	private Member getMemberFromSecurityContext(Authentication authentication) {
		UserPrincipal user = (UserPrincipal)authentication.getPrincipal();
		return user.getMember();
	}

}
