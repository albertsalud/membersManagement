package com.albertsalud.members.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.albertsalud.members.controllers.dto.MemberLoginFormDTO;
import com.albertsalud.members.controllers.dto.MembersFormDTO;
import com.albertsalud.members.model.entities.Member;
import com.albertsalud.members.model.services.MemberServices;
import com.albertsalud.members.model.services.result.MemberServicesResultBean;

@Controller
@RequestMapping("/members")
public class MemberController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MemberServices memberServices;
	
	@GetMapping("/new")
	public String newMemberForm(Model model) {
		MembersFormDTO dto = new MembersFormDTO();
		
		return goToMembersForm(model, dto);
	}

	private String goToMembersForm(Model model, MembersFormDTO dto) {
		model.addAttribute("membersFormDTO", dto);
		return "membersForm";
	}
	
	@PostMapping("/save")
	public String saveMember(@Valid @ModelAttribute MembersFormDTO dto,
			BindingResult bindingResult,
			Model model) {
		
		if(bindingResult.hasErrors()) {
			return this.goToMembersForm(model, dto);
		}
		
		Member memberToSave = modelMapper.map(dto, Member.class);
		
		MemberServicesResultBean result = memberServices.save(memberToSave);
		
		if(result.isOk()) {
			return "memberRegistration";
		} else {
			model.addAttribute("message", result.getError());
			return this.goToMembersForm(model, dto);
			
		}
		
	}
	
	@GetMapping(value= {"",  "/"})
	public String goToLoginForm(Model model) {
		return goToMemberLoginForm(model, new MemberLoginFormDTO());
	}

	private String goToMemberLoginForm(Model model, MemberLoginFormDTO memberLoginFormDTO) {
		model.addAttribute("memberLoginFormDTO", memberLoginFormDTO);
		return "memberLoginForm";
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute MemberLoginFormDTO dto,
			BindingResult result,
			Model model) {
		
		if(result.hasErrors()) return goToMemberLoginForm(model, dto);
		
		Member member = memberServices.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
		if(member == null) {
			model.addAttribute("errorMessage", "User not found");
			return goToMemberLoginForm(model, dto);
		}
		
		// TODO set user to security context
		return "redirect:/private/members/home";
	}

}
