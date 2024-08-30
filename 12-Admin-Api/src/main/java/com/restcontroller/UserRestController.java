package com.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.binding.DashboardCards;
import com.binding.LoginForm;
import com.binding.UserAccountForm;
import com.constant.AppConstants;
import com.service.AccountService;
import com.service.UserService;

@RestController
public class UserRestController {

	@Autowired
	private UserService userservice;
	
	@Autowired
	private AccountService accservice;
	
	@PostMapping("/login")
	public String login(@RequestBody LoginForm form)
	{
		String result = userservice.login(form);
		if(AppConstants.UNLOCKED_ACCOUNT_STATUS.equals(result))
		{
			return "redirect:/dashboard?email="+form.getEmail();
		}else
		{
			return result;
		}
	}
	
	@GetMapping("/dashboard")
	public ResponseEntity<DashboardCards> dashboard(@RequestParam String email)
	{
		UserAccountForm user = userservice.getUserByEmail(email);
		DashboardCards card = userservice.getDashboardInfo();
		card.setUser(user);
		return new ResponseEntity<>(card,HttpStatus.OK);
	}
	
}
