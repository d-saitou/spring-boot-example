package com.example.springboot.domain.scheduler;

import com.example.springboot.config.AppProperties;
import com.example.springboot.domain.service.ScheduledTaskService;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/**
 * Component that registers scheduled tasks.
 */
@Component
@RequiredArgsConstructor
public class ScheduledTasks {

	private final AppProperties props;

	private final ScheduledTaskService service;

	/**
	 * fixedRate task.
	 */
	@Scheduled(fixedRateString = "${application.scheduler.fixedRate}")
	public void fixedRateTask() {
		if (props.getScheduler().getEnable()) {
			service.txSaveTaskHistory("fixedRateTask");
		}
	}

	/**
	 * initialDelay and fixedDelay task.
	 */
	@Scheduled(initialDelayString = "${application.scheduler.initialDelay}", fixedDelayString = "${application.scheduler.fixedDelay}")
	public void initialAndFixedDelayTask() {
		if (props.getScheduler().getEnable()) {
			service.txSaveTaskHistory("initialAndFixedDelayTask");
		}
	}

	/**
	 * cron task.
	 */
	@Scheduled(cron = "${application.scheduler.cron}")
	public void cronTask() {
		if (props.getScheduler().getEnable()) {
			service.txSaveTaskHistory("cronTask");
		}
	}

}
