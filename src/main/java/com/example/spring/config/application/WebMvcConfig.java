package com.example.spring.config.application;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.spring.config.interceptor.AppCallableProcessingInterceptor;
import com.example.spring.config.interceptor.AppDeferredResultProcessingInterceptor;
import com.example.spring.config.interceptor.LogMDCPutInterceptor;

import lombok.RequiredArgsConstructor;

/**
 * Configure Spring Web MVC.
 */
@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

	private final MessageSource messageSource;

	private final AsyncTaskExecutor taskExecutor;

	/**
	 * Configure validator (JSR-303 Bean Validation) and register as bean.
	 * @return Validator object.
	 */
	@Override
	public Validator getValidator() {
		final LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(messageSource);
		return validator;
	}

	/**
	 * Configure view controllers.
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/main").setViewName("main");
	}

	/**
	 * Configure interceptor.
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LogMDCPutInterceptor());
	}

	/**
	 * Configure asynchronous request handling options.
	 * @see WebMvcConfigurer#configureAsyncSupport(AsyncSupportConfigurer)
	 */
	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		configurer.setTaskExecutor(taskExecutor);
		configurer.setDefaultTimeout(60000);
		configurer.registerCallableInterceptors(new AppCallableProcessingInterceptor());
		configurer.registerDeferredResultInterceptors(new AppDeferredResultProcessingInterceptor());
	}

}
