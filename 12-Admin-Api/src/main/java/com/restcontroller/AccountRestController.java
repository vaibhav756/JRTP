package com.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.binding.UserAccountForm;
import com.constant.AppConstants;
import com.service.AccountService;

@RestController
public class AccountRestController {

	@Autowired
	private AccountService accService;

	private Logger logger=LoggerFactory.getLogger(AccountRestController.class);
	
	@PostMapping("/usercreation")
	public ResponseEntity<String> createAccount(@RequestBody UserAccountForm form)
	{
		Boolean result = accService.createUserAccount(form);
		if(result)
		{
			return new ResponseEntity<>(AppConstants.ACCOUNT_CREATED_SUCCESSFULLY,HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(AppConstants.ACCOUNT_CREATION_FAILED,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getusers")
	public ResponseEntity<List<UserAccountForm>> getUsers()
	{
		List<UserAccountForm> allUser = accService.fetchUserAccounts();
		return new ResponseEntity<>(allUser,HttpStatus.OK);
	}
	
	@GetMapping("/user/{userid}")
	public ResponseEntity<UserAccountForm> getUserById(@PathVariable Integer userid)
	{
		UserAccountForm user = accService.getAccountById(userid);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@GetMapping("/actdeactacc/{userid}")
	public ResponseEntity<List<UserAccountForm>> actDeactAccount(@PathVariable Integer userid)
	{
		Boolean actDeactAccount = accService.actDeactAccount(userid);
		List<UserAccountForm> allUser = accService.fetchUserAccounts();
		return new ResponseEntity<>(allUser,HttpStatus.OK);
	}
	
}
