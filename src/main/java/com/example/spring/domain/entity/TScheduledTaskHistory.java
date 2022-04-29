package com.example.spring.domain.entity;

import java.io.Serializable;

import com.example.spring.domain.entity.base.AbstractBaseDateOnlyEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * MyBatis entity (table: t_scheduledtask_history).
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class TScheduledTaskHistory extends AbstractBaseDateOnlyEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String method;

	private String message;

}
