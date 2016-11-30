package com.wang.so.love.mobile.web.csrf;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.support.RequestDataValueProcessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 与页面中的<@form.form>配合使用，生成token的隐藏域
 *
 * @author HeJiawang
 * @date   2016.09.20
 */
public class CSRFRequestDataValueProcessor implements RequestDataValueProcessor {

	@Override
	public String processAction(HttpServletRequest httpServletRequest, String action, String action2) {
		return action;
	}

	@Override
	public String processFormFieldValue(HttpServletRequest request, String name, String value, String type) {
		return value;
	}

	@Override
	public Map<String, String> getExtraHiddenFields(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Map<String, String> hiddenFields = new HashMap<String, String>();
		String              memKey       = CSRFTokenManager.getMemkeyFromRequest(request);
		if (StringUtils.isEmpty(memKey)) {
			memKey = UUID.randomUUID().toString();
		}
		hiddenFields.put(CSRFTokenManager.MEM_KEY_NAME, memKey);
		hiddenFields.put(CSRFTokenManager.CSRF_PARAM_NAME, CSRFTokenManager.getTokenForSession(session, memKey));
		return hiddenFields;
	}

	@Override
	public String processUrl(HttpServletRequest request, String url) {
		return url;
	}

}
