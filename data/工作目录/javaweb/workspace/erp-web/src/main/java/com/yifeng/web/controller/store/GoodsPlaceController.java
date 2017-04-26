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
import com.yifeng.identify.api.GoodsPlaceApi;
import com.yifeng.identify.dto.GoodsPlaceDto;


/**
 * 货位管理
 * 
 * @author yangqili
 *
 */
@Controller
@RequestMapping(value = "/store")
public class GoodsPlaceController extends BaseController {
	@Autowired
	private GoodsPlaceApi goodsPlaceApi;
    /**
	 * 仓库管理页面跳转
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showGoodsPlaceList.jhtml")
	public String showGoodsPlaceList() {
		return "/store/goodsPlaceList";
	}
	/**
	 * 根据查询条件查询货位信息
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/loadGoodsPlacePageData.jhtml")
	@ResponseBody
	public PageData<GoodsPlaceDto> loadPositionListBycondition(HttpServletRequest request)
			throws BusinessException {
		PageRequest pageRequest = this.getPageRequest(request);
		pageRequest.getConditions().put("deleted", "0");// 是否删除
		return goodsPlaceApi.getGoodsPlaceList(pageRequest);
	}
	
	/**
	 * 新建货位
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/addOrUpdateGoodsPlace.jhtml")
	@ResponseBody
	public String addOrUpdateGoodsPlace(GoodsPlaceDto goodsPlaceDto, HttpServletRequest request) throws BusinessException {
		String userName = this.getUserName(request);// 当前用户
		String oldId = goodsPlaceDto.getId();
		if (StringUtils.isEmpty(oldId)) {// 只有新建的时候添加创建人和创建时间
			goodsPlaceDto.setCreateBy(userName);
			goodsPlaceDto.setCreateTime(new Date());// 创建时间
			goodsPlaceDto.setDelete("0");
		} else {
			GoodsPlaceDto oldDto = goodsPlaceApi.getDtoById(oldId);// 获取数据库中的值
			goodsPlaceDto.setCreateBy(oldDto.getCreateBy());
			goodsPlaceDto.setCreateTime(oldDto.getCreateTime());
			goodsPlaceDto.setDelete(oldDto.getDelete());// 有效无效
		}
		
		goodsPlaceDto.setUpdateBy(userName);
		goodsPlaceDto.setUpdateTime(new Date());// 更新时间

		String returnid = this.goodsPlaceApi.insertOrUpdate(goodsPlaceDto);
		if (StringUtils.isNotEmpty(returnid)) {
			return "true";
		}
		return "false";

	}

	/**
	 * 根据id查询信息
	 * 
	 * @param request
	 * @return GoodsPlaceDto
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/getGoodsPlaceDetail.jhtml")
	@ResponseBody
	public GoodsPlaceDto getGoodsPlaceDetail(HttpServletRequest request) throws BusinessException {
		String id = request.getParameter("id");
		GoodsPlaceDto goodsPlaceDto = goodsPlaceApi.getDtoById(id);
		return goodsPlaceDto;
	}
	
	/**
	 * 根据id删除
	 * 
	 * @param request
	 * @return boolean
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/deleteGoodsPlacePageData.jhtml")
	@ResponseBody
	public boolean deletePositionPageData(HttpServletRequest request) throws BusinessException {
		String id = request.getParameter("id");
		if (StringUtils.isEmpty(id)) {
			return false;
		}
		return goodsPlaceApi.delete(id);
	}
	/**
	 * 获取所有货位
	 * 
	 * @param request
	 * @return GoodsPlaceDto
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/getAllGoodsPlace.jhtml")
	@ResponseBody
	public List<GoodsPlaceDto> getAllGoodsPlaceDtoList(HttpServletRequest request) throws BusinessException {
		String dishid = request.getParameter("dishid");
		return  goodsPlaceApi.getAllGoodsPlaceDtoList(dishid);
	}
}
