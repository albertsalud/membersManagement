package com.albertsalud.members.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.albertsalud.members.model.entities.Activity;
import com.albertsalud.members.model.services.ActivityServices;

@RestController
@RequestMapping("/api/activities")
public class ActivityRestController {
	
	@Autowired
	private ActivityServices activityServices;
	
	
	@GetMapping(value = {"", "/"})
	public List<Activity> getNextActivities(){
		return activityServices.findNextActivities();
		
	}

}
