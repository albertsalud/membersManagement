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
			.cors().disable()
			.headers().frameOptions().disable()
			.and()
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/", "/new", "/save", "/login").permitAll()
				.antMatchers("/private", "/private/").hasAnyAuthority(UserRole.MEMBER.name(), UserRole.ADMIN.name())
				.antMatchers("/private/*").hasAuthority(UserRole.MEMBER.name())
				.antMatchers("/admin/*").hasAuthority(UserRole.ADMIN.name())
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/")
				.loginProcessingUrl("/login").permitAll()
				.defaultSuccessUrl("/private")
//				.and()
//			.logout()
//				.logoutSuccessUrl("/")
//				.permitAll()
				;
		
	}
	
	
	
}
