package com.yifeng.web.controller.org;

import java.util.Date;
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
import com.ds.identify.api.DepartApi;
import com.ds.identify.dto.DepartDto;

/**
 * 单位维护
 * 
 * @author yangqili
 *
 */
@Controller
@RequestMapping(value = "/org")
public class DepartController extends BaseController {
	@Autowired
	private DepartApi departApi;

	/**
	 * 部门维护页面跳转
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showDepartList.jhtml")
	public String showDepartList() {
		return "/org/departList";
	}

	/**
	 * 根据查询条件查询数据库中Code信息
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/loadDepartPageData.jhtml")
	@ResponseBody
	public PageData<Map<String, Object>> loadDepartListBycondition(HttpServletRequest request)
			throws BusinessException {
		PageRequest pageRequest = this.getPageRequest(request);
		pageRequest.getConditions().put("deleted", "0");// 是否删除
		return departApi.getDepartist(pageRequest);
	}

	/**
	 * 新建部门
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/addOrUpdateDepart.jhtml")
	@ResponseBody
	public String addOrUpdateDepart(DepartDto departDto, HttpServletRequest request) throws BusinessException {
		String userName = this.getUserName(request);// 当前用户
		String oldId = departDto.getId();
		if (StringUtils.isEmpty(oldId)) {// 只有新建的时候添加创建人和创建时间
			departDto.setCreateBy(userName);
			departDto.setCreateTime(new Date());// 创建时间
			departDto.setDelete("0");
		} else {
			DepartDto oldDto = departApi.getDtoById(oldId);// 获取数据库中的值
			departDto.setCreateBy(oldDto.getCreateBy());
			departDto.setCreateTime(oldDto.getCreateTime());
			departDto.setDelete(oldDto.getDelete());// 有效无效
		}
		if (StringUtils.isEmpty(departDto.getpId())) {//如果上级部门为空，默认为-1
			departDto.setpId("-1");
		}
		departDto.setUpdateBy(userName);
		departDto.setUpdateTime(new Date());// 更新时间

		String returnid = this.departApi.insertOrUpdateOrg(departDto);
		if (StringUtils.isNotEmpty(returnid)) {
			return "true";
		}
		return "false";

	}

	/**
	 * 根据id删除
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/deleteDepartPageData.jhtml")
	@ResponseBody
	public boolean deleteDepartPageData(HttpServletRequest request) throws BusinessException {
		String id = request.getParameter("id");
		if (StringUtils.isEmpty(id)) {
			return false;
		}
		return departApi.delete(id);
	}

	/**
	 * 根据id查询部门信息
	 * 
	 * @param request
	 * @return pageData
	 * @throws BusinessException
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/getDepartDetail.jhtml")
	@ResponseBody
	public DepartDto getdepartDetial(HttpServletRequest request) throws BusinessException {
		String id = request.getParameter("id");
		DepartDto departDto = departApi.getDtoById(id);
		return departDto;
	}

	/**
	 * 根据公司Id查询出公司下所有部门
	 * 
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/getAllDepartByOrgId.jhtml")
	@ResponseBody
	public List<DepartDto> getAllDepartByOrgId(HttpServletRequest request) throws BusinessException {
		String orgid = request.getParameter("orgId");
		if(StringUtils.isEmpty(orgid)){
			return null;
		}
		List<DepartDto>  allpart =departApi.getAllDepartByOrgId(orgid);
		 return allpart;
	}

}
