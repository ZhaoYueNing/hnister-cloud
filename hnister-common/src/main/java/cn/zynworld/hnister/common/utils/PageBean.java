package cn.zynworld.hnister.common.utils;

import java.util.List;

/**
 * @auther Buynow Zhao
 * @create 2018/1/10
 * 分页bean
 */
public class PageBean<T> {
//	条目总数
	private Long total;
//	当前页
	private Integer pageCount;
//	页大小
	private Integer pageSize;
//	list
	private List<T> items;

	private Long totalPage;

	public Integer getFirstItemIndex(){
		return (pageCount-1)*( pageSize);
	}
	public Long getTotal() {
		return total;
	}

	public PageBean setTotal(Long total) {
		this.total = total;
		if (this.pageSize != null) {
			this.totalPage = ((total-1)/ (long)pageSize)+1;
		}
		return this;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public PageBean setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
		return this;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public PageBean setPageSize(Integer pageSize) {
		if (this.totalPage != null) {
			this.totalPage = ((total-1)/ (long)pageSize)+1;
		}
		this.pageSize = pageSize;
		return this;
	}

	public List<T> getItems() {
		return items;
	}

	public PageBean setItems(List<T> items) {
		this.items = items;
		return this;
	}

	public Long getTotalPage() {
		return totalPage;
	}

	public PageBean setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
		return this;
	}

}
