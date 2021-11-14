package com.example.springboot.domain.service;

import java.util.List;

import com.example.springboot.domain.entity.TTask;
import com.example.springboot.domain.helper.Page;
import com.example.springboot.domain.repository.TTaskMapper;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * Service that manages tasks as an example of DB-CRUD.
 */
@Service
//@SessionScope
@RequiredArgsConstructor
public class TaskManageService {

	private final TTaskMapper repo;

	/**
	 * Get pagination tasks by user ID.
	 * @param id task id.
	 * @return TTask entity.
	 */
	public TTask txGetTaskById(Integer id) {
		return repo.findByIdEquals(id);
	}

	/**
	 * Get tasks by user ID.
	 * @param userId user id.
	 * @return TTask entities.
	 */
	public List<TTask> txGetTaskByUserid(String userId) {
		return repo.findByUserIdEquals(userId);
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
		int count = repo.countByUserIdEquals(userId);
		List<TTask> content = repo.findPageByUserIdEquals(userId, pageSize, offset);
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
	 * @param idList task id list.
	 */
	public void txDeleteTasks(List<Integer> idList) {
		if (idList.size() > 0) {
			repo.delete(idList);
		}
	}

}
