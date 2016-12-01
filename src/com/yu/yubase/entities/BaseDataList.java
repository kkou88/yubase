package com.yu.yubase.entities;

import java.util.List;

public class BaseDataList<T> {
	private int totalRecords; //总条数
	private int totalPages; //总页数
	private int pageSize; //每页最多的条数
	private int currentPage; //当前页数
	private List<T> list;
	private String type; //类型
	
	public int getTotalRecords() {
		return totalRecords;
	}
	
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	
	public int getTotalPages() {
		return totalPages;
	}
	
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<T> getList() {
		return list;
	}
	
	public void clearList(){
		list.clear();
	}
	
	public void setList(List<T> list) {
		this.list = list;
	}

	public void addList(List<T> list) {
		this.list.addAll(list);
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
