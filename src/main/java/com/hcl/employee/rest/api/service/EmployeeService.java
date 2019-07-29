package com.hcl.employee.rest.api.service;

import com.hcl.employee.rest.api.entity.Employee;

public interface EmployeeService {
	
	public Employee addEmployee(Employee employee);
	
	public Employee getEmployeeById(long empId);
	
	public Employee updateEmployee(long empId,double salary);

}
