package com.example.spring.domain.aop;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.List;

import com.example.spring.utility.StringUtility;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

/**
 * Aspect component that sets audit properties for database entities.
 */
@Aspect
@Component
public class EntityAuditInterceptor {

	/**
	 * Set the authenticated user and modification date and time in the audit properties.
	 * @param jp JoinPoint object.
	 * @throws Throwable
	 */
	@Before("execution(public * com.example.spring.domain.repository.*Mapper.create*(..)) || "
			+ "execution(public * com.example.spring.domain.repository.*Mapper.update*(..))")
	public void setAuditProperties(JoinPoint jp) throws Throwable {
		MethodSignature signature = (MethodSignature) jp.getSignature();
		Method method = signature.getMethod();

		// Get the entity from the arguments.
		Object[] args = jp.getArgs();
		Object entity = args[0];

		// Get an authenticated user.
		String user = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			user = ((UserDetails) authentication.getPrincipal()).getUsername();
		}

		// Get the current date and time.
		LocalDateTime now = LocalDateTime.now();

		if (method.getName().startsWith("create")) {
			// If the method name prefix is create, set audit properties at creation and
			// modification.
			if (entity instanceof List<?>) {
				for (Object item : (List<?>) entity) {
					setCreatedProperty(item, user, now);
					setModifiedProperty(item, user, now);
				}
			} else {
				setCreatedProperty(entity, user, now);
				setModifiedProperty(entity, user, now);
			}
		} else if (method.getName().startsWith("update")) {
			// If the method name prefix is update, set audit properties at modification.
			if (entity instanceof List<?>) {
				for (Object item : (List<?>) entity) {
					setModifiedProperty(item, user, now);
				}
			} else {
				setModifiedProperty(entity, user, now);
			}
		}
	}

	/**
	 * Set audit properties at creation.
	 * @param entity entity object.
	 * @param user   authenticated user name.
	 * @param now    current date and time.
	 * @throws Throwable
	 */
	private void setCreatedProperty(Object entity, String user, LocalDateTime now) throws Throwable {
		Method setCreatedBy = ReflectionUtils.findMethod(entity.getClass(), "setCreatedBy", String.class);
		if (setCreatedBy != null && StringUtility.isNotEmpty(user)) {
			setCreatedBy.invoke(entity, user);
		}
		Method setCreatedDate = ReflectionUtils.findMethod(entity.getClass(), "setCreatedDate", LocalDateTime.class);
		if (setCreatedDate != null) {
			setCreatedDate.invoke(entity, now);
		}
	}

	/**
	 * set audit properties at modification.
	 * @param entity entity object.
	 * @param user   authenticated user name.
	 * @param now    current date and time.
	 * @throws Throwable
	 */
	private void setModifiedProperty(Object entity, String user, LocalDateTime now) throws Throwable {
		Method setModifiedBy = ReflectionUtils.findMethod(entity.getClass(), "setModifiedBy", String.class);
		if (setModifiedBy != null && StringUtility.isNotEmpty(user)) {
			setModifiedBy.invoke(entity, user);
		}
		Method setModifiedDate = ReflectionUtils.findMethod(entity.getClass(), "setModifiedDate", LocalDateTime.class);
		if (setModifiedDate != null) {
			setModifiedDate.invoke(entity, now);
		}
	}

}
