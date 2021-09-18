package com.example.springboot.domain.repository;

import com.example.springboot.domain.entity.MUser;

import org.apache.ibatis.annotations.Mapper;

/**
 * MyBatis repository (table: m_user).
 */
@Mapper
public interface MUserMapper {

	/**
	 * Select by user id.
	 * @param userId user id.
	 * @return user entity.
	 */
	MUser findByUserId(String userId);

}
