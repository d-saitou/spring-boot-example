package com.example.spring.domain.entity.base;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * Base entity with creation date and time and modification date and time as audit
 * properties. Automatically set audit properties with
 * {@link com.example.spring.domain.aop.EntityAuditInterceptor EntityAuditInterceptor}.
 */
@Getter
@Setter
public abstract class AbstractBaseDateOnlyEntity {

	private LocalDateTime createdDate;

	private LocalDateTime modifiedDate;

}
