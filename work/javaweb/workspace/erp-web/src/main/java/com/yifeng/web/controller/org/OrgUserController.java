package com.yifeng.web.controller.org;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ds.business.controller.BaseController;
import com.ds.common.utils.StringUtils;
import com.ds.fw.exception.BusinessException;
import com.ds.identify.api.CompApi;
import com.ds.identify.api.DepartApi;
import com.ds.identify.api.OrgUserApi;
import com.ds.identify.dto.DepartDto;
import com.ds.identify.dto.OrgDto;
import com.ds.identify.dto.OrgUserDto;
import com.yifeng.web.vo.TreeNode;

/**
 * 关联用户和公司部门
 * 
 * @author yangqili
 *
 */
@Controller
@RequestMapping(value = "/org")
public class OrgUserController extends BaseController {
	@Autowired
	private CompApi compApi;
	@Autowired
	private DepartApi departApi;
	@Autowired
	private OrgUserApi orgUserApi;

	/**
	 * 关联用户部门页面跳转
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showOrgUserList.jhtml")
	public String showOrgUserList() {
		return "/org/orgUserList";
	}

	/**
	 * 查询数据库中所有公司部门信息
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getOrgTreeList.jhtml")
	@ResponseBody
	public Object getOrgTreeList(HttpServletResponse response, HttpServletRequest request) {
		try {
			// 查询到公司信息
			List<OrgDto> orgLsit = compApi.getAllComp();
			// 将查询到的数据转换为树形结构的list
			List<TreeNode> list = this.fillTree();
			// 转化成json
			return this.getListToJson(list);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List fillTree() {
		List<TreeNode> list = new ArrayList<TreeNode>();// 定义一个实体
		Map map = new HashMap<String, TreeNode>(); // 定义一个map

		// 将传递过来的参数赋值给公司实体，遍历每一个将公司赋值给treenode相应的属性
		List<OrgDto> orgLsit = compApi.getAllComp();
		for (OrgDto orgEach : orgLsit) {
			TreeNode node = new TreeNode();
			node.setId(orgEach.getId()); // id
			node.setText(orgEach.getOrgName());// 名称
			node.setBsId(orgEach.getId());
			// 判断是否有父节点，如果父节点不为空则赋值给treenode的父节点
			if (!"-1".equals(orgEach.getpId())) {
				node.setParentId(orgEach.getpId());
			} else {
				node.setParentId("");
			}
			map.put(orgEach.getId(), node); // 将公司的id与树形结构实体放在map中

			// 添加部门map
			List<DepartDto> departList = departApi.getAllDepartByOrgId(orgEach.getId());// 获取公司下的部门
			for (DepartDto departEach : departList) {
				String deptId = departEach.getId();
				TreeNode deptNode = new TreeNode();
				deptNode.setId(deptId);
				deptNode.setText(departEach.getDepartName());
				deptNode.setBsId(departEach.getId());
				// 获取部门的父节点
				String pId = getDepartPid(departEach, true);
				deptNode.setParentId(pId);
				map.put(departEach.getId(), deptNode);

			}

			// 添加部门节点
			for (DepartDto departEachw : departList) {
				String pId = this.getDepartPid(departEachw, true);
				String childId = departEachw.getId();

				// 添加父节点，子节点
				TreeNode pNode = (TreeNode) map.get(pId);
				TreeNode cNode = (TreeNode) map.get(childId);
				pNode.addChild(cNode);

				// 用部门Id获取下面的人员列表，如果不为空，增加人员children
				List<OrgUserDto> userList = getUserListBydeptId(childId);
				if (null != userList || userList.size() > 0) {

					for (OrgUserDto userEach : userList) {
						TreeNode userNode = new TreeNode();
						userNode.setId(userEach.getUserId());
						userNode.setText(userEach.getUserName());
						userNode.setBsId(userEach.getId());//数据库中的唯一标示Id
						userNode.setParentId(childId);
						map.put(userEach.getUserId(), userNode);

						TreeNode upNode = (TreeNode) map.get(childId);
						TreeNode ucNode = (TreeNode) map.get(userEach.getUserId());
						upNode.addChild(ucNode);
					}

				}

			}
			list.add(node);

		}

		return list;
	}

	/**
	 * 确定部门pId
	 */
	@SuppressWarnings("unused")
	private String getDepartPid(DepartDto departEach, boolean isHaveOrg) {
		String pId = "";
		// 判断部门的父节点
		if ("-1".equals(departEach.getpId())) {
			if (isHaveOrg) {// 有公司信息
				pId = departEach.getOrgId();// 部门如果pid为-1，上级为公司
			} else {
				pId = "";
			}

		} else {
			pId = departEach.getpId();// pid为自己上级部门
		}
		return pId;
	}

	/**
	 * 根据部门id获取部门下的人员
	 */
	public List<OrgUserDto> getUserListBydeptId(String deptId) {
		if (StringUtils.isEmpty(deptId)) {
			return null;
		}
		try {
			return orgUserApi.getUserListBydeptId(deptId);
		} catch (BusinessException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 获取部门树结构
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/getDepartTreeList.jhtml")
	@ResponseBody
	public Object getDepartTreeList(HttpServletResponse response, HttpServletRequest request) {
		try {
			// 获取到公司Id
			String orgId = request.getParameter("orgId");// 公司id
			//String orgId = "d0db5a4dfc4a4d609be8b857a42519fa";
			// 将查询到的数据转换为树形结构的list
			List<TreeNode> list = this.departFillTree(orgId);
			// 转化成json
			return this.getListToJson(list);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 将list转化成json
	 * 
	 * @param list
	 * @return
	 */
	private Object getListToJson(List<TreeNode> list) {
		JSONArray json = new JSONArray();
		for (TreeNode node : list) {
			JSONObject jo = new JSONObject();
			jo.put("id", node.getId());
			jo.put("text", node.getText());
			jo.put("children", node.getChildren());
			json.add(jo);

		}
		return json;
	}
	/**
	 * 根据公司获取公司下的部门树节点
	 * @param orgId
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List departFillTree(String orgId) {
		List<TreeNode> list = new ArrayList<TreeNode>();// 定义一个实体
		List<DepartDto> departList =departApi.getAllDepartByOrgId(orgId);//获取公司下的部门
		Map map = this.getDepartNodeMap(departList);
		// 添加部门下节点
		for (DepartDto departEachw : departList) {
			String pId = this.getDepartPid(departEachw, false);
			String childId = departEachw.getId();
			// 添加父节点，子节点
			if (StringUtils.isEmpty(pId)) {
				list.add((TreeNode) map.get(childId));
			} else {
				TreeNode pNode = (TreeNode) map.get(pId);
				TreeNode cNode = (TreeNode) map.get(childId);
				pNode.addChild(cNode);
			}
		}
		return list;

	}

	/**
	 * 获取部门节点Map
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map<String, TreeNode> getDepartNodeMap(List<DepartDto> departList) {
		Map map = new HashMap<String, TreeNode>();
		for (DepartDto departEach : departList) {
			TreeNode node = new TreeNode();
			String deptId = departEach.getId();

			node.setId(deptId);
			node.setText(departEach.getDepartName());
			// 获取部门的父节点
			String pId = getDepartPid(departEach, false);
			node.setParentId(pId);
			map.put(departEach.getId(), node);
		}
		return map;
	}
	/**
	 * 修改用户公司部门
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/updateOrgUser.jhtml")
	@ResponseBody
	public Object updateOrgUser( HttpServletRequest request) {
		String bsId = request.getParameter("bsId");//人员id
		String userId = request.getParameter("userId");//人员id
		String orgId = request.getParameter("orgId");// 公司id
		String deptId = request.getParameter("deptId");// 部门id
		String currentUserName = this.getUserName(request);//当前用户
		
		
		OrgUserDto orgUserDto = new OrgUserDto();
		orgUserDto.setId(bsId);
		orgUserDto.setUserId(userId);
		orgUserDto.setOrgId(orgId);
		orgUserDto.setDepartId(deptId);
		orgUserDto.setCreateBy(currentUserName);
		orgUserDto.setCreateTime(new Date());
		orgUserDto.setDelete("0");//有效
		orgUserDto.setUpdateBy(currentUserName);
		orgUserDto.setUpdateTime(new Date());//更新时间
		
		 String returnid =this.orgUserApi.updateOrgUser(orgUserDto);
		 if(StringUtils.isNotEmpty(returnid)){
			 return "true";
		 }
		 return "false";
	}
	
	/**
	 * 根据id查询部门信息
	 * 
	 * @param request
	 * @return pageData
	 * @throws BusinessException
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/getOrgUserDetial.jhtml")
	@ResponseBody
	public OrgUserDto getOrgUserDetial(HttpServletRequest request) throws BusinessException {
		String bsId = request.getParameter("bsId");
		OrgUserDto orgUserDto = orgUserApi.getUserOrgDeptById(bsId);
		return orgUserDto;
	}
	
	
}