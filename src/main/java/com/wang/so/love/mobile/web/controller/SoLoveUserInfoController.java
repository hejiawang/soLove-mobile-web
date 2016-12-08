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
import com.wang.so.love.service.param.SoLoveMateInfoParam;
import com.wang.so.love.service.service.SoLoveUserInfoService;
import com.wang.so.love.service.vo.SoLoveUserSimpleInfoVO;

/**
 * 用户信息操作
 * 
 * @author HeJiawang
 * @date   2016.12.08
 */
@Controller
@RequestMapping(value = "/userInfo/base")
public class SoLoveUserInfoController extends BaseController {

	/**
	 * logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(SoLoveUserInfoController.class);
	
	/**
	 * soLoveUserInfoService
	 */
	@Autowired
	private SoLoveUserInfoService soLoveUserInfoService;
	
	/**
	 * 根据择偶条件筛选信息</br>
	 * 并进行分页
	 * 
	 * @param mateInfo 择偶信息
	 * @return 简单的用户信息,如：年龄、兴趣爱好等
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET})
	@ResponseBody
	public ServiceResult<List<SoLoveUserSimpleInfoVO>> getUserByMateInfo( HttpServletRequest request, SoLoveMateInfoParam mateInfo ){
		ServiceResult<List<SoLoveUserSimpleInfoVO>> result = new ServiceResult<List<SoLoveUserSimpleInfoVO>>();
		try{
			result = soLoveUserInfoService.getUserByMateInfo(mateInfo);
		}catch(Exception e){
			logger.error("异常发生在"+this.getClass().getName()+"类的getUserByMateInfo方法，异常原因是："+e.getMessage(), e.fillInStackTrace());
		}
		return result;
	}
}
