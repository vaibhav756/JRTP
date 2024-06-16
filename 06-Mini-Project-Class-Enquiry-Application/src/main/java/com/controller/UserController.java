package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.dto.UserDto;

@Controller
public class UserController {

	@GetMapping("/loginpage")
	public String login()
	{
		return "login";
	}
	
	@PostMapping("/signup")
	public String signup(@ModelAttribute("") UserDto dto)
	{
		return "signup";
	}
	
	@GetMapping("/unlock")
	public String unlock()
	{
		return "unlock";
	}
	
	@GetMapping("/forgotpwd")
	public String forgot()
	{
		return "forgotpwd";
	}
	
}
