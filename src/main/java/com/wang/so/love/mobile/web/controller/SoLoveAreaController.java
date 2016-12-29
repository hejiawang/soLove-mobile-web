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
import com.wang.so.love.service.entity.SoLoveAreaEntity;
import com.wang.so.love.service.service.SoLoveAreaService;

/**
 * 地区controller
 * 
 * @author HeJiawang
 * @date   2016.12.08
 */
@Controller
@RequestMapping(value = "/area")
public class SoLoveAreaController extends BaseController {

	/**
	 * logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(SoLoveAreaController.class);
	
	/**
	 * soLoveAreaService
	 */
	@Autowired
	private SoLoveAreaService soLoveAreaService;
	
	/**
	 * 根据父级地址获取子地址集合
	 * 
	 * @param parentID 地址父ID
	 * @return 地址信息集合
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET})
	@ResponseBody
	public ServiceResult<List<SoLoveAreaEntity>> getAreaListByParentID( HttpServletRequest request, Integer parentID ){
		ServiceResult<List<SoLoveAreaEntity>> result = new ServiceResult<List<SoLoveAreaEntity>>();
		try{
			if( parentID == null ) parentID = 1001;
			result = soLoveAreaService.getAreaListByParentID(parentID);
		}catch( Exception e ){
			logger.error("异常发生在"+this.getClass().getName()+"类的getAreaListByParentID方法，异常原因是："+e.getMessage(), e.fillInStackTrace());
		}
		return result;
	}
}
