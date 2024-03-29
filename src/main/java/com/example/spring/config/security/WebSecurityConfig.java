package com.example.spring.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.example.spring.config.AppConstants;

import lombok.RequiredArgsConstructor;

/**
 * Configure authentication and authorization.
 */
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsServiceImpl userDetailsService;

	/**
	 * Register PasswordEncoder as a bean.
	 * @return PasswordEncoder object.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Configure user authentication.
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

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
		String[] uriPermitAll = AppConstants.URI_PERMIT_ALL.toArray(new String[0]);
		String[] uriUsersPage = AppConstants.URI_USERS_PAGE.toArray(new String[0]);
		String[] uriAdminPage = AppConstants.URI_ADMIN_PAGE.toArray(new String[0]);
		http.authorizeRequests()
				.antMatchers(uriPermitAll).permitAll()
				.antMatchers(uriUsersPage).access("hasAuthority('USERS')")
				.antMatchers(uriAdminPage).access("hasAuthority('ADMIN')")
//				.anyRequest().authenticated()
				.anyRequest().denyAll()
			.and()
			.formLogin()
				.loginPage("/login")
				.usernameParameter("username")
				.passwordParameter("password")
				.loginProcessingUrl("/login")
				// Use AuthenticationSuccessHandler instead of defaultSuccessUrl().
//				.defaultSuccessUrl("/main", true)
				.successHandler(new AuthenticationSuccessHandlerImpl())
				.failureUrl("/login?error=true")
			.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)
			.and()
			.sessionManagement()
				.sessionFixation().newSession()
				.invalidSessionUrl("/login")
			.and()
			.csrf()
			.and()
			.exceptionHandling()
				.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
				.accessDeniedPage("/denied");
//			.and()
//			.headers()
//				.defaultsDisabled() // Disable default security headers.
//				.cacheControl().disable()
//				.frameOptions().sameOrigin()
//				.contentTypeOptions().disable()
//				.xssProtection().disable()
//				.httpStrictTransportSecurity().disable()
	}

}
