package com.wang.so.love.mobile.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wang.core.ServiceResult;
import com.wang.core.util.PhoneFormatCheckUtil;
import com.wang.so.love.mobile.web.utils.SessionUtil;
import com.wang.so.love.service.param.SoLoveUserInfoParam;
import com.wang.so.love.service.service.SoLoveLoginService;

/**
 * 登录、注册
 * 
 * @author HeJiawang
 * @date   2016.12.02
 */
public class SoLoveLoginController extends BaseController {

	/**
	 * logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(SoLoveLoginController.class);

	/**
	 * soLoveLoginService
	 */
	@Autowired
	private SoLoveLoginService soLoveLoginService;
	
	/**
	 * 登录 
	 * @param request request
	 * @param passWord 密码
	 * @param loginName 手机号
	 * @return ServiceResult
	 * @author HeJiawang
	 * @date   2016.12.02
	 */
	@RequestMapping(value = "/login", method = {RequestMethod.POST})
	@ResponseBody
	public ServiceResult<SoLoveUserInfoParam> doLogin(HttpServletRequest request, String loginName, String passWord) {
		ServiceResult<SoLoveUserInfoParam> result = new ServiceResult<SoLoveUserInfoParam>();
		
		try{
			if (StringUtils.isBlank(loginName)) {
				result.setMessage("手机号不能为空!");
				result.setSuccess(false);
				return result;
			}
			if (StringUtils.isBlank(passWord)) {
				result.setMessage("密码不能为空!");
				result.setSuccess(false);
				return result;
			}
			
			result = soLoveLoginService.doLogin(loginName, passWord);
			if( !result.getSuccess() ){	//未取到用户
				result.setMessage("手机号或密码错误!");
				result.setSuccess(false);
				return result;
			}
			
			SessionUtil.writeUserToSession(request, result.getResult());	//记录session
			
			result.setMessage("登录成功!");
			result.setSuccess(true);
			
			logger.info("login info : " + result.getResult().getLoginName() + " 登录成功");
		}catch(Exception e){
			logger.error("异常发生在"+this.getClass().getName()+"类的doLogin方法，异常原因是："+e.getMessage(), e.fillInStackTrace());
		}
		
		return result;
	}
	
	/**
	 * 退出
	 * 
	 * @author HeJiawang
	 * @date   2016.12.02
	 */
	@RequestMapping(value = "/logout", method = {RequestMethod.GET})
	public ServiceResult<Void> logout( HttpServletRequest request){
		ServiceResult<Void> result = new ServiceResult<Void>();
		
		try {
			SessionUtil.deleteUserFromSession(request);
		} catch (Exception e) {
			logger.error("异常发生在"+this.getClass().getName()+"类的logout方法，异常原因是："+e.getMessage(), e.fillInStackTrace());
		}
		
		return result;
	}
	
	/**
	 * 注册 
	 *  
	 * @param request request
	 * @param loginName 手机号
	 * @param passWord 密码
	 * @param rePassWord 重复密码 
	 * @return ServiceResult
	 */
	@RequestMapping(value = "/register", method = {RequestMethod.POST})
	public ServiceResult<SoLoveUserInfoParam> register(HttpServletRequest request, String loginName, String passWord, String rePassWord){
		ServiceResult<SoLoveUserInfoParam> result = new ServiceResult<SoLoveUserInfoParam>();
		
		try {
			if( !PhoneFormatCheckUtil.isPhoneLegal(loginName) ){
				result.setMessage("手机号码不正确!");
				result.setSuccess(false);
				return result;
			}
			if( !passWord.equals(rePassWord) || passWord != rePassWord ){
				result.setMessage("密码不一致!");
				result.setSuccess(false);
				return result;
			}
			
			result = soLoveLoginService.register(loginName, passWord);
			
			SessionUtil.writeUserToSession(request, result.getResult());	//记录session
			
			result.setMessage("注册成功!");
			result.setSuccess(true);
			logger.info("login info : " + result.getResult().getLoginName() + " 注册成功");
		} catch (Exception e) {
			logger.error("异常发生在"+this.getClass().getName()+"类的register方法，异常原因是："+e.getMessage(), e.fillInStackTrace());
		}
		
		return result;
	}
}
