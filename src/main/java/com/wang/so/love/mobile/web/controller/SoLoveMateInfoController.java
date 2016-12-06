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
import com.wang.so.love.service.entity.SoLoveMateInfoEntity;
import com.wang.so.love.service.param.SoLoveMateInfoParam;
import com.wang.so.love.service.service.SoLoveMateInfoService;

/**
 * 用户择偶信息controller
 * 
 * @author HeJiawang
 * @date   2016.12.06
 */
@Controller
@RequestMapping(value = "/userInfo/mate")
public class SoLoveMateInfoController extends BaseController {

	/**
	 * logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(SoLoveMateInfoController.class);
	
	/**
	 * soLoveUserDetailInfoService
	 */
	@Autowired
	private SoLoveMateInfoService soLoveMateInfoService;
	
	/**
	 * 择偶信息维护
	 * 
	 * @param request request
	 * @param mateInfo 择偶信息
	 * @return ServiceResult
	 */
	@RequestMapping(value = "/modify", method = {RequestMethod.POST})
	@ResponseBody
	public ServiceResult<Void> modifyMateInfo( HttpServletRequest request, SoLoveMateInfoParam mateInfo ){
		ServiceResult<Void> result = new ServiceResult<Void>();
		try{
			result = soLoveMateInfoService.updateMateInfo(mateInfo);
		}catch(Exception e){
			logger.error("异常发生在"+this.getClass().getName()+"类的modifyMateInfo方法，异常原因是："+e.getMessage(), e.fillInStackTrace());
		}
		return result;
	}
	
	/**
	 * 获取择偶信息
	 * 
	 * @param request request
	 * @param userID 用户ID
	 * @return 用户择偶信息
	 */
	@RequestMapping(value = "/view", method = {RequestMethod.GET})
	@ResponseBody
	public ServiceResult<SoLoveMateInfoEntity> viewMateInfo( HttpServletRequest request, Integer userID ){
		ServiceResult<SoLoveMateInfoEntity> result = new ServiceResult<SoLoveMateInfoEntity>();
		try{
			result = soLoveMateInfoService.getMateInfoByUserID(userID);
		}catch(Exception e){
			logger.error("异常发生在"+this.getClass().getName()+"类的viewMateInfo方法，异常原因是："+e.getMessage(), e.fillInStackTrace());
		}
		return result;
	}

}
