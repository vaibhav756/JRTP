package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="ELIG_DTLS")
@Data
public class EligEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer edgTraceId;
	private String planStatus;
	private Double benefitAmt;
}
