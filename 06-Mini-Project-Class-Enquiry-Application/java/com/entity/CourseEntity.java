package com.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="courses")
@AllArgsConstructor
@NoArgsConstructor
public class CourseEntity {
	@Id
	private Integer courseId;
	private String courseName;
}
