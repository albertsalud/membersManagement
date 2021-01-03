package com.albertsalud.members.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.albertsalud.members.model.entities.Activity;

@Service
public class ActivityServices {
	
	@Autowired
	private ActivityDAO activityDao;

	public Activity findById(Long id) {
		return activityDao.findById(id).orElse(null);
	}

	public void save(Activity activityToSave) {
		activityDao.save(activityToSave);
		
	}

	public List<Activity> findAll() {
		return activityDao.findAll();
	}

}
