package com.example.springboot.utility;

import java.util.List;

/**
 * String utility.
 */
public class StringUtility {

	/**
	 * Private constructor.
	 */
	private StringUtility() {
	}

	/**
	 * Checks if a CharSequence is empty ("") or null.
	 * @param str String
	 * @return true if the CharSequence is empty or null
	 */
	public static boolean isEmpty(String str) {
		return (str == null || str.length() == 0 ? true : false);
	}

	/**
	 * Checks if a CharSequence is not empty ("") or null.
	 * @param str String
	 * @return true if the CharSequence is not empty or null
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * Convert a string list to a string array.
	 * @param list string list
	 * @return string array
	 */
	public static String[] convertListToStringArray(List<String> list) {
		return (String[]) list.toArray(new String[0]);
	}

}
