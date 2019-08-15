package com.cyh.sfxt.entirty.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 
*    
* 项目名称：app-sxzz   
* 类名称：PageInfo   
* 类描述：   分页内容
* 创建人：xfm   
* 创建时间：2018年12月6日 下午3:07:20
* 备注：   
* @version    
*
 */
@ApiModel(value ="分页返回信息")
public class PageInfoResult {

	//当前页
	@ApiModelProperty(value ="当前页")
    private int pageNum;
    //每页的数量
	@ApiModelProperty(value ="每页的数量")
    private int pageSize;
    //当前页的数量
	@ApiModelProperty(value ="当前页的数量")
    private int size;
    //总记录数
	@ApiModelProperty(value ="总记录数")
    private long total;
    //总页数
	@ApiModelProperty(value ="总页数")
    private int pages;
	
	//结果集
	@ApiModelProperty(value ="结果集")
    private List<Object> list;
    
	public PageInfoResult() {
		super();
	}

	public PageInfoResult(int pageNum, int pageSize, int size, long total, int pages, List<Object> list) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.size = size;
		this.total = total;
		this.pages = pages;
		this.list = list;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

	
	
	
    
    
}
