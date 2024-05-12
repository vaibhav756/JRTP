package com.dto;

import java.time.LocalDate;

import lombok.Data;
@Data
public class CitizenFormBindingDto {

	private String planName;
	private String gender;
	private String planStatus;
	private String planStartDate;
	private String planEndDate;
	
}
