package com.example.springboot.application.form;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import lombok.Data;

/**
 * Task paging data form.
 */
@Data
public class TaskPageForm implements Serializable {

	private static final long serialVersionUID = 1L;

	@Valid
	private List<TaskForm> tasklist;

	private int currentPage;

	private int totalPages;

	private boolean hasPreviousPage;

	private boolean hasNextPage;

}
