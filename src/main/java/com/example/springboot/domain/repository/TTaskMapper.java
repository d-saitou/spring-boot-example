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
	 * Select by user id and count after pagination.
	 * @param userId user id.
	 * @return record count after pagnation.
	 */
	int countByUserIdEquals(@Param("userId") String userId);

	/**
	 * Select by user id and pagination.
	 * @param userId user id.
	 * @param size   number of content data per page.
	 * @param offset number of start records for pagenation.
	 * @return TTask entities.
	 */
	List<TTask> findPageByUserIdEquals(
			@Param("userId") String userId, @Param("size") int size, @Param("offset") int offset);

	/**
	 * Insert.
	 * @param entities TTask entities.
	 */
	void create(@Param("entities") List<TTask> entities);

}
