package com.appsdeveloperblog.api.books.configuration;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectsBooksMicroservice {

	private static final Logger log = LoggerFactory.getLogger(AspectsBooksMicroservice.class);
	// Commented the @before and @after notations for the methods down below because
	// we do not want to call all these methods from various cases. They are just
	// for reference and show different possibilities

	@Pointcut("execution(* com.appsdeveloperblog.api.books.io.controllers.BookController.*(..))")
	public void allMethodsOfBooksController() {
		// This method defines a pointcut with the name , which is the name of the
		// method, i.e. allMethodsOfBooksController
		// The pointcut is defined to be executed on all methods of BookController
		// class, as per the definition of the pointcut in the annotation
		// Whenever we want to create an advice on this this pointcut, we can just refer
		// to this method , i.e. allMethodsOfBooksController() in the @before / @after
		// or
		// whatever annotation we use in the advice
	}

//	@Pointcut("within(com.appsdeveloperblog.api.books.io.controllers.BookController)")
	public void allMethodsOfBooksControllerUsingWithin() {
		// This method defines a pointcut with the name , which is the name of the
		// method, i.e. allMethodsOfBooksControllerUsingWithin
		// The pointcut is defined to be executed on all methods of BookController
		// class, as per the definition of the pointcut in the annotation

		// using within makes it easier to understand that all methods within this class
		// have been selected in the pointcut, rather than using execution
	}

//	@Pointcut("execution(* com.appsdeveloperblog.api.books..*)")
	public void allClassesOfBooksMicroservice() {
		// The pointcut is defined to be executed on all classes in
		// all the packages under com.appsdeveloperblog.api.books

		// .. means 0 or more packages inside books

	}

	// args can take classname or interfaces names as
	// parameters to denote what type
	// of arguments the pointcut has to expect
//	@Pointcut("args(com.appsdeveloperblog.api.books.data.Book)")
	public void methodsWithArgumentBook() {
		// The pointcut is defined to be executed on all methods which take Book class /
		// interface as a paramter
	}

	// This aspect is added to add debug log for invocation of every method on
	// BooksController
	@Before("allMethodsOfBooksController()")
	public void before(JoinPoint joinPoint) {
		System.out.print("AOP advice --  Method invocation started : " + joinPoint.getSignature().getName() + "-");
		System.out.println("Arguments : " + Arrays.toString(joinPoint.getArgs()));
		log.info("AOP advice --  Method invocation started : {0} with arguments {1}",
				joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
	}

	// This aspect is added to add debug logging after execution of every method on
	// BooksController
	@After("execution(* com.appsdeveloperblog.api.books.io.controllers.BookController.*(..))")
	public void after(JoinPoint joinPoint) {
		System.out.print("AOP advice --  Method execution finished: " + joinPoint.getSignature().getName() + "-");
		System.out.println("Arguments : " + Arrays.toString(joinPoint.getArgs()));
		log.info("AOP advice --  Method invocation finished : {0} with arguments {1}",
				joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
		// debug log not working
	}

	// This aspect is added to add debug logging to show what has been returned
	// after execution of every method on BooksController
	// execution
	// -- first * notates the return type, so it means methods of any return type
	// -- BookController.* means all methods of BookController class and (.. means
	// with 0 or more arguments)
	@AfterReturning(pointcut = "execution(* com.appsdeveloperblog.api.books.io.controllers.BookController.*(..))", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		System.out.println("Result is : " + result);
//		System.out.println("JoinPoint" + joinPoint.getTarget()); 
//		BookController controller = (BookController) joinPoint.getTarget();
//		controller.getBooks(); // gettarget will get the target which we can use to perform other actions
		log.info("AOP advice --  Method invocation for {0} returned result  : {1} ",
				joinPoint.getSignature().getName(), result);
	}

	// This aspect is added to add debug log for invocation of every method on
	// BooksController
//	@Before("allMethodsOfBooksController() && methodsWithArgumentBook()")
	public void beforeMethodsOfBookControllerWithArgumentBooks(JoinPoint joinPoint) {
		System.out.print("AOP advice --  Method invocation started : " + joinPoint.getSignature().getName() + "-");
		System.out.println("Arguments : " + Arrays.toString(joinPoint.getArgs()));
	}

	// Executed before all public getter methods - notated by get*, of any return
	// type ,
	// notated by return type *, but without any arguments because there is no
	// argument in the method notation
//	@Before("execution(public * get*())")
	public void beforePublicGetterMethodsWithNoArgs(JoinPoint joinPoint, Object result) {
		System.out.println(
				"AOP advice -- Getter Method invocation started : " + joinPoint.getSignature().getName() + "-");
	}

	// Executed before all getter methods, public or private - notated by get*, of
	// any return type , but without any arguments because there is no argument in
	// the method notation
	// notated by return type *
//	@Before("execution(public * get*())")
	public void beforeAllGetterMethodsWithNoArgs(JoinPoint joinPoint, Object result) {
		System.out.println(
				"AOP advice -- Getter Method invocation started : " + joinPoint.getSignature().getName() + "-");
	}

	// Executed before all getter methods, public or private - notated by get*, of
	// any return type ,
	// notated by return type * // get*(..) notates getter methods with 0 or more
	// arguments
//	 @Before("execution(public * get*(..))")
	public void beforeAllGetterMethodsWithArgs(JoinPoint joinPoint, Object result) {
		System.out.println(
				"AOP advice -- Getter Method invocation started : " + joinPoint.getSignature().getName() + "-");
		System.out.println("Arguments : " + Arrays.toString(joinPoint.getArgs()));
	}

	// Executed before all getter methods, public or private - notated by get*, of
	// any return type ,
	// notated by return type * // get*(*) notates getter methods with 1 or more
	// arguments
//	 @Before("execution(public * get*(*))")
	public void beforeAllGetterMethodsWithOneOrMoreArgs(JoinPoint joinPoint, Object result) {
		System.out.println(
				"AOP advice -- Getter Method invocation started : " + joinPoint.getSignature().getName() + "-");
		System.out.println("Arguments : " + Arrays.toString(joinPoint.getArgs()));
	}

	// This aspect is added to add debug log for invocation of every method on
	// the classes in this package
	// @Before("execution(*
	// com.appsdeveloperblog.api.books.io.controllers.*.*(..))")
	public void beforeAllMethods(JoinPoint joinPoint) {
		System.out.print("AOP advice --  Method invocation started : " + joinPoint.getSignature().getName() + "-");
		System.out.println("Arguments : " + Arrays.toString(joinPoint.getArgs()));
	}

}
