package com.example.springboot.domain.repository;

import com.example.springboot.domain.entity.TTask;

import org.apache.ibatis.annotations.Mapper;

/**
 * MyBatis repository (table: t_task).
 */
@Mapper
public interface TTaskMapper {

	/**
	 * Insert.
	 * @param entity TTask entity.
	 * @return result.
	 */
	boolean create(TTask entity);

}
