package com.example.springboot.config.application;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configure Spring Web MVC.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	/**
	 * Configure view controllers.
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/main").setViewName("main");
	}

}
