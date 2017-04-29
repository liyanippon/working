package com.yifeng.web.controller.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds.business.controller.BaseController;
import com.ds.business.pagination.PageData;
import com.ds.business.pagination.PageRequest;
import com.ds.fw.exception.BusinessException;
import com.ds.identify.api.RoleApi;
import com.ds.identify.dto.MenuRoleMappingDto;
import com.ds.system.api.MenuApi;
import com.ds.system.dto.MenuDto;
import com.yifeng.web.vo.RelationVO;
@Controller
@RequestMapping(value = "/menuRelation")
public class MenuRoleRelationController extends BaseController{
	
	 @Autowired
	 private RoleApi roleApi;
	 @Autowired
	  private MenuApi menuApi;
	 
	@RequestMapping(value = "/showMenuRelation.jhtml")
	public String showStaffList() {
		return "/relation/menuRelation";
	}

	/**
	 * 查询信息
	 * 菜单和角色
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/loadMenuRelation.jhtml")
	@ResponseBody
	public List<RelationVO> loadRelationList(HttpServletRequest request) throws BusinessException{
		PageRequest pageRequest = this.getPageRequest(request);
		PageData<MenuDto> menuDto = this.menuApi.getPageData(pageRequest);
		List<MenuDto> menu = menuDto.getRows();
		List<RelationVO> list = new ArrayList<RelationVO>();
		for(MenuDto m:menu){
			RelationVO relation = new RelationVO();
			relation.setMenuName(m.getMenuName());
			relation.setMenuId(m.getId()); 
			List<Map<String, Object>> roleList = this.roleApi.selectRelation(m.getId());
			relation.setRoleList(roleList);
			list.add(relation);
		}
		return list;
	}
	/**
	 * 关联
	 * 菜单和角色
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/addmenuRelation.jhtml")
    @ResponseBody
	public void addRelation(HttpServletRequest request) {
		 MenuRoleMappingDto menuRole = new MenuRoleMappingDto();
		 String menuId = request.getParameter("menuId");
		 String roleId = request.getParameter("roleId");
		 menuRole.setMenuId(menuId);
		 menuRole.setRoleId(roleId);
		 menuRole.setCreateBy(this.getUserName(request));
		 menuRole.setUpdateBy(this.getUserName(request));
		 this.roleApi.insertMenuRole(menuRole);
	 }
	
	
}
