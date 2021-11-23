package com.example.springboot.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.example.springboot.config.AppProperties;

import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service that manages email sending threads.
 * This class implements Spring's asynchronous processing. This class uses {@link Async}
 * annotation to implement mail sending function as asynchronous processing.
 */
@Service
//@SessionScope
@RequiredArgsConstructor
@Slf4j
@SuppressFBWarnings(value = "EI_EXPOSE_REP2")
public class SendEmailThreadControlService {

	private final ApplicationContext context;

	private final AppProperties props;

	/**
	 * Send emails to all email addresses.
	 * @param toAddrList destination email address list.
	 * @param subject    email subject.
	 * @param body       email body.
	 * @param okUrl      request URL when processing is successful.
	 * @param ngUrl      request URL when processing fails.
	 * @return true if all emails are sent successfully.
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Async
	public CompletableFuture<String> sendMails(
			String[] toAddrList, String subject, String body, String okUrl, String ngUrl)
			throws InterruptedException, ExecutionException {
		log.info("control thread - start.");

		// Send emails asynchronously by destination.
		List<CompletableFuture<Boolean>> futures = new ArrayList<CompletableFuture<Boolean>>();
		for (String toAddr : toAddrList) {
			// Get a bean for each email address and assign a thread
			SendEmailService service = context.getBean(SendEmailService.class);
			futures.add(service.sendMail(toAddr, props.getMailAddress(), subject, body));
		}

		// Wait for all threads to complete.
		CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).join();
		log.info("control thread - completed.");

		// Return the result of sending all emails.
		for (CompletableFuture<Boolean> future : futures) {
			if (!future.get().booleanValue()) {
				return CompletableFuture.completedFuture(ngUrl);
			}
		}
		return CompletableFuture.completedFuture(okUrl);
	}

}
