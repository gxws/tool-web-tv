package com.gxws.tool.web.tv.data;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 机顶盒浏览器类型枚举类
 * 
 * @author zhuwl120820@gxwsxx.com
 * @ @since 1.1
 */
public enum StbType {

	ONE("通用电视机顶盒0001", new String[] { "01", "sd", "480p" }, "0001"), TWO("通用电视机顶盒0002",
			new String[] { "02", "sd", "480P" },
			"0002"), THREE("通用电视机顶盒0003", new String[] { "03", "hd", "720p" }, "0003");

	private String name;
	private String[] aliases;
	private String key;
	private Set<String> aliasesSet;

	/**
	 * 根据value值获取机顶盒类型枚举对象
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param value
	 *            值
	 * @return 机顶盒类型枚举对象
	 * @since 1.1
	 */
	public static StbType fromValue(String value) {
		StbType[] all = StbType.values();
		for (StbType t : all) {
			if (t.key.equals(value)) {
				return t;
			} else if (t.aliasesSet.contains(value)) {
				return t;
			}
		}
		return null;
	}

	/**
	 * 浏览器类型构造
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param name
	 *            名称
	 * @param aliases
	 *            别名
	 * @param key
	 *            值
	 * @since 1.1
	 */
	private StbType(String name, String[] aliases, String key) {
		this.name = name;
		this.aliases = aliases;
		this.key = key;
		this.aliasesSet = new HashSet<>(Arrays.asList(aliases));
	}

	public String getName() {
		return name;
	}

	public String getKey() {
		return key;
	}

	public String[] getAliases() {
		return aliases;
	}

}
