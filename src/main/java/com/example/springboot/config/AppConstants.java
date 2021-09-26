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

	// @formatter:off
	/**
	 * configuration : pointcut expression for transaction advice.
	 * <ul>
	 * <li>Package class of the hierarchy under "com.example.springmvc.domain.di.service".</li>
	 * <li>Classes and methods for which Transactional annotation is not set.</li>
	 * <li>The access modifier is public.</li>
	 * <li>The method name prefix is "tx".</li>
	 * </ul>
	 */
	public static final String TRANSACTION_POINTCUT_EXPRESSION =
			"execution(public * com.example.springboot.domain.service..*.tx*(..)) and "
			//+ "@target(org.springframework.stereotype.Service) and "
			+ "not @annotation(org.springframework.transaction.annotation.Transactional) and "
			+ "not @target(org.springframework.transaction.annotation.Transactional)";
	// @formatter:on

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
		list.add("/user/list");
		list.add("/user/delete/*");
		URI_ADMIN_PAGE = Collections.unmodifiableList(list);
	};

}
