package com.example.springboot.domain.entity.base;

import lombok.Getter;
import lombok.Setter;

/**
 * Base entity with authenticated user and creation date and time and modification date
 * and time as audit properties. Automatically set audit properties with
 * {@link com.example.springboot.domain.aop.EntityAuditInterceptor EntityAuditInterceptor}.
 */
@Getter
@Setter
public abstract class AbstractBaseEntity extends AbstractBaseDateOnlyEntity {

	private String createdBy;

	private String modifiedBy;

}
