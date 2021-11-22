package com.example.springboot.config.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import com.example.springboot.utility.CommonUtility;

import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

/**
 * Controller that handles application exceptions.
 */
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
@RequiredArgsConstructor
public class AppErrorController implements ErrorController {

	private final ErrorAttributes errorAttributes;

	private final MessageSource msg;

	/**
	 * Error handling for HTML responses.
	 * @param request HttpServletRequest object.
	 * @param mav     ModelAndView object.
	 * @return ModelAndView object.
	 */
	@RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView errorHtml(HttpServletRequest request, ModelAndView mav, Locale locale) {
		Throwable e = errorAttributes.getError(new ServletWebRequest(request));
		HttpStatus status = getHttpStatus(request);

		mav.setStatus(status);
		mav.setViewName("error");
		mav.addObject("stacktrace", e == null ? "" : CommonUtility.getStackTraceString(e));
		if (status == HttpStatus.NOT_FOUND) {
			mav.addObject("message", msg.getMessage("common.msg.404", null, locale));
		} else {
			mav.addObject("message", msg.getMessage("common.msg.500", null, locale));
		}
		return mav;
	}

	/**
	 * Error handling for REST.
	 * @param request HttpServletRequest object.
	 * @return ResponseEntity object.
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
		Throwable e = errorAttributes.getError(new ServletWebRequest(request));
		HttpStatus status = getHttpStatus(request);

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("status", status.value());
		body.put("path", request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI));
		body.put("exception", e == null ? "" : e.getClass().getCanonicalName());
		body.put("message", e.getMessage());

		return new ResponseEntity<>(body, status);
	}

	/**
	 * Get HTTP status for response.
	 * @param request HttpServletRequest object.
	 * @return HTTP status.
	 */
	private static HttpStatus getHttpStatus(HttpServletRequest request) {
		Integer code = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		HttpStatus status = HttpStatus.resolve(code);
		return (status != null) ? status : HttpStatus.INTERNAL_SERVER_ERROR;
	}

}
