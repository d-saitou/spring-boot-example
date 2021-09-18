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
	 * Convert a string list to a string array.
	 * @param list string list
	 * @return string array
	 */
	public static String[] convertListToStringArray(List<String> list) {
		return (String[]) list.toArray(new String[0]);
	}

}
