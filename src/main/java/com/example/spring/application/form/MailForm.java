package com.example.spring.application.form;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Send mail screen form.
 */
@Data
@AllArgsConstructor
public class MailForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String from;

	@NotEmpty
	private String to;

	private String subject;

	@NotEmpty
	private String body;

}