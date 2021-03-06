package com.example.spring.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;

import com.example.spring.domain.entity.base.AbstractBaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * MyBatis entity (table: m_user).
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class MUser extends AbstractBaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;

	private String userName;

	private String password;

	private String emailAddress1;

	private String emailAddress2;

	private String gender;

	private String nationality1;

	private String nationality2;

	private LocalDate dateOfBirth;

	private String address;

	private String creditCardNo;

	private LocalDate creditCardExpirationDate;

	private String passwordHint;

	private String passwordHintAnswer;

	private Integer sessionTimeout;

	private boolean emailNewsletter1;

	private boolean emailNewsletter2;

	private boolean readonly;

	private boolean enabled;

}
