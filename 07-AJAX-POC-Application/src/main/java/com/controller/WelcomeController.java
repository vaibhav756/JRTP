package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WelcomeController {

	@GetMapping("/")
	public String index()
	{
		return "index";
	}
	
	@GetMapping("/welcomeuser")
	@ResponseBody
	public String welcomeUser(@RequestParam String username)
	{
		//model.addAttribute("result","Welcome "+username);
		//return "index";
		return "Welcome "+username;
	}
	
	@GetMapping("/welcomecountry")
	@ResponseBody
	public String welcomeCountry(@RequestParam String country)
	{
		return "I am going to "+country+" next month.";
	}
}
