package com.lq.webUtils;

import java.util.ArrayList;
import java.util.List;

public class PageResult {
	private long totalCount;	
	private int pageNumber;	
	private int pageSize;		
	private int totalPageCount;
	private List items;
	
	
	public PageResult(long totalCount, int pageNumber, int pageSize, List items) {
		if(items==null)	items = new ArrayList();
		this.pageSize = pageSize;
		this.pageNumber = pageNumber;
		if(totalCount > 0){
			this.totalCount = totalCount;
			int item = (int) (totalCount / pageSize);
			if(totalCount % pageSize != 0){
				totalPageCount = item + 1;
			}else{
				totalPageCount = item;
			}
		}
		this.items = items;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
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
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public List getItems() {
		return items;
	}
	public void setItems(List items) {
		this.items = items;
	}
	
}
