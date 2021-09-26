package com.example.springboot.application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller to test error handling.
 */
@RestController
@RequestMapping("/exception/rest/api")
public class ExceptionTestRestController {

	/**
	 * Test error handling for REST.
	 * @throws NullPointerException NullPointerException
	 */
	@GetMapping
	public void exceptionTestForRest() throws NullPointerException {
		throw new NullPointerException("Throw a null pointer exception to test error handling.");
	}

}
