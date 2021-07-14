package com.albertsalud.members.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.albertsalud.members.controllers.dto.ChangeMemberPasswordDTO;
import com.albertsalud.members.controllers.dto.EmailRecoveryFormDTO;
import com.albertsalud.members.controllers.dto.MemberLoginFormDTO;
import com.albertsalud.members.controllers.dto.MembersFormDTO;
import com.albertsalud.members.model.entities.Member;
import com.albertsalud.members.model.services.MemberServices;
import com.albertsalud.members.model.services.result.MemberServicesResultBean;
import com.albertsalud.members.security.UserPrincipal;
import com.albertsalud.members.utils.mailing.EmailService;
import com.albertsalud.members.utils.mailing.EmailService.EmailServiceResultBean;

@Controller
@RequestMapping("/")
public class MemberController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MemberServices memberServices;
	
	@Autowired
	private EmailService emailService;
	
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
		
		MemberServicesResultBean result = memberServices.registryMember(memberToSave);
		EmailServiceResultBean emailResult = null;
		if(result.isOk() &&
				(emailResult = emailService.sendActivationMessage(result.getMember())).isOk()) {
			return "memberRegistration";

		} else {
			model.addAttribute("message", result.isOk() ? emailResult.getError() : result.getError());
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
	
	@GetMapping("/recovery")
	public String goToRecoveryForm(Model model) {
		EmailRecoveryFormDTO dto = new EmailRecoveryFormDTO();
		return goToEmailRecoveryForm(model, dto);
	}
	
	private String goToEmailRecoveryForm(Model model, EmailRecoveryFormDTO dto) {
		model.addAttribute("emailRecoveryFormDTO", dto);
		return "membersRecoveryEmailForm";
	}

	@PostMapping("/recovery")
	public String sendRecoveryMail(@Valid @ModelAttribute EmailRecoveryFormDTO dto,
			BindingResult result, 
			Model model) {
		if(result.hasErrors()) this.goToEmailRecoveryForm(model, dto);
		
		try {
			UserPrincipal user = (UserPrincipal) memberServices.loadUserByUsername(dto.getEmail());
			
			EmailServiceResultBean emailResult = emailService.sendRecoveryMessage(user.getMember());
			
			if(emailResult.isOk()) return "membersRecovery";
				
			model.addAttribute("message", "Mailing delivery error: " + emailResult.getError());
			
		} catch (UsernameNotFoundException e) {
			model.addAttribute("message", e.getMessage());
			
		}
		
		return this.goToEmailRecoveryForm(model, dto);
	}
	
	@GetMapping("/changePassword")
	public String getChangePasswordForm(Model model,
			@RequestParam(name="p", required = true) String password,
			@RequestParam(name="e", required = true) String email) {
		Member member = memberServices.findByEmailAndPassword(email, password);
		if(member == null) {
			return "redirect:/error";
		}
		
		ChangeMemberPasswordDTO dto = new ChangeMemberPasswordDTO();
		dto.setEmail(email);
		dto.setPassword(password);
		
		return goToMemberRecoveryPasswordForm(model, dto);
	}

	private String goToMemberRecoveryPasswordForm(Model model, ChangeMemberPasswordDTO dto) {
		model.addAttribute("changeMemberPasswordDTO", dto);
		return "membersRecoveryPasswordForm";
	}
	
	@PostMapping("/changePassword")
	public String changeMemberPassword(@Valid @ModelAttribute ChangeMemberPasswordDTO dto,
			BindingResult bindingResult,
			Model model
			) {
		
		if(bindingResult.hasErrors()) {
			return this.goToMemberRecoveryPasswordForm(model, dto);
		}
		
		MemberServicesResultBean result = memberServices.updateMember(dto);
		
		if(result.isOk()) {
			return "redirect:/private";
		
		} else {
			model.addAttribute("message", result.getError());
			return this.goToMemberRecoveryPasswordForm(model, dto);
			
		}
		
	}
	
	@GetMapping("/activeUser")
	public String activeUser(Model model,
			@RequestParam(name="p", required = true) String password,
			@RequestParam(name="e", required = true) String email) {
		Member member = memberServices.findByEmailAndPassword(email, password);
		if(member == null) {
			return "redirect:/error";
		}
		
		MemberServicesResultBean serviceResult =  memberServices.activeUser(member);
		if(!serviceResult.isOk()) {
			model.addAttribute("message", "No s'ha pogut activar l'usuari: " + serviceResult.getError());
			return goToLoginForm(model);
		}
		
		return "userActivation";
	}
}
