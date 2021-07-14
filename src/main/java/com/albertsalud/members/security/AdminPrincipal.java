package com.albertsalud.members.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.albertsalud.members.model.entities.Member;

public class AdminPrincipal extends UserPrincipal {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AdminPrincipal(Member member) {
		super(member);
		authorities = initAuthorities();
	}

	private List<GrantedAuthority> initAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.name()));
		return authorities;
	}

}
