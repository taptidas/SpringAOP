package com.example.springaop.aop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
	
		   @ExceptionHandler(value = EmployeeNotPresent.class)
		   public ResponseEntity<Object> exception(EmployeeNotPresent exception) {
		      return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
		   }
		}

