package com.example.spring.application.controller;

import java.util.Locale;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.spring.application.form.TaskPageForm;
import com.example.spring.application.helper.TaskListHelper;
import com.example.spring.config.AppProperties;
import com.example.spring.config.security.UserDetailsImpl;
import com.example.spring.domain.entity.TTask;
import com.example.spring.domain.helper.Page;
import com.example.spring.domain.service.TaskManageService;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.RequiredArgsConstructor;

/**
 * Task list screen controller.
 */
@Controller
@RequiredArgsConstructor
@SuppressFBWarnings(value = "EI_EXPOSE_REP2")
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
