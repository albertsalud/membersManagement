package com.albertsalud.members.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.albertsalud.members.model.entities.Member;

public interface MembersDAO extends JpaRepository<Member, Long> {

}
