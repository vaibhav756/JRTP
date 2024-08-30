package com.exceptions;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class AppException {

	private String exCode;
	private String exDesc;
	private LocalDateTime date;
	
}
