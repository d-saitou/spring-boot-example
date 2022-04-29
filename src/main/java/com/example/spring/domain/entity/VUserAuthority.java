package com.example.spring.domain.entity;

import java.io.Serializable;

import com.example.spring.domain.entity.base.AbstractBaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * MyBatis entity (view: v_user_authority).
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class VUserAuthority extends AbstractBaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;

	private String authorityName;

}
