package com.example.spring.domain.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring.config.AppProperties;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.RequiredArgsConstructor;

/**
 * Service to manage multipart files.
 */
@Service
//@SessionScope
@RequiredArgsConstructor
@SuppressFBWarnings(value = "EI_EXPOSE_REP2")
public class MultipartFileManageService {

	private final AppProperties props;

	/**
	 * Save files to localhost.
	 * @param files MultipartFile[] object.
	 * @return List of saved file paths.
	 * @throws IOException
	 */
	public List<String> saveMultipartFiles(MultipartFile[] files) throws IOException {
		List<String> list = new ArrayList<String>();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuuMMddHHmmssSSS");
		String date = LocalDateTime.now().format(dtf);
		for (MultipartFile file : files) {
			String filename = date + "_" + file.getOriginalFilename();
			file.transferTo(new File(props.getDataDirectory(), filename));
			list.add(props.getDataDirectory() + File.separator + filename);
		}
		return list;
	}

}
