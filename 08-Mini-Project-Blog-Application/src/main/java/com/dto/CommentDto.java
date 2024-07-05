package com.dto;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.entity.BlogEntity;

import lombok.Data;

@Component
@Data
public class CommentDto implements Serializable{

	private Integer commentId;
	private String name;
	private String email;
	private String comment;
	private Integer deleteFlag;
	private LocalDate crtnTime;
	private LocalDate updtTime;
	private Integer blogid;
	private BlogEntity blog;
}
