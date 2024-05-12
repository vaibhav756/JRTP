package com.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;
@Data
public class CitizenDto implements Serializable{

	private String citizenId;
	private String citizenName;
	private String planName;
	private String gender;
	private String planStatus;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private Double benefitAmt;
	private String denialReason;
	private LocalDate terminationDate;
	private String terminationReason;
	
}
