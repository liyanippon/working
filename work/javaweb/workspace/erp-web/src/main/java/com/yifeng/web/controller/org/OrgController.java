package com.yifeng.web.controller.org;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds.business.controller.BaseController;
import com.ds.business.support.components.tree.vo.OrgTreeNodeVO;
import com.ds.common.utils.StringUtils;
import com.ds.fw.exception.BusinessException;
import com.ds.fw.vo.TreeNodeVO;
import com.ds.ump.identify.api.UserDetailApi;
import com.ds.ump.mapping.dto.UserOrgMappingDto;
import com.ds.ump.org.api.OrgApi;
import com.ds.ump.org.dto.DepartDto;
import com.ds.ump.org.dto.OrgDto;
import com.ds.ump.org.dto.UserDetailDto;

/**
 * 关联用户和公司部门
 * 
 * @author yangqili
 *
 */
@Controller
@RequestMapping(value = "/org")
public class OrgController extends BaseController {
    

    @Autowired
    private UserDetailApi userDetailApi;
    
    @Autowired
    private OrgApi orgApi;
    
    /**
     * 查询数据库中所有公司信息
     * 
     * @param compId 公司id
     * @param pId 父节点id
     * @return Set
     * @throws BusinessException
     */
    @RequestMapping(value = "/getOrgTreeList.ajax")
    @ResponseBody
    public Set<TreeNodeVO> getOrgTreeList(String compId, String pId) throws BusinessException {

        Set<TreeNodeVO> treeSet = new TreeSet<TreeNodeVO>();
        
        // 查询到公司信息
        List<OrgDto> orgList = this.orgApi.getCompList(pId);
        for(OrgDto eachDto : orgList) {
            treeSet.add(this.createTreeNode(eachDto));
        }
        if(StringUtils.isNotBlank(pId)) {
            // 查询公司根节点部门列表
            List<DepartDto> departList = this.orgApi.getRootDepartByOrgId(compId);
            for(DepartDto eachDto : departList) {
                TreeNodeVO treeNode = this.createTreeNode(eachDto);
                treeSet.add(treeNode);
            }
        }
        
        return treeSet;
        
    }
    
    /**
     * 查询公司内部门信息
     * 
     * @param request
     * @return pageData
     * @throws BusinessException
     */
    @RequestMapping(value = "/getDepartTreeList.ajax")
    @ResponseBody
    public Set<TreeNodeVO> getDepartTreeList(String orgId, String pId) throws BusinessException {
        
        Set<TreeNodeVO> treeSet = new TreeSet<TreeNodeVO>();
        
        List<DepartDto> departList = null;
        if(StringUtils.isBlank(pId)) {
            // 查询公司根节点部门列表
            departList = this.orgApi.getRootDepartByOrgId(orgId);
        } else {
            departList = this.orgApi.getDepartByPId(pId);
            
            List<UserOrgMappingDto> userList = this.getUserListBydepartId(pId);
            for(UserOrgMappingDto user : userList) {
                TreeNodeVO userVO = new TreeNodeVO();
                userVO.setId(user.getUserId());
                UserDetailDto userDetail = (UserDetailDto)this.userDetailApi.getUserByUserName(user.getUserId()).getUserDetail();
                String diaplayName = "";
                // 没有昵称，显示登录帐号
                if(StringUtils.isBlank(userDetail.getNickName())) {
                    diaplayName = userDetail.getUserName() + "（登录帐号）";
                } else {
                    diaplayName = userDetail.getNickName();
                }
                userVO.setText(diaplayName);
                
                userVO.setSortorder(99);
                userVO.setIcon(null);
                treeSet.add(userVO);
            }
        }
        
        
        for(DepartDto eachDto : departList) {
            TreeNodeVO treeNode = this.createTreeNode(eachDto);
            treeSet.add(treeNode);
        }
        
        
        return treeSet;
        
    }
    
    /**
     * 根据部门id获取部门下的人员
     * 
     * @param deptId deptId
     * @return 部门下的人员
     */
    private List<UserOrgMappingDto> getUserListBydepartId(String deptId) throws BusinessException {
        if (StringUtils.isEmpty(deptId)) {
            return new ArrayList<UserOrgMappingDto>();
        }
        
        return this.orgApi.getUserListBydepartId(deptId);

    }
    
    /**
     * 创建树节点
     * 
     * @param dto dto
     * @return 树节点
     * @throws BusinessException
     */
    private OrgTreeNodeVO createTreeNode(OrgDto dto) {
        OrgTreeNodeVO org = new OrgTreeNodeVO();
        org.setId(dto.getId());
        org.setText(dto.getOrgName());
        // 节点类型为公司：1
        org.setNodeType("1");
        org.setSortorder(dto.getSortOrder());
        org.setState("closed");
        org.setIcon(null);
        
        return org;
    }
    
    /**
     * 创建树节点
     * 
     * @param dto dto
     * @return 树节点
     * @throws BusinessException
     */
    private OrgTreeNodeVO createTreeNode(DepartDto dto) {
        OrgTreeNodeVO depart = new OrgTreeNodeVO();
        depart.setId(dto.getId());
        depart.setText(dto.getDepartName());
        // 节点类型为部门：2
        depart.setNodeType("2");
        depart.setSortorder(dto.getSortOrder());
        depart.setState("closed");
        depart.setIcon(null);
        
        return depart;
    }
	
	
}