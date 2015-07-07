package com.gxws.tool.web.tv.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zhuwl120820@gxwsxx.com
 * @since
 */
public class TvInterceptor implements HandlerInterceptor {

	private final String[] INIT_NAME = new String[] { "user_id", "device_id",
			"area_code" };

	private final String[] NAME = new String[] { "dvbId", "stbId", "areaId" };

	private final String TV_STB_INFO = "tvStbInfo";

	/**
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < NAME.length; i++) {
			String v = request.getParameter(NAME[i]);
			if (null == v || "".equals(v)) {
				v = request.getParameter(INIT_NAME[i]);
				if (null == v || "".equals(v)) {
					continue;
				}
			}
			session.setAttribute(NAME[i], v);
			sb.append("&");
			sb.append(NAME[i]);
			sb.append("=");
			sb.append(v);
		}
		session.setAttribute(TV_STB_INFO, sb.toString());
		return true;
	}

	/**
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	/**
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
