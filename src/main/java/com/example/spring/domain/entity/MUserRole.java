package com.example.spring.domain.entity;

import java.io.Serializable;

import com.example.spring.domain.entity.base.AbstractBaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * MyBatis entity (table: m_user_role).
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class MUserRole extends AbstractBaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;

	private String roleId;

	private String description;

}
