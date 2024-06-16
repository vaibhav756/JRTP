package com.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="user_master")
public class UserEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer userId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="user_email")
	private String userEmail;
	
	@Column(name="user_pwd")
	private String userPwd;
	
	@Column(name="user_mobile")
	private Long userMobile;
	
	@Column(name="user_enable")
	private Integer userEnable;
	
	@Column(name="crtn_time")
	private Timestamp crtnTime;
	
	@Column(name="mod_time")
	private Timestamp modTime;
	
	@Column(name="crtn_by")
	private Integer crtnBy;
	
	@Column(name="mod_by")
	private Integer modBy;

	@OneToMany(cascade=CascadeType.ALL,mappedBy="",fetch=FetchType.EAGER)
	private List<StudentEnqEntity> enqs;
	
}
