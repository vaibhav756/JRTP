package com.service;

import javax.servlet.http.HttpServletRequest;

import com.dto.LoginForm;
import com.dto.SignUp;
import com.dto.UnlockForm;
import com.entity.UserEntity;

public interface UserService {

	public String login(LoginForm form);

	public String signup(SignUp form, HttpServletRequest req);

	public String unlockAccount(UnlockForm form);

	public String forgotPwd(String email);

	public UserEntity getUserByEmail(String email);
}
