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
import com.yifeng.identify.api.GoodsDishApi;
import com.yifeng.identify.api.StorePositionApi;
import com.yifeng.identify.dto.GoodsDishDto;
import com.yifeng.identify.dto.StorePositionDto;


/**
 * 库位管理
 * 
 * @author yangqili
 *
 */
@Controller
@RequestMapping(value = "/store")
public class StorePositionController extends BaseController {
	@Autowired
	private StorePositionApi storePositionApi;
	@Autowired
	private GoodsDishApi goodsDishApi;
    /**
	 * 仓库管理页面跳转
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showStorePositionList.jhtml")
	public String showStorePositionList() {
		return "/store/storePositionList";
	}
	/**
	 * 根据查询条件查询仓库信息
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/loadStorePositionPageData.jhtml")
	@ResponseBody
	public PageData<StorePositionDto> loadPositionListBycondition(HttpServletRequest request)
			throws BusinessException {
		PageRequest pageRequest = this.getPageRequest(request);
		pageRequest.getConditions().put("deleted", "0");// 是否删除
		return storePositionApi.getStorePositionist(pageRequest);
	}
	
	/**
	 * 新建部门
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/addOrUpdateStorePosition.jhtml")
	@ResponseBody
	public String addOrUpdateStorePosition(StorePositionDto storePositionDto, HttpServletRequest request) throws BusinessException {
		String userName = this.getUserName(request);// 当前用户
		String oldId = storePositionDto.getId();
		if (StringUtils.isEmpty(oldId)) {// 只有新建的时候添加创建人和创建时间
			storePositionDto.setCreateBy(userName);
			storePositionDto.setCreateTime(new Date());// 创建时间
			storePositionDto.setDelete("0");
		} else {
			StorePositionDto oldDto = storePositionApi.getDtoById(oldId);// 获取数据库中的值
			storePositionDto.setCreateBy(oldDto.getCreateBy());
			storePositionDto.setCreateTime(oldDto.getCreateTime());
			storePositionDto.setDelete(oldDto.getDelete());// 有效无效
		}
		
		storePositionDto.setUpdateBy(userName);
		storePositionDto.setUpdateTime(new Date());// 更新时间

		String returnid = this.storePositionApi.insertOrUpdate(storePositionDto);
		if (StringUtils.isNotEmpty(returnid)) {
			return "true";
		}
		return "false";

	}

	/**
	 * 根据id查询部门信息
	 * 
	 * @param request
	 * @return StoreHouseDto
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/getStorePositionDetail.jhtml")
	@ResponseBody
	public StorePositionDto getStorePositionDetail(HttpServletRequest request) throws BusinessException {
		String id = request.getParameter("id");
		StorePositionDto storePositionDto = storePositionApi.getDtoById(id);
		return storePositionDto;
	}
	
	/**
	 * 根据id删除
	 * 
	 * @param request
	 * @return boolean
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/deleteStorePositionPageData.jhtml")
	@ResponseBody
	public String deletePositionPageData(HttpServletRequest request) throws BusinessException {
		String id = request.getParameter("id");
		if (StringUtils.isEmpty(id)) {
			return "false";
		}
		//获取库盘数值
	    List<GoodsDishDto> dish = goodsDishApi.getAllGoodsDishDtoList(id);
	    if(null==dish&&dish.size()==0){
	    	if(storePositionApi.delete(id)){
	    		return "true";
	    	}else{
	    		return "false";
	    	}
	    	
		}else{
			return "position";
		}
	}
	/**
	 * 根据条件获取货盘
	 * 
	 * @param request
	 * @return GoodsDishDto
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/getAllStorePositionDto.jhtml")
	@ResponseBody
	public List<StorePositionDto> getAllStorePositionDtoList(HttpServletRequest request) throws BusinessException {
		String storeid = request.getParameter("storeid");
		 return  storePositionApi.getAllStorePositionDtoList(storeid);
	}
}
