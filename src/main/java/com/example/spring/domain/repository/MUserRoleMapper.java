package com.example.spring.domain.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.spring.domain.entity.MUserRole;

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
