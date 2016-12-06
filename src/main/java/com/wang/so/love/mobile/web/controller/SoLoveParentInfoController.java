package com.wang.so.love.mobile.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wang.core.ServiceResult;
import com.wang.so.love.service.entity.SoLoveParentInfoEntity;
import com.wang.so.love.service.param.SoLoveParentInfoParam;
import com.wang.so.love.service.service.SoLoveParentInfoService;

/**
 * 用户择偶信息controller
 * 
 * @author HeJiawang
 * @date   2016.12.06
 */
@Controller
@RequestMapping(value = "/userInfo/parent")
public class SoLoveParentInfoController extends BaseController {

	/**
	 * logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(SoLoveParentInfoController.class);
	
	/**
	 * SoLoveParentInfoService
	 */
	@Autowired
	private SoLoveParentInfoService SoLoveParentInfoService;
	
	/**
	 * 新增用户父母信息
	 * 
	 * @param parentInfo 用户父母信息
	 * @return ServiceResult
	 */
	@RequestMapping(value = "/raise", method = {RequestMethod.POST})
	@ResponseBody
	public ServiceResult<Void> raiseParentInfo( HttpServletRequest request, SoLoveParentInfoParam parentInfo ){
		ServiceResult<Void> result = new ServiceResult<Void>();
		
		try{
			result = SoLoveParentInfoService.addParentInfo(parentInfo);
		}catch(Exception e){
			logger.error("异常发生在"+this.getClass().getName()+"类的raiseParentInfo方法，异常原因是："+e.getMessage(), e.fillInStackTrace());
		}
		return result;
	}
	
	/**
	 * 修改用户父母信息
	 * 
	 * @param parentInfo 用户父母信息
	 * @return ServiceResult
	 */
	@RequestMapping(value = "/modify", method = {RequestMethod.POST})
	@ResponseBody
	public ServiceResult<Void> modifyParentInfo( HttpServletRequest request, SoLoveParentInfoParam parentInfo ){
		ServiceResult<Void> result = new ServiceResult<Void>();
		
		try{
			result = SoLoveParentInfoService.updateParentInfo(parentInfo);
		}catch(Exception e){
			logger.error("异常发生在"+this.getClass().getName()+"类的modifyParentInfo方法，异常原因是："+e.getMessage(), e.fillInStackTrace());
		}
		return result;
	}
	
	/**
	 * 根据父母信息ID删除用户父母信息
	 * 
	 * @param parentInfoID 用户父母信息ID
	 * @return ServiceResult
	 */
	@RequestMapping(value = "/delete", method = {RequestMethod.GET})
	@ResponseBody
	public ServiceResult<Void> deleteParentInfo( HttpServletRequest request, Integer parentID ){
		ServiceResult<Void> result = new ServiceResult<Void>();
		
		try{
			result = SoLoveParentInfoService.deleteParentInfo(parentID);
		}catch(Exception e){
			logger.error("异常发生在"+this.getClass().getName()+"类的deleteParentInfo方法，异常原因是："+e.getMessage(), e.fillInStackTrace());
		}
		return result;
	}
	
	/**
	 * 根据父母信息ID获取用户父母详细信息
	 * 
	 * @param parentID 用户父母信息ID
	 * @return 用户父母信息
	 */
	@RequestMapping(value = "/view", method = {RequestMethod.GET})
	@ResponseBody
	public ServiceResult<SoLoveParentInfoEntity> getParentInfoByID( HttpServletRequest request, Integer parentID ){
		ServiceResult<SoLoveParentInfoEntity> result = new ServiceResult<SoLoveParentInfoEntity>();
		
		try{
			result = SoLoveParentInfoService.getParentInfoByID(parentID);
		}catch(Exception e){
			logger.error("异常发生在"+this.getClass().getName()+"类的getParentInfo方法，异常原因是："+e.getMessage(), e.fillInStackTrace());
		}
		return result;
	}
	
	/**
	 * 根据用户ID获取该用户的所有父母
	 * 
	 * @param userID 用户ID
	 * @return 该用户的所有父母信息
	 */
	@RequestMapping(value = "/viewParentInfoList", method = {RequestMethod.GET})
	@ResponseBody
	public ServiceResult<List<SoLoveParentInfoEntity>> getParentInfoByUserID( HttpServletRequest request, Integer userID ){
		ServiceResult<List<SoLoveParentInfoEntity>> result = new ServiceResult<List<SoLoveParentInfoEntity>>();
		
		try{
			result = SoLoveParentInfoService.getParentInfoByUserID(userID);
		}catch(Exception e){
			logger.error("异常发生在"+this.getClass().getName()+"类的getParentInfoByUserID方法，异常原因是："+e.getMessage(), e.fillInStackTrace());
		}
		return result;
	}
}
