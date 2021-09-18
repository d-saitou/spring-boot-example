package com.example.springboot.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Configure authentication and authorization.
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * Configure WEB security.
	 */
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/webjars/**", "/js/**", "/css/**");
	}

	/**
	 * Configure user authentication and authorization.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll();
	}

}
