package com.leonyip.core.dao.support;

import java.util.ArrayList;
import java.util.Collection;

public class Page {
	private static int DEFAULT_PAGE_SIZE = 20;
	private int pageSize;
	private long start;
	@SuppressWarnings("unchecked")
	private Collection data;
	private long totalCount;

	@SuppressWarnings("unchecked")
	public Page() {
		this(0L, 0L, DEFAULT_PAGE_SIZE, new ArrayList(0));
	}

	@SuppressWarnings("unchecked")
	public Page(long start, long totalSize, int pageSize, Collection data) {
		this.pageSize = DEFAULT_PAGE_SIZE;

		this.pageSize = pageSize;
		this.start = start;
		this.totalCount = totalSize;
		this.data = data;
	}

	public long getTotalCount() {
		return this.totalCount;
	}

	public long getTotalPageCount() {
		if (this.totalCount % this.pageSize == 0L)
			return (this.totalCount / this.pageSize);

		return (this.totalCount / this.pageSize + 1L);
	}

	public int getPageSize() {
		return this.pageSize;
	}

	@SuppressWarnings("unchecked")
	public Collection getResult() {
		return this.data;
	}

	@SuppressWarnings("unchecked")
	public void setResult(Collection date) {
		this.data = date;
	}

	public long getCurrentPageNo() {
		return (this.start / this.pageSize + 1L);
	}

	public boolean hasNextPage() {
		return (getCurrentPageNo() < getTotalPageCount());
	}

	public boolean hasPreviousPage() {
		return (getCurrentPageNo() > 1L);
	}

	public long getNextPageNo() {
		if (hasNextPage()) {
			return (getCurrentPageNo() + 1L);
		}

		return getCurrentPageNo();
	}

	public long getPreviousPageNo() {
		if (hasPreviousPage()) {
			return (getCurrentPageNo() - 1L);
		}

		return getCurrentPageNo();
	}

	public static int getStartOfPage(int pageNo, int pageSize) {
		return ((pageNo - 1) * pageSize);
	}

	protected static int getStartOfPage(int pageNo) {
		return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
	}
}