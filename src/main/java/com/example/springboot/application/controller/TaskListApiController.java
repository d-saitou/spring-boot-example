package com.example.springboot.application.controller;

import java.util.List;
import java.util.Locale;

import com.example.springboot.application.form.TaskForm;
import com.example.springboot.application.helper.TaskListHelper;
import com.example.springboot.config.security.UserDetailsImpl;
import com.example.springboot.domain.entity.TTask;
import com.example.springboot.domain.service.TaskManageService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * REST controller to get task list.
 */
@RestController
@RequestMapping("/task/api")
@RequiredArgsConstructor
@Slf4j
public class TaskListApiController {

	private final TaskManageService service;

	private final TaskListHelper helper;

	/**
	 * GET request.
	 * @param userDetails UserDetailsImpl object.
	 * @param locale      Locale object.
	 * @return task form list to convert to JSON.
	 */
	@GetMapping
	public List<TaskForm> get(
			@AuthenticationPrincipal UserDetailsImpl userDetails, Locale locale) {
		List<TaskForm> formList = null;
		try {
			// Get task data
			List<TTask> entityList = service.txGetTaskByUserid(userDetails.getUsername());
			formList = helper.convertEntityToForm(entityList, locale);
		} catch (Exception e) {
			log.error("An exception occurred during DB access.", e);
			throw e; // Exceptions are handled by GlobalExceptionHandler class.
		}
		return formList;
	}

}
