package com.hcl.employee.rest.api.customException;

public class EmployeeDoesNotExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmployeeDoesNotExistException(String message)
	{
		super(message);
	}

}
