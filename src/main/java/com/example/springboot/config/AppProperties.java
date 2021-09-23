package com.example.springboot.config;

import javax.validation.constraints.NotEmpty;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * Load the properties of application.yml.
 */
@ConfigurationProperties(prefix = "application")
@Data
public class AppProperties {

	@NotEmpty
	private String mailAddress;

}
