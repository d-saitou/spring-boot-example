package com.example.springboot.application.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.springboot.application.validation.annotation.DateFormatCheckForString;

import lombok.Data;

/**
 * Task data form.
 */
@Data
public class TaskForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@Size(min = 2, max = 100)
	private String title;

	@DateFormatCheckForString(format = { "uuuu/MM/dd", "MM-dd-uuuu" })
	private String scheduleDate;

	@NotNull
	private boolean status;

	@NotNull
	@Size(max = 200)
	private String description;

	private String userId;

	private boolean updateFlg;

	private boolean deleteFlg;

}
