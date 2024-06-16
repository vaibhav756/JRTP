package com.service;

import com.dto.LoginForm;
import com.dto.SignUp;
import com.dto.UnlockForm;
import com.dto.UserDto;

public interface UserService {

	public String login(LoginForm form);
	
	public boolean signup(SignUp form);
	
	public boolean unlockAccount(UnlockForm form);
	
	public String forgotPwd(String email);
}
