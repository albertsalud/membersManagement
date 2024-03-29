package com.albertsalud.members.controllers.secured;

import java.util.Calendar;
import java.util.Date;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.albertsalud.members.controllers.dto.ChangeMemberPasswordDTO;
import com.albertsalud.members.controllers.dto.MembersDataFormDTO;
import com.albertsalud.members.model.entities.Member;
import com.albertsalud.members.model.services.ActivityServices;
import com.albertsalud.members.model.services.MemberServices;
import com.albertsalud.members.model.services.result.MemberServicesResultBean;
import com.albertsalud.members.security.UserPrincipal;

@Controller
@RequestMapping("/private")
public class PrivateMemberController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ActivityServices activityServices;
	
	@Autowired
	private MemberServices memberServices;
	
	@GetMapping("/home")
	public String goToMembersHome(Model model, Authentication authentication) {
		Member member = this.getMemberFromSecurityContext(authentication);
		member.setActivities(memberServices.getActivitiesByMember(member, Calendar.getInstance().get(Calendar.YEAR)));
		model.addAttribute("member", member);
		model.addAttribute("activities", activityServices.findNextActivities());
		model.addAttribute("now", new Date());

		return "membersHome";
	}
	
	private Member getMemberFromSecurityContext(Authentication authentication) {
		UserPrincipal user = (UserPrincipal)authentication.getPrincipal();
		return user.getMember();
	}
	
	@GetMapping("/data")
	public String getMemberData(Model model, Authentication authentication) {
		MembersDataFormDTO dto = modelMapper.map(this.getMemberFromSecurityContext(authentication), MembersDataFormDTO.class);
		return this.getMemberDataForm(model, dto);
	}
	
	private String getMemberDataForm(Model model, MembersDataFormDTO dto) {
		model.addAttribute("membersDataFormDTO", dto);
		return "membersDataForm";
	}
	
	@PostMapping("/save")
	public String saveMemberData(@Valid @ModelAttribute MembersDataFormDTO dto,
			BindingResult bindingResult,
			Model model,
			Authentication authentication) {
		
		if(bindingResult.hasErrors()) {
			return this.getMemberDataForm(model, dto);
		}
		
		MemberServicesResultBean result = memberServices.updateMember(dto);
		
		if(result.isOk()) {
			updateSecurityContext(authentication, result.getMember());
			return "redirect:/private/home";
		} else {
			model.addAttribute("message", result.getError());
			return this.getMemberDataForm(model, dto);
			
		}
		
	}

	private void updateSecurityContext(Authentication authentication, Member member) {
		((UserPrincipal)authentication.getPrincipal()).setMember(member);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
	@GetMapping("/changePassword")
	public String changeMemberPassword(Model model, Authentication authentication) {
		ChangeMemberPasswordDTO dto = modelMapper.map(this.getMemberFromSecurityContext(authentication), 
				ChangeMemberPasswordDTO.class);
		
		return this.getChangeMemberPasswordForm(model, dto);
	}
	
	private String getChangeMemberPasswordForm(Model model, ChangeMemberPasswordDTO dto) {
		model.addAttribute("changeMemberPasswordDTO", dto);
		return "membersPasswordForm";
	}
	
	@PostMapping("/changePassword")
	public String changeMemberPassword(@Valid @ModelAttribute ChangeMemberPasswordDTO dto,
			BindingResult bindingResult,
			Model model,
			Authentication authentication
			) {
		
		if(bindingResult.hasErrors()) {
			return this.getChangeMemberPasswordForm(model, dto);
		}
		
		MemberServicesResultBean result = memberServices.updateMember(dto);
		
		if(result.isOk()) {
			updateSecurityContext(authentication, result.getMember());
			return "redirect:/private/home";
		
		} else {
			model.addAttribute("message", result.getError());
			return this.getChangeMemberPasswordForm(model, dto);
			
		}
		
	}
	
	@GetMapping("/participation")
	public String getParticipation(Model model, 
			@RequestParam(name = "year", required = false) Integer year,
			Authentication authentication) {
		model.addAttribute("now", new Date());
		model.addAttribute("activities", 
				memberServices.getActivitiesByMember(
						this.getMemberFromSecurityContext(authentication), year));
		return "membersParticipation";
	}

}
