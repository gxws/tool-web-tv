package com.gxws.tool.web.tv.exception;

import com.gxws.tool.common.exception.BaseException;

/**
 * 要求的参数缺失
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.1
 */
public class WebTvParameterMissingException extends BaseException {
	private static final long serialVersionUID = -3670342752299034585L;
	private String msg = "必要的参数缺失:";

	public void setParam(String name, String description) {
		msg = msg + name + "(" + description + ") ";
	}

	public String getMessage() {
		return msg;
	}
}
