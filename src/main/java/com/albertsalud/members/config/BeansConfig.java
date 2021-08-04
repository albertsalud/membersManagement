package com.albertsalud.members.config;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeansConfig {
	
	private Log log = LogFactory.getLog(BeansConfig.class);
	
	@Autowired
	private ServletContext sc;
	
	@Value("${dd5.web.url}")
	private String webURL;
	
	@PostConstruct
	public void initGlobalParameters() {
		log.debug("Setting webURL parameter: " + webURL);
		sc.setAttribute("webURL", webURL);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
