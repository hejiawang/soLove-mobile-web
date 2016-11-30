package com.wang.so.love.mobile.web.csrf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.UUID;

/**
 * csrf机制的帮助类
 *
 * @author HeJiawang
 * @date   2016.09.20
 */
public final class CSRFTokenManager {

	/**
	 * The token parameter name
	 */
	public static final String CSRF_PARAM_NAME = "CSRFToken";
	public static final String MEM_KEY_NAME    = "CSRFMemKey";
	public final static byte[] sync            = new byte[0];
	public final static Logger log             = LoggerFactory.getLogger(CSRFTokenManager.class);

	public CSRFTokenManager() {
	}

	public static String getTokenForSession(HttpSession session, String key) {
		String token = null;
		// I cannot allow more than one token on a session - in the case of two requests trying to
		// init the token concurrently
		synchronized (sync) {
			try {
				token = (String) session.getAttribute(key);
			} catch (Exception e1) {
				log.error("[csrf]从session中取缓存数据时发生异常：" + e1);
			}
			if (null == token) {
				token = UUID.randomUUID().toString();
				try {
					session.setAttribute(key, token);
				} catch (Exception e) {
					log.error("[csrf]向session缓存数据时发生异常：" + e);
				}
			}
		}
		return token;
	}

	/**
	 * 删除已经使用的session
	 *
	 * @param key
	 */
	public static void destroyTokenForSession(HttpSession session, String key) {
		try {
			session.removeAttribute(key);
		} catch (Exception e) {
			log.error("[csrf]从session删除缓存数据时发生异常：" + e);
		}
	}

	public static String getTokenFromRequest(HttpServletRequest request) {
		return request.getParameter(CSRF_PARAM_NAME);
	}

	public static String getMemkeyFromRequest(HttpServletRequest request) {
		return request.getParameter(MEM_KEY_NAME);
	}

	/**
	 * 取得token值，点击提交后页面不跳转的页面使用
	 */
	public static String getCSRFTokenForPreservePage(HttpSession session, String key) {
		String token = null;

		token = UUID.randomUUID().toString();
		try {
			session.setAttribute(key, token);
		} catch (Exception e) {
			log.error("[csrf]向session缓存数据时发生异常：" + e);
		}
		return token;
	}

}
