package com.example.mvcdemo.base.baseClass;

import com.example.mvcdemo.base.util.ToolUtils;

public class PageModel{

	private int pageNumber = Constant.PAG_PAGENUMBER;//当前页
	private int pageSize = Constant.PAG_PAGESIZE; //每页条数
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
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	public int getPageStartNumber() {
		if(pageNumber>1){
			pageStartNumber = (pageNumber-1)*pageSize;
		}else {
			pageStartNumber =0;
		}
		
		return pageStartNumber;
	}
	
	public void setPageStartNumber(int pageStartNumber) {
		
		this.pageStartNumber = pageStartNumber;
	}
	
	public int getPageEndNumber() {
		   if(pageNumber==0){
			   pageEndNumber=pageSize-1;
		   }else{
			pageEndNumber=pageNumber*pageSize;
		   }
		return pageEndNumber;
	}
	
	public void setPageEndNumber(int pageEndNumber) {
			
		this.pageEndNumber = pageEndNumber;
	}
	
	public boolean isHasPreviousPage() {
		if(pageNumber<=1){
			hasPreviousPage = false;
		}else{
			hasPreviousPage = true;
		}
		return hasPreviousPage;
	}
	
	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}
	
	public boolean isHasNextPage() {
		if(pageNumber<totalPages){
			hasNextPage = true;
		}else{
			hasNextPage = false;
		}
		return hasNextPage;
	}
	
	public void setHasNextPage(boolean hasNextPage) {
		
		this.hasNextPage = hasNextPage;
	}
	
	public Long getTotalNumber() {
		return totalNumber;
	}
	
	public void setTotalNumber(Long totalNumber) {
		if (ToolUtils.isNotEmpty(totalNumber)) {
			 Long l =((Integer)pageSize).longValue();
			 int t = (int)Math.ceil(totalNumber/l);
			 double d = totalNumber%l;
			 if(d!=0){
				 totalPages=t+1;
			 }else{
				 totalPages=t;
			 }
			
		}
		this.totalNumber = totalNumber;
	}

}
