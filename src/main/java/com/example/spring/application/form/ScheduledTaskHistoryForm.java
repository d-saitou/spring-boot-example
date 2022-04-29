package com.example.spring.application.form;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * Scheduled task history screen form.
 */
@Data
public class ScheduledTaskHistoryForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String method;

	private String message;

	private LocalDateTime createdDate;

}
