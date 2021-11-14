package com.example.springboot.application.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.example.springboot.domain.service.TaskManageService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

/**
 * Task delete controller.
 */
@Controller
@RequiredArgsConstructor
public class TaskDeleteController {

	private final TaskManageService service;

	/**
	 * GET request.
	 * @param page request page no.
	 * @param id   task id.
	 * @return redirect uri.
	 * @throws ParseException
	 */
	@GetMapping("/task/delete/{id}")
	public String delete(
			@RequestParam(name = "page", required = false) int page,
			@PathVariable(name = "id", required = false) int id) throws ParseException {
		List<Integer> idList = new ArrayList<Integer>();
		idList.add(id);
		service.txDeleteTasks(idList);
		return "redirect:/task/list?page=" + Integer.valueOf(page);
	}

}
