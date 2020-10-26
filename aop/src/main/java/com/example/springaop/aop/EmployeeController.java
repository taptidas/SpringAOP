package com.example.springaop.aop;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
		@Autowired
		EmployeeService empService;
		
		@GetMapping("/")
		public Collection<Employee> getAll() {
			return empService.getAllEmployees();
		}
		
		@GetMapping("/{id}")
			public ResponseEntity<ResponseEntity<Optional<Employee>>> getEmployeeById(@PathVariable(value = "id") Long employeeId) throws EmployeeNotPresent
			{
			return ResponseEntity.ok().body(empService.getEmployeeById(employeeId));
			}
		@PostMapping("/")
		public Employee createEmployeecon(@RequestBody Employee employee) throws DuplicateUserException {
		
			return empService.createEmployee(employee);

		}

	}

	
	
	

