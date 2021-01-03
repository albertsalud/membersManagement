package com.albertsalud.members.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.albertsalud.members.model.dao.MembersDAO;
import com.albertsalud.members.model.entities.Activity;
import com.albertsalud.members.model.entities.Member;
import com.albertsalud.members.model.services.result.MemberServicesResultBean;

@Service
public class MemberServices {
	
	@Autowired
	private MembersDAO membersDao;
	
	public MemberServicesResultBean save(Member memberToSave) {
		MemberServicesResultBean result = new MemberServicesResultBean();
		
		try {
			membersDao.save(memberToSave);
			
		} catch (Exception e) {
			result.setError(e.getMessage());
			result.setOk(false);
		}
		
		return result;
	}

	public Member findByEmailAndPassword(String email, String password) {
		return membersDao.findByEmailAndPassword(email, password).orElse(null);
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

}
