package cn.ustb.core.utils;

import java.util.List;

public class PageResult {
	
	
	private int pageSize;//页面大小
	private long totalCount = 0l;//总记录数
	private long currPage = 1l;//当前页
	private long pageCount = 0l;//总页数
	
	private List items;//数据列表
	
	//初始化分页大小
	public static int INIT_PAGESIZE = 3;
	
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public long getCurrPage() {
		return currPage;
	}
	public void setCurrPage(long currPage) {
		this.currPage = currPage;
	}
	public long getPageCount() {
		return pageCount;
	}
	public void setPageCount() {
		if(totalCount>0){
			this.pageCount = ((totalCount%pageSize)==0)? (totalCount/pageSize):(totalCount/pageSize)+1;
		}
	}
	public List getItems() {
		return items;
	}
	public void setItems(List items) {
		this.items = items;
	}

	
	
	
}
