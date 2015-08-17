package com.gxws.tool.web.tv.core;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.gxws.tool.web.tv.annotation.WebTvParameter;
import com.gxws.tool.web.tv.data.StbType;
import com.gxws.tool.web.tv.data.WebTvParam;
import com.gxws.tool.web.tv.exception.WebTvParameterIllegalException;
import com.gxws.tool.web.tv.exception.WebTvParameterMissingException;

/**
 * 电视用户信息处理类
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */
public class WebTvCore {

	private static final Pattern STB_0203 = Pattern.compile("(Safari)|(Chrome)", Pattern.CASE_INSENSITIVE);

	private static final int[] CALENDAR_ARR = new int[] { Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH,
			Calendar.DAY_OF_WEEK, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND };

	/**
	 * 处理request参数
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param req
	 *            HttpServletRequest
	 * @return web tv 相关参数
	 * @throws WebTvParameterMissingException
	 *             必要的参数缺失
	 * @throws IllegalArgumentException
	 *             非法参数异常
	 * @throws IllegalAccessException
	 *             非法访问权限异常
	 * @throws WebTvParameterIllegalException
	 *             必要的参数非法异常
	 * @throws UnsupportedEncodingException
	 * @since 1.1
	 */
	public WebTvParam handleRequest(HttpServletRequest req)
			throws WebTvParameterMissingException, IllegalArgumentException, IllegalAccessException,
			WebTvParameterIllegalException, UnsupportedEncodingException {
		WebTvParam param = new WebTvParam();
		// 处理请求参数
		Class<WebTvParam> cls = WebTvParam.class;
		Field[] fields = cls.getDeclaredFields();
		for (Field f : fields) {
			handleField(f, param, req);
		}
		StbType type = handleStbType(param, req);
		param.setStbType(type.getKey());
		// 根据机顶盒类型，设置request编码方式
		if (StbType.ONE.equals(type)) {
			req.setCharacterEncoding("gb2312");
		}
		// 处理url
		String url = handleUrlParam(param);
		param.setUrl(url);
		req.setAttribute(WebTvParam.ATTR_NAME, param);
		return param;
	}

	/**
	 * 处理字段，通过注解
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param f
	 *            需要处理 的字段
	 * @param param
	 *            web tv 相关参数对象
	 * @param req
	 *            HttpServletRequest
	 * @throws WebTvParameterMissingException
	 *             必要的参数缺失
	 * @throws IllegalArgumentException
	 *             非法参数异常
	 * @throws IllegalAccessException
	 *             非法访问权限异常
	 * @since 1.1
	 */
	public void handleField(Field f, WebTvParam param, HttpServletRequest req)
			throws WebTvParameterMissingException, IllegalArgumentException, IllegalAccessException {
		WebTvParameter ann = f.getAnnotation(WebTvParameter.class);
		if (null == ann) {
			return;
		}
		String v = null;
		for (String s : ann.name()) {
			if ("".equals(s)) {
				s = f.getName();
			}
			v = req.getParameter(s);
			if (null != v) {
				f.setAccessible(true);
				f.set(param, v);
				return;
			}
		}
		if (null == v && ann.require()) {
			WebTvParameterMissingException e = new WebTvParameterMissingException();
			e.setParam(getName(ann, f), ann.description());
			throw e;
		}
	}

	/**
	 * 将web tv 相关参数处理为url参数
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param param
	 *            web tv参数对象
	 * @return 返回url参数
	 * @throws IllegalArgumentException
	 *             非法参数异常
	 * @throws IllegalAccessException
	 *             非法访问权限异常
	 * @since 1.1
	 */
	public String handleUrlParam(WebTvParam param) throws IllegalArgumentException, IllegalAccessException {
		StringBuilder sb = new StringBuilder();
		Class<WebTvParam> cls = WebTvParam.class;
		Field[] fields = cls.getDeclaredFields();
		WebTvParameter ann = null;
		Object value = null;
		for (Field f : fields) {
			ann = f.getAnnotation(WebTvParameter.class);
			if (null == ann) {
				continue;
			}
			f.setAccessible(true);
			value = f.get(param);
			if (null == value) {
				value = "";
			}
			sb.append("&");
			sb.append(getName(ann, f));
			sb.append("=");
			sb.append(value.toString());
		}
		return sb.toString();
	}

	/**
	 * 处理机顶盒类型
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param param
	 *            web tv参数对象
	 * @param req
	 *            HttpServletRequest对象
	 * @return 机顶盒类型枚举对象
	 * @throws WebTvParameterIllegalException
	 *             必要的参数非法异常
	 * @since 1.1
	 */
	public StbType handleStbType(WebTvParam param, HttpServletRequest req) throws WebTvParameterIllegalException {
		StbType type = StbType.fromValue(param.getStbType());
		if (null == type) {
			WebTvParameterIllegalException e = new WebTvParameterIllegalException();
			e.setParam("stbType", "机顶盒类型");
			throw e;
		}
		if (StbType.THREE.equals(type)) {
			return StbType.THREE;
		}
		String ua = req.getHeader("User-Agent");
		Matcher m2 = STB_0203.matcher(ua);
		if (m2.find()) {
			return StbType.TWO;
		} else {
			return StbType.ONE;
		}
	}

	/**
	 * 获取参数名
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param ann
	 *            web tv 注解对象
	 * @param f
	 *            字段
	 * @return 参数名
	 * @since 1.1
	 */
	private String getName(WebTvParameter ann, Field f) {
		String name = ann.name()[0];
		if ("".equals(name)) {
			name = f.getName();
		}
		return name;
	}

	/**
	 * 处理机顶盒展示要求的时间格式
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param req
	 *            HttpServletRequest对象
	 * @since 1.0
	 */
	public void handleWebTvTime(HttpServletRequest req) {
		WebTvParam param = (WebTvParam) req.getAttribute(WebTvParam.ATTR_NAME);
		StringBuilder sb = new StringBuilder();
		Calendar c = Calendar.getInstance();
		for (int i : CALENDAR_ARR) {
			sb.append("|");
			if (i == Calendar.MONTH) {
				sb.append(c.get(i) + 1);
			} else if (i == Calendar.DAY_OF_WEEK) {
				if (1 == c.get(i)) {
					sb.append("7");
				} else {
					sb.append(c.get(i) - 1);
				}
			} else {
				sb.append(c.get(i));
			}
		}
		param.setTime(sb.substring(1));
	}
}
