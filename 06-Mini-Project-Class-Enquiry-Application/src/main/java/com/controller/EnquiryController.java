package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnquiryController {

	@GetMapping("/dashboard")
	public String dashboardPage()
	{
		return "dashboard";
	}
	
	@GetMapping("/addenq")
	public String addEnquiry()
	{
		return "enquiry";
	}
	
	@GetMapping("/viewenq")
	public String viewEnquiries()
	{
		return "viewenq";
	}
}
