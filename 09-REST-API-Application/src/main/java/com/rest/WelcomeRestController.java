package com.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.constant.AppConstants;
import com.props.AppProperties;

@RestController
public class WelcomeRestController {

	//If we have less properties to read from application.properties file we can go with below approach.
	
	//Old Approach
	/*@Value("${app.commons.welcomemsg}")
	private String welcomemsg;
	
	@Value("${app.commons.greetmsg}")
	private String greetmsg;*/
	
	//If we have nth properties in application.yml file we can go with below approach.
	//New Approach
	@Autowired
	private AppProperties appproperties;
	
	@GetMapping("/welcome")
	public String welcomemsg()
	{
		return appproperties.getCommons().get(AppConstants.WELCOME_MSG);
	}
	
}
