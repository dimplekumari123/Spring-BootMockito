package com.hcl.employee.rest.api.controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
	}

}
