package com.yifeng.web.controller.member;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds.business.controller.BaseController;
import com.ds.business.dto.CodeDto;
import com.ds.business.pagination.PageData;
import com.ds.business.pagination.PageRequest;
import com.ds.fw.exception.BusinessException;
import com.ds.identify.api.RoleApi;
import com.ds.identify.api.RoleGroupApi;
import com.ds.identify.dto.RoleDto;
import com.ds.identify.dto.RoleGroupDto;
import com.ds.system.api.CodeGroupApi;


@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseController{
    
    private static final Logger logger = Logger.getLogger(RoleController.class);
    
    @Autowired
    private RoleApi roleApi;
    
    @Autowired
    private CodeGroupApi codeGroupApi;
    
    @Autowired
    private RoleGroupApi roleGroupApi;
    
    
    /**
     * 
     * @return String
     */
    @RequestMapping(value = "/showRoleList.jhtml")
    public String showRoleList() {
        return "/role/roleList";
    }
    
    /**
     * 查询数据库中所有角色信息
     * 
     * @param request
     * @return pageData
     * @throws ServiceException
     */
    @RequestMapping(value = "/loadRolePageData.jhtml")
    @ResponseBody
    public PageData<RoleDto> loadRoleListByRole(HttpServletRequest request) throws BusinessException{
    	PageRequest pageRequest = this.getPageRequest(request);
    	return roleApi.getRolePageList(pageRequest);
    }
    
    /**
     * 根据id删除该用户
     * 
     * @param request
     * @return pageData
     * @throws ServiceException
     */
    @RequestMapping(value = "/deleteRolePageData.jhtml")
    @ResponseBody
    public int deleteUser(HttpServletRequest request) {
        String memberId = request.getParameter("id");
        return roleApi.delete(memberId); 
    }
    
    /**
     * 根据id查询用户信息
     * 
     * @param request
     * @return pageData
     * @throws ServiceException
     */
    @RequestMapping(value = "/getRoleDetail.jhtml")
    @ResponseBody
    public RoleDto getRoleDetial(HttpServletRequest request) {
        String id = request.getParameter("id");
        RoleDto role = roleApi.getRole(id);
        return role;
    }
    
    /**
     * 修改用户信息
     * 
     * @param request
     * @return pageData
     * @throws ServiceException
     */
    @RequestMapping(value = "/updateRole.jhtml")
    @ResponseBody
    public boolean updateRole(HttpServletRequest request) {
    	RoleDto role = new RoleDto();
    	role.setId(request.getParameter("id"));
    	role.setRoleName(request.getParameter("roleName"));
    	role.setGroupId(request.getParameter("groupId"));
        return roleApi.updateRole(role);
    }
    
    /**
     * 保存新建的用户信息
     * 
     * @param request
     * @return pageData
     * @throws ServiceException
     */
    @RequestMapping(value = "/addRole.jhtml")
    @ResponseBody
    public void addRole(RoleDto roleDto,HttpServletRequest request) {
    	String s = request.getParameter("sortorder");
    	Short sh = new Short(s);
    	roleDto.setCreateBy(this.getUserName(request));
    	roleDto.setUpdateBy(this.getUserName(request)); 
        roleDto.setRoleName(request.getParameter("roleName"));
        roleDto.setGroupId(request.getParameter("groupId"));
        roleDto.setSortorder(sh); 
        this.roleApi.insertRole(roleDto);
    }
    
    /**
	 * 代码类型下拉框
	 * 
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping(value = "/getRoleGroupCodeDate.jhtml")
	@ResponseBody
	public List<RoleGroupDto> getRoleGroupName(HttpServletRequest request){
		return this.roleGroupApi.selectRoleGroup();
	} 
}
