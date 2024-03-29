package com.example.spring.domain.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.spring.config.AppProperties;
import com.example.spring.domain.service.ScheduledTaskService;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.RequiredArgsConstructor;

/**
 * Component that registers scheduled tasks.
 */
@Component
@RequiredArgsConstructor
@SuppressFBWarnings(value = "EI_EXPOSE_REP2")
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
	@Scheduled(
			initialDelayString = "${application.scheduler.initialDelay}",
			fixedDelayString = "${application.scheduler.fixedDelay}")
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
