package com.exceptions;

public class SsnWebException extends RuntimeException{

	public SsnWebException() {
		
	}
	
	public SsnWebException(String error) {
		super(error);
	}
	
}
