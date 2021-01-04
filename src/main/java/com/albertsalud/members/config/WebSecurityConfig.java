package com.albertsalud.members.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", 
						"/activities/", "/activities/*",
						"/members", "/members/*").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.permitAll()
				.defaultSuccessUrl("/admin/activities")
				.and()
			.logout()
				.logoutSuccessUrl("/")
				.permitAll();
		
	}
	
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("daudecinc")
				.password("jocsdetaula")
				.roles("ADMIN")
				.build();
		

		return new InMemoryUserDetailsManager(user);
	}

	
}
