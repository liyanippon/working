package com.yifeng.web.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds.business.controller.BaseController;
import com.ds.business.pagination.PageData;
import com.ds.business.pagination.PageRequest;
import com.ds.fw.exception.BusinessException;
import com.yifeng.identify.api.UnitApi;
import com.yifeng.identify.dto.CategoryDto;
import com.yifeng.identify.dto.UnitDto;
@Controller
@RequestMapping(value = "/unit")
public class UnitController extends BaseController{
	
	@Autowired
	private UnitApi unitApi;
	
	/**
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showUnitList.jhtml")
	public String showUnitList() {
		return "/category/unit";
	}
	
	/**
	 * 查询数据库中所有单位
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/loadUnitPageData.jhtml")
	@ResponseBody
	public PageData<UnitDto> loadUnitListByUser(HttpServletRequest request) throws BusinessException {
		PageRequest pageRequest = this.getPageRequest(request);
		return unitApi.getVarietiesListByPage(pageRequest);
	}
	
	/**
	 * 根据id查询单位
	 * */
	@RequestMapping(value = "/getUnit.jhtml")
	@ResponseBody
	public UnitDto getUnit(HttpServletRequest request){
		String id = request.getParameter("id");
		UnitDto unitDto = unitApi.selectById(id);
		return unitDto;
	}
	
	/**
	 * 根据id修改单位
	 * */
	@RequestMapping(value = "/updateUnit.jhtml")
	@ResponseBody
	public boolean updateUnit(HttpServletRequest request){
		UnitDto unitDto = new UnitDto();
		unitDto.setId(request.getParameter("id"));
		unitDto.setUnitName(request.getParameter("unitName"));
		unitDto.setUpdateBy(this.getUserName(request));
		return unitApi.update(unitDto);
	}
	
	/**
	 * 根据id删除单位
	 * */
	@RequestMapping(value = "/deleteUnit.jhtml")
	@ResponseBody
	public int deleteUnit(HttpServletRequest request){
		String id = request.getParameter("id");
		return unitApi.delete(id);
	}
	
	/**
	 * 新建单位
	 * */
	@RequestMapping(value = "/createUnit.jhtml")
	@ResponseBody
	public void createUnit(UnitDto unitDto,HttpServletRequest request){
		unitDto.setUnitName(request.getParameter("unitName"));
		unitDto.setCreateBy(this.getUserName(request));
		unitDto.setUpdateBy(this.getUserName(request));
		this.unitApi.insert(unitDto);
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/getAllUnit.jhtml")
	@ResponseBody
	public List<UnitDto> getAllUnit(HttpServletRequest request) throws BusinessException{
		return unitApi.getAllUnit();
	}
	
	
}
