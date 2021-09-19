package com.example.springboot.domain.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * MyBatis entity (table: m_user_role).
 */
@Data
@Accessors(chain = true)
public class MUserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;

	private String roleId;

	private String description;

}
