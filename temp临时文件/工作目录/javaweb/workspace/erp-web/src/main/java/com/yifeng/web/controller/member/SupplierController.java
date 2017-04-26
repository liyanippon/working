package com.yifeng.web.controller.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds.business.controller.BaseController;
import com.ds.business.pagination.PageData;
import com.ds.business.pagination.PageRequest;
import com.ds.fw.exception.BusinessException;
import com.yifeng.identify.api.SupplierApi;
import com.yifeng.identify.dto.SupplierDto;

@Controller
@RequestMapping(value = "/supplier")
public class SupplierController extends BaseController {

	@Autowired
	private SupplierApi supplierApi;

	/**
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showSupplierList.jhtml")
	public String showUnitList() {
		return "/supplier/supplierList";
	}

	/**
	 * 查询数据库中所有供应商
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/loadSupplierPageData.jhtml")
	@ResponseBody
	public PageData<SupplierDto> loadSupplier(HttpServletRequest request) throws BusinessException {
		PageRequest pageRequest = this.getPageRequest(request);

		String supplierName = request.getParameter("supplierName");// 供应商名称
		pageRequest.getConditions().put("supplierName", supplierName);

		return supplierApi.getSupplierListByPage(pageRequest);
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
	 * 修改供应商信息
	 */
	@RequestMapping(value = "/updateSupplier.jhtml")
	@ResponseBody
	public boolean updateSupplier(HttpServletRequest request) {
		SupplierDto supplierDto = new SupplierDto();
		supplierDto.setId(request.getParameter("id"));
		supplierDto.setCompanyName(request.getParameter("companyName"));
		supplierDto.setSupplierName(request.getParameter("supplierName"));
		supplierDto.setPeople(request.getParameter("people"));
		supplierDto.setTel(request.getParameter("tel"));
		supplierDto.setAdress(request.getParameter("adress"));
		supplierDto.setUpdateBy(this.getUserName(request));
		supplierDto.setMome(request.getParameter("mome"));
		return supplierApi.update(supplierDto);
	}

	/**
	 * 点击详情页面跳转
	 */
	@RequestMapping(value = "/supplierDetail.jhtml")
	public String supplierDetail(HttpServletRequest request) {
		return "/supplier/supplierDetail";
	}

	/**
	 * 新建供应商
	 */
	@RequestMapping(value = "/createSupplier.jhtml")
	@ResponseBody
	public void createSupplier(SupplierDto supplierDto, HttpServletRequest request) {
		supplierDto.setCompanyName(request.getParameter("companyName"));
		supplierDto.setSupplierName(request.getParameter("supplierName"));
		supplierDto.setUpdateBy(this.getUserName(request));
		supplierDto.setCreateBy(this.getUserName(request));
		supplierDto.setAdress(request.getParameter("adress"));
		supplierDto.setMome(request.getParameter("mome"));
		supplierDto.setPeople(request.getParameter("people"));
		supplierDto.setTel(request.getParameter("tel"));
		this.supplierApi.insert(supplierDto);
	}

}
