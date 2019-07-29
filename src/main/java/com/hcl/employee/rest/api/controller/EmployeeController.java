package com.hcl.employee.rest.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.employee.rest.api.entity.Employee;
import com.hcl.employee.rest.api.service.ImplEmployeeService;

@RestController
@RequestMapping("/rest")
public class EmployeeController {
	
	@Autowired
	ImplEmployeeService implEmployeeService;
	
	@PostMapping("/employee/add")
	public Employee addEmployee(@RequestBody Employee employee)
	{
		return implEmployeeService.addEmployee(employee);
	}
	
	@GetMapping("/employee/getEmployee/{empId}")
	public Employee getEmployeeById(@PathVariable int empId)
	{
		return implEmployeeService.getEmployeeById(empId);
	}
	
	@PutMapping("/employee/updateEmployee/")
	public Employee updateEmployee(@RequestParam("empId") long empId,@RequestParam("salary") double salary)
	{
		return implEmployeeService.updateEmployee(empId, salary);
	}
	@GetMapping("/employee/allEmployee")
	public List<Employee> getAllEmployee(){
		
		return implEmployeeService.getAllEmployees();
	}
}
