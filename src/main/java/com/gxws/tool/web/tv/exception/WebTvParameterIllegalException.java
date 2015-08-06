package com.gxws.tool.web.tv.exception;

/**
 * 电视机顶盒请求所要求的参数非法异常
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.1
 */
public class WebTvParameterIllegalException extends WebTvParameterException {
	private static final long serialVersionUID = -34691664330466530L;

	/**
	 * 初始化构造方法
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @since 1.1
	 */
	public WebTvParameterIllegalException() {
		super();
		setMsg("必要的参数非法异常:");
	}
}
