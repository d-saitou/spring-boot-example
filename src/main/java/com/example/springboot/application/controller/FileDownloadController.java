package com.example.springboot.application.controller;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import com.example.springboot.config.AppProperties;
import com.example.springboot.config.security.UserDetailsImpl;
import com.example.springboot.domain.service.TaskExcelManageService;
import com.example.springboot.utility.WebUtility;

import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.RequiredArgsConstructor;

/**
 * File download controller.
 */
@Controller
@RequestMapping("/file/download")
@RequiredArgsConstructor
@SuppressFBWarnings(value = "EI_EXPOSE_REP2")
public class FileDownloadController {

	private final AppProperties props;

	private final MessageSource msg;

	private final TaskExcelManageService service;

	/**
	 * GET request.
	 * @param response    HttpServletResponse.
	 * @param locale      locale.
	 * @param userDetails UserDetailsImpl object.
	 * @throws IOException
	 */
	@GetMapping
	public void get(
			HttpServletResponse response, Locale locale,
			@AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
		String path = service.generateExcelFileAbsolutePath();
		String downloadFileName = props.getContent().getDownloadFileName();
		try {
			service.txCreateExcelFile(path, userDetails.getUsername(), locale);
			WebUtility.setFileDownloadResponse(response, path, downloadFileName, props.getCharset());
		} catch (Exception e) {
			String message = msg.getMessage("FileDownload.msg.failed", null, locale);
			WebUtility.setAlertWindowResponse(response, message, props.getCharset());
		} finally {
			service.deleteExcelFile(path);
		}
	}

}
