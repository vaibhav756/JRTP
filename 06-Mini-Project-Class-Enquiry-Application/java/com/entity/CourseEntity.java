package com.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name = "courses")
@AllArgsConstructor
public class CourseEntity {
	@Id
	private Integer courseId;
	private String courseName;
}
