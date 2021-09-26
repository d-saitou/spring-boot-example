package com.example.springboot.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller to test error handling.
 */
@Controller
public class ExceptionTestController {

	/**
	 * Test error handling for screen displays.
	 * @throws NullPointerException NullPointerException
	 */
	@GetMapping("/exception/html")
	public void exceptionTestForHtml() throws NullPointerException {
		throw new NullPointerException("Throw a null pointer exception to test error handling.");
	}

	/**
	 * Display screen to test REST error handling.
	 */
	@GetMapping("/exception/rest")
	public String exceptionTestForRest() {
		return "content/ExceptionTestForRest";
	}

}
