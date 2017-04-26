package com.yifeng.web.controller.warehouse;

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
import com.ds.business.dto.CodeDto;
import com.ds.business.pagination.PageData;
import com.ds.business.pagination.PageRequest;
import com.ds.fw.exception.BusinessException;
import com.ds.system.api.CodeApi;
import com.yifeng.identify.api.RepertoryApi;
import com.yifeng.identify.api.WarehouseApi;
import com.yifeng.identify.dto.RepertoryDto;
import com.yifeng.identify.dto.WarehouseDto;
import com.yifeng.web.Constants;

/**
 * 入库管理
 * 
 * @author yangqili
 *
 */
@Controller
@RequestMapping(value = "/warehouse")
public class WarehouseController extends BaseController {
	@Autowired
	private WarehouseApi warehouseApi;
	@Autowired
	private CodeApi codeApi;
	@Autowired
	private RepertoryApi repertoryApi;

	/**
	 * 入库页面跳转
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showWarehouseList.jhtml")
	public String showWarehouseList() {
		return "/warehouse/warehouseList";
	}

	/**
	 * 分页显示数据
	 * 
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/getWarehouseListBycondition.jhtml")
	@ResponseBody
	public PageData<WarehouseDto> getWarehouseListBycondition(HttpServletRequest request) throws BusinessException {
		PageRequest pageRequest = this.getPageRequest(request);
		pageRequest.getConditions().put("deleted", "0");// 是否删除
		return warehouseApi.getWarehouseList(pageRequest);
	}
	/**
	 * 根据入库单显示数据
	 * 
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/getWarehouseListBywarehousenum.jhtml")
	@ResponseBody
	public PageData<WarehouseDto> getWarehouseListBywarehousenum(HttpServletRequest request) throws BusinessException {
		PageRequest pageRequest = this.getPageRequest(request);
		pageRequest.getConditions().put("deleted", "0");// 是否删除
		String warehousenum = request.getParameter("rkwarehousenum");
		pageRequest.getConditions().put("warehousenum", warehousenum);// 是否删除
		if(StringUtils.isEmpty(warehousenum)){
			return new PageData();
		}
		return warehouseApi.getWarehouseList(pageRequest);
	}

	
	/**
	 * 根据主键id删除入库信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delWarehouseDtoById.jhtml")
	@ResponseBody
	public String delWarehouseDtoById(HttpServletRequest request) {
		String id = request.getParameter("id");
		if (StringUtils.isEmpty(id)) {
			return "false";
		} else {
			warehouseApi.delWarehouseDtoById(id);
			return "true";
		}
	}

	/**
	 * 获取状态编码方式
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/getStatusCode.jhtml")
	@ResponseBody
	public List<CodeDto> getStatusCode(HttpServletRequest request) {
		String status = Constants.STATUS_TYPE;
		try {
			return codeApi.getCodeListByType(status);
		} catch (BusinessException e) {
			e.printStackTrace();
			return null;
		}

	}

	
}
