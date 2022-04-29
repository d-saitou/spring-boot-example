package com.example.spring.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Application constants.
 */
public class AppConstants {

	/**
	 * Private constructor.
	 */
	private AppConstants() {}

	/** configuration : session time out. */
	public static final int DEFAULT_SESSION_TIMEOUT = 300;

	// @formatter:off
	/**
	 * configuration : pointcut expression for transaction advice.
	 * <ul>
	 * <li>Package class of the hierarchy under "com.example.spring.domain.service".</li>
	 * <li>Classes and methods for which Transactional annotation is not set.</li>
	 * <li>The access modifier is public.</li>
	 * <li>The method name prefix is "tx".</li>
	 * </ul>
	 */
	public static final String TRANSACTION_POINTCUT_EXPRESSION =
			"execution(public * com.example.spring.domain.service..*.tx*(..)) and "
			//+ "@target(org.springframework.stereotype.Service) and "
			+ "not @annotation(org.springframework.transaction.annotation.Transactional) and "
			+ "not @target(org.springframework.transaction.annotation.Transactional)";
	// @formatter:on

	/** configuration : MDC key name - request id. */
	public static final String MDC_KEY_REMOTE_ADDR = "REMOTE_ADDR";

	/** configuration : MDC key name - user id. */
	public static final String MDC_KEY_USER_ID = "USER_ID";

	/** security : role id - administrator role. */
	public static final String ROLE_ID_FOR_ADMIN = "0001";

	/** security : role id - public role. */
	public static final String ROLE_ID_FOR_USERS = "0002";

	/** security : URI to permit for all users. */
	public static final List<String> URI_PERMIT_ALL;
	static {
		List<String> list = new ArrayList<String>();
		list.add("/");
		list.add("/login");
		list.add("/logout");
		list.add("/user/regist");
		URI_PERMIT_ALL = Collections.unmodifiableList(list);
	};

	/** security : URI to permit for USERS authority. */
	public static final List<String> URI_USERS_PAGE;
	static {
		List<String> list = new ArrayList<String>();
		list.add("/main");
		list.add("/file/upload"); // GET request
		list.add("/file/upload/*"); // POST request
		list.add("/file/download");
		list.add("/task/create");
		list.add("/task/list");
		list.add("/task/delete/*");
		list.add("/task/update"); // POST request
		list.add("/task/update/*"); // GET request
		list.add("/task/bulk/list");
		list.add("/task/bulk/update");
		list.add("/task/api");
		list.add("/task/api/page");
		list.add("/exception/html");
		list.add("/exception/rest");
		list.add("/exception/rest/api");
		list.add("/async/mail");
		URI_USERS_PAGE = Collections.unmodifiableList(list);
	};

	/** security : URI to permit for ADMIN authority. */
	public static final List<String> URI_ADMIN_PAGE;
	static {
		List<String> list = new ArrayList<String>();
		list.add("/schedule/history");
		list.add("/user/list");
		list.add("/user/delete/*");
		URI_ADMIN_PAGE = Collections.unmodifiableList(list);
	};

}
