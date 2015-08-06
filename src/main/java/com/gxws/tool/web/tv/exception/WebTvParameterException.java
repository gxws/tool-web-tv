package com.gxws.tool.web.tv.exception;

import com.gxws.tool.common.exception.BaseException;

/**
 * 电视机顶盒请求所要求的参数
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.1
 */
public class WebTvParameterException extends BaseException {
	private static final long serialVersionUID = 4012399303445443629L;

	public void setParam(String name, String description) {
		setMsg(getMsg() + name + "(" + description + ") ");
	}

	public String getMessage() {
		return getMsg();
	}
}
