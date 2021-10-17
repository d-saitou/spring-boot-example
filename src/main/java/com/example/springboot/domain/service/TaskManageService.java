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
	 * Create tasks.
	 * @param entities task entity.
	 */
	public void txCreateTasks(List<TTask> entities) {
		repo.create(entities);
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

}
