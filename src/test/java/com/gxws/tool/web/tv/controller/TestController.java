package com.gxws.tool.web.tv.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用于测试的控制层
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.1
 */
@Controller
public class TestController {

	private Logger log = LoggerFactory.getLogger(TestController.class);

	/**
	 * 测试url1
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return 测试url1页面
	 * @since 1.1
	 */
	@RequestMapping("/testurl1")
	public String testurl1() {
		System.out.println("testurl1");
		return "testurl1";
	}

	/**
	 * 测试url2
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return 测试url2页面
	 * @since 1.1
	 */
	@RequestMapping("/testurl2")
	public String testurl2() {
		log.info("testurl2");
		return "testurl2";
	}
}
