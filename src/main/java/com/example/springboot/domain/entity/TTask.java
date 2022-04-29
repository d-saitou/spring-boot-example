package com.example.springboot.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;

import com.example.springboot.domain.entity.base.AbstractBaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * MyBatis entity (table: t_task).
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class TTask extends AbstractBaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer taskId;

	private String title;

	private LocalDate scheduledDate;

	private Boolean completion;

	private String description;

}
