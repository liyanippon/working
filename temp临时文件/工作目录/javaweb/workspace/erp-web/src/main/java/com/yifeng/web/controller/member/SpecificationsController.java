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
import com.yifeng.identify.api.SpecificationsApi;
import com.yifeng.identify.dto.SpecificationsDto;
import com.yifeng.identify.dto.UnitDto;
@Controller
@RequestMapping(value = "/specifications")
public class SpecificationsController extends BaseController{
	
	@Autowired
	private SpecificationsApi specificationsApi;
	
	/**
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showSpecificationsList.jhtml")
	public String showSpecificationsList() {
		return "/category/specifications";
	}
	
	/**
	 * 查询数据库中所有规格
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/loadSpecificationsPageData.jhtml")
	@ResponseBody
	public PageData<SpecificationsDto> loadSpecifications(HttpServletRequest request) throws BusinessException {
		PageRequest pageRequest = this.getPageRequest(request);
		return specificationsApi.getSpecificationsListByPage(pageRequest);
	}
	
	/**
	 * 根据id查询规格
	 * */
	 @RequestMapping(value = "/getSpecifications.jhtml")
	 @ResponseBody
	 public SpecificationsDto getSpecifications(HttpServletRequest request) {
		 String id = request.getParameter("id");
		 SpecificationsDto specificationsDto = specificationsApi.selectById(id);
		 return specificationsDto;
	 }
	 
	 /**
	  * 根据id修改规格
	  * */
	 @RequestMapping(value = "/updateSpecifications.jhtml")
	 @ResponseBody
	 public boolean updateSpecifications(HttpServletRequest request){
		 SpecificationsDto specificationsDto = new SpecificationsDto();
		 specificationsDto.setId(request.getParameter("id"));
		 specificationsDto.setSpecificationsName(request.getParameter("specificationsName"));
		 specificationsDto.setUpdateBy(this.getUserName(request));
		 return specificationsApi.update(specificationsDto);
	 }
	 
	 /**
	  * 根据id删除规格
	  * */
	 @RequestMapping(value = "/deleteSpecifications.jhtml")
	 @ResponseBody
	 public int deleteSpecifications(HttpServletRequest request) {
		 String id = request.getParameter("id");
		 return specificationsApi.delete(id);
	 }
	 
	 /**
	  * 新建规格
	  * */
	 @RequestMapping(value = "/createSpecifications.jhtml")
	 @ResponseBody
	 public void createSpecifications(SpecificationsDto specificationsDto,HttpServletRequest request){
		 specificationsDto.setSpecificationsName(request.getParameter("specificationsName"));
		 specificationsDto.setCreateBy(this.getUserName(request));
		 specificationsDto.setUpdateBy(this.getUserName(request));
		 this.specificationsApi.insert(specificationsDto);
	 }
	 /**
	  * 获取规格
	  * */
	 @RequestMapping(value = "/getAllSpecifications.jhtml")
		@ResponseBody
		public List<SpecificationsDto> getAllSpecifications(HttpServletRequest request) throws BusinessException{
			return specificationsApi.getAllSpecifications();
		}
	 
} 
