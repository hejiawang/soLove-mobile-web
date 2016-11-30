package com.wang.so.love.mobile.web.xss;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 防跨域脚本攻击过滤器
 * 
 * @author HeJiawang
 * @date   2016.09.20
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}
		int count = values.length;
		String[] encodeValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodeValues[i] = XssUtil.cleanXss(values[i]);
		}
		return encodeValues;
	}

	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);
		if (value == null) {
			return value;
		}
		return XssUtil.cleanXss(value);
	}

	public String getHeader(String name) {
		String value = super.getHeader(name);
		if ("User-Agent".equalsIgnoreCase(name))
			return super.getHeader(name);
		if ("Referer".equalsIgnoreCase(name))
			return super.getHeader(name);
		if ("Accept".equalsIgnoreCase(name)) return super.getHeader(name);
		if (value == null) {
			return null;
		}
		return XssUtil.cleanXss(value);
	}

}
