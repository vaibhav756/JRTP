package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig{

/*	@Bean
	public SecurityFilterChain config(HttpSecurity http) throws Exception
	{
		http.authorizeHttpRequests(req->{
			req.antMatchers("/","/loginpage","/signup","/unlock","/forgotpwd","/images/**","/signuphandle","/unlockaccount","/loginuser","/forgotpassword","/dashboard").permitAll().anyRequest().authenticated();
		}).formLogin();
		return http.build();
	}
*/
}
