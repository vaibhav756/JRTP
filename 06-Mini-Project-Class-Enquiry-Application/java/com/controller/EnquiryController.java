package com.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.LoginForm;
import com.service.EnquiryService;

@Controller
public class EnquiryController {

	@Autowired
	private EnquiryService enqservice;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/dashboard")
	public String dashboard(@RequestParam("userid") String id,Model model)
	{
		Map<String, Integer> enqStatus = enqservice.getEnqStatus(Integer.valueOf(id));
		model.addAttribute("total",enqStatus.get("total"));
		model.addAttribute("enrolled",enqStatus.get("enrolled"));
		model.addAttribute("lost",enqStatus.get("lost"));
		return "dashboard";
	}
	
	@GetMapping("/addenquiry")
	public String addEnquiry()
	{
		
		return "enquiry";
	}
	
	@GetMapping("/viewenq")
	public String viewEnquiries()
	{
		return "viewenq";
	}
	
	@GetMapping("/logout")
	public String logout(Model model)
	{
		session.invalidate();
		model.addAttribute("loginform",new LoginForm());
		return "login";
	}
}
