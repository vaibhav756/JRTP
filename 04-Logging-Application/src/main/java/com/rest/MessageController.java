package com.rest;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

	private Logger logger=LoggerFactory.getLogger(MessageController.class);
	
	@GetMapping("welcome")
	public String welcomeMsg()
	{
		logger.info("Inside MessageRestController for method welcomeMsg() started...");
		String msg="Welcome to Mumbai.";
		try {
			int val=10/0;
		} catch (Exception e) {
			//Logging level
			logger.error("Error occured in welcomeMsg() method");
			e.printStackTrace();
		}
		logger.info("Inside MessageRestController for method welcomeMsg() ended...");
		return msg;
	}
	
	@GetMapping("greet")
	public String greetMsg()
	{
		logger.info("Inside MessageRestController for method greetMsg() started...");
		String msg="Good Morning...";
		String name=null;
		try {
			name.length();
		} catch (Exception e) {
			//Logging level
			logger.error("Error occured in greetMsg() method");
			e.printStackTrace();
		}
		logger.info("Inside MessageRestController for method greetMsg() ended...");
		return msg;
	}
	
}
