package com.example.springboot.application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.example.springboot.application.form.TaskForm;
import com.example.springboot.application.helper.TaskListHelper;
import com.example.springboot.config.security.UserDetailsImpl;
import com.example.springboot.domain.entity.TTask;
import com.example.springboot.domain.service.TaskManageService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

/**
 * Task update screen controller.
 */
@Controller
@RequiredArgsConstructor
public class TaskUpdateController {

	private final TaskManageService service;

	private final TaskListHelper helper;

	/**
	 * POST request.
	 * @param model  Model object.
	 * @param locale Locale object.
	 * @param page   request page no.
	 * @param id     task id.
	 * @return HTML template path.
	 */
	@GetMapping("/task/update/{id}")
	public String get(Model model, Locale locale,
			@RequestParam(name = "page", required = false) String page,
			@PathVariable(name = "id", required = false) Integer id) {
		TTask task = service.txGetTaskByTaskId(id);
		model.addAttribute("taskForm", helper.convertEntityToForm(task, locale));
		model.addAttribute("page", page);
		return "content/TaskUpdate";
	}

	/**
	 * POST request.
	 * @param page        request page no.
	 * @param form        TaskForm.
	 * @param result      BindingResult object.
	 * @param model       Model object.
	 * @param locale      Locale object.
	 * @param userDetails UserDetailsImpl object.
	 * @return redirect uri.
	 */
	@PostMapping("/task/update")
	public String post(
			@RequestParam(name = "page", required = false) String page,
			@Validated @ModelAttribute TaskForm form,
			BindingResult result, Model model, Locale locale,
			@AuthenticationPrincipal UserDetailsImpl userDetails) {
		if (result.hasErrors()) {
			model.addAttribute("taskForm", form);
			model.addAttribute("page", page);
			return "content/TaskUpdate";
		} else {
			form.setUserId(userDetails.getUsername());
			List<TTask> entities = new ArrayList<TTask>();
			entities.add(helper.convertFormToEntity(form, locale));
			service.txUpdateTasks(entities);
			return "redirect:/task/list?page=" + page;
		}
	}

}
