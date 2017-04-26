package com.yifeng.web.controller.store;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds.business.controller.BaseController;
import com.ds.business.pagination.PageData;
import com.ds.business.pagination.PageRequest;
import com.ds.fw.exception.BusinessException;
import com.yifeng.identify.api.NumberApi;
import com.yifeng.identify.api.StoreHouseApi;
import com.yifeng.identify.api.StorePositionApi;
import com.yifeng.identify.dto.StoreHouseDto;
import com.yifeng.identify.dto.StorePositionDto;


/**
 * 仓库管理
 * 
 * @author yangqili
 *
 */
@Controller
@RequestMapping(value = "/store")
public class StoreHouseController extends BaseController {
	@Autowired
	private StoreHouseApi storeHouseApi;
	@Autowired
	private StorePositionApi storePositionApi;
	@Autowired
	private NumberApi numberApi;
    /**
	 * 仓库管理页面跳转
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showStoreHouseList.jhtml")
	public String showStoreHouseList() {
		return "/store/storeList";
	}
	/**
	 * 根据查询条件查询仓库信息
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/loadStorePageData.jhtml")
	@ResponseBody
	public PageData<StoreHouseDto> loadStoreListBycondition(HttpServletRequest request)
			throws BusinessException {
		PageRequest pageRequest = this.getPageRequest(request);
		pageRequest.getConditions().put("deleted", "0");// 是否删除
		return storeHouseApi.getStoreHouseist(pageRequest);
	}
	
	/**
	 * 新建部门
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/addOrUpdateStoreHouse.jhtml")
	@ResponseBody
	public String addOrUpdateStoreHouse(StoreHouseDto storeHouseDto, HttpServletRequest request) throws BusinessException {
		String userName = this.getUserName(request);// 当前用户
		String oldId = storeHouseDto.getId();
		if (StringUtils.isEmpty(oldId)) {// 只有新建的时候添加创建人和创建时间
			storeHouseDto.setCreateBy(userName);
			storeHouseDto.setCreateTime(new Date());// 创建时间
			storeHouseDto.setDelete("0");
		} else {
			StoreHouseDto oldDto = storeHouseApi.getDtoById(oldId);// 获取数据库中的值
			storeHouseDto.setCreateBy(oldDto.getCreateBy());
			storeHouseDto.setCreateTime(oldDto.getCreateTime());
			storeHouseDto.setDelete(oldDto.getDelete());// 有效无效
		}
		
		storeHouseDto.setUpdateBy(userName);
		storeHouseDto.setUpdateTime(new Date());// 更新时间

		String returnid = this.storeHouseApi.insertOrUpdate(storeHouseDto);
		if (StringUtils.isNotEmpty(returnid)) {
			return "true";
		}
		return "false";

	}

	/**
	 * 根据id查询信息
	 * 
	 * @param request
	 * @return StoreHouseDto
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/getStoreDetail.jhtml")
	@ResponseBody
	public StoreHouseDto getStoreDetail(HttpServletRequest request) throws BusinessException {
		String id = request.getParameter("id");
		StoreHouseDto storeHouseDto = storeHouseApi.getDtoById(id);
		return storeHouseDto;
	}
	
	/**
	 * 根据id删除
	 * 
	 * @param request
	 * @return boolean
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/deleteStorePageData.jhtml")
	@ResponseBody
	public String deleteStorePageData(HttpServletRequest request) throws BusinessException {
		String id = request.getParameter("id");
		if (StringUtils.isEmpty(id)) {
			return "false";
		}
		//获取库位数值
	    List<StorePositionDto>position = storePositionApi.getAllStorePositionDtoList(id);
	    if(null==position&&position.size()==0){
	    	if(storeHouseApi.delete(id)){
	    		return "true";
	    	}else{
	    		return "false";
	    	}
	    	
		}else{
			return "position";
		}
	}
	/**
	 * 获取所有仓库
	 * 
	 * @param request
	 * @return StoreHouseDto
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/getAllStoreHouse.jhtml")
	@ResponseBody
	public List<StoreHouseDto> getAllDtoList() throws BusinessException {
		return  storeHouseApi.getAllDtoList();
	}
	/**
	 * 生成仓库编码
	 */
	@RequestMapping(value = "/getNumberByType.jhtml")
	@ResponseBody
	public String getNumberByType(){
		String type = "batch";
		return numberApi.getNumberByType(type);
		
	}
}
