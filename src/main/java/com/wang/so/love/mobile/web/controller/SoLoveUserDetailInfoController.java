package com.wang.so.love.mobile.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wang.core.ServiceResult;
import com.wang.so.love.service.entity.SoLoveUserDetailInfoEntity;
import com.wang.so.love.service.param.SoLoveUserDetailInfoParam;
import com.wang.so.love.service.service.SoLoveUserDetailInfoService;

/**
 * 用户详细信息controller
 * 
 * @author HeJiawang
 * @date   2016.12.06
 */
@Controller
@RequestMapping(value = "/userInfo/detail")
public class SoLoveUserDetailInfoController extends BaseController {

	/**
	 * logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(SoLoveUserDetailInfoController.class);
	
	/**
	 * soLoveUserDetailInfoService
	 */
	@Autowired
	private SoLoveUserDetailInfoService soLoveUserDetailInfoService;
	
	/**
	 * 个人详细信息维护
	 * 
	 * @param request request
	 * @param userDetailInfo 用户详细信息
	 * @return ServiceResult
	 */
	@RequestMapping(value = "/modify", method = {RequestMethod.POST})
	@ResponseBody
	public ServiceResult<Void> modifyUserDetailInfo( HttpServletRequest request, SoLoveUserDetailInfoParam userDetailInfo ){
		ServiceResult<Void> result = new ServiceResult<Void>();
		try{
			result = soLoveUserDetailInfoService.updateUserDetailInfo(userDetailInfo);
		}catch(Exception e){
			logger.error("异常发生在"+this.getClass().getName()+"类的modifyUserDetailInfo方法，异常原因是："+e.getMessage(), e.fillInStackTrace());
		}
		return result;
	}
	
	/**
	 * 获取用户详细信息
	 * 
	 * @param request request
	 * @param userID 用户ID
	 * @return 用户详细信息
	 */
	@RequestMapping(value = "/view", method = {RequestMethod.GET})
	@ResponseBody
	public ServiceResult<SoLoveUserDetailInfoEntity> viewUserDetailInfo( HttpServletRequest request, Integer userID ){
		ServiceResult<SoLoveUserDetailInfoEntity> result = new ServiceResult<SoLoveUserDetailInfoEntity>();
		try{
			result = soLoveUserDetailInfoService.getUserDetailInfoByUserID(userID);
		}catch(Exception e){
			logger.error("异常发生在"+this.getClass().getName()+"类的viewUserDetailInfo方法，异常原因是："+e.getMessage(), e.fillInStackTrace());
		}
		return result;
	}

}
