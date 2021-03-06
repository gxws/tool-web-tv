package com.gxws.tool.web.tv.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.gxws.tool.web.tv.core.WebTvCore;
import com.gxws.tool.web.tv.data.WebTvParam;

/**
 * 处理电视机顶盒访问参数
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.1
 */
public class WebTvInterceptor implements HandlerInterceptor {

	private static final Logger LOG = LoggerFactory.getLogger(WebTvInterceptor.class);

	private WebTvCore core = new WebTvCore();

	/**
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		WebTvParam param = core.handleRequest(request);
		request.setAttribute(WebTvParam.ATTR_NAME, param);
		return true;
	}

	/**
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// WebTvParam param = (WebTvParam)
		// request.getAttribute(WebTvParam.ATTR_NAME);
		// if (null != param) {
		// String urlparam = core.handleUrlParam(param);
		// request.setAttribute(WebTvParam.URL_PARAM_NAME, urlparam);
		// }
	}

	/**
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
