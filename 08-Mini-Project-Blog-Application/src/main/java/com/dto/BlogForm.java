package com.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BlogForm {
	private Integer blogid;
	private String blogtitle;
	private String blogshortdesc;
	private String blogcontent;
	private Integer blogflag;
	private LocalDate crtntime;
	private LocalDate modtime;
}
