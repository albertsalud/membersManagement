package com.albertsalud.members.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.albertsalud.members.model.dao.MembersDAO;
import com.albertsalud.members.model.entities.Activity;
import com.albertsalud.members.model.entities.Member;
import com.albertsalud.members.model.services.result.MemberServicesResultBean;
import com.albertsalud.members.security.UserPrincipal;

@Service
public class MemberServices implements UserDetailsService {
	
	@Autowired
	private MembersDAO membersDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public MemberServicesResultBean save(Member memberToSave) {
		MemberServicesResultBean result = new MemberServicesResultBean();
		
		try {
			managePassword(memberToSave);
			membersDao.save(memberToSave);
			
		} catch (Exception e) {
			result.setError(e.getMessage());
			result.setOk(false);
		}
		
		return result;
	}

	private void managePassword(Member member) {
		member.setPassword(encryptPassword(member.getPassword()));
	}
	
	private String encryptPassword(String password) {
		return passwordEncoder.encode(password);
	}

	public Member findByEmailAndPassword(String email, String password) {
		return membersDao.findByEmailAndPassword(email, encryptPassword(password)).orElse(null);
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
		Member member = membersDao.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
		return new UserPrincipal(member);
	}

}
