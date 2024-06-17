package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dto.DashboardResponseDto;
import com.dto.EnquiryFormDto;
import com.dto.LoginForm;
import com.service.EnquiryService;

@Controller
public class EnquiryController {

	@Autowired
	private EnquiryService enqservice;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/dashboard")
	public String getDashboardData(Model model)
	{
		Integer id=(Integer)session.getAttribute("userid");
		if(null!=id)
		{
			DashboardResponseDto data = enqservice.getDashboardData(Integer.valueOf(id));
			model.addAttribute("dashboarddata", data);
			return "dashboard";			
		}else
		{
			model.addAttribute("loginform",new LoginForm());
			model.addAttribute("error", "Kindly login first");
			return "login";
		}
	}
	
	@GetMapping("/addenquiry")
	public String addEnquiry(Model model)
	{
		Integer id=(Integer)session.getAttribute("userid");
		String view=null;
		if(null!=id)
		{
			commonmodels(model);
			view="addenquiry";
		}else
		{
			model.addAttribute("loginform",new LoginForm());
			model.addAttribute("error", "Kindly login first");
			view="login";
		}
		return view;
	}
	
	@PostMapping("/submitenq")
	public String submitEnquiry(@ModelAttribute("enqform") EnquiryFormDto form ,Model model)
	{
		String view=null;
		Integer id=(Integer)session.getAttribute("userid");
		if(null!=id)
		{
			String result = enqservice.upsertEnquiry(form);
			if("success".equals(result))
			{
				commonmodels(model);
				model.addAttribute("result","Enquiry added successfully.");
			}else
			{
				model.addAttribute("result","System busy,Try after sometime.");
			}
			view="addenquiry";
		}else
		{
			model.addAttribute("loginform",new LoginForm());
			model.addAttribute("error", "Kindly login first");
			view="login";
		}
		return view;
	}

	private void commonmodels(Model model) {
		model.addAttribute("courses",enqservice.getCourseName());
		model.addAttribute("status", enqservice.getEnqStatus());
		model.addAttribute("enqform", new EnquiryFormDto());
	}
	
	@GetMapping("/viewenquiry")
	public String viewEnquiries(Model model)
	{	
		commonmodels(model);
		String view=null;
		Integer userid=(Integer)session.getAttribute("userid");
		if(null!=userid)
		{
			model.addAttribute("enquiries",enqservice.getEnquiry(userid));
			view="viewenq";
		}else
		{
			model.addAttribute("loginform",new LoginForm());
			model.addAttribute("error", "Kindly login first");
			view="login";
		}
		return view;
	}
	
	@GetMapping("/logoutuser")
	public String logout(Model model)
	{
		session.invalidate();
		return "redirect:/loginpage";
	}
}
