package com.example.springboot.application.form;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * User list screen form.
 */
@Data
@Accessors(chain = true)
public class UserListForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<UserForm> userList;

}
