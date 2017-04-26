package com.yifeng.web.controller.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds.business.controller.BaseController;
import com.ds.business.pagination.PageData;
import com.ds.business.pagination.PageRequest;
import com.ds.fw.exception.BusinessException;
import com.yifeng.identify.api.OutboundRepertoryApi;
import com.yifeng.identify.api.RepertoryApi;
import com.yifeng.identify.api.VarietiesApi;
import com.yifeng.identify.dto.RepertoryDto;
import com.yifeng.identify.dto.VarietiesDto;
@Controller
@RequestMapping(value = "/varieties")
public class VarietiesController extends BaseController{
	@Autowired
	private RepertoryApi repertoryApi;
	@Autowired
	private VarietiesApi varietiesApi;
	
	/**
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showVarietiesList.jhtml")
	public String showVarietiesList() {
		return "/category/varieties";
	}
	
	/**
	 * 查询数据库中所有类别
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/loadVarietiesPageData.jhtml")
	@ResponseBody
	public PageData<VarietiesDto> loadUserListByUser(HttpServletRequest request) throws BusinessException {
		PageRequest pageRequest = this.getPageRequest(request);
		return varietiesApi.getVarietiesListByPage(pageRequest);
	}
	
	/**
	 * 根据id查询类别 
	 * */
	@RequestMapping(value = "/getVarieties.jhtml")
    @ResponseBody
    public VarietiesDto getVarieties(HttpServletRequest request){
		String id = request.getParameter("id");
		VarietiesDto varietiesDto = varietiesApi.selectById(id);
		return varietiesDto;
	}
	
	/**
	 * 根据id修改类别
	 * */
    @RequestMapping(value = "/updateVarieties.jhtml")
    @ResponseBody
    public boolean upupdateVarieties(HttpServletRequest request){
    	VarietiesDto varietiesDto = new VarietiesDto();
    	varietiesDto.setVarietiesName(request.getParameter("varietiesName"));
    	varietiesDto.setUpdateBy(this.getUserName(request));
    	varietiesDto.setId(request.getParameter("id"));
    	return varietiesApi.update(varietiesDto);
    }
	
	/**
	 * 根据id删除类别
	 * */
    @RequestMapping(value = "/deleteVarieties.jhtml")
    @ResponseBody
    public int deleteVarieties(HttpServletRequest request){
    	String id = request.getParameter("id");
    	return varietiesApi.delete(id);
    }
    
    /**
     * 新建类别
     * */
    @RequestMapping(value = "/createVarieties.jhtml")
    @ResponseBody
    public void createVarieties(VarietiesDto varietiesDto,HttpServletRequest request){
    	varietiesDto.setVarietiesName(request.getParameter("varietiesName"));
    	varietiesDto.setCreateBy(this.getUserName(request));
    	varietiesDto.setUpdateBy(this.getUserName(request));
    	this.varietiesApi.insert(varietiesDto);
    }
    /**
	 * 查询数据库中所有类别
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/getVarietiesList.jhtml")
	@ResponseBody
	public List<VarietiesDto> getVarietiesList() throws BusinessException {
		return varietiesApi.getVarietiesList();
	}
	
}
