package com.service;

import java.util.List;

import com.binding.UnlockAccountForm;
import com.binding.UserAccountForm;

public interface AccountService {

	public Boolean createUserAccount(UserAccountForm form);
	public List<UserAccountForm> fetchUserAccounts();
	public UserAccountForm getAccountById(Integer id);
	public Boolean updateUserAccount(UserAccountForm form);
	public Boolean actDeactAccount(Integer id);
	public String unlockUserAccount(UnlockAccountForm form);
	
}
