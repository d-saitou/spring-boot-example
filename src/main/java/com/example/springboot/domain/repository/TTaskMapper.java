package com.example.springboot.domain.repository;

import java.util.List;

import com.example.springboot.domain.entity.TTask;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
	void create(@Param("entities") List<TTask> entities);

}
