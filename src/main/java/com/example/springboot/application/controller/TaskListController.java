package com.example.springboot.application.controller;

import java.util.Locale;

import com.example.springboot.application.form.TaskPageForm;
import com.example.springboot.application.helper.TaskListHelper;
import com.example.springboot.config.AppProperties;
import com.example.springboot.config.security.UserDetailsImpl;
import com.example.springboot.domain.entity.TTask;
import com.example.springboot.domain.helper.Page;
import com.example.springboot.domain.service.TaskManageService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

/**
 * Task list screen controller.
 */
@Controller
@RequiredArgsConstructor
public class TaskListController {

	protected final AppProperties props;

	protected final TaskManageService service;

	protected final TaskListHelper helper;

	/**
	 * GET request.
	 * @param page        request page no.
	 * @param userDetails UserDetailsImpl object.
	 * @param model       Model object.
	 * @param locale      Locale object..
	 * @return HTML template path.
	 */
	@GetMapping("/task/list")
	public String get(
			@RequestParam("page") int page,
			@AuthenticationPrincipal UserDetailsImpl userDetails,
			Model model, Locale locale) {
		String userid = userDetails.getUsername();
		model.addAttribute("taskPageForm", getTaskPage(page, locale, userid));
		return "content/TaskList";
	}

	/**
	 * Get the form of the paged task.
	 * @param page   request page no.
	 * @param locale Locale object.
	 * @param userId user id.
	 * @return task page form.
	 */
	protected TaskPageForm getTaskPage(int page, Locale locale, String userId) {
		// get task page
		int size = props.getContent().getPageSize();
		int pageNo = page - 1;
		Page<TTask> taskPage = service.txGetTaskPagingListByUserid(userId, size, pageNo);
		// create form
		TaskPageForm form = new TaskPageForm();
		form.setTasklist(helper.convertEntityToForm(taskPage.getContent(), locale));
		form.setCurrentPage(page);
		form.setTotalPages(taskPage.getTotalPages());
		form.setHasPreviousPage(taskPage.hasPrevious());
		form.setHasNextPage(taskPage.hasNext());
		return form;
	}

}
