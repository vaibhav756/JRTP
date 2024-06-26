package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.constant.AppConstants;
import com.dto.DashboardResponseDto;
import com.dto.EnquiryFormDto;
import com.dto.EnquirySearchCriteria;
import com.dto.LoginForm;
import com.entity.StudentEnqEntity;
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
		Integer id=(Integer)session.getAttribute(AppConstants.USER_ID);
		if(null!=id)
		{
			DashboardResponseDto data = enqservice.getDashboardData(Integer.valueOf(id));
			model.addAttribute("dashboarddata", data);
			return "dashboard";			
		}else
		{
			model.addAttribute("loginform",new LoginForm());
			model.addAttribute(AppConstants.ERROR_MSG,AppConstants.LOGIN_FIRST);
			return "login";
		}
	}
	
	@GetMapping("/addenquiry")
	public String addEnquiry(Model model)
	{
		Integer id=(Integer)session.getAttribute(AppConstants.USER_ID);
		String view=null;
		if(null!=id)
		{
			commonmodels(model);
			view="addenquiry";
		}else
		{
			model.addAttribute("loginform",new LoginForm());
			model.addAttribute(AppConstants.ERROR_MSG,AppConstants.LOGIN_FIRST);
			view="login";
		}
		return view;
	}
	
	@PostMapping("/submitenq")
	public String submitEnquiry(@ModelAttribute("enqform") EnquiryFormDto form ,Model model)
	{
		String view=null;
		Integer id=(Integer)session.getAttribute(AppConstants.USER_ID);
		if(null!=id)
		{
			String result = enqservice.upsertEnquiry(form);
			if(AppConstants.SUCCESS_MSG.equals(result))
			{
				commonmodels(model);
				model.addAttribute(AppConstants.RESULT,AppConstants.ENQUIRY_ADDED_SUCCESS);
			}else
			{
				model.addAttribute(AppConstants.ERROR_MSG,AppConstants.SYSTEM_BUSY);
			}
			view="addenquiry";
		}else
		{
			model.addAttribute("loginform",new LoginForm());
			model.addAttribute(AppConstants.ERROR_MSG, AppConstants.LOGIN_FIRST);
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
		Integer userid=(Integer)session.getAttribute(AppConstants.USER_ID);
		if(null!=userid)
		{
			model.addAttribute("enquiries",enqservice.getEnquiryByUserId(userid));
			view="viewenq";
		}else
		{
			model.addAttribute("loginform",new LoginForm());
			model.addAttribute(AppConstants.ERROR_MSG, AppConstants.LOGIN_FIRST);
			view="login";
		}
		return view;
	}
	
	@GetMapping("/getfilteredenq")
	public String getFilteredEnq(@RequestParam() String name,@RequestParam() String mode,@RequestParam() String status,Model model)
	{
		String view=null;
		Integer userid =(Integer)session.getAttribute(AppConstants.USER_ID);
		if(null!=userid)
		{
			EnquirySearchCriteria criteria=new EnquirySearchCriteria();
			if(""==name)
			criteria.setCourse(null);
			else
			criteria.setCourse(name);
			if(""==mode)
			criteria.setClassmode(null);
			else
			criteria.setClassmode(mode);
			if(""==status)
			criteria.setEnqstatus(null);
			else
			criteria.setEnqstatus(status);
			model.addAttribute("enquiries", enqservice.getEnquiries(userid, criteria));
			view="filteredenq";
		}else
		{
			model.addAttribute("loginform",new LoginForm());
			model.addAttribute(AppConstants.ERROR_MSG, AppConstants.LOGIN_FIRST);
			view="login";
		}
		return view;
	}
	
	@GetMapping("editenq")
	public String editEnquiry(@RequestParam() Integer enqid,Model model)
	{
		String view="viewenq";
		Integer userid=(Integer)session.getAttribute(AppConstants.USER_ID);
		if(null!=userid)
		{
			EnquiryFormDto studenqdto = enqservice.getEnquiryByEnqId(enqid);
			if(null!=studenqdto.getEnqId())
			{
				commonmodels(model);
				model.addAttribute("enquiry",studenqdto);
				view="editenq";
			}else
			{
				model.addAttribute("enquiries",enqservice.getEnquiryByUserId(userid));
				model.addAttribute(AppConstants.ERROR_MSG, AppConstants.INVALID_ENQ_ID);
			}
		}else
		{
			model.addAttribute("loginform",new LoginForm());
			model.addAttribute(AppConstants.ERROR_MSG, AppConstants.LOGIN_FIRST);
			view="login";
		}
		return view;
	}
	
	@PostMapping("updateenq")
	public String updateEnquiry(@ModelAttribute("enquiry") EnquiryFormDto form ,Model model)
	{
		String view="editenq";
		Integer userid=(Integer)session.getAttribute(AppConstants.USER_ID);
		if(null!=userid)
		{
			String result = enqservice.upsertEnquiry(form);
			if(AppConstants.SUCCESS_MSG.equals(result))
			{
				model.addAttribute("enquiries",enqservice.getEnquiryByUserId(userid));
				model.addAttribute("success","Enquiry with enquiry id : "+form.getEnqId()+" has been updated.");
				view="viewenq";
			}else
			{
				commonmodels(model);
				EnquiryFormDto studenqdto = enqservice.getEnquiryByEnqId(form.getEnqId());
				model.addAttribute("enquiry",studenqdto);
				model.addAttribute(AppConstants.ERROR_MSG, AppConstants.SYSTEM_BUSY);
			}
		}
		else
		{
			model.addAttribute("loginform",new LoginForm());
			model.addAttribute(AppConstants.ERROR_MSG, AppConstants.LOGIN_FIRST);
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
