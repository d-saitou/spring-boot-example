package com.example.springboot.application.validation.component;

import com.example.springboot.application.form.UserRegistForm;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Custom validator for user registration screen.
 */
@Component
public class UserRegistFormValidator implements Validator {

	/**
	 * Validate supported classes.
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		// return UserRegistForm.class.equals(clazz);
		return UserRegistForm.class.isAssignableFrom(clazz);
	}

	/**
	 * Validate UserRegistForm instances.
	 */
	@Override
	public void validate(Object obj, Errors e) {
		UserRegistForm form = (UserRegistForm) obj;
		if (!form.getPassword().equals(form.getRetypePassword())) {
			e.rejectValue("retypePassword", "UserRegist.msg.error.notMatchPassword");
		}
		if (!form.getAgreeTeams().booleanValue()) {
			e.rejectValue("agreeTeams", "UserRegist.msg.error.notAgree");
		}
	}

}
