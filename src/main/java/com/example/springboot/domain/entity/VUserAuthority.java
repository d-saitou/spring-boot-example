package com.example.springboot.domain.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * MyBatis entity (view: v_user_authority).
 */
@Data
@Accessors(chain = true)
public class VUserAuthority implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;

	private String authorityName;

}