package com.example.springboot.domain.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import com.example.springboot.config.AppProperties;
import com.example.springboot.domain.entity.TTask;
import com.example.springboot.domain.repository.TTaskMapper;
import com.example.springboot.utility.FileUtility;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service to manage Excel files for download.
 */
@Service
//@SessionScope
@RequiredArgsConstructor
@Slf4j
@SuppressFBWarnings(value = "EI_EXPOSE_REP2")
public class TaskExcelManageService {

	private final AppProperties props;

	private final MessageSource msg;

	private final TTaskMapper repo;

	/**
	 * Generate temporary excel file path.
	 * @return excel file path.
	 */
	public String generateExcelFileAbsolutePath() {
		StringBuilder path = new StringBuilder();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuuMMddHHmmssSSS");
		return path
				.append(FileUtility.getSystemTempDirectoryPath() + File.separator)
				.append(LocalDateTime.now().format(dtf) + ".")
				.append(props.getContent().getDownloadFileName()).toString();
	}

	/**
	 * Create temporary excel file.
	 * @param filePath excel file path.
	 * @param userid   user id.
	 * @param locale   Locale.
	 * @throws Exception
	 */
	public void txCreateExcelFile(String filePath, String userid, Locale locale) throws Exception {
		Workbook wb = null;
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(filePath);
			wb = new XSSFWorkbook();
			Row row = null;
			// Create sheet
			String safeName = WorkbookUtil.createSafeSheetName("Taskdata");
			Sheet sheet = wb.createSheet(safeName);
			// Set header to sheet
			row = sheet.createRow(0);
			row.createCell(0).setCellValue(msg.getMessage("FileDownload.label.excel.taskId", null, locale));
			row.createCell(1).setCellValue(msg.getMessage("FileDownload.label.excel.title", null, locale));
			row.createCell(2).setCellValue(msg.getMessage("FileDownload.label.excel.scheduledDate", null, locale));
			row.createCell(3).setCellValue(msg.getMessage("FileDownload.label.excel.completion", null, locale));
			row.createCell(4).setCellValue(msg.getMessage("FileDownload.label.excel.description", null, locale));
			row.createCell(5).setCellValue(msg.getMessage("FileDownload.label.excel.userId", null, locale));
			// Set task items to sheet
			List<TTask> taskList = this.repo.findByCreatedByEquals(userid);
			String dateFormat = msg.getMessage("common.format.date", null, locale);
			String status = msg.getMessage("FileDownload.label.excel.completion", null, locale);
			int cnt = 1;
			for (TTask task : taskList) {
				final String date = task.getScheduledDate().format(DateTimeFormatter.ofPattern(dateFormat));
				row = sheet.createRow(cnt);
				row.createCell(0).setCellValue(task.getTaskId().intValue());
				row.createCell(1).setCellValue(task.getTitle());
				row.createCell(2).setCellValue(date);
				row.createCell(3).setCellValue(task.getCompletion() ? status : "");
				row.createCell(4).setCellValue(task.getDescription());
				row.createCell(5).setCellValue(task.getCreatedBy());
				cnt++;
			}
			// Write to file
			wb.write(fileOut);
		} catch (IOException e) {
			log.error("Failed to create excel file. [file:" + filePath + "]", e);
			throw e;
		} catch (Exception e) {
			log.error("Failed to get task data.", e);
			throw e;
		} finally {
			try {
				fileOut.close();
			} catch (Exception expected) {
			}
			try {
				wb.close();
			} catch (Exception expected) {
			}
		}
	}

	/**
	 * Delete temporary excel file.
	 * @param filePath excel file path.
	 * @return success:true, failure:false
	 */
	public boolean deleteExcelFile(String filePath) {
		boolean isSuccess = true;
		File file = new File(filePath);
		if (file.exists()) {
			isSuccess = file.delete();
			if (!isSuccess) {
				log.error("Failed to delete excel file. [file:" + filePath + "]");
			}
		}
		return isSuccess;
	}

}
