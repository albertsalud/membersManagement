package com.albertsalud.members.model.services;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.albertsalud.members.model.dao.ActivityDAO;
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
		return activityDao.findAll().stream()
				.sorted(Comparator.comparing(Activity::getStartDate).reversed())
				.collect(Collectors.toList());
	}

	public List<Activity> getCurrentActivities() {
		return activityDao.findByStartDateBeforeAndEndDateAfterOrderByTitle(new Date(), new Date());
	}

	public List<Activity> findNextActivities() {
		return activityDao.findTop5ByEndDateAfterOrderByStartDate(new Date());
	}


	public List<Activity> findAllByYear(Integer year) {
		List<Activity> allActivities = this.findAll();
		if(allActivities == null || allActivities.isEmpty()) return null;
		
		int myYear = this.manageYear(year);
		return allActivities.stream()
				.filter(a -> {
					Calendar activityCalendar = Calendar.getInstance();
					activityCalendar.setTime(a.getStartDate());
					
					return activityCalendar.get(Calendar.YEAR) == myYear;
				})
				.collect(Collectors.toList());
	}

	private int manageYear(Integer year) {
		if(year == null) return Calendar.getInstance().get(Calendar.YEAR);
		return year.intValue();
	}

}
