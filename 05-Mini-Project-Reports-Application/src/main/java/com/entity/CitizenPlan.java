package com.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@IdClass(CitizenCompositeKey.class)
@NoArgsConstructor
@AllArgsConstructor
public class CitizenPlan {

	@Id
	private String citizenId;
	@Id
	private String citizenName;
	@Id
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
