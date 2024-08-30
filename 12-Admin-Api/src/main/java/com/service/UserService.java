package com.service;

import com.binding.DashboardCards;
import com.binding.LoginForm;
import com.binding.UserAccountForm;

public interface UserService {

	public String login(LoginForm form);
	public Boolean recoverPassword(String email);
	public DashboardCards getDashboardInfo();
	public UserAccountForm getUserByEmail(String email);
	
}
