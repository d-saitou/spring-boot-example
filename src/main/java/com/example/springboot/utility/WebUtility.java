package com.example.springboot.utility;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpMethod;
import org.springframework.web.util.HtmlUtils;

/**
 * Web utility.
 */
public class WebUtility {

	/**
	 * Private constructor.
	 */
	private WebUtility() {
	}

	/**
	 * Escape HTML.
	 * @param str String before conversion
	 * @return String after conversion
	 */
	public static String escapeHtml(String str) {
		return HtmlUtils.htmlEscape(str);
	}

	/**
	 * Judge whether the request method is GET.
	 * @param request HttpServletRequest
	 * @return True if it is a GET request
	 */
	public static boolean isGetRequest(HttpServletRequest request) {
		return HttpMethod.GET.matches(request.getMethod());
	}

	/**
	 * Judge whether the request method is POST.
	 * @param request HttpServletRequest
	 * @return True if it is a POST request
	 */
	public static boolean isPostRequest(HttpServletRequest request) {
		return HttpMethod.POST.matches(request.getMethod());
	}

}
