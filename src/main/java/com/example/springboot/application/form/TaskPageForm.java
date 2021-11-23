package com.example.springboot.application.form;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Data;

/**
 * Task paging data form.
 */
@Data
@SuppressFBWarnings(
		value = { "EI_EXPOSE_REP", "EI_EXPOSE_REP2" },
		justification = "Allow to set array values of form by Spring.")
public class TaskPageForm implements Serializable {

	private static final long serialVersionUID = 1L;

	@Valid
	private List<TaskForm> tasklist;

	private int currentPage;

	private int totalPages;

	private boolean hasPreviousPage;

	private boolean hasNextPage;

}
