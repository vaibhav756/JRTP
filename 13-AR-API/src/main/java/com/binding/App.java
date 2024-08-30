package com.binding;

import java.time.LocalDate;

import com.entities.UserEntity;

public class App {

	private Long caseNumber;
	private String fullname;
	private String email;
	private String gender;
	private LocalDate dob;
	private String phno;
	private Long ssn;
	private UserEntity user;
	private Integer userId;
	
	public Long getCaseNumber() {
		return caseNumber;
	}
	public void setCaseNumber(Long caseNumber) {
		this.caseNumber = caseNumber;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getPhno() {
		return phno;
	}
	public void setPhno(String phno) {
		this.phno = phno;
	}
	public Long getSsn() {
		return ssn;
	}
	public void setSsn(Long ssn) {
		this.ssn = ssn;
	}
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
