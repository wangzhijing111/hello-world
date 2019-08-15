package com.cyh.sfxt.entirty.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @Title: Response
 * @Description:结果返回信息
 * @author:xfm
 * @date:2017年7月20日 下午3:00:46
 *
 */
@ApiModel(value ="返回信息")
public class Response {
	/** 返回信息码*/
	@ApiModelProperty(value ="返回信息码,0成功,1失败")
	private String resultCode="0";
	/** 返回信息提示*/
	@ApiModelProperty(value ="返回信息提示")
	private String resultMsg="操作成功";

	public Response() {
	}
	/***
	 * 根据框架定义好的提示信息,分别赋值
	 * @param msg
	 */
	public Response(ExceptionMsg msg){
		this.resultCode=msg.getCode();
		this.resultMsg=msg.getMsg();
	}
	
	/**
	 * 一般不用,只提示结果编号
	 * @param resultCode
	 */
	public Response(String resultCode) {
		this.resultCode = resultCode;
		this.resultMsg = "";
	}

	/**
	 * 重新赋值结果提示信息以及结果编号
	 * @param resultCode 结果编号
     * @param resultMsg  结果提示信息
	 */
	public Response(String resultCode, String resultMsg) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}
	public String getResultCode() {
		return resultCode;
	}
	

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	@Override
	public String toString() {
		return "Response{" +
				"resultCode='" + resultCode + '\'' +
				", resultMsg='" + resultMsg + '\'' +
				'}';
	}
}
