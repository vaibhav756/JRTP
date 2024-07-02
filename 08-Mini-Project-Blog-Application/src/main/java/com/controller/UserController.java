package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.constant.AppConstants;
import com.dto.LoginForm;
import com.dto.SignUp;
import com.dto.UnlockForm;
import com.entity.UserEntity;
import com.service.BlogService;
import com.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userservice;
	
	@Autowired
	private HttpSession session;

	@Autowired
	private BlogService blogservice;
	
	@GetMapping("/")
	public String index(Model model)
	{
		model.addAttribute("blogs", blogservice.getAllBlogs());
		return "index";
	}
	
	@GetMapping("/loginpage")
	public String login(Model model)
	{
		model.addAttribute("loginform",new LoginForm());
		return "login";
	}
	
	@PostMapping("/loginuser")
	public String loginUser(@ModelAttribute("loginform") LoginForm form,Model model)
	{
		String view=null;
		String result = userservice.login(form);
		if(AppConstants.SUCCESS_MSG.equals(result))
		{
			UserEntity user = userservice.getUserByEmail(form.getEmail());
			//Create session and store user data in session.
			session.setAttribute(AppConstants.USER_ID, user.getUserId());
			return "redirect:/blogdashboard";
		}else
		{
			view="login";
			model.addAttribute(AppConstants.RESULT,result);
		}
		return view;
	}
	
	@GetMapping("/signup")
	public String signup(Model model)
	{
		model.addAttribute("signupform",new SignUp());
		return "signup";
	}
	
	@PostMapping("/signuphandle")
	public String handleSignUp(@ModelAttribute("signupform") SignUp form,Model model,HttpServletRequest req)
	{
		String result = userservice.signup(form,req);
		if(AppConstants.SUCCESS_MSG.equals(result))
		{
			model.addAttribute(AppConstants.RESULT,AppConstants.CHECK_EMAIL);
		}else if(AppConstants.ERROR_MSG.equals(result))
		{
			model.addAttribute(AppConstants.RESULT, AppConstants.SYSTEM_BUSY);			
		}else
		{
			model.addAttribute(AppConstants.RESULT,result);
		}
		return "signup";
	}
	
	@GetMapping("/unlock")
	public String unlock(@RequestParam() String email,Model model)
	{
		UnlockForm unlockform=new UnlockForm();
		unlockform.setEmail(email);
		model.addAttribute("unlockform", unlockform);
		model.addAttribute(AppConstants.RESULT,null);
		return "unlock";
	}

	@PostMapping("unlockaccount")
	public String unlockAccount(@ModelAttribute("unlockform") UnlockForm form,Model model)
	{
		if(form.getNewPwd().equals(form.getConfirmPwd()))
		{
			String unlockresult = userservice.unlockAccount(form);
			model.addAttribute(AppConstants.RESULT,unlockresult);
		}else
		{
			model.addAttribute(AppConstants.RESULT,AppConstants.PWD_MISMATCH);
		}
		return "unlock";
	}
	
	@GetMapping("/forgotpwd")
	public String forgot(Model model)
	{
		model.addAttribute("form", new LoginForm());
		return "forgotpwd";
	}
	
	@PostMapping("/forgotpassword")
	public String forgotPassword(@ModelAttribute("form") LoginForm form,Model model)
	{
		String forgotresult = userservice.forgotPwd(form.getEmail());
		if(AppConstants.INVALID_USER_ACC.equals(forgotresult) || AppConstants.INACTIVE_USER.equals(forgotresult) || AppConstants.ERROR_MSG.equals(forgotresult))
		{
			if(AppConstants.INVALID_USER_ACC.equals(forgotresult))
			{
				model.addAttribute(AppConstants.RESULT,AppConstants.INVALID_EMAIL);
			}else if(AppConstants.INACTIVE_USER.equals(forgotresult))
			{
				model.addAttribute(AppConstants.RESULT,AppConstants.USER_IS_INACTIVE);
			}else
			{
				model.addAttribute(AppConstants.RESULT,AppConstants.SYSTEM_BUSY);
			}
		}else
		{
			model.addAttribute(AppConstants.RESULT,AppConstants.FORGOT_PWD_SUCCESS);
		}
		return "forgotpwd";
	}
	
}
