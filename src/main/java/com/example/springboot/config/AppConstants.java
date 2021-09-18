package com.example.springboot.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Application constants.
 */
public class AppConstants {

	/** configuration : session time out. */
	public static final int DEFAULT_SESSION_TIMEOUT = 300;

	/** security : URI to permit for all users. */
	public static final List<String> URI_PERMIT_ALL;
	static {
		List<String> list = new ArrayList<String>();
		list.add("/");
		list.add("/login");
		list.add("/logout");
		URI_PERMIT_ALL = Collections.unmodifiableList(list);
	};

	/** security : URI to permit for USERS authority. */
	public static final List<String> URI_USERS_PAGE;
	static {
		List<String> list = new ArrayList<String>();
		list.add("/main");
		URI_USERS_PAGE = Collections.unmodifiableList(list);
	};

	/** security : URI to permit for ADMIN authority. */
	public static final List<String> URI_ADMIN_PAGE;
	static {
		List<String> list = new ArrayList<String>();
		URI_ADMIN_PAGE = Collections.unmodifiableList(list);
	};

}
