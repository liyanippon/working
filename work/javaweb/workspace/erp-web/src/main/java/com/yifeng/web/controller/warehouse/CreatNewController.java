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
import com.yifeng.identify.api.NumberApi;
import com.yifeng.identify.api.WarehouseApi;
import com.yifeng.identify.dto.GoodsSupplierMapplingDto;
import com.yifeng.identify.dto.WarehouseDto;

/**
 * 新增入库
 * 
 * @author yangqili
 *
 */
@Controller
@RequestMapping(value = "/warehouse")
public class CreatNewController extends BaseController {
	@Autowired
	private WarehouseApi warehouseApi;
	@Autowired
	private CreatNewWarehouseApi creatNewWarehouseApi;
	@Autowired
	private NumberApi numberApi;

	/**
	 * 新建入库页面跳转
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showCreatNew.jhtml")
	public String showCreatNew(String warehouseId) {
		return "/warehouse/creatNew";
	}

	/**
	 * 审核页面跳转
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showSubmitSh.jhtml")
	public String showSubmitSh() {
		return "/warehouse/submitSH";
	}

	/**
	 * 审批意见页面跳转
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showOpinionSP.jhtml")
	public String showOpinionSP() {
		return "/warehouse/opinionSP";
	}

	/**
	 * 供应商详情页面跳转
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showSelsupplierDetail.jhtml")
	public String showSelsupplierDetail() {
		return "/warehouse/selsupplierDetail";
	}

	/**
	 * 入库商品列表页面跳转
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showRkgoodsDetail.jhtml")
	public String showRugoodsDetail() {
		return "/warehouse/rkgoodsList";
	}
	/**
	 * 打印入库单
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showPrintRkd.jhtml")
	public String showPrintRkd() {
		return "/warehouse/print";
	}
	/**
	 * 新增入库
	 * 
	 * @return
	 */
	@RequestMapping(value = "/creatNewWarehouse.jhtml")
	@ResponseBody
	public Map<String, Object> insertOrUpdateWarehouse(WarehouseDto warehouseDto, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		String userName = this.getUserName(request);// 当前用户
		String oldId = warehouseDto.getId();
		if (StringUtils.isEmpty(oldId)) {// 新建时写入创建人，创建时间
			warehouseDto.setCreateBy(userName);
			warehouseDto.setCreateTime(new Timestamp(new java.util.Date().getTime()));// 创建时间
			warehouseDto.setDelete("0");
		}
		warehouseDto.setUpdateBy(userName);
		warehouseDto.setUpdateTime(new Timestamp(new java.util.Date().getTime()));// 更新时间

		String returnid = this.warehouseApi.insertOrUpdate(warehouseDto);
		
		if (StringUtils.isNotEmpty(returnid)) {
			WarehouseDto dto = this.warehouseApi.getWarehouseDtoById(returnid);
			result.put("succ", true);
			result.put("returnid", returnid);
			result.put("warehousenum", dto.getWarehousenum());
		} else {
			result.put("succ", false);
		}
		return result;

	}

	/**
	 * 根据主键获取Warehouse
	 * 
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/getWarehouseDtoById.jhtml")
	@ResponseBody
	public WarehouseDto getWarehouseDtoById(String id) {
		WarehouseDto warehouseDto = this.warehouseApi.getWarehouseDtoById(id);
		if (null != warehouseDto) {
			return warehouseDto;
		}
		return null;

	}

	/**
	 * 新增供应商关系
	 * 
	 * @param goodsSupplierMappling
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/creatNewGoodsSupplierMappling.jhtml")
	@ResponseBody
	public Map<String, Object> insertOrUpdateGoodsSupplierMappling(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		GoodsSupplierMapplingDto goodsSupplierMapplingDto = new GoodsSupplierMapplingDto();

		String userName = this.getUserName(request);// 当前用户
		String supplier = request.getParameter("supplier");//
		String warehouseNum = request.getParameter("warehousenum");// 入库编号
		String warehouseId = request.getParameter("warehouseId");//

		supplier = supplier.substring(1, supplier.length() - 1);// 去掉[]
		supplier = supplier.replaceAll("\"", "");
		String[] supplierId = supplier.split(",");

		for (int i = 0; i < supplierId.length; i++) {
			if (StringUtils.isNotEmpty(supplierId[i].trim())) {
				goodsSupplierMapplingDto.setWarehouseId(warehouseId);
				goodsSupplierMapplingDto.setWarehouseNum(warehouseNum);
				goodsSupplierMapplingDto.setCreateBy(userName);// 创建人
				goodsSupplierMapplingDto.setCreateTime(new Date());// 创建时间
				goodsSupplierMapplingDto.setDelete("0");
				goodsSupplierMapplingDto.setUpdateBy(userName);// 更新人
				goodsSupplierMapplingDto.setUpdateTime(new Date());// 更新时间

				goodsSupplierMapplingDto.setSupplierId(supplierId[i].trim());// 供应商编号

				this.creatNewWarehouseApi.insert(goodsSupplierMapplingDto);
			}

		}
		List<GoodsSupplierMapplingDto> list = creatNewWarehouseApi
				.getGoodsSupplierMapplingDtoBywarehouseId(warehouseId);
		if (null != list) {
			result.put("succ", true);
			result.put("warehouseId", warehouseId);
		} else {
			result.put("succ", false);
			result.put("warehouseId", "");
		}
		return result;
	}

	/**
	 * 查找供应商关系
	 * 
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/getGoodsSupplierMapplingList.jhtml")
	@ResponseBody
	public PageData<GoodsSupplierMapplingDto> getgetGoodsSupplierMapplingList(HttpServletRequest request)
			throws BusinessException {
		PageRequest pageRequest = this.getPageRequest(request);
		String warehouseId = request.getParameter("warehouseId");
		if (StringUtils.isEmpty(warehouseId)) {
			return null;
		} else {
			pageRequest.getConditions().put("deleted", "0");// 是否删除
			pageRequest.getConditions().put("warehouseId", warehouseId);// 是否删除
			return this.creatNewWarehouseApi.getGoodsSupplierMapplingListBywarehouseId(pageRequest);
		}

	}

	/**
	 * 根据主键id删除供应商关系
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delGoodsSupplierMapplingDtoById.jhtml")
	@ResponseBody
	public String delGoodsSupplierMapplingDtoById(HttpServletRequest request) {
		String id = request.getParameter("id");
		if (StringUtils.isEmpty(id)) {
			return "false";
		} else {
			creatNewWarehouseApi.delGoodsSupplierMapplingDtoById(id);
			return "true";
		}

	}

	/**
	 * 根据仓库id删除供应商关系
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delGoodsSupplierMapplingDtoByWarehouseId.jhtml")
	@ResponseBody
	public String delGoodsSupplierMapplingDtoByWarehouseId(HttpServletRequest request) {
		String warehouseId = request.getParameter("warehouseId");
		if (StringUtils.isEmpty(warehouseId)) {
			return "false";
		} else {
			creatNewWarehouseApi.delGoodsSupplierMapplingDtoByWarehouseId(warehouseId);
			return "true";
		}

	}

	/**
	 * 根据仓库id查询供应商关系
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getGoodsSupplierMapplingDtoBywarehouseId.jhtml")
	@ResponseBody
	public String getGoodsSupplierMapplingDtoBywarehouseId(HttpServletRequest request) {
		String warehouseId = request.getParameter("warehouseId");
		if (StringUtils.isEmpty(warehouseId)) {
			return "false";
		} else {
			creatNewWarehouseApi.getGoodsSupplierMapplingDtoBywarehouseId(warehouseId);
			return "true";
		}

	}

	/**
	 * 生成入库单编码
	 */
	@RequestMapping(value = "/getRkdNumberByType.jhtml")
	@ResponseBody
	public String getRkdNumberByType() {
		String type = "warehouse";
		return numberApi.getNumberByType(type);

	}

}
