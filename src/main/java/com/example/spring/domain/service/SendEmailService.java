package com.example.spring.domain.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service that sends emails by asynchronous processing.
 * This class implements Spring's asynchronous processing. This class uses {@link Async}
 * annotation to implement mail sending function as asynchronous processing.
 * Parameters for sending mail are stored in application.properties and referenced by
 * {@link Value} annotation.
 */
@Service
//@SessionScope
@RequiredArgsConstructor
@Slf4j
public class SendEmailService {

	private final MailSender mailSender;

	/**
	 * Send email by asynchronous processing.
	 * @param toAddr   destination email address.
	 * @param fromAddr sender email address.
	 * @param subject  email subject.
	 * @param body     email body.
	 * @return CompletableFuture
	 */
	@Async
	public CompletableFuture<Boolean> sendMail(
			String toAddr, String fromAddr, String subject, String body) {
		log.info("email sending thread - start. (address:" + toAddr + ")");
		boolean isSuccess = false;
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(toAddr);
		mail.setFrom(fromAddr);
		mail.setSubject(subject);
		mail.setText(body);
		try {
			mailSender.send(mail);
			isSuccess = true;
			log.info("email sending thread - completed. (address:" + toAddr + ")");
		} catch (MailException e) {
			log.info("email sending thread - failed. (address:" + toAddr + ")");
		}
		return CompletableFuture.completedFuture(Boolean.valueOf(isSuccess));
	}

}
