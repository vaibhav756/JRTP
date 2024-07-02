package com.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
public class Comments {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="comment_id")
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
	
	@CreationTimestamp
	@Column(name="crtn_time",updatable=false)
	private Timestamp crtnTime;
	
	@UpdateTimestamp
	@Column(name="mod_time",insertable=false)
	private Timestamp updtTime;
	
	@ManyToOne
	private BlogEntity blog;
	
}
