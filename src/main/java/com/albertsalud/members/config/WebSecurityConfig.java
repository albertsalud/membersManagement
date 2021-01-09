package com.albertsalud.members.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.albertsalud.members.security.UserRole;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/private/*").hasAuthority(UserRole.MEMBER.name())
				.antMatchers("/admin/*").hasAuthority(UserRole.ADMIN.name())
				.antMatchers("/", 
//						"/activities/", "/activities/*",
						"/members", "/members/*",
						"/admin", "/login").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/members")
				.loginProcessingUrl("/login").permitAll()
				.defaultSuccessUrl("/private")
//				.and()
//			.logout()
//				.logoutSuccessUrl("/")
//				.permitAll()
				;
		
	}
	
	
	
}
