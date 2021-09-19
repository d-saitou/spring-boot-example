package com.example.springboot.config.application;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageSourceConfig {

	/**
	 * Configure MessageSource and register as bean.
	 * @return MessageSource object.
	 */
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
		ms.setBasenames("classpath:i18n/messages", "classpath:i18n/ValidationMessages");
		ms.setFallbackToSystemLocale(false);
		ms.setDefaultEncoding("UTF-8");
		ms.setCacheSeconds(60);
		return ms;
	}

}
