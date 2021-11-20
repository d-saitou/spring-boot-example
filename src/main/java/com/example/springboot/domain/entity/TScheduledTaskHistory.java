package com.example.springboot.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * MyBatis entity (table: t_scheduledtask_history).
 */
@Data
@Accessors(chain = true)
public class TScheduledTaskHistory implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String method;

	private String message;

	private LocalDateTime updateDate;

}
