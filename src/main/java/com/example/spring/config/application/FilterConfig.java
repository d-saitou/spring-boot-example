package com.example.spring.config.application;

import com.example.spring.config.filter.LogMDCPutFilter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure the filter.
 */
@Configuration
public class FilterConfig {

	/**
	 * Register a filter to put data to MDC.
	 * @return FilterRegistrationBean object.
	 */
	@Bean
	public FilterRegistrationBean<LogMDCPutFilter> logMDCPutFilter() {
		FilterRegistrationBean<LogMDCPutFilter> bean = new FilterRegistrationBean<>();
		bean.setFilter(new LogMDCPutFilter());
		bean.addUrlPatterns("/*");
		// bean.setOrder(1);
		return bean;
	}

}
