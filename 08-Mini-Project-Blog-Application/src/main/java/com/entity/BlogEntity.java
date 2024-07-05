package com.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="blog")
@Setter
@Getter
public class BlogEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer blogId;
	
	@Column(name="blog_title")
	private String blogTitle;
	
	@Column(name="blog_short_desc",columnDefinition="LONGBLOB")
	private String blogShortDesc;
	
	@Lob
	@Column(name="blog_content",columnDefinition="LONGBLOB")
	private String blogContent;
	
	@Column(name="blog_flag")
	private Integer blogFlag=1;
	
	@Column(name="crtn_time")
	private LocalDate crtnTime;
	
	@Column(name="mod_time")
	private LocalDate modTime;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="blog",fetch=FetchType.EAGER,orphanRemoval=true)
	private List<CommentEntity> comments;
	
	@ManyToOne
	private UserEntity user;
	
}
