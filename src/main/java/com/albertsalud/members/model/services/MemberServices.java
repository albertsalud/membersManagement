package com.albertsalud.members.model.services;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
	
	public MemberServicesResultBean registryMember(Member memberToRegistry) {
		managePassword(memberToRegistry);
		
		MemberServicesResultBean result = saveMember(memberToRegistry);

		return result;
	}
	
	private MemberServicesResultBean saveMember(Member memberToSave) {
		MemberServicesResultBean result = new MemberServicesResultBean();
		try {
			result.setMember(membersDao.save(memberToSave));
			
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
		return this.saveMember(member);
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if("daudecinc".equals(username)) return new AdminPrincipal(this.generateAdminMember());
		
		Member member = membersDao.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
		return new MemberPrincipal(member);
	}

	private Member generateAdminMember() {
		Member fakeMember = new Member();
		fakeMember.setId(-1l);
		fakeMember.setName("Dau de cinc");
		fakeMember.setEmail("daudecinc@gmail.com");
		fakeMember.setPassword(passwordEncoder.encode("jocsdetaula"));
		fakeMember.setActive(true);

		return fakeMember;
	}

	public MemberServicesResultBean updateMember(@Valid MembersDataFormDTO dto) {
		MemberServicesResultBean result = new MemberServicesResultBean();
		
		Member member = membersDao.findByIdAndPassword(dto.getId(), dto.getPassword()).orElse(null);
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
		
		Member member = membersDao.findByEmailAndPassword(dto.getEmail(), dto.getPassword()).orElse(null);
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
		member.setPassword(dto.getNewPassword());
		managePassword(member);
		
	}

	public List<Activity> getActivitiesByYear(Member member, Integer year) {
		
		int myYear = manageYear(year);
		
		return member.getActivities().stream()
				.filter(a -> {
					Calendar activityDate = Calendar.getInstance();
					activityDate.setTime(a.getStartDate());
					
					return activityDate.get(Calendar.YEAR) == myYear;
				})
				.sorted(Comparator.comparing(Activity::getStartDate))
				.collect(Collectors.toList());
	}

	private int manageYear(Integer year) {
		if(year == null) return Calendar.getInstance().get(Calendar.YEAR);
		return year.intValue();
	}

	public List<Member> findByActivityId(Long activityId) {
		return membersDao.findByActivitiesId(activityId);
	}

	public List<Member> findAllActive() {
		return membersDao.findAll();
	}

	public Member findById(Long memberId) {
		return membersDao.findById(memberId).orElse(null);
	}

	public MemberServicesResultBean removeActivity(Member member, Activity activity) {
		MemberServicesResultBean result = new MemberServicesResultBean();
		
		try {
			result.setOk(member.getActivities().remove(activity));
			if(result.isOk()) this.saveMember(member);
		
		} catch (Exception e) {
			result.setOk(false);
			result.setError(e.getMessage());
		}
		
		return result;
	}

	public MemberServicesResultBean activeUser(Member member) {
		member.setActive(true);
		return this.saveMember(member);
	}

	public List<Activity> getActivitiesByMember(Member member, Integer year) {
		Member databaseMember = membersDao.findActivitiesByMember(member);
		if(databaseMember.getActivities() == null || databaseMember.getActivities().isEmpty()) return null;
		
		int myYear = manageYear(year);
		return databaseMember.getActivities().stream()
				.filter(a -> {
					Calendar activityDate = Calendar.getInstance();
				
					activityDate.setTime(a.getStartDate());
					
					return activityDate.get(Calendar.YEAR) == myYear;
					})
				.sorted(Comparator.comparing(Activity::getStartDate))
				.collect(Collectors.toList());
	}

}
