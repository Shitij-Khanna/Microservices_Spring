package com.appsdeveloperblog.api.books.configuration;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectsBooksMicroservice {

	// This aspect is added to add debug log for invocation of every method on
	// BooksController
	@Before("execution(* com.appsdeveloperblog.api.books.io.controllers.BookController.*(..))")
	public void before(JoinPoint joinPoint) {
		System.out.print("AOP advice --  Method invocation started : " + joinPoint.getSignature().getName() + "-");
		System.out.println("Arguments : " + Arrays.toString(joinPoint.getArgs()));
	}

	// This aspect is added to add debug logging after execution of every method on
	// BooksController
	@After("execution(* com.appsdeveloperblog.api.books.io.controllers.BookController.*(..))")
	public void after(JoinPoint joinPoint) {
		System.out.print("AOP advice --  Method execution finished: " + joinPoint.getSignature().getName() + "-");
		System.out.println("Arguments : " + Arrays.toString(joinPoint.getArgs()));
	}

	// This aspect is added to add debug logging to show what has been returned
	// after execution of every method on BooksController
	@AfterReturning(pointcut = "execution(* com.appsdeveloperblog.api.books.io.controllers.BookController.*(..))", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		System.out.println("Result is : " + result);
	}

}
