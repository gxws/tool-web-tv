package com.gxws.tool.web.tv.data;

import java.io.Serializable;

import com.gxws.tool.web.tv.annotation.WebTvParameter;

/**
 * 电视用户信息
 * 
 * @author zhuwl120820@gxwsxx.com
 * @deprecated 使用com.gxws.tool.web.tv.data.WebTvParam替代
 * @since 1.0
 */
@Deprecated
public class TvUserInfo implements Serializable {

	private static final long serialVersionUID = -8378608874134427474L;
	public final static String TV_USER_INFO_URL_PARAM_NAME = "tvParam";
	public final static String TV_USER_INFO_OBJECT_NAME = "tvUserInfo";

	// 机顶盒编号
	private String stbId;

	// dvb用户编号
	private String dvbId;

	// 区域编号
	private String areaId;

	// 机顶盒类型
	private String stbType;

	@Deprecated
	public String urlParam() {
		StringBuffer sb = new StringBuffer("&");
		sb.append(TvUserInfoType.STB_ID.getName());
		sb.append("=");
		sb.append(this.stbId);

		sb.append("&");
		sb.append(TvUserInfoType.DVB_ID.getName());
		sb.append("=");
		sb.append(this.dvbId);

		sb.append("&");
		sb.append(TvUserInfoType.AREA_ID.getName());
		sb.append("=");
		sb.append(this.areaId);

		sb.append("&");
		sb.append(TvUserInfoType.STB_TYPE.getName());
		sb.append("=");
		sb.append(this.stbType);
		return sb.toString();
	}

	public String getStbId() {
		return stbId;
	}

	public void setStbId(String stbId) {
		this.stbId = stbId;
	}

	public String getDvbId() {
		return dvbId;
	}

	public void setDvbId(String dvbId) {
		this.dvbId = dvbId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getStbType() {
		return stbType;
	}

	public void setStbType(String stbType) {
		this.stbType = stbType;
	}
}
