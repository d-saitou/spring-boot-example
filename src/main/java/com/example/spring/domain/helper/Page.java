package com.example.spring.domain.helper;

import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Store pagination data.
 */
@Data
@AllArgsConstructor
@SuppressFBWarnings(
		value = { "EI_EXPOSE_REP", "EI_EXPOSE_REP2" },
		justification = "Allow to set list values of form by Spring.")
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
