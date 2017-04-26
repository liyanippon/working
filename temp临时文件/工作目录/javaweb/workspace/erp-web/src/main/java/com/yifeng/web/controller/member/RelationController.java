package com.yifeng.web.controller.member;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds.business.controller.BaseController;
import com.ds.business.pagination.PageData;
import com.ds.business.pagination.PageRequest;
import com.ds.common.utils.BeanUtils;
import com.ds.fw.exception.BusinessException;
import com.ds.identify.api.RoleApi;
import com.ds.identify.api.RoleGroupApi;
import com.ds.identify.api.UserApi;
import com.ds.identify.dto.RoleDto;
import com.ds.identify.dto.RoleGroupDto;
import com.ds.identify.dto.UserDto;
import com.ds.identify.dto.UserRoleMappingDto;
import com.yifeng.web.vo.RelationVO;
import com.yifeng.web.vo.RoleVo;


@Controller
@RequestMapping(value = "/relation")
public class RelationController extends BaseController{
	
	@Autowired
	private UserApi useApi;
	
	@Autowired
	private RoleGroupApi roleGroupApi;
	
	@Autowired
	private RoleApi roleApi;
	
//	@Autowired
//	private RelationService relationService;
	
	@RequestMapping(value = "/showRuRelation.jhtml")
	public String showStaffList() {
		return "/relation/ruRelation";
	}
	
	/**
	 * 查询数据库中所有用户角色信息
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/loadRelation.jhtml")
	@ResponseBody
	public List<RelationVO> loadRelationList(HttpServletRequest request) throws BusinessException{
		PageRequest pageRequest = this.getPageRequest(request);
		PageData<UserDto> userDto = this.useApi.getUserPageList(pageRequest);
		List<UserDto> user = userDto.getRows();
		List<RelationVO> list = new ArrayList<RelationVO>();
		for(UserDto u :user){
			RelationVO relation = new RelationVO();
			relation.setUserName(u.getUserDetail().put("userName",u.getUserName()).toString());
			List<Map<String, Object>> roleList = this.useApi.selectRelation(relation.getUserName());
			relation.setRoleList(roleList);
			list.add(relation);
		}
		return list;
		
	}

	 /**
     * 查询数据库中所有角色信息
     * 
     * @param request
     * @return pageData
     * @throws ServiceException
     */
    @RequestMapping(value = "/loadrole.jhtml")
    @ResponseBody
    public  Set<RoleVo> getRoleList() {
        List<RoleGroupDto> roleDtoList = this.roleGroupApi.selectRoleGroup();
        Set<RoleVo> roleSet = new TreeSet<RoleVo>();
//        Map<String, Set<RoleVo>> children = new HashMap<String, Set<RoleVo>>();
        for (RoleGroupDto each : roleDtoList) {
        	RoleVo role = this.createVO(each);
        	List<RoleDto> roleDto = this.roleApi.getRoleByGroupId();
                // 一级菜单
        		roleSet.add(role);
        		// 二级菜单
                Set<RoleVo> subSet = new HashSet<RoleVo>();
                for(RoleDto eachRole : roleDto) {
                	if(each.getId().equals(eachRole.getGroupId())){
                		RoleVo roleVo = this.createVO(eachRole);
                    	subSet.add(roleVo);
                	}
                }
                role.setChildren(subSet);
                
        }
    	return roleSet;
    }
    
    private RoleVo createVO(RoleGroupDto dto) {
    	RoleVo vo = new RoleVo();
        BeanUtils.copyProperties(vo, dto);
        vo.setText(dto.getRolegroupName());
        vo.setState("open");
        return vo;
    }
    private RoleVo createVO(RoleDto dto) {
    	RoleVo vo = new RoleVo();
        BeanUtils.copyProperties(vo, dto);
        vo.setText(dto.getRoleName());
        return vo;
    }
    
    
    /**
     * 关联用户和角色
     * relation
     * */
    @RequestMapping(value = "/addRelation.jhtml")
    @ResponseBody
    public void addRelation(HttpServletRequest request) {
    	UserRoleMappingDto u = new UserRoleMappingDto();
    	String a=request.getParameter("userName");
    	String b=request.getParameter("roleId");
        u.setUserId(a);
        u.setRoleId(b);
        u.setCreateBy(this.getUserName(request));
        u.setUpdateBy(this.getUserName(request));
    	this.useApi.insertUserRole(u);
    }
}
