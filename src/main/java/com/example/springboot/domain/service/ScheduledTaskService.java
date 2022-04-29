package com.example.springboot.domain.service;

import java.util.List;

import com.example.springboot.domain.entity.TScheduledTaskHistory;
import com.example.springboot.domain.repository.TScheduledTaskHistoryMapper;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service that saves the execution history of scheduled tasks.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduledTaskService {

	private final TScheduledTaskHistoryMapper repo;

	/**
	 * Save task history.
	 * @param methodName method name of scheduled task.
	 */
	public void txSaveTaskHistory(String methodName) {
		TScheduledTaskHistory history = new TScheduledTaskHistory();
		history.setMethod(methodName);
		history.setMessage("");
		log.info("Scheduled task execute. [method : {}()]", methodName);
		try {
			this.repo.create(history);
		} catch (Exception e) {
			log.error("Scheduled task failed. [method : {}()]", methodName, e);
			throw e;
		}
	}

	/**
	 * Get all task history.
	 * @return entities.
	 */
	public List<TScheduledTaskHistory> txGetAllTaskHistory() {
		return repo.findAllOrderById();
	}

}
