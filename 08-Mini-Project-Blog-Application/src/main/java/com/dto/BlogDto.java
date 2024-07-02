package com.dto;

import java.time.LocalDate;

import com.entity.UserEntity;

import lombok.Data;

@Data
public class BlogDto {
	private Integer blogId;
	private String blogTitle;
	private String blogShortDesc;
	private String blogContent;
	private Integer blogFlag;
	private UserEntity user;
	private LocalDate crtnTime;
	private LocalDate modTime;
}
