package com.example.spring.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.spring.domain.entity.MUser;

/**
 * MyBatis repository (table: m_user).
 */
@Mapper
public interface MUserMapper {

	/**
	 * Select all order by user id.
	 * @return user entities.
	 */
	List<MUser> findAllByOrderByUserIdAsc();

	/**
	 * Select by user id.
	 * @param userId user id.
	 * @return user entity.
	 */
	MUser findByUserId(String userId);

	/**
	 * Insert.
	 * @param entity MUser entity.
	 * @return result.
	 */
	boolean create(MUser entity);

	/**
	 * Update enabled by user id.
	 * @param entity MUser entity.
	 * @return result.
	 */
	boolean updateEnabledByUserId(MUser entity);

	/**
	 * Delete by user id.
	 * @param userId user id.
	 * @return result.
	 */
	boolean deleteByUserId(String userId);

}
