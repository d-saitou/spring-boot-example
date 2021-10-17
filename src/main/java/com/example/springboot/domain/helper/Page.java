package com.example.springboot.domain.helper;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Store pagination data.
 */
@Data
@AllArgsConstructor
public class Page<T> {

	private int pageSize;

	private int currentPage;

	private int totalElements;

	private List<T> content;

	/**
	 * Return the total number of pages.
	 * @return the total number of pages.
	 */
	public int getTotalPages() {
		return (totalElements + pageSize - 1) / pageSize;
	}

	/**
	 * Return the existense of the previous page.
	 * @return the existense of the previous page.
	 */
	public boolean hasPrevious() {
		return currentPage <= 0 ? false  : true;
	}

	/**
	 * Return the existense of the next page.
	 * @return the existense of the next page.
	 */
	public boolean hasNext() {
		return currentPage >= (getTotalPages() - 1) ? false  : true;
	}

}
