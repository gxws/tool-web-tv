package com.gxws.tool.web.tv.exception;

/**
 * 电视机顶盒请求所要求的参数缺失异常
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.1
 */
public class WebTvParameterMissingException extends WebTvParameterException {
	private static final long serialVersionUID = -3670342752299034585L;

	/**
	 * 初始化异常
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @since 1.1
	 */
	public WebTvParameterMissingException() {
		super();
		setMsg("必要的参数缺失异常:");
	}
}
