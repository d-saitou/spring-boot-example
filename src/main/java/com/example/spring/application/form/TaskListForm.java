package com.example.spring.application.form;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Task data list form.
 */
@Data
@Accessors(chain = true)
@SuppressFBWarnings(
		value = { "EI_EXPOSE_REP", "EI_EXPOSE_REP2" },
		justification = "Allow to set array values of form by Spring.")
public class TaskListForm implements Serializable {

	private static final long serialVersionUID = 1L;

	@Valid
	private List<TaskForm> tasklist;

}
