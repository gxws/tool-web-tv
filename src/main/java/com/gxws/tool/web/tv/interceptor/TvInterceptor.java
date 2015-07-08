package com.gxws.tool.web.tv.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.gxws.tool.web.tv.core.WebTvCore;
import com.gxws.tool.web.tv.data.TvUserInfo;

/**
 * 处理电视机顶盒访问参数
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */
public class TvInterceptor implements HandlerInterceptor {

	private WebTvCore core = new WebTvCore();

	/**
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		TvUserInfo info = core.getTvUserInfo(request);
		session.setAttribute(TvUserInfo.TV_USER_INFO_URL_PARAM_NAME,
				info.urlParam());
		session.setAttribute(TvUserInfo.TV_USER_INFO_OBJECT_NAME, info);
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
		String type = core.getTvUserInfo(request).getStbType();
		if (null == type || "".equals(type)) {
			type = "";
		} else {
			type = type + "/";
		}
		if (null != modelAndView) {
			// jsp文件路径
			String jspPath = modelAndView.getViewName();
			if (jspPath.startsWith("forward")) {

			} else if (jspPath.startsWith("redirect")) {

			} else {
				modelAndView.setViewName(type + jspPath);
			}
		}
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
