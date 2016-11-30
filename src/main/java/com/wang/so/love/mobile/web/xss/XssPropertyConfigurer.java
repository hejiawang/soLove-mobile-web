package com.wang.so.love.mobile.web.xss;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 防跨域脚本攻击过滤器
 * 
 * @author HeJiawang
 * @date   2016.09.20
 */
public class XssPropertyConfigurer extends PropertyPlaceholderConfigurer {
	private static Map<String, Object> propMap;

	protected void processProperties(ConfigurableListableBeanFactory beaFactory, Properties props) throws BeansException {
		super.processProperties(beaFactory, props);
		propMap = new HashMap<String, Object>();
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			String value = props.getProperty(keyStr);
			propMap.put(keyStr, value);
		}
	}

	public static Object getProperty(String name) {
		return propMap.get(name);
	}

	public static Map<String, Object> getPropMap() {
		return propMap;
	}
}
