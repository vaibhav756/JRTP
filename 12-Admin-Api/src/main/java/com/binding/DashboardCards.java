package com.binding;

import lombok.Data;

@Data
public class DashboardCards {

	private Long plansCount;
	private Long approvedCount;
	private Long deniedCount;
	private Double beniftAmtGiven;
	private UserAccountForm user;
	
	
}
