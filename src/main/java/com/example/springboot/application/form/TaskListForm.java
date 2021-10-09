package com.example.springboot.application.form;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Task data list form.
 */
@Data
@Accessors(chain = true)
public class TaskListForm implements Serializable {

	private static final long serialVersionUID = 1L;

	@Valid
	private List<TaskForm> tasklist;

}
