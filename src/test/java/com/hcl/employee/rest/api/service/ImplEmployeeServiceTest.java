package com.hcl.employee.rest.api.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.employee.rest.api.customException.EmployeeDoesNotExistException;
import com.hcl.employee.rest.api.entity.Employee;
import com.hcl.employee.rest.api.repository.EmployeeRepository;

@RunWith(MockitoJUnitRunner.class)
public class ImplEmployeeServiceTest {

	@InjectMocks
	ImplEmployeeService implEmployeeService;
	
	@Mock
	EmployeeRepository employeeRepository;
	
	Employee emp;
	
	@Before
	public void setData()
	{
		emp= new Employee(1000, "Raja", "Bengaluru", "Private job", 899999);
	}
	
	@Test
	public void addEmployeeTest()
	{
		Mockito.when(employeeRepository.save(Mockito.any())).thenReturn(emp);
		
		Employee employee = implEmployeeService.addEmployee(emp);
		
		assertEquals(emp.getEmpName(), employee.getEmpName());
	}
	
	@Test
	public void getEmployeeByIdTest_P()
	{
		
		Mockito.when(employeeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(emp));
		
		Employee employee = implEmployeeService.getEmployeeById(1000);
		assertEquals(emp.getEmpName(), employee.getEmpName());
	}
	
	//@Test(expected=EmployeeDoesNotExistException.class)
	public void getEmployeeByIdTest_N()
	{	
		Mockito.when(employeeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(emp));
		
		Employee employee = implEmployeeService.getEmployeeById(10000);
		assertEquals(emp.getEmpName(), employee.getEmpName());
	}
	
	
	@Test
	public void updateEmployeeTest()
	{
		Mockito.when(employeeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(emp));
		Mockito.when(employeeRepository.save(Mockito.any())).thenReturn(emp);
		
		Employee employee = implEmployeeService.updateEmployee(1000, 56565);
		
		assertEquals(1000, employee.getEmpId());
	}
	
	@Test
	public void getAllEmployeesTest()
	{
		List<Employee> list = new ArrayList<>();
		list.add(emp);
		Mockito.when(employeeRepository.findAll()).thenReturn((list));
		List<Employee> emp_list = implEmployeeService.getAllEmployees();
		assertEquals(1, emp_list.size());
	}
	
}
