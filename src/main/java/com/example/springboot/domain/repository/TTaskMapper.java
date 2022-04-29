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
	 * Select by task id.
	 * @param taskId task id.
	 * @return TTask entity.
	 */
	TTask findByTaskIdEquals(@Param("taskId") Integer taskId);

	/**
	 * Select by user id.
	 * @param createdBy user id.
	 * @return TTask entities.
	 */
	List<TTask> findByCreatedByEquals(@Param("createdBy") String createdBy);

	/**
	 * Select by user id and count after pagination.
	 * @param createdBy user id.
	 * @return record count after pagnation.
	 */
	int countByCreatedByEquals(@Param("createdBy") String createdBy);

	/**
	 * Select by user id and pagination.
	 * @param createdBy user id.
	 * @param size      number of content data per page.
	 * @param offset    number of start records for pagenation.
	 * @return TTask entities.
	 */
	List<TTask> findPageByCreatedByEquals(
			@Param("createdBy") String createdBy, @Param("size") int size, @Param("offset") int offset);

	/**
	 * Insert.
	 * @param entities TTask entities.
	 */
	void create(@Param("entities") List<TTask> entities);

	/**
	 * Update.
	 * @param entities TTask entities.
	 */
	void update(@Param("entities") List<TTask> entities);

	/**
	 * Delete.
	 * @param taskIdList task id.
	 * @return Number of deleted records.
	 */
	int delete(@Param("taskIdList") List<Integer> taskIdList);

}
