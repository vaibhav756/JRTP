package com.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
@Data
public class CitizenCompositeKey implements Serializable{

	@Id
	@GenericGenerator(name="citizen_id_gen",strategy="com.generators.CitizenIdGenerator")
	@GeneratedValue(generator="citizen_id_gen")
	private String citizenId;
	private String citizenName;
	private String planName;
	
}
