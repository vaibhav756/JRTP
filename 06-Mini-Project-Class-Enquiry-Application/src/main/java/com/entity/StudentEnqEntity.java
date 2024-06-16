package com.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name="student_enquiry")
@Data
public class StudentEnqEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer enqId;
	
	@Column(name="student_name")
	private String studName;
	
	@Column(name="student_phno")
	private Long studPhno;
	
	@Column(name="class_mode")
	private String classMode;
	
	@Column(name="course_name")
	private String courseName;
	
	@Column(name="enq_status")
	private String enqStatus;
	
	@CreationTimestamp
	@Column(name="crtn_time")
	private Timestamp crtnTime;
	
	@UpdateTimestamp
	@Column(name="mod_time")
	private Timestamp modTime;
	
	@ManyToOne
	private UserEntity user;
}
