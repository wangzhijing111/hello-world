package com.cyh.sfxt.entirty.result;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @Title: ResponseData
 * @Description:返回数据结果
 * @author:xfm
 * @date:2017年7月20日 下午3:02:57
 *
 */
public class ResponseData extends Response {
	@ApiModelProperty(value = "返回结果数据")
	private Object resultContent;

	public ResponseData(Object resultContent) {
		this.resultContent = resultContent;
	}

	public ResponseData(ExceptionMsg msg) {
		super(msg);
	}
	public ResponseData() {
		super();
	}

	/**
	 * 自定义提示信息
	 * 
	 * @param resultCode
	 *            结果编号
	 * @param resultMsg
	 *            结果提示信息
	 */
	public ResponseData(String resultCode, String resultMsg) {
		super(resultCode, resultMsg);
	}

	/**
	 * 自定义提示信息
	 * 
	 * @param resultCode
	 *            结果编号
	 * @param resultMsg
	 *            结果提示信息
	 * @param resultContent
	 *            结果信息
	 */
	public ResponseData(String resultCode, String resultMsg, Object resultContent) {
		super(resultCode, resultMsg);
		this.resultContent = resultContent;
	}

	/**
	 * 必须按照框架规则提示
	 * 
	 * @param msg
	 *            框架定义好的结果提示信息以及结果编号
	 * @param resultContent
	 *            结果信息
	 */
	public ResponseData(ExceptionMsg msg, Object resultContent) {
		super(msg);
		this.resultContent = resultContent;
	}
	
	


	/**
	 * 必须按照框架规则提示
	 * 
	 * @param msg
	 *            框架定义好的结果提示信息以及结果编号
	 * @param resultContent
	 *            结果信息
	 */
	public ResponseData(PageInfo pageInfo,ExceptionMsg msg) {
		super(msg);
		this.resultContent =new PageInfoResult(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getSize(), pageInfo.getTotal(),
				pageInfo.getPages(), pageInfo.getList());
	}

	public Object getResultContent() {
		return resultContent;
	}

	public void setResultContent(Object resultContent) {
		this.resultContent = resultContent;
	}

	@Override
	public String toString() {
		return "ResponseData{" + "resultContent=" + resultContent + "} " + super.toString();
	}
}
