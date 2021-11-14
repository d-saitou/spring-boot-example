package com.example.springboot.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * MyBatis entity (table: t_task).
 */
@Data
@Accessors(chain = true)
public class TTask implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String title;

	private LocalDate scheduleDate;

	private Boolean status;

	private String description;

	private String userId;

}
