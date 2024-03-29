package com.example.spring.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.spring.domain.entity.TScheduledTaskHistory;

/**
 * MyBatis repository (view: t_scheduledtask_history).
 */
@Mapper
public interface TScheduledTaskHistoryMapper {

	/**
	 * Insert.
	 * @param entity TScheduledTaskHistory entity.
	 */
	void create(TScheduledTaskHistory entity);

	/**
	 * Select all task history order by id.
	 * @return entities.
	 */
	List<TScheduledTaskHistory> findAllOrderById();

}
