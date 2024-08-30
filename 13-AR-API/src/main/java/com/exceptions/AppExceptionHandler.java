package com.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(value=SsnWebException.class)
	public ResponseEntity<AppException> handleSsnWebException(SsnWebException ex)
	{
		AppException appex=new AppException();
		appex.setExCode("002");
		appex.setExDesc(ex.getMessage());
		appex.setDate(LocalDateTime.now());
		return new ResponseEntity<>(appex,HttpStatus.OK);
	}
	
}
