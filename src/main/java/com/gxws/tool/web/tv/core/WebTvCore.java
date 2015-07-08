package com.gxws.tool.web.tv.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gxws.tool.web.tv.data.TvUserInfo;
import com.gxws.tool.web.tv.data.TvUserInfoType;

/**
 * 电视用户信息处理类
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */
public class WebTvCore {

	/**
	 * 从request参数获取电视用户信息对象
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param req
	 *            信息来源
	 * @return 用户信息对象
	 * @since 1.0
	 */
	public TvUserInfo getTvUserInfo(HttpServletRequest req) {
		TvUserInfo info = new TvUserInfo();
		info.setStbId(value(req, TvUserInfoType.STB_ID));
		info.setDvbId(value(req, TvUserInfoType.DVB_ID));
		info.setAreaId(value(req, TvUserInfoType.AREA_ID));
		info.setStbType(value(req, TvUserInfoType.STB_TYPE));
		return info;
	}

	/**
	 * 从session获取电视机顶盒用户信息
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param session
	 *            信息来源
	 * @return 用户信息对象
	 * @since 1.0
	 */
	public TvUserInfo getTvUserInfo(HttpSession session) {
		TvUserInfo info = (TvUserInfo) session
				.getAttribute(TvUserInfo.TV_USER_INFO_OBJECT_NAME);
		return info;
	}

	/**
	 * 获取属性值
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param req
	 *            参数来源
	 * @param type
	 *            属性
	 * @return 值
	 * @since 1.0
	 */
	private String value(HttpServletRequest req, TvUserInfoType type) {
		String v = req.getParameter(type.getName());
		if (null == v || "".equals(v)) {
			v = req.getParameter(type.getInitName());
			if (null == v) {
				v = "";
			}
		}
		return v;
	}
}
