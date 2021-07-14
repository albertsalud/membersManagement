package com.albertsalud.members.model.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.albertsalud.members.model.entities.Activity;

public interface ActivityDAO extends JpaRepository<Activity, Long> {
	
	List<Activity> findByStartDateBeforeAndEndDateAfterOrderByTitle(Date start, Date end);

	List<Activity> findTop5ByEndDateAfterOrderByStartDate(Date endDate);

}
