package com.example.springaop.aop;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	public Employee findByEmailId(String emailId);

}
