package com.albertsalud.members.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String surname;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	private String phone;
	private String password;
	
	@ManyToMany
	private List<Activity> activities;

	
	public void addActivity(Activity activity) {
		if(activities == null) activities = new ArrayList<>();
		
		activities.add(activity);
	}
	

}