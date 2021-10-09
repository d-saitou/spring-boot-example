package com.example.springboot.application.form;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * File upload screen form.
 */
@Data
public class FileUploadForm {

	private MultipartFile[] file;

}
