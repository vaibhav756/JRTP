package com.constant;

import org.springframework.stereotype.Component;

@Component
public class AppConstants {

	public static final String SUCCESS_MSG="success";
	public static final String ERROR_MSG="error";
	public static final String USER_ID="userid";
	
	public static final String RESULT="result";
	
	public static final Integer USER_ENABLE=1;
	public static final String INVALID_PWD="Invalid password.";
	public static final String ACC_LOCKED="Account is locked,Please unlock account.";
	public static final String INVALID_USER="No user found with given email.";
	public static final String EMAIL_ALREADY_REG="Email is already register";
	public static final String UNLOCK_ACCOUNT="Account unlock successfully.";
	public static final String ACC_ALREADY_ACTIVE="Account is already active.";
	public static final String INACTIVE_USER="InactiveUser";
	public static final String INVALID_USER_ACC="InvalidUser";
	public static final String CHECK_EMAIL="Check your email.";
	public static final String SYSTEM_BUSY="System busy.Try after sometime.";
	public static final String PWD_MISMATCH="New password and confirm password is not same.";
	public static final String INVALID_EMAIL="Invalid email address.";
	public static final String USER_IS_INACTIVE="User is inactive.Please active.";
	public static final String FORGOT_PWD_SUCCESS="Forgot password successfully.Kindly check email.";
	public static final String LOGIN_FIRST="Kindly login first";
	public static final String ENQUIRY_ADDED_SUCCESS="Enquiry added successfully.";
	public static final String INVALID_ENQ_ID="Invalid EnquiryId";
	
}
