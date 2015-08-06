package com.gxws.tool.web.tv.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * web-tv类型项目要求的参数
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.1
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface WebTvParameter {

	/**
	 * 参数名
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return 参数名
	 * @since 1.1
	 */
	public String[]name() default "";

	/**
	 * 描述
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return 描述说明
	 * @since 1.1
	 */
	public String description() default "";

	/**
	 * 是否必要参数
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return 是否
	 * @since 1.1
	 */
	public boolean require() default true;
}
