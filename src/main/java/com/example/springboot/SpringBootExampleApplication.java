package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * Spring Boot application main.
 */
@SpringBootApplication
@ConfigurationPropertiesScan
public class SpringBootExampleApplication {

	/**
	 * main.
	 * @param args arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringBootExampleApplication.class, args);
	}

}
