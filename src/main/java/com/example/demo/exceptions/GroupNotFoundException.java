package com.example.demo.exceptions;
public class GroupNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GroupNotFoundException(String Message) {
		super(Message);
	}

}