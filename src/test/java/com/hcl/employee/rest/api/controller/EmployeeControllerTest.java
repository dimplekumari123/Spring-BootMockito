package com.hcl.employee.rest.api.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.employee.rest.api.entity.Employee;
import com.hcl.employee.rest.api.service.ImplEmployeeService;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = { TestContext.class, EmployeeController.class })
@WebAppConfiguration
public class EmployeeControllerTest {
	
	@InjectMocks
	EmployeeController employeeController;
	
	@Mock
	ImplEmployeeService implEmployeeService;
	
	private MockMvc mockMvc;
	
	Employee emp;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
		//create employee object
		emp= new Employee(1000, "Raja", "Bengaluru", "Private job", 899999);
	}

	@Test
	public void addEmployeeTest() throws Exception {
		//mock data in service implementation
		Mockito.when(implEmployeeService.addEmployee(Mockito.any())).thenReturn(emp);
		//invoke end point from controller
		this.mockMvc.perform(
				post("/rest/employee/add").contentType(MediaType.APPLICATION_JSON).content(asJsonString(emp))).andReturn();
		
		//call service from controller
		ResponseEntity<Employee> employee = employeeController.addEmployee(emp);
		//test data
		assertEquals(1000, employee.getBody().getEmpId());
	}
	
	@Test
	public void getEmployeeByIdTest() throws Exception {
		//mock data in service implementation
		Mockito.when(implEmployeeService.getEmployeeById(Mockito.anyLong())).thenReturn(emp);
		//invoke end point from controller
		this.mockMvc.perform(
				get("/rest/employee/getEmployee/{empId}",Mockito.anyLong()).contentType(MediaType.APPLICATION_JSON).content(asJsonString(emp.getEmpId()))).andReturn();
		
		//call service from controller
		ResponseEntity<Employee> employee = employeeController.getEmployeeById(emp.getEmpId());
		
		assertEquals("Raja", employee.getBody().getEmpName());
	}
	
	@Test
	public void updateEmployeeTest() throws Exception {
		//mock data in service implementation
		Mockito.when(implEmployeeService.updateEmployee(Mockito.anyLong(), Mockito.anyDouble())).thenReturn(emp);
		//invoke end point from controller
		this.mockMvc.perform(
				put("/rest/employee/updateEmployee").contentType(MediaType.APPLICATION_JSON).content(asJsonString(emp.getEmpId()))).andReturn();
		
		//call service from controller
		ResponseEntity<Employee> employee = employeeController.updateEmployee(1000, 677565);
		
		assertEquals("Raja", employee.getBody().getEmpName());
	}
	
	@Test
	public void getAllEmployeeTest() throws Exception {
		//mock data in service implementation
		List<Employee> list_emp = new ArrayList<>();
		list_emp.add(emp);
		Mockito.when(implEmployeeService.getAllEmployees()).thenReturn(list_emp);
		//invoke end point from controller
		this.mockMvc.perform(
				get("/rest/employee/allEmployee").contentType(MediaType.APPLICATION_JSON).content(asJsonString(emp))).andReturn();
		
		//call service from controller
		ResponseEntity<List<Employee>> employee = employeeController.getAllEmployee();
		
		assertEquals(1, employee.getBody().size());
	}
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
