package com.example.springboot.domain.service;

import java.util.List;

import com.example.springboot.domain.entity.TTask;
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
		for (TTask entity : entities) {
			repo.create(entity);
		}
	}

}
