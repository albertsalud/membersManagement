package com.albertsalud.members.controllers.secured;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.albertsalud.members.controllers.dto.ActivitiesFormDTO;
import com.albertsalud.members.controllers.dto.ParticipationFormDTO;
import com.albertsalud.members.controllers.exceptions.ActivityNotFoundException;
import com.albertsalud.members.model.entities.Activity;
import com.albertsalud.members.model.services.ActivityServices;
import com.albertsalud.members.model.services.MemberServices;
import com.albertsalud.members.model.services.result.MemberServicesResultBean;

@Controller
@RequestMapping("/admin/activities")
public class AdminActivityController {
	
	@Autowired
	private ActivityServices activityServices;
	
	@Autowired
	private MemberServices memberServices;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/new")
	public String newActivityForm(Model model) {
		ActivitiesFormDTO dto = new ActivitiesFormDTO();
		return goToActivitiesForm(model, dto);
		
	}

	private String goToActivitiesForm(Model model, ActivitiesFormDTO dto) {
		model.addAttribute("activitiesFormDTO", dto);
		return "activitiesForm";
		
	}
	
	@PostMapping("/save")
	public String saveActivity(@Valid @ModelAttribute ActivitiesFormDTO dto,
			BindingResult bindingResult,
			Model model) {
		
		if(bindingResult.hasErrors()) return goToActivitiesForm(model, dto);
		
		try {
			Activity activityToSave = manageActivity(dto);
			activityServices.save(activityToSave);
		
		} catch (ActivityNotFoundException e) {
			model.addAttribute("errorMessage", e.getMessage());
			
		}
		return this.getActivities(model);
		
	}
	
	@GetMapping("/{activityId}")
	public String getActivityById(@PathVariable(name = "activityId") Long activityId,
			Model model) {

		try {
			Activity activity = activityServices.findById(activityId);
			if(activity == null) throw new ActivityNotFoundException();
			ActivitiesFormDTO dto = modelMapper.map(activity, ActivitiesFormDTO.class);
			
			return goToActivitiesForm(model, dto);
		} catch (ActivityNotFoundException e) {
			model.addAttribute("errorMessage", e.getMessage());
		}
		
		return this.getActivities(model);
	}

	@GetMapping(value = {"", "/"})
	public String getActivities(Model model) {
		model.addAttribute("activities", activityServices.findAll());
		return "activitiesList";
	}

	private Activity manageActivity(@Valid ActivitiesFormDTO dto) throws ActivityNotFoundException {
		Activity activity = null;
		if(dto.getId() == null) {
			activity = modelMapper.map(dto, Activity.class);
		
		} else {
			activity = setActivityValues(dto);
		}
		return activity;
	}

	private Activity setActivityValues(@Valid ActivitiesFormDTO dto) throws ActivityNotFoundException {
		Activity activity = activityServices.findById(dto.getId());
		
		if(activity == null) throw new ActivityNotFoundException();
		
		activity.setCode(dto.getCode());
		activity.setEndDate(dto.getEndDate());
		activity.setPoints(dto.getPoints());
		activity.setStartDate(dto.getStartDate());
		activity.setTitle(dto.getTitle());
		
		return activity;
		
	}
	
	@GetMapping("/{activityId}/members")
	public String getActivityMembers(Model model,
			@PathVariable(name = "activityId") Long activityId) {
		
		model.addAttribute("allMembers", memberServices.findAllActive());
		model.addAttribute("members", memberServices.findByActivityId(activityId));
		
		Activity activity = activityServices.findById(activityId);
		
		try {
			if(activity == null) throw new ActivityNotFoundException();
			
			model.addAttribute("activity", activity);
			ParticipationFormDTO dto = new ParticipationFormDTO(activity);
			
			model.addAttribute("participationFormDTO", dto);
			return "participationList";
		
		} catch (ActivityNotFoundException e ) {
			model.addAttribute("message", e.getMessage());
			return "redirect:/";
		}
	}
	
	@PostMapping("/{activityId}/members")
	public String addMemberToActivity(@Valid @ModelAttribute ParticipationFormDTO dto,
			BindingResult result,
			Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("message", "Unable to add member: unknow.");
		}
		
		MemberServicesResultBean serviceResult = memberServices.addActivity(dto.getMember(), dto.getActivity());
		
		if(!serviceResult.isOk()) {
			model.addAttribute("message", serviceResult.getError());
		}
		return this.getActivityMembers(model, dto.getActivity().getId());
	}
}
