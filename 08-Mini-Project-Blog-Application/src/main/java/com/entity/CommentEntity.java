package com.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="comments")
public class CommentEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer commentId;
	
	@Column(name="user_name")
	private String name;
	
	@Column(name="user_email")
	private String email;
	
	@Lob
	@Column(name="comment")
	private String comment;

	@Column(name="delete_flag")
	private Integer deleteFlag=0;
	
	@Column(name="crtn_time")
	private LocalDate crtnTime;
	
	@Column(name="mod_time")
	private LocalDate updtTime;
	
	@ManyToOne
	private BlogEntity blog;
	
}
