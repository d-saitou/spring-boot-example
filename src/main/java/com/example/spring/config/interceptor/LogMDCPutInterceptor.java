package com.example.spring.config.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.spring.config.AppConstants;

import org.slf4j.MDC;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

/**
 * Interceptor that puts the login user to MDC.
 * As multiple implementation examples, this application puts MDC by the following classes
 * and outputs request information to the log.
 * <ul><li>
 *  {@link com.example.spring.config.filter.LogMDCPutFilter LogMDCPutFilter}
 *  : Put IP address to MDC.
 * </li><li>
 *  {@link com.example.spring.config.interceptor.LogMDCPutInterceptor LogMDCPutInterceptor}
 *  : Put login user into MDC.
 * </li></ul>
 */
public class LogMDCPutInterceptor implements AsyncHandlerInterceptor {

	/**
	 * Processing before processing the controller method.
	 */
	@Override
	public boolean preHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler) {
		MDC.put(AppConstants.MDC_KEY_USER_ID, request.getRemoteUser());
		return true;
	}

	/**
	 * Processing after processing the controller method.
	 */
	@Override
	public void afterConcurrentHandlingStarted(
			HttpServletRequest request, HttpServletResponse response, Object handler) {
		// MDC.clear();
	}

	/**
	 * Processing after request processing is completed.
	 */
	@Override
	public void afterCompletion(
			HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		// MDC.clear();
	}

}
