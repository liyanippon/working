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
import com.ds.identify.dto.RoleGroupDto;
import com.yifeng.identify.api.CategoryApi;
import com.yifeng.identify.api.RepertoryApi;
import com.yifeng.identify.dto.CategoryDto;
import com.yifeng.identify.dto.RepertoryDto;
@Controller
@RequestMapping(value = "/category")
public class CategoryController extends BaseController{
	@Autowired
	private RepertoryApi repertoryApi;
	@Autowired
	private CategoryApi categoryApi;
	
	/**
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showCategoryList.jhtml")
	public String showStaffList() {
		return "/category/category";
	}
	
	/**
	 * 查询数据库中所有品种
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/loadCategoryPageData.jhtml")
	@ResponseBody
	public PageData<CategoryDto> loadUserListByUser(HttpServletRequest request) throws BusinessException {
		PageRequest pageRequest = this.getPageRequest(request);
		return categoryApi.getCategoryListByPage(pageRequest);
	}
	
	 /**
     * 根据id查询品种
     */
    @RequestMapping(value = "/getCategory.jhtml")
    @ResponseBody
    public CategoryDto getCategory(HttpServletRequest request) {
        String id = request.getParameter("id");
        CategoryDto categoryDto = categoryApi.selectById(id);
        return categoryDto;
    }
	
	/**
	 * 根据id修改品种
	 */
	@RequestMapping(value = "/updateCategory.jhtml")
	@ResponseBody
	public boolean updateCategory(HttpServletRequest request){
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setId(request.getParameter("id"));
		categoryDto.setCategoryName(request.getParameter("categoryName"));
		categoryDto.setUpdateBy(this.getUserName(request));
		return categoryApi.update(categoryDto);
	}
	
	/**
     * 根据id删除品种
     */
    @RequestMapping(value = "/deleteCategory.jhtml")
    @ResponseBody
    public int deleteCategory(HttpServletRequest request) {
        String id = request.getParameter("id");
        return categoryApi.delete(id);
    }
    
    /**
     * 新建品种
     * */
    @RequestMapping(value = "/createCategory.jhtml")
    @ResponseBody
    public void createCategory(CategoryDto categoryDto,HttpServletRequest request){
    	categoryDto.setCategoryName(request.getParameter("categoryName"));
    	categoryDto.setCreateBy(this.getUserName(request));
    	categoryDto.setUpdateBy(this.getUserName(request));
    	this.categoryApi.insert(categoryDto);
    }
    /**
	 * 
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/getrepertoryProducts.jhtml")
	@ResponseBody
	public List<RepertoryDto> getrepertoryProducts(HttpServletRequest request) throws BusinessException{
		Map map=new HashMap();//传值工具,以备后患		
		return repertoryApi.selectAllRepertory(map);
	}
	/**
	 * 
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/getAllCategory.jhtml")
	@ResponseBody
	public List<CategoryDto> getAllCategory(HttpServletRequest request) throws BusinessException{
		return categoryApi.getCategory();
	}
}
