package com.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "user_activation_dtls")
public class UserActivationDtls {

	@Id
	private Integer userId;

	@Column(name = "user_email")
	private String userEmail;

	@Column(name = "user_pwd")
	private String userPwd;

	@Column(name = "user_enable")
	private Integer userEnable;

	@CreationTimestamp
	@Column(name = "crtn_time")
	private Timestamp crtnTime;

	@UpdateTimestamp
	@Column(name = "mod_time")
	private Timestamp modTime;

}
