package com.example.springaop.aop;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Service
public class EmployeeService {

	Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	//public static Map<Long,Employee> repo=new HashMap<>();

	@Autowired
	private EmployeeRepository employeeRepository;

//get all
	public Collection <Employee> getAllEmployees() {
		logger.debug("Entering getAllEmployees method");
		return (List<Employee>) employeeRepository.findAll();
	}

//get by id
	public ResponseEntity<Optional<Employee>> getEmployeeById(Long employeeId) throws EmployeeNotPresent
	{
	Optional<Employee> employee = employeeRepository.findById(employeeId);
	logger.info("Checking if employee exists!");
	if(employee==null)
	{
		logger.error("No such employee exists!");
		throw new EmployeeNotPresent();	
	}
	
	return ResponseEntity.ok().body(employee);
	}


//post 
	public Employee createEmployee(Employee employee) throws DuplicateUserException {
		Optional<Employee> employee1 = Optional.ofNullable(employeeRepository.findByEmailId(employee.getEmailId()));

		logger.info("Checking if employee already exists!");
		if(employee1.isPresent())
		{	
			logger.error("employee already exists!");
			throw new DuplicateUserException();
		}

		return employeeRepository.saveAndFlush(employee);
		//employeeRepository.flush();
		//return emp;

	}

	//@Scheduled(cron="*/5 * * * * ?")
	/**public void demoServiceMethod()
	{
		System.out.println("Method executed at every 5 seconds. Current time is :: "+ new Date());
	}**/


	//@PutMapping("/{id}")
	public ResponseEntity < Employee > updateEmployee(@PathVariable(value = "id") Long employeeId,
			@RequestBody Employee employeeDetails) {
		logger.trace("Entering updateEmployees method");
		Optional<Employee> employee = employeeRepository.findById(employeeId);

		logger.info("Checking if employee exists!");
		if(!employee.isPresent())
			return (ResponseEntity<Employee>) ResponseEntity.notFound();

		employeeDetails.setId(employeeId);
		Employee updatedEmployee = employeeRepository.save(employeeDetails);
		logger.trace("employee updated");
		return ResponseEntity.ok(updatedEmployee);
	}


	//@DeleteMapping("/{id}")
	public Map < String, Boolean > deleteEmployee(@PathVariable(value = "id") Long employeeId)
	{logger.trace("Entering deleteEmployees method");

	//Employee employee =employeeRepository.findById(employeeId);
	employeeRepository.deleteById(employeeId);
	Map < String, Boolean > response = new HashMap < > ();
	response.put("deleted", Boolean.TRUE);

	logger.trace("employee deleted");
	return response;
	}


}


