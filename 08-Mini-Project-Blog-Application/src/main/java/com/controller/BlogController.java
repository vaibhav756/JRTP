package com.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.constant.AppConstants;
import com.dto.BlogDto;
import com.dto.CommentDto;
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
			model.addAttribute("allblogs",blogservice.getAllBlogs(userId));
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
				model.addAttribute("allblogs",blogservice.getAllBlogs(userId));
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

	@GetMapping("/viewblog")
	public String viewBlog(@RequestParam Integer blogid,Model model)
	{
		String view="index";	
		BlogDto dto=blogservice.getBlogById(blogid);
		if(null!=dto.getBlogId())
		{
			List<CommentDto> comments=blogservice.getBlogCommentsByBlogId(blogid);
			view="viewblog";
			model.addAttribute("blogdto",dto);
			model.addAttribute("comments", comments);
			model.addAttribute("commentform", new CommentDto());
		}else
		{
			model.addAttribute("blogs", blogservice.getAllBlogs(null));
			model.addAttribute(AppConstants.ERROR_MSG,"Invalid blogid.");
		}
		return view;
	}
	
	@PostMapping("/submitcomment")
	public String addComment(@RequestBody CommentDto comment,Model model)
	{
		blogservice.submitComment(comment);
		List<CommentDto> comments=blogservice.getBlogCommentsByBlogId(comment.getBlogid());
		model.addAttribute("comments",comments);
		return "newcomments";
	}
	
	@GetMapping("/deleteblog")
	public String deleteBlog(@RequestParam Integer blogid,Model model)
	{
		Integer userId=(Integer)session.getAttribute(AppConstants.USER_ID);
		String view="login";
		if(null!=userId)
		{
			String deleteresult = blogservice.deleteBlog(blogid);
			model.addAttribute("allblogs",blogservice.getAllBlogs(userId));
			view="filterdblogfordashboard";
		}else
		{
			model.addAttribute("loginform",new LoginForm());
			model.addAttribute(AppConstants.RESULT,AppConstants.LOGIN_FIRST);
		}
		return view;
	}
}
