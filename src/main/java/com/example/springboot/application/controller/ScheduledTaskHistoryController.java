package com.example.springboot.application.controller;

import java.util.LinkedList;
import java.util.List;

import com.example.springboot.application.form.ScheduledTaskHistoryForm;
import com.example.springboot.domain.entity.TScheduledTaskHistory;
import com.example.springboot.domain.service.ScheduledTaskService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

/**
 * Scheduled task history (inaccessible) screen controller.
 */
@Controller
@RequestMapping("/schedule/history")
@RequiredArgsConstructor
public class ScheduledTaskHistoryController {

	private final ScheduledTaskService service;

	/**
	 * GET request.
	 * @param model Model object.
	 * @return HTML template path.
	 */
	@GetMapping
	public String get(Model model) {
		List<ScheduledTaskHistoryForm> formList = new LinkedList<ScheduledTaskHistoryForm>();
		for (TScheduledTaskHistory entity : service.txGetAllTaskHistory()) {
			ScheduledTaskHistoryForm form = new ScheduledTaskHistoryForm();
			form.setId(entity.getId());
			form.setMethod(entity.getMethod());
			form.setMessage(entity.getMessage());
			form.setUpdatedate(entity.getUpdateDate());
			formList.add(form);
		}
		model.addAttribute("formList", formList);
		return "content/ScheduledTaskHistory";
	}

}