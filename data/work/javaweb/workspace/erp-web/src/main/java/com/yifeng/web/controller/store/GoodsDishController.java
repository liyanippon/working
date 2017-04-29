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
import com.yifeng.identify.api.GoodsPlaceApi;
import com.yifeng.identify.dto.GoodsDishDto;
import com.yifeng.identify.dto.GoodsPlaceDto;


/**
 * 货盘管理
 * 
 * @author yangqili
 *
 */
@Controller
@RequestMapping(value = "/store")
public class GoodsDishController extends BaseController {
	@Autowired
	private GoodsDishApi goodsDishApi;
	@Autowired
	private GoodsPlaceApi goodsPlaceApi;
    /**
	 * 货盘管理页面跳转
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showGoodsDishList.jhtml")
	public String showGoodsDishList() {
		return "/store/goodsDishList";
	}
	/**
	 * 根据查询条件查询货盘信息
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/loadGoodsDishPageData.jhtml")
	@ResponseBody
	public PageData<GoodsDishDto> loadPositionListBycondition(HttpServletRequest request)
			throws BusinessException {
		PageRequest pageRequest = this.getPageRequest(request);
		pageRequest.getConditions().put("deleted", "0");// 是否删除
		return goodsDishApi.getGoodsDishList(pageRequest);
	}
	
	/**
	 * 新建货盘
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/addOrUpdateGoodsDish.jhtml")
	@ResponseBody
	public String addOrUpdateGoodsDish(GoodsDishDto goodsDishDto, HttpServletRequest request) throws BusinessException {
		String userName = this.getUserName(request);// 当前用户
		String oldId = goodsDishDto.getId();
		if (StringUtils.isEmpty(oldId)) {// 只有新建的时候添加创建人和创建时间
			goodsDishDto.setCreateBy(userName);
			goodsDishDto.setCreateTime(new Date());// 创建时间
			goodsDishDto.setDelete("0");
		} else {
			GoodsDishDto oldDto = goodsDishApi.getDtoById(oldId);// 获取数据库中的值
			goodsDishDto.setCreateBy(oldDto.getCreateBy());
			goodsDishDto.setCreateTime(oldDto.getCreateTime());
			goodsDishDto.setDelete(oldDto.getDelete());// 有效无效
		}
		
		goodsDishDto.setUpdateBy(userName);
		goodsDishDto.setUpdateTime(new Date());// 更新时间

		String returnid = this.goodsDishApi.insertOrUpdate(goodsDishDto);
		if (StringUtils.isNotEmpty(returnid)) {
			return "true";
		}
		return "false";

	}

	/**
	 * 根据id查询信息
	 * 
	 * @param request
	 * @return GoodsDishDto
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/getGoodsDishDetail.jhtml")
	@ResponseBody
	public GoodsDishDto getGoodsDishDetail(HttpServletRequest request) throws BusinessException {
		String id = request.getParameter("id");
		GoodsDishDto goodsDishDto = goodsDishApi.getDtoById(id);
		return goodsDishDto;
	}
	
	/**
	 * 根据id删除
	 * 
	 * @param request
	 * @return boolean
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/deleteGoodsDishPageData.jhtml")
	@ResponseBody
	public String deletePositionPageData(HttpServletRequest request) throws BusinessException {
		String id = request.getParameter("id");
		if (StringUtils.isEmpty(id)) {
			return "false";
		}
		
		//获取货位数值
	    List<GoodsPlaceDto> place = goodsPlaceApi.getAllGoodsPlaceDtoList(id);
	    if(null==place||place.size()==0){
	    	if(goodsDishApi.delete(id)){
	    		return "true";
	    	}else{
	    		return "false";
	    	}
	    	
		}else{
			return "position";
		}
	}
	/**
	 * 获取所有货盘
	 * 
	 * @param request
	 * @return GoodsDishDto
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/getAllGoodsDishDto.jhtml")
	@ResponseBody
	public List<GoodsDishDto> getAllGoodsDishDtoList(HttpServletRequest request) throws BusinessException {
		String positionid = request.getParameter("positionid");
		return  goodsDishApi.getAllGoodsDishDtoList(positionid);
	}
}
