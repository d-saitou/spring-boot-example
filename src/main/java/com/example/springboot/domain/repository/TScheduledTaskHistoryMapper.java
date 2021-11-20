package com.example.springboot.domain.repository;

import java.util.List;

import com.example.springboot.domain.entity.TScheduledTaskHistory;

import org.apache.ibatis.annotations.Mapper;

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
