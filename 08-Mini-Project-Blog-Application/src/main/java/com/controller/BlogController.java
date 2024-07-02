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
import com.dto.BlogDto;
import com.dto.LoginForm;
import com.service.BlogService;
import com.service.UserService;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogservice;
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private HttpSession session; 
	
	@GetMapping("/blogdashboard")
	public String blogDashboard(Model model)
	{
		Integer userId=(Integer)session.getAttribute(AppConstants.USER_ID);
		String view="login";
		if(null!=userId)
		{
			model.addAttribute("allblogs",blogservice.getAllBlogs());
			view="blogdashboard";
		}else
		{
			model.addAttribute("loginform",new LoginForm());
			model.addAttribute(AppConstants.RESULT,AppConstants.LOGIN_FIRST);
		}
		return view;
	}
	
	@GetMapping("/createblog")
	public String createBlog(Model model)
	{
		Integer userId=(Integer)session.getAttribute(AppConstants.USER_ID);
		String view="login";
		if(null!=userId)
		{
			model.addAttribute("blog",new BlogDto());
			view="newblog";
		}else
		{
			model.addAttribute("loginform",new LoginForm());
			model.addAttribute(AppConstants.RESULT,AppConstants.LOGIN_FIRST);
		}
		return view;
	}
	
	@PostMapping("/submitblog")
	public String submitBlog(@ModelAttribute() BlogDto blog,Model model)
	{
		String view="newblog";
		Integer userId=(Integer)session.getAttribute(AppConstants.USER_ID);
		if(null!=userId)
		{
			
			blog.setUser(userservice.getUserById(userId));
			String result = blogservice.addBlog(blog);
			if(AppConstants.SUCCESS_MSG.equals(result))
			{
				model.addAttribute("allblogs",blogservice.getAllBlogs());
				model.addAttribute(AppConstants.SUCCESS_MSG,"Blog Added Successfully.");
				view="blogdashboard";
			}else
			{
				model.addAttribute("blog",blog);
				model.addAttribute(AppConstants.ERROR_MSG, "Error occured while creating blog.");
			}
		}else
		{
			model.addAttribute("loginform",new LoginForm());
			model.addAttribute(AppConstants.RESULT,AppConstants.LOGIN_FIRST);
			view="login";
		}
		return view;
	}
	
	@GetMapping("/logout")
	public String logout(Model model)
	{
		session.invalidate();
		model.addAttribute("loginform",new LoginForm());
		return "login";
	}
	
	@GetMapping("/getBlogByName")
	public String getBlogByName(@RequestParam() String blogname,Model model)
	{
		model.addAttribute("blogs",blogservice.getBlogsByName(blogname));
		return "filteredblogs";
	}
	
}
