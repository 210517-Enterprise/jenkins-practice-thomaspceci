package com.revature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.revature.model.Employee;
import com.revature.repositories.EmployeeDAOImpl;
import com.revature.service.EmployeeService;

public class ServiceTests {
	
	// Service Layer depends on DAO
	private EmployeeDAOImpl edao;
	
	// Before every test method is run, do this
	@Before
	public void setup() {
		
		edao = mock(EmployeeDAOImpl.class); // Reflection at work
		
		// set the edao of the employee service class = to the mocked dao
		EmployeeService.edao = edao;
		
	}
	
	// Happy Path Scenario test
	@Test
	public void test_Employee_findByUsername() {
		
		Employee emp1 = new Employee(1, "a", "b", "c", "d");
		Employee emp2 = new Employee(2, "a", "b", "c", "d");
		
		List<Employee> list = new ArrayList<>();
		list.add(emp1);
		list.add(emp2);
		
		// we program our dao to return that data as its fake DB data
		when(edao.findAll()).thenReturn(list);
		
		String dummyusername = emp1.getUsername();
		
		// findByUsername()
		Employee returned = EmployeeService.findByUsername(dummyusername);
		
		assertEquals(emp1, returned);
	}
	
	@Test
	public void employeeNotPresentInDb() {
		
		List<Employee> list = new ArrayList<>();
		
		when(edao.findAll()).thenReturn(list);
		
		Employee empFound = EmployeeService.findByUsername("test");
		
		// check that it returns null if the user doesn't exist
		assertNull(empFound);
		
	}
	
	
	
	
	
	
	
	
	
	

}
