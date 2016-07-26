package com.example.mvcdemo.base.baseClass;


public class PagePayModel{

	private int pageNumber ;//当前页
	private int pageSize ; //每页条数
	private int pageStartNumber;//开始页数
	private int pageEndNumber;//结束页数
	private int totalPages;//总页数
	private Long totalNumber;//总条数
	private boolean  hasPreviousPage;//之前的页数
	private boolean  hasNextPage;//之后的页数    
	private String sortType ;//排序字段
	private Object obj ;
	
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageStartNumber() {
		return pageStartNumber;
	}
	public void setPageStartNumber(int pageStartNumber) {
		this.pageStartNumber = pageStartNumber;
	}
	public int getPageEndNumber() {
		return pageEndNumber;
	}
	public void setPageEndNumber(int pageEndNumber) {
		this.pageEndNumber = pageEndNumber;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public Long getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(Long totalNumber) {
		this.totalNumber = totalNumber;
	}
	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}
	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}
	public boolean isHasNextPage() {
		return hasNextPage;
	}
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
 
	

}
