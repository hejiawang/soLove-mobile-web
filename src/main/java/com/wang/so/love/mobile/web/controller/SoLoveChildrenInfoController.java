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
import com.wang.so.love.service.entity.SoLoveChildrenInfoEntity;
import com.wang.so.love.service.param.SoLoveChildrenInfoParam;
import com.wang.so.love.service.service.SoLoveChildrenInfoService;

/**
 * 用户择偶信息controller
 * 
 * @author HeJiawang
 * @date   2016.12.06
 */
@Controller
@RequestMapping(value = "/userInfo/children")
public class SoLoveChildrenInfoController extends BaseController {

	/**
	 * logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(SoLoveChildrenInfoController.class);
	
	/**
	 * SoLoveChildrenInfoService
	 */
	@Autowired
	private SoLoveChildrenInfoService SoLoveChildrenInfoService;
	
	/**
	 * 新增用户子女信息
	 * 
	 * @param childrenInfo 用户子女信息
	 * @return ServiceResult
	 */
	@RequestMapping(value = "/raise", method = {RequestMethod.POST})
	@ResponseBody
	public ServiceResult<Void> raiseChildrenInfo( HttpServletRequest request, SoLoveChildrenInfoParam childrenInfo ){
		ServiceResult<Void> result = new ServiceResult<Void>();
		
		try{
			result = SoLoveChildrenInfoService.addChildrenInfo(childrenInfo);
		}catch(Exception e){
			logger.error("异常发生在"+this.getClass().getName()+"类的raiseChildrenInfo方法，异常原因是："+e.getMessage(), e.fillInStackTrace());
		}
		return result;
	}
	
	/**
	 * 修改用户子女信息
	 * 
	 * @param childrenInfo 用户子女信息
	 * @return ServiceResult
	 */
	@RequestMapping(value = "/modify", method = {RequestMethod.POST})
	@ResponseBody
	public ServiceResult<Void> modifyChildrenInfo( HttpServletRequest request, SoLoveChildrenInfoParam childrenInfo ){
		ServiceResult<Void> result = new ServiceResult<Void>();
		
		try{
			result = SoLoveChildrenInfoService.updateChildrenInfo(childrenInfo);
		}catch(Exception e){
			logger.error("异常发生在"+this.getClass().getName()+"类的modifyChildrenInfo方法，异常原因是："+e.getMessage(), e.fillInStackTrace());
		}
		return result;
	}
	
	/**
	 * 根据子女信息ID删除用户子女信息
	 * 
	 * @param childrenInfoID 用户子女信息ID
	 * @return ServiceResult
	 */
	@RequestMapping(value = "/delete", method = {RequestMethod.GET})
	@ResponseBody
	public ServiceResult<Void> deleteChildrenInfo( HttpServletRequest request, Integer childrenID ){
		ServiceResult<Void> result = new ServiceResult<Void>();
		
		try{
			result = SoLoveChildrenInfoService.deleteChildrenInfo(childrenID);
		}catch(Exception e){
			logger.error("异常发生在"+this.getClass().getName()+"类的deleteChildrenInfo方法，异常原因是："+e.getMessage(), e.fillInStackTrace());
		}
		return result;
	}
	
	/**
	 * 根据子女信息ID获取用户子女详细信息
	 * 
	 * @param childrenID 用户子女信息ID
	 * @return 用户子女信息
	 */
	@RequestMapping(value = "/view", method = {RequestMethod.GET})
	@ResponseBody
	public ServiceResult<SoLoveChildrenInfoEntity> getChildrenInfoByID( HttpServletRequest request, Integer childrenID ){
		ServiceResult<SoLoveChildrenInfoEntity> result = new ServiceResult<SoLoveChildrenInfoEntity>();
		
		try{
			result = SoLoveChildrenInfoService.getChildrenInfoByID(childrenID);
		}catch(Exception e){
			logger.error("异常发生在"+this.getClass().getName()+"类的getChildrenInfo方法，异常原因是："+e.getMessage(), e.fillInStackTrace());
		}
		return result;
	}
	
	/**
	 * 根据用户ID获取该用户的所有子女
	 * 
	 * @param userID 用户ID
	 * @return 该用户的所有子女信息
	 */
	@RequestMapping(value = "/viewChildrenInfoList", method = {RequestMethod.GET})
	@ResponseBody
	public ServiceResult<List<SoLoveChildrenInfoEntity>> getChildrenInfoByUserID( HttpServletRequest request, Integer userID ){
		ServiceResult<List<SoLoveChildrenInfoEntity>> result = new ServiceResult<List<SoLoveChildrenInfoEntity>>();
		
		try{
			result = SoLoveChildrenInfoService.getChildrenInfoByUserID(userID);
		}catch(Exception e){
			logger.error("异常发生在"+this.getClass().getName()+"类的getChildrenInfoByUserID方法，异常原因是："+e.getMessage(), e.fillInStackTrace());
		}
		return result;
	}
}
