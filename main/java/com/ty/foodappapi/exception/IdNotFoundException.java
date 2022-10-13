package com.ty.foodappapi.exception;

public class IdNotFoundException extends RuntimeException{
	
	private String message="Given id doesnt exist";

	public String getMessage() {
		return message;
	}
	public IdNotFoundException() {
		
	}
	public IdNotFoundException(String message) {
		this.message = message;
	}
	
	
	
	

}
