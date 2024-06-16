package com.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name="Status")
@AllArgsConstructor
@NoArgsConstructor
public class EnqStatusEntity {

	@Id
	private Integer statusId;
	private String statusName;
	
}
