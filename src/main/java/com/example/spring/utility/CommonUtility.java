package com.example.spring.utility;

import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * Common utility.
 */
public class CommonUtility {

	/**
	 * Private constructor.
	 */
	private CommonUtility() {
	}

	/**
	 * Get the stack trace string.
	 * @param e Exception
	 * @return stack trace string
	 */
	public static String getStackTraceString(Throwable e) {
		return ExceptionUtils.getStackTrace(e);
	}

}
