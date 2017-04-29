package com.yifeng.web.controller.member;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds.business.controller.BaseController;
import com.ds.business.pagination.PageData;
import com.ds.business.pagination.PageRequest;
import com.ds.fw.exception.BusinessException;
import com.ds.identify.api.RoleGroupApi;
import com.ds.identify.dto.RoleGroupDto;

@Controller
@RequestMapping(value = "/roleGroup")
public class RoleGroupController extends BaseController{
	
	 private static final Logger logger = Logger.getLogger(RoleGroupController.class);
	 
	 @Autowired
	 private RoleGroupApi roleGroupApi;
	 
	    @RequestMapping(value = "/showRoleList.jhtml")
	    public String showRoleList() {
	        return "/role/roleGroupList";
	    }

	    /**
	     * 查询数据库中所有角色组信息
	     * 
	     * @param request
	     * @return pageData
	     * @throws ServiceException
	     */
	    @RequestMapping(value = "/loadRoleGroupPageData.jhtml")
	    @ResponseBody
	    public PageData<RoleGroupDto> loadRoleGroupListByRole(HttpServletRequest request) throws BusinessException{
	    	PageRequest pageRequest = this.getPageRequest(request);
	    	return roleGroupApi.getRolePageList(pageRequest);
	    }
	    
	    /**
	     * 根据id查询角色组信息
	     * 
	     * @param request
	     * @return pageData
	     * @throws ServiceException
	     */
	    @RequestMapping(value = "/getRoleGroup.jhtml")
	    @ResponseBody
	    public RoleGroupDto getRoleDetial(HttpServletRequest request) {
	        String id = request.getParameter("id");
	        RoleGroupDto roleGroup = roleGroupApi.getRoleGroup(id);
	        return roleGroup;
	    }
	    
	    /**
	     * 根据id删除该用户
	     * 
	     * @param request
	     * @return pageData
	     * @throws ServiceException
	     */
	    @RequestMapping(value = "/deleteRoleGroup.jhtml")
	    @ResponseBody
	    public int deleteUser(HttpServletRequest request) {
	        String memberId = request.getParameter("id");
	        return roleGroupApi.delete(memberId); 
	    }
	    
	    /**
	     * 修改用户信息
	     * 
	     * @param request
	     * @return pageData
	     * @throws ServiceException
	     */
	    @RequestMapping(value = "/updateRoleGroup.jhtml")
	    @ResponseBody
	    public boolean updateRole(HttpServletRequest request) {
	        RoleGroupDto rolegroupDto = new RoleGroupDto();
	        rolegroupDto.setId(request.getParameter("id"));
	        rolegroupDto.setRolegroupName(request.getParameter("rolegroupName"));   
	        return roleGroupApi.updateRoleGroup(rolegroupDto); 
	    }
	    
	    /**
	     * 保存新建的用户信息
	     * 
	     * @param request
	     * @return pageData
	     * @throws ServiceException
	     */
	    @RequestMapping(value = "/addRoleGroup.jhtml")
	    @ResponseBody
	    public void addRoleGroup(HttpServletRequest request) {
	    	RoleGroupDto roleGroupDto = new RoleGroupDto();
	    	String s = request.getParameter("sortorder");
	    	Short sh = new Short(s);
	    	roleGroupDto.setSortorder(sh);
	    	roleGroupDto.setCreateBy(this.getUserName(request));
	    	roleGroupDto.setUpdateBy(this.getUserName(request)); 
	    	roleGroupDto.setRolegroupName(request.getParameter("rolegroupName"));
	        this.roleGroupApi.insert(roleGroupDto);
	    }
}
