package com.example.spring.domain.service;

import java.util.List;

import com.example.spring.domain.entity.TTask;
import com.example.spring.domain.helper.Page;
import com.example.spring.domain.repository.TTaskMapper;

import org.springframework.stereotype.Service;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.RequiredArgsConstructor;

/**
 * Service that manages tasks as an example of DB-CRUD.
 */
@Service
//@SessionScope
@RequiredArgsConstructor
@SuppressFBWarnings(value = "EI_EXPOSE_REP2")
public class TaskManageService {

	private final TTaskMapper repo;

	/**
	 * Get pagination tasks by user ID.
	 * @param taskId task id.
	 * @return TTask entity.
	 */
	public TTask txGetTaskByTaskId(Integer taskId) {
		return repo.findByTaskIdEquals(taskId);
	}

	/**
	 * Get tasks by user ID.
	 * @param userId user id.
	 * @return TTask entities.
	 */
	public List<TTask> txGetTaskByUserid(String userId) {
		return repo.findByCreatedByEquals(userId);
	}

	/**
	 * Get pagination tasks by user ID.
	 * @param userId      user id.
	 * @param pageSize    number of content data per page.
	 * @param currentPage current page number.
	 * @return Page object.
	 */
	public Page<TTask> txGetTaskPagingListByUserid(String userId, int pageSize, int currentPage) {
		int offset = pageSize * currentPage;
		int count = repo.countByCreatedByEquals(userId);
		List<TTask> content = repo.findPageByCreatedByEquals(userId, pageSize, offset);
		return new Page<TTask>(pageSize, currentPage, count, content);
	}

	/**
	 * Create tasks.
	 * @param entities task entity.
	 */
	public void txCreateTasks(List<TTask> entities) {
		if (entities.size() > 0) {
			repo.create(entities);
		}
	}

	/**
	 * Update tasks.
	 * @param entities task entity.
	 */
	public void txUpdateTasks(List<TTask> entities) {
		if (entities.size() > 0) {
			repo.update(entities);
		}
	}

	/**
	 * Delete tasks.
	 * @param taskIdList task id list.
	 */
	public void txDeleteTasks(List<Integer> taskIdList) {
		if (taskIdList.size() > 0) {
			repo.delete(taskIdList);
		}
	}

}
