package com.example.spring.config.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.MDC;

import com.example.spring.config.AppConstants;

/**
 * Filter that puts the request IP address to MDC.
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
public class LogMDCPutFilter implements Filter {

	/**
	 * Filter processing.
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			MDC.put(AppConstants.MDC_KEY_REMOTE_ADDR, request.getRemoteAddr());
			chain.doFilter(request, response);
		} finally {
			MDC.remove(AppConstants.MDC_KEY_REMOTE_ADDR);
		}
	}

}
