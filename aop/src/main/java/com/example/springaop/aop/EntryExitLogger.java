package com.example.springaop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class EntryExitLogger {
	
	Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Before("execution(* com.example.springaop.aop.EmployeeController.*(..))")
	public void before(JoinPoint joinPoint)
	{
		logger.debug("in before method");
		logger.debug("method executing for {} \n {} \n class: {} \n method: {}",joinPoint,joinPoint.getStaticPart().getSignature().getDeclaringType().getPackage(),joinPoint.getStaticPart().getSignature().getDeclaringType().getSimpleName(),joinPoint.getStaticPart().getSignature().getName());
	}

	@AfterReturning(value = "execution(* com.example.springaop.aop.EmployeeController.*(..))", returning = "result")
	public void afterReturn(JoinPoint joinPoint, Object result)
	{
		logger.debug("{} returned with value {} \n method: {} ", joinPoint, result, joinPoint.getStaticPart().getSignature().getName());
	}
	
	@Around("execution(* com.example.springaop.aop.EmployeeController.*(..))")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();

		Object obj=joinPoint.proceed();

		long timeTaken = System.currentTimeMillis() - startTime;
		logger.info("Time Taken by {} is {}", joinPoint, timeTaken);
		return obj;
	}
}