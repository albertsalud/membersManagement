package com.albertsalud.members.model.services;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.albertsalud.members.controllers.dto.ChangeMemberPasswordDTO;
import com.albertsalud.members.controllers.dto.MembersDataFormDTO;
import com.albertsalud.members.controllers.exceptions.MemberNotFoundException;
import com.albertsalud.members.model.dao.MembersDAO;
import com.albertsalud.members.model.entities.Activity;
import com.albertsalud.members.model.entities.Member;
import com.albertsalud.members.model.services.result.MemberServicesResultBean;
import com.albertsalud.members.security.AdminPrincipal;
import com.albertsalud.members.security.MemberPrincipal;

@Service
public class MemberServices implements UserDetailsService {
	
	@Autowired
	private MembersDAO membersDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public MemberServicesResultBean save(Member memberToSave) {
		memberToSave.setBkpPassword(memberToSave.getPassword());
		managePassword(memberToSave);
		return saveMember(memberToSave);
	}
	
	private MemberServicesResultBean saveMember(Member memberToSave) {
		MemberServicesResultBean result = new MemberServicesResultBean();
		try {
			result.setMember(memberToSave);
			membersDao.save(memberToSave);
			
		} catch (Exception e) {
			result.setError(e.getMessage());
			result.setOk(false);
		}
		
		return result;
		
	}

	private void managePassword(Member member) {
		member.setPassword(passwordEncoder.encode(member.getPassword()));
	}
	

	public Member findByEmailAndPassword(String email, String password) {
		return membersDao.findByEmailAndPassword(email, passwordEncoder.encode(password)).orElse(null);
	}

	public MemberServicesResultBean addActivity(Member member, Activity activity) {
		if(member.getActivities().contains(activity)) {
			MemberServicesResultBean result = new MemberServicesResultBean();
			result.setError("Activity registered");
			result.setOk(false);
			
			return result;
		}
		
		member.addActivity(activity);
		return this.save(member);
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if("daudecinc".equals(username)) return new AdminPrincipal(this.generateAdminMember());
		
		Member member = membersDao.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
		return new MemberPrincipal(member);
	}

	private Member generateAdminMember() {
		Member fakeMember = new Member();
		fakeMember.setName("Dau de cinc");
		fakeMember.setEmail("daudecinc");
		fakeMember.setPassword(passwordEncoder.encode("jocsdetaula"));

		return fakeMember;
	}

	public MemberServicesResultBean updateMember(@Valid MembersDataFormDTO dto) {
		MemberServicesResultBean result = new MemberServicesResultBean();
		
		Member member = membersDao.findById(dto.getId()).orElse(null);
		try {
			if(member == null) throw new MemberNotFoundException();
			manageMemberData(member, dto);
			
			return this.saveMember(member);
			
		} catch (Exception e) {
			result = new MemberServicesResultBean();
			result.setOk(false);
			result.setError(e.getMessage());
		
		}
		
		return result;
	}

	private void manageMemberData(Member member, @Valid MembersDataFormDTO dto) {
		member.setEmail(dto.getEmail());
		member.setName(dto.getName());
		member.setPhone(dto.getPhone());
		member.setSurname(dto.getSurname());
		
	}

	public MemberServicesResultBean updateMember(@Valid ChangeMemberPasswordDTO dto) {
		MemberServicesResultBean result = new MemberServicesResultBean();
		
		Member member = membersDao.findById(dto.getId()).orElse(null);
		try {
			if(member == null) throw new MemberNotFoundException();
			manageMemberPassword(member, dto);
			
			return this.saveMember(member);
			
		} catch (Exception e) {
			result = new MemberServicesResultBean();
			result.setOk(false);
			result.setError(e.getMessage());
		
		}
		
		return result;
	}

	private void manageMemberPassword(Member member, @Valid ChangeMemberPasswordDTO dto) {
		member.setPassword(dto.getPassword());
		member.setBkpPassword(dto.getPassword());
		managePassword(member);
		
	}

}
