package com.gxws.tool.web.tv;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gxws.tool.web.tv.core.WebTvCore;
import com.gxws.tool.web.tv.data.TvUserInfo;

/**
 * session工具
 * 
 * @author zhuwl120820@gxwsxx.com
 * @deprecated 由com.gxws.tool.web.tv.core.WebTvCore替代相应的功能
 * @since 1.0
 */
@Deprecated
public class WebTvTool {

	private static WebTvCore core;

	/**
	 * 从request获取电视用户机顶盒信息
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param request
	 * @return 电视用户机顶盒信息
	 * @since 1.0
	 */
	public static TvUserInfo getTvUserInfo(HttpServletRequest request) {
		if (null == core) {
			core = new WebTvCore();
		}
		TvUserInfo info = core.getTvUserInfo(request);
		if (null == info) {
			return core.getTvUserInfo(request.getSession());
		} else {
			return info;
		}
	}

	public static TvUserInfo getTvUserInfo(HttpSession session) {
		if (null == core) {
			core = new WebTvCore();
		}
		return core.getTvUserInfo(session);
	}
}
