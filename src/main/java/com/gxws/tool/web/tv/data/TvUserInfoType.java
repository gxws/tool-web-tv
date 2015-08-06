package com.gxws.tool.web.tv.data;

/**
 * 电视用户需要的属性
 * 
 * @author zhuwl120820@gxwsxx.com
 * @deprecated 使用注解方式替代
 * @since 1.0
 */
@Deprecated
public enum TvUserInfoType {
	DVB_ID("dvbId", "user_id", "用户编号"), STB_ID("stbId", "device_id", "机顶盒编号"), AREA_ID("areaId", "area_code",
			"区域码"), STB_TYPE("stbType", "stbType", "机顶盒类型");

	private TvUserInfoType(String name, String initName, String description) {
		this.name = name;
		this.initName = initName;
		this.description = description;
	}

	private String name;
	private String initName;
	private String description;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
