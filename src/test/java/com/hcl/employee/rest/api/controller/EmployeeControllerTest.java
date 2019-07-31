package com.hcl.employee.rest.api.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
		
		assertEquals(1000, employee.getBody().getEmpId());
	}
	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
