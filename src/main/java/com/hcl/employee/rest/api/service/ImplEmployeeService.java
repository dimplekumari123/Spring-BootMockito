package com.hcl.employee.rest.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.employee.rest.api.customException.EmployeeDoesNotExistException;
import com.hcl.employee.rest.api.entity.Employee;
import com.hcl.employee.rest.api.repository.EmployeeRepository;

@Service
public class ImplEmployeeService implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public Employee addEmployee(Employee employee) {
		
		return employeeRepository.save(employee);
	}

	@Override
	public Employee getEmployeeById(long empId) {
		
	Optional<Employee> optionalEmp =	employeeRepository.findById(empId);
	if(optionalEmp.isPresent())
	{
		return optionalEmp.get();
	}
	else
	{
		throw new EmployeeDoesNotExistException("Employee with id "+empId+" does not exist");
	}
	}

	@Override
	public Employee updateEmployee(long empId, double salary) {
		
		Optional<Employee> optionalEmp = employeeRepository.findById(empId);
		if(optionalEmp.isPresent())
		{
			Employee employee =  optionalEmp.get();
			employee.setSalary(salary);
			return employeeRepository.save(employee);
		}
		else
		{
			throw new EmployeeDoesNotExistException("Employee with id "+empId+" does not exist");
		}
	}

	@Override
	public List<Employee> getAllEmployees() {
		
		   
		return employeeRepository.findAll();
	}

}
