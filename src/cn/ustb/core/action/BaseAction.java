package cn.ustb.core.action;

import cn.ustb.core.utils.PageResult;

import com.opensymphony.xwork2.ActionSupport;


/**
 * 公共的action抽取，不想把每个action撑得很大
 * @author Wch
 *
 */
public abstract class BaseAction extends ActionSupport {
	
	
	protected String[] selectedRow;//接受jsp传过来的seletedRow的id数组
	
	protected long pageNo;//维护pageNo
	
	protected int pageSize;
	
	protected PageResult pageResult;//维护分页数据

	
	
	
	
	
	
	
	
	public String[] getSelectedRow() {
		return selectedRow;
	}
	public void setSelectedRow(String[] selectedRow) {
		this.selectedRow = selectedRow;
	}
	public Long getPageNo() {
		return pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public void setPageNo(long pageNo) {
		this.pageNo = pageNo;
	}
	public PageResult getPageResult() {
		return pageResult;
	}
	public void setPageResult(PageResult pageResult) {
		this.pageResult = pageResult;
	}
}
