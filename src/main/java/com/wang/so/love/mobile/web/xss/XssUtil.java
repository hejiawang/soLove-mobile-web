package com.wang.so.love.mobile.web.xss;

import java.util.Iterator;
import java.util.Map;

/**
 * 防跨域脚本攻击过滤器
 * 
 * @author HeJiawang
 * @date   2016.09.20
 */
public class XssUtil {

	private static Map<String, Object> map = XssPropertyConfigurer.getPropMap();

	public static String cleanXss(String value) {
		if (map != null) {
			Iterator<String> it = map.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				value = value.replaceAll(key, String.valueOf(map.get(key)));
			}
		}
		return value.trim();
	}
}
