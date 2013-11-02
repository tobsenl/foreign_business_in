package cn.com.jnpc.foreign.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Page {
	
	private int pageId;   //当前第几页
	private int pageSize;   //共有多少条记录
	private int pageNum  ;  //共分多少页
	private int pageRe = 10;  //每面显示多少条记录

	private List list = new ArrayList();
	private Map<String, List<Object>> map=new HashMap<String, List<Object>>();
	/**
	 * 当前第几页
	 * @return
	 */
	public int getPageId() {
		return pageId;
	}
	/**
	 * 当前第几页
	 * @return
	 */
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
/**
 * 共有多少条记录
 * @return
 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * 共有多少条记录
	 * @return
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
/**
 * 共分多少页
 * @return
 */
	public int getPageNum() {
		return pageNum;
	}
	/**
	 * 共分多少页
	 * @return
	 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	/**
	 * 每面显示多少条记录
	 * @param pageNum
	 */
	public int getPageRe() {
		return pageRe;
	}
	/**
	 * 每面显示多少条记录
	 * @param pageNum
	 */
	public void setPageRe(int pageRe) {
		this.pageRe = pageRe;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Map<String, List<Object>> getMap() {
		return map;
	}

	public void setMap(Map<String, List<Object>> map) {
		this.map = map;
	}
	
	
	
	
	/*
	public int getGroupSize() {
		return this.groupSize;
	}

	public void setGroupSize(int groupSize) {
		if (groupSize > 0)
			this.groupSize = groupSize;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize > 0)
			this.pageSize = pageSize;
	}

	public int getRecordCount() {
		return this.recordCount;
	}

	public void setRecordCount(int recordCount) {
		if (recordCount >= 0) {
			this.recordCount = recordCount;
			setTotalPageCountByRs();
			if (this.currPageNo > this.totalPageCount) {
				this.currPageNo = this.totalPageCount;
			}
			setTotalGroupCountByRs();
		}
	}

	public int getCurrPageNo() {
		return this.currPageNo;
	}

	public void setCurrPageNo(int currPageNo) {
		if (currPageNo > 0)
			this.currPageNo = currPageNo;
	}

	public int getTotalPageCount() {
		return this.totalPageCount;
	}

	public int getTotalGroupCount() {
		return this.totalGroupCount;
	}

	private void setTotalPageCountByRs() {
		if ((this.pageSize > 0) && (this.recordCount > 0)
				&& (this.recordCount % this.pageSize == 0)) {
			this.totalPageCount = (this.recordCount / this.pageSize);
		} else if ((this.pageSize > 0) && (this.recordCount > 0)
				&& (this.recordCount % this.pageSize > 0)) {
			this.totalPageCount = (this.recordCount / this.pageSize + 1);
		} else
			this.totalPageCount = 1;
	}

	private void setTotalGroupCountByRs() {
		if (this.groupSize == 0) {
			this.totalGroupCount = 1;
		} else
			this.totalGroupCount = (this.totalPageCount % this.groupSize > 0 ? this.totalPageCount
					/ this.groupSize + 1 : this.totalPageCount / this.groupSize);
	}*/
}