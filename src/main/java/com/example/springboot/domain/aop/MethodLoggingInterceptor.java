package com.example.springboot.domain.aop;

import com.example.springboot.config.AppProperties;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Component that implements AOP advice.
 */
@Aspect
@Component
@AllArgsConstructor
@Slf4j
@SuppressFBWarnings(value = "EI_EXPOSE_REP2")
public class MethodLoggingInterceptor {

	private final AppProperties props;

	/**
	 * Named pointcut. (Controller class)
	 */
	@Pointcut("execution(public * com.example.springboot..*Controller.*(..))")
	public void controllerPointCut() {}

	/**
	 * Named pointcut. (Service class)
	 */
	@Pointcut("execution(public * com.example.springboot..*Service*.*(..))")
	public void servicePointCut() {
	}

	/**
	 * Output method start log.
	 * @param jp JoinPoint object.
	 */
	// @Before("execution(public * com.example.springboot.*..*Controller.*(..))")
	@Before("controllerPointCut()") // Named pointcut (single)
	// @Before("controllerPointCut() || servicePointCut()") // Named pointcut (multiple)
	public void startLog(JoinPoint jp) {
		if (props.getAopMethodLogging()) {
			log.info("method start:{}", getMethodName(jp));
		}
	}

	/**
	 * Output method end log.
	 * @param jp JoinPoint object.
	 */
	// @After("execution(public * com.example.springboot..*Controller.*(..))")
	@After("controllerPointCut()") // Named pointcut (single)
	// @After("controllerPointCut() || servicePointCut()") // Named pointcut (multiple)
	public void endLog(JoinPoint jp) {
		if (props.getAopMethodLogging()) {
			log.info("method end:{}", getMethodName(jp));
		}
	}

//	/**
//	 * Output method end log (AfterReturning)
//	 * @param jp JoinPoint object.
//	 */
//	@AfterReturning("execution(public * com.example.springboot..*Controller.*(..))")
//	public void endAfterReturningLog(JoinPoint jp) {
//		if (props.getAopMethodLogging()) {
//			log.info("method end:{}", getMethodName(jp));
//		}
//	}
//
//	/**
//	 * Output method end log. (AfterThrowing)
//	 * @param jp JoinPoint object.
//	 * @param e  Throwable object.
//	 */
//	@AfterThrowing(
//			value = "execution(public * com.example.springboot..*Controller.*(..))", throwing = "e")
//	public void endAfterThrowingLog(JoinPoint jp, Throwable e) {
//		if (props.getAopMethodLogging()) {
//			log.error("method exception end:{}", getMethodName(jp), e);
//		}
//	}
//
//	/**
//	 * Output method start and end log.
//	 * @param jp ProceedingJoinPoint object.
//	 * @return method return value.
//	 * @throws Throwable Throwable object.
//	 */
//	@Around("execution(public * com.example.springboot..*Controller.*(..))")
//	public Object aroundLog(ProceedingJoinPoint jp) throws Throwable {
//		if (props.getAopMethodLogging()) {
//			log.info("method start:{}", getMethodName(jp));
//		}
//		try {
//			if (props.getAopMethodLogging()) {
//				log.info("method end:{}", getMethodName(jp));
//			}
//			Object result = jp.proceed();
//			return result;
//		} catch (Exception e) {
//			if (props.getAopMethodLogging()) {
//				log.error("method exception end:{}", getMethodName(jp), e);
//			}
//			throw e;
//		}
//	}

	/**
	 * Get method name of JoinPoint.
	 * @param jp JoinPoint object.
	 * @return method name.
	 */
	private String getMethodName(JoinPoint jp) {
		return jp.getTarget().getClass().toString()
				.replace("class ", "") + "#" + jp.getSignature().getName() + "()";
	}

}
