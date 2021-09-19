package com.example.springboot.application.form;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * User information form.
 */
@Data
@Accessors(chain = true)
public class UserForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;

	private String userName;

	private String emailAddress1;

	private String emailAddress2;

	private boolean readonly;

	private boolean enable;

	private boolean updateFlg;

}
