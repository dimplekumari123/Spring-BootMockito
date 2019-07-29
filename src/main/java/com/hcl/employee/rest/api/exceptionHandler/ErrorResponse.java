package com.hcl.employee.rest.api.exceptionHandler;

import java.util.Date;

public class ErrorResponse {
	
	private Date date;
	private String message;
	private String path;
	private int statusCode;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public ErrorResponse(Date date, String message, String path, int statusCode) {
		super();
		this.date = date;
		this.message = message;
		this.path = path;
		this.statusCode = statusCode;
	}



	

	
	

}
