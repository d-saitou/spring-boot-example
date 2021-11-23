package com.example.springboot.application.form;

import java.io.Serializable;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * User list screen form.
 */
@Data
@Accessors(chain = true)
@SuppressFBWarnings(
		value = { "EI_EXPOSE_REP", "EI_EXPOSE_REP2" },
		justification = "Allow to set array values of form by Spring.")
public class UserListForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<UserForm> userList;

}
