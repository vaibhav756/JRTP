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

import com.dto.LoginForm;
import com.dto.SignUp;
import com.dto.UnlockForm;
import com.entity.UserEntity;
import com.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userservice;
	
	@Autowired
	private HttpSession session;
	
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
		if("success".equals(result))
		{
			UserEntity user = userservice.getUserByEmail(form.getEmail());
			session.setAttribute("userid", user.getUserId());
			//Create session and store user data in session.
			
			return "redirect:/dashboard?userid="+user.getUserId();
		}else
		{
			view="login";
			model.addAttribute("result",result);
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
		if("success".equals(result))
		{
			model.addAttribute("result", "Check your email.");
		}else if("error".equals(result))
		{
			model.addAttribute("result", "System busy.Try after sometime.");			
		}else
		{
			model.addAttribute("result",result);
		}
		return "signup";
	}
	
	@GetMapping("/unlock")
	public String unlock(@RequestParam() String email,Model model)
	{
		UnlockForm unlockform=new UnlockForm();
		unlockform.setEmail(email);
		model.addAttribute("unlockform", unlockform);
		model.addAttribute("result",null);
		return "unlock";
	}

	@PostMapping("unlockaccount")
	public String unlockAccount(@ModelAttribute("unlockform") UnlockForm form,Model model)
	{
		if(form.getNewPwd().equals(form.getConfirmPwd()))
		{
			String unlockresult = userservice.unlockAccount(form);
			model.addAttribute("result",unlockresult);
		}else
		{
			model.addAttribute("result","New password and confirm password is not same.");
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
		if("InvalidUser".equals(forgotresult) || "InactiveUser".equals(forgotresult) || "fail".equals(forgotresult))
		{
			if("InvalidUser".equals(forgotresult))
			{
				model.addAttribute("result","Invalid email address.");
			}else if("InactiveUser".equals(forgotresult))
			{
				model.addAttribute("result","User is inactive.Please active.");
			}else
			{
				model.addAttribute("result","System busy,Try after sometime.");
			}
		}else
		{
			model.addAttribute("result","Forgot password successfully.Kindly check email.");
		}
		return "forgotpwd";
	}
	
}
