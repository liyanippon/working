package com.yifeng.web.controller.warehouse;

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
import com.yifeng.identify.api.CreatNewWarehouseApi;
import com.yifeng.identify.api.SupplierApi;
import com.yifeng.identify.dto.GoodsSupplierMapplingDto;
import com.yifeng.identify.dto.SupplierDto;

@Controller
@RequestMapping(value = "/warehouse")
public class SelectSupplierController extends BaseController {

	@Autowired
	private SupplierApi supplierApi;

	@Autowired
	private CreatNewWarehouseApi creatNewWarehouseApi;

	/**
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showselSupplierList.jhtml")
	public String showselSupplierList() {
		return "/warehouse/selsupplierList";
	}

	/**
	 * 根据id查询
	 */
	@RequestMapping(value = "/getSupplier.jhtml")
	@ResponseBody
	public SupplierDto getSupplier(HttpServletRequest request) {
		String id = request.getParameter("id");
		SupplierDto supplierDto = supplierApi.selectById(id);
		return supplierDto;
	}

	/**
	 * 根据id删除
	 */
	@RequestMapping(value = "/deleteSupplier.jhtml")
	@ResponseBody
	public int deleteSupplier(HttpServletRequest request) {
		String id = request.getParameter("id");
		return supplierApi.delete(id);
	}

	/**
	 * 显示未选择供应商
	 * 
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/getNoneSelectSupplier.jhtml")
	@ResponseBody
	public PageData<SupplierDto> getNoneSelectSupplier(HttpServletRequest request) throws BusinessException {
		PageRequest pageRequest = this.getPageRequest(request);
		List<GoodsSupplierMapplingDto> getGoodsSupplierMapplingDto = null;
		String strId = "";

		String warehouseId = request.getParameter("warehouseId");// 仓库id

		if (StringUtils.isNotEmpty(warehouseId)) {
			getGoodsSupplierMapplingDto = creatNewWarehouseApi.getGoodsSupplierMapplingDtoBywarehouseId(warehouseId);
		}
		if (null != getGoodsSupplierMapplingDto && !getGoodsSupplierMapplingDto.isEmpty()) {
			for (GoodsSupplierMapplingDto goodsSupplierMapplingDto : getGoodsSupplierMapplingDto) {
				strId += "'" + goodsSupplierMapplingDto.getSupplierId() + "',";
			}
			if (StringUtils.isNotEmpty(strId)) {
				strId = strId.substring(0, strId.length() - 1);
				pageRequest.getConditions().put("strId", strId);
			}

		}

		return supplierApi.getSupplierListByPage(pageRequest);
	}
}
