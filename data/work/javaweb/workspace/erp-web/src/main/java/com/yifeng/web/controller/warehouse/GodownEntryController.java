package com.yifeng.web.controller.warehouse;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.yifeng.identify.api.CreatNewWarehouseApi;
import com.yifeng.identify.api.GodownEntryApi;
import com.yifeng.identify.api.RepertoryApi;
import com.yifeng.identify.api.WarehouseApi;
import com.yifeng.identify.dto.GodownEntryDto;
import com.yifeng.identify.dto.RepertoryDto;
import com.yifeng.identify.dto.WarehouseDto;
import com.yifeng.web.Constants;

/**
 * 新增入库单
 * 
 * @author yangqili
 *
 */
@Controller
@RequestMapping(value = "/warehouse")
public class GodownEntryController extends BaseController {
	@Autowired
	private GodownEntryApi godownEntryApi;
	@Autowired
	private RepertoryApi repertoryApi;
	@Autowired
	private WarehouseApi warehouseApi;
	@Autowired
	private CreatNewWarehouseApi creatNewWarehouseApi;
	/**
	 * 新建入库页面跳转
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showGodownEntry.jhtml")
	public String showGodownEntry(String warehouseId) {
		return "/warehouse/godownEntryList";
	}
	/**
	 * 分页显示数据
	 * 
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/getGodownEntryListBycondition.jhtml")
	@ResponseBody
	public PageData<GodownEntryDto> getWarehouseListBycondition(HttpServletRequest request) throws BusinessException {
		PageRequest pageRequest = this.getPageRequest(request);
		pageRequest.getConditions().put("deleted", "0");// 是否删除
		return godownEntryApi.getGodownEntryList(pageRequest);
	}
	/**
	 * 根据主键id删除入库信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delGodownEntryListByById.jhtml")
	@ResponseBody
	public String delGodownEntryListByById(HttpServletRequest request) {
		String godownEntryId = request.getParameter("godownEntryId");
		String warehousenum = request.getParameter("warehousenum");
		if (StringUtils.isEmpty(godownEntryId)) {
			return "false";
		} else {
			godownEntryApi.deleteGodownEntryDto(godownEntryId);
			//同时删除入库信息内容
			warehouseApi.delRkdListBywarehousenum(warehousenum);
			//根据入库单获取供应商list
			creatNewWarehouseApi.delGoodsSupplierMapplingDtoByWarehouseNum(warehousenum);
			return "true";
		}
	}
	
	/**
	 * 更新数据
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/UpdateGodownEntry.jhtml")
	@ResponseBody
	public Map<String, Object> UpdateGodownEntry(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();

		String status = request.getParameter("status");
		String godownEntryId = request.getParameter("godownEntryId");
		String warehousenum = request.getParameter("warehousenum");
		String processBy = request.getParameter("processBy");
		String userName = this.getUserName(request);// 当前用户
		GodownEntryDto upDto = new GodownEntryDto();
		upDto.setGodownEntryId(godownEntryId);
		upDto.setUpdateBy(userName);// 更新人
		upDto.setUpdateTime(new Timestamp(new java.util.Date().getTime()));// 更新时间
		upDto.setProcessBy(processBy);// 处理人
		upDto.setStatus(status);// 状态

		if (status.equals(Constants.STATUS_FAILED_PSAA)) {
			// 未通过提交
			upDto.setSubmitBy(userName);// 提交人
		} else {
			// 正常提交
			upDto.setSubmitBy(userName);// 提交人
		}

		String returnid = godownEntryApi.insertOrUpdate(upDto);
        //如果是已审批，同事将数据插入到库房
		if (status.equals(Constants.STATUS_BEEN_APPROVAL)) {
			//根据入库单获取商品list
			List<WarehouseDto> warehouseList = warehouseApi.getRkdListBywarehousenum(warehousenum);
			for(WarehouseDto each:warehouseList){
				//分别插入到库房表中
				RepertoryDto repertoryDto = this.getRepertoryDto(each);
				repertoryApi.insertOrUpdateRepertory(repertoryDto);
			}
		}

		if (StringUtils.isNotEmpty(returnid)) {
			result.put("succ", true);
		} else {
			result.put("succ", false);
		}

		return result;
	}
	private RepertoryDto getRepertoryDto(WarehouseDto warehouseDto) {
		RepertoryDto repertoryDto = new RepertoryDto();
		repertoryDto.setStorehouse(warehouseDto.getId());
		repertoryDto.setStoreposition(warehouseDto.getStorepositionId());
		repertoryDto.setStoredish(warehouseDto.getGoodsdishId());
		repertoryDto.setStoreplace(warehouseDto.getGoodsplaceId());
		repertoryDto.setVarieties(warehouseDto.getVarietiesId());
		repertoryDto.setCategory(warehouseDto.getCategoryId());
		repertoryDto.setGoodsname(warehouseDto.getGoodsname());
		repertoryDto.setGoodscount(warehouseDto.getGoodscount());
		repertoryDto.setSpecifications(warehouseDto.getSpecificationsId());
		repertoryDto.setCreateTime(new Date());
		repertoryDto.setCreateBy(warehouseDto.getCreateBy());
		repertoryDto.setUpdateTime(new Date());
		repertoryDto.setUpdateBy(warehouseDto.getUpdateBy());
		return repertoryDto;

	}

}
