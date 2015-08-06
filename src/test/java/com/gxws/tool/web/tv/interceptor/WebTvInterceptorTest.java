package com.gxws.tool.web.tv.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.gxws.tool.web.tv.data.WebTvParam;

@WebAppConfiguration
@ContextConfiguration(locations = { "/test-spring.xml" })
public class WebTvInterceptorTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	MockHttpServletRequest request;

	private MockMvc mockMvc;

	@BeforeClass
	private void beforeClass() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void preHandleWithParam() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/testurl1?stbId=123&dvbId=213&areaId=321&stbType=0001"))
				.andDo(MockMvcResultHandlers.print()).andReturn();
		Assert.assertNotNull(result.getModelAndView().getViewName());
		WebTvParam p =  (WebTvParam) result.getRequest().getAttribute(WebTvParam.ATTR_NAME);
		String value =  p.getUrl();
		Assert.assertEquals(value, "&stbId=123&dvbId=213&areaId=321&stbType=0001");
	}

	@Test
	public void preHandleWithoutParam0() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/testurl1")).andDo(MockMvcResultHandlers.print())
				.andReturn();
		Assert.assertNull(result.getModelAndView());
	}

	@Test
	public void preHandleWithoutParam1() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/testurl1?stbId=123"))
				.andDo(MockMvcResultHandlers.print()).andReturn();
		Assert.assertNull(result.getModelAndView());
	}

	@Test
	public void preHandleWithoutParam2() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/testurl1?stbId=123&dvbId=213"))
				.andDo(MockMvcResultHandlers.print()).andReturn();
		Assert.assertNull(result.getModelAndView());
	}

	@Test
	public void preHandleWithoutParam3() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/testurl1?stbId=123&dvbId=213&areaId=321"))
				.andDo(MockMvcResultHandlers.print()).andReturn();
		Assert.assertNotNull(result.getModelAndView().getViewName());
	}

	@Test
	public void preHandleWithoutParam4() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/testurl1?device_id=123"))
				.andDo(MockMvcResultHandlers.print()).andReturn();
		Assert.assertNull(result.getModelAndView());
	}

	@Test
	public void preHandleWithoutParam5() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/testurl1?device_id=123&user_id=213"))
				.andDo(MockMvcResultHandlers.print()).andReturn();
		Assert.assertNull(result.getModelAndView());
	}

	@Test
	public void preHandleWithoutParam6() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/testurl1?device_id=123&user_id=213&area_code=321&stbType=0002"))
				.andDo(MockMvcResultHandlers.print()).andReturn();
		Assert.assertNotNull(result.getModelAndView().getViewName());
		WebTvParam p =  (WebTvParam) result.getRequest().getAttribute(WebTvParam.ATTR_NAME);
		String value =  p.getUrl();
		Assert.assertEquals(value, "&stbId=123&dvbId=213&areaId=321&stbType=0002");
	}

}
