package com.wang.so.love.mobile.web.controller;

import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;
import freemarker.template.TemplateHashModel;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.wang.core.util.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 控制层基类， 所有controller类都需要继承此类
 *
 * @author HeJiawang
 * @date 2016.09.21
 */
public class BaseController {
	protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	protected final String MESSAGE = "message";
	protected final String ERROR = "redirect:/error.html";

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// date,datetime
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			public void setAsText(String value) {
				if (StringUtils.isEmpty(value)) {
					setValue(null);
					return;
				}
				try {
					if (value.length() == 10) {
						setValue(new SimpleDateFormat("yyyy-MM-dd").parse(value));
					} else if (value.length() == 8) {
						setValue(new SimpleDateFormat("HH:mm:ss").parse(value));
					} else {
						setValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value));
					}

				} catch (ParseException e) {
					logger.error("Can not convert '" + value + "' to java.util.Date", e);
				}
			}

			public String getAsText() {
				return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) getValue());
			}

		});
		// int
		binder.registerCustomEditor(Integer.class, new PropertyEditorSupport() {
			public void setAsText(String value) {
				if (StringUtils.isEmpty(value)) {
					// setValue(0);
					return;
				}
				setValue(Integer.parseInt(value));
			}

			public String getAsText() {
				return getValue().toString();
			}

		});

		// long
		binder.registerCustomEditor(Long.class, new PropertyEditorSupport() {
			public void setAsText(String value) {
				if (StringUtils.isEmpty(value)) {
					// setValue(0);
					return;
				}
				setValue(Long.parseLong(value));
			}

			public String getAsText() {
				return getValue().toString();
			}

		});

		// double
		binder.registerCustomEditor(Double.class, new PropertyEditorSupport() {
			public void setAsText(String value) {
				if (StringUtils.isEmpty(value)) {
					// setValue(0);
					return;
				}
				setValue(Double.parseDouble(value));
			}

			public String getAsText() {
				return getValue().toString();
			}

		});
	}

	/**
	 * 返回jsonp格式数据
	 */
	protected void writeJSONPToResponse(Object result, HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("P3P",
					"CP=CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR");
			response.setContentType("text/plain;charset=utf-8");
			out = response.getWriter();
			String jsonStr = null;
			String callback = request.getParameter("callback");
			if (StringUtils.isEmpty(callback)) {
				jsonStr = JsonUtil.toJson(result);
			} else {
				jsonStr = callback + "(" + JsonUtil.toJson(result) + ")";
			}
			out.write(jsonStr);
		} catch (IOException e) {
			logger.error("开启输出流错误", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		return request;
	}

	public ModelAndView redirectHandlerFor301(String sURL) {
		RedirectView rv = new RedirectView(sURL);
		rv.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
		rv.setUrl(sURL);
		ModelAndView mv = new ModelAndView(rv);
		return mv;
	}

	BeansWrapper wrapper = new BeansWrapperBuilder(Configuration.VERSION_2_3_21).build();

	TemplateHashModel staticModels = wrapper.getStaticModels();

	public TemplateHashModel templateBean(String strBeanResource) throws Exception {
		TemplateHashModel ordersWrapStatics = (TemplateHashModel) staticModels.get(strBeanResource);
		return ordersWrapStatics;
	}

}
