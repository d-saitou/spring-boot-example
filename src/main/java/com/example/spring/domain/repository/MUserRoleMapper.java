package com.example.spring.domain.repository;

import com.example.spring.domain.entity.MUserRole;

import org.apache.ibatis.annotations.Mapper;

/**
 * MyBatis repository (table: m_user_role).
 */
@Mapper
public interface MUserRoleMapper {

	/**
	 * Insert.
	 * @param entity MUserRole entity.
	 * @return result.
	 */
	boolean create(MUserRole entity);

	/**
	 * Delete by user id.
	 * @param userId user id.
	 * @return result.
	 */
	int deleteByUserId(String userId);

}
