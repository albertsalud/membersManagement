package com.albertsalud.members.model.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.albertsalud.members.model.entities.Activity;

public interface ActivityDAO extends JpaRepository<Activity, Long> {

}
