package com.example.spring.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.spring.config.AppConstants;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Handler on successful authentication.
 */
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

	/**
	 * Processing when authentication is successful.
	 */
	@Override
	public void onAuthenticationSuccess(
			HttpServletRequest req, HttpServletResponse res, Authentication authentication)
			throws IOException, ServletException {

		// Set session timeout from authenticated UserDetails.
		HttpSession session = req.getSession(false);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		if (userDetails.getSessiontimeout() == null) {
			session.setMaxInactiveInterval(AppConstants.DEFAULT_SESSION_TIMEOUT);
		} else {
			session.setMaxInactiveInterval(userDetails.getSessiontimeout().intValue());
		}

		// Redirect to URI when authentication is successful.
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		redirectStrategy.sendRedirect(req, res, "/main");
	}

}
