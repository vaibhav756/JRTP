package com.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.constant.AppConstants;
import com.props.AppProperties;

@RestController()
public class GreetRestController {

	@Autowired()
	private AppProperties appproperties;
	
	@GetMapping("/greet")
	public String greetMsg()
	{
		return appproperties.getCommons().get(AppConstants.GREET_MSG);
	}
}
