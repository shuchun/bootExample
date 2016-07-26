package com.example.mvcdemo.base.baseClass;

import java.util.List;
/**
 * 数据返回基本map
 * @author Administrator
 * @param <T>
 *
 */
public class RootJson<T> {
	private Long pageCount; //总共条数
	private List<T> root; //显示列表
	

	public Long getPageCount() {
		return pageCount;
	}
	public void setPageCount(Long pageCount) {
		this.pageCount = pageCount;
	}
	public List<T> getRoot() {
		return root;
	}
	public void setRoot(List<T> root) {
		this.root = root;
	}
	
	
	
	
}
