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
	private String charset;

	@NotEmpty
	private Boolean aopMethodLogging;

	@NotEmpty
	private String mailAddress;

	@NotEmpty
	private String dataDirectory;

	@NotEmpty
	private Content content;

	@NotEmpty
	private Scheduler scheduler;

	/**
	 * properties: application.content.*
	 */
	@Data
	public static class Content {

		@NotEmpty
		private int pageSize;

		@NotEmpty
		private String downloadFileName;

	}

	/**
	 * properties: application.scheduler.*
	 */
	@Data
	public static class Scheduler {

		@NotEmpty
		private Boolean enable;
	
	}

}
