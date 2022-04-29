package com.example.spring.application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.example.spring.application.form.TaskForm;
import com.example.spring.application.form.TaskPageForm;
import com.example.spring.application.helper.TaskListHelper;
import com.example.spring.config.AppProperties;
import com.example.spring.config.security.UserDetailsImpl;
import com.example.spring.domain.entity.TTask;
import com.example.spring.domain.service.TaskManageService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Task bulk update screen controller.
 */
@Controller
public class TaskBulkUpdateController extends TaskListController {

	/**
	 * Constructor.
	 * @param props   AppProperties bean.
	 * @param service TaskManageService bean.
	 * @param helper  TaskListHelper bean.
	 */
	public TaskBulkUpdateController(
			AppProperties props, TaskManageService service, TaskListHelper helper) {
		super(props, service, helper);
	}

	/**
	 * GET request.
	 * @param page        request page no.
	 * @param userDetails UserDetailsImpl object.
	 * @param model       Model object.
	 * @param locale      Locale object..
	 * @return HTML template path.
	 */
	@Override
	@GetMapping("/task/bulk/list")
	public String get(
			@RequestParam("page") int page,
			@AuthenticationPrincipal UserDetailsImpl userDetails,
			Model model, Locale locale) {
		String userid = userDetails.getUsername();
		model.addAttribute("taskPageForm", super.getTaskPage(page, locale, userid));
		return "content/TaskBulkUpdate";
	}

	/**
	 * POST request.
	 *
	 * <p>NOTE:<br>
	 * When using validation with a controller, the order of the methods must be as
	 * follows. If you do not follow the above rules, you will get a 400 error.<br>
	 * methodName(form, BindingResult, Model)
	 * @param form   TaskPageForm.
	 * @param result BindingResult object.
	 * @param model  Model object.
	 * @param locale Locale object.
	 * @return redirect uri.
	 */
	@PostMapping("/task/bulk/update")
	public String update(
			@Validated @ModelAttribute TaskPageForm form,
			BindingResult result, Model model, Locale locale) {
		if (result.hasErrors()) {
			model.addAttribute("taskPageForm", form);
			return "content/TaskBulkUpdate";
		} else {
			// convert forms to entities
			List<TTask> updateTasks = new ArrayList<TTask>();
			List<Integer> deleteTasks = new ArrayList<Integer>();
			for (TaskForm task : form.getTasklist()) {
				if (task.isUpdateFlg()) {
					updateTasks.add(super.helper.convertFormToEntity(task, locale));
				}
				if (task.isDeleteFlg()) {
					deleteTasks.add(task.getTaskId());
				}
			}
			// update and delete tasks
			super.service.txUpdateTasks(updateTasks);
			super.service.txDeleteTasks(deleteTasks);
			// redirect to task list
			int page = form.getCurrentPage();
			return "redirect:/task/bulk/list?page=" + Integer.valueOf(page);
		}
	}

}