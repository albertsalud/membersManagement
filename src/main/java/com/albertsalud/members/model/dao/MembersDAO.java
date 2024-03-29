package com.albertsalud.members.model.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.albertsalud.members.model.entities.Member;

public interface MembersDAO extends JpaRepository<Member, Long> {

	Optional<Member> findByEmailAndPassword(String email, String password);
	
	Optional<Member> findByEmail(String email);

	List<Member> findByActivitiesId(Long activityId);

	Optional<Member> findByIdAndPassword(Long id, String password);

	@Query("FROM Member m LEFT JOIN FETCH m.activities WHERE m = :member")
	Member findActivitiesByMember(Member member);

}
