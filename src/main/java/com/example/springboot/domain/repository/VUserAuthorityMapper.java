package com.example.springboot.domain.repository;

import java.util.List;

import com.example.springboot.domain.entity.VUserAuthority;

import org.apache.ibatis.annotations.Mapper;

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
