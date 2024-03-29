package com.example.spring.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.spring.domain.entity.VUserAuthority;

/**
 * MyBatis repository (view: v_user_authority).
 */
@Mapper
public interface VUserAuthorityMapper {

	/**
	 * Select by user id.
	 * @param userId user id.
	 * @return user and authority entities.
	 */
	List<VUserAuthority> findByUserId(String userId);

}
