package com.gxws.tool.web.tv.data;

/**
 * 电视用户需要的属性
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */
public enum TvUserInfoType {
	DVB_ID("dvbId", "user_id"), STB_ID("stbId", "device_id"), AREA_ID("areaId",
			"area_code"), STB_TYPE("stbType", "stbType");

	private TvUserInfoType(String name, String initName) {
		this.name = name;
		this.initName = initName;
	}

	private String name;
	private String initName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInitName() {
		return initName;
	}

	public void setInitName(String initName) {
		this.initName = initName;
	}
}
