package com.cyh.sfxt.entirty.result;

/**
 * 
 * @Title: ExceptionMsg
 * @Description:结果异常信息
 * @author:xfm
 * @date:2017年7月20日 下午2:59:16
 * 
 */
public enum ExceptionMsg {
	SUCCESS("0", "操作成功"), 
	FAILED("1", "操作失败"), 
	/**
	 * 暂无数据
	 */
	NODATA("0", "暂无数据"), 
	
	/**
	 * 参数不能为空
	 */
	PARAERROR("1","参数不能为空！"),
	
	/**
	 * 系统错误
	 */
	SYSERROR("1","系统错误！"),

	/**
	 * 参数错误
	 */
	PARASTRERROR("1","参数错误！"),
	
	/**
	 * 请求超时
	 */
	WANGLERROR("1","网络异常请求超时,请重试!"),


	/**
	 * 尚未登录,请重新登录!
	 */
	LOGINERROR("1","尚未登录,请重新登录!"),
	
	/**
	 * 登陆成功!
	 */
	LOGINSUCCESS("0","登陆成功!"),
	
	/**
	 *该用户信息有误!请重新登录!
	 */
	LOGINUSERERROR("1","该用户信息有误,请重新登录!"),

	/** 
	 * 请求地址无效 -105
	 */
	SYSADDRESSERROR("-105","请求地址无效！"),

	/**
	 * 请输入正确的JSON格式！
	 */
	JSONERROR("1","请输入正确的JSON格式！");
	
	private ExceptionMsg(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	private String code;
	private String msg;

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}
