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
import com.ds.identify.api.UserApi;
import com.ds.identify.api.UserDetailApi;
import com.ds.identify.dto.UserDto;
import com.ds.system.api.CodeApi;
import com.ds.system.api.CodeGroupApi;;

/**
 * 
 * 控制Staff页面，返回pagedata 数据，给jsp页面
 * 
 * @author fuqiang
 * @version 1.0
 */

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

	private static final Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	private UserApi useApi;
	@Autowired
	private CodeApi codeApi;
	@Autowired
	private UserDetailApi userDetailApi;
	@Autowired
    private CodeGroupApi codeGroupApi;
	/**
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showStaffList.jhtml")
	public String showStaffList() {
		return "/user/userList";
	}

	/**
	 * 查询数据库中所有用户信息
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/loadUserPageData.jhtml")
	@ResponseBody
	public PageData<UserDto> loadUserListByUser(HttpServletRequest request) throws BusinessException {
		PageRequest pageRequest = this.getPageRequest(request);
		return useApi.getUserPageList(pageRequest);
	}

	/**
	 * 根据id删除该用户
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/deleteUserPageData.jhtml")
	@ResponseBody
	public boolean deleteUser(HttpServletRequest request) {
		String memberId = request.getParameter("id");
		return useApi.delete(memberId);
	}
	
	/**
     * 修改用户信息
     * 
     * @param request
     * @return pageData
     * @throws ServiceException
     */
    @RequestMapping(value = "/updateUser.jhtml")
    @ResponseBody
    public boolean updateUser(HttpServletRequest request) throws BusinessException {
        UserDto userDto = new UserDto();
        userDto.setUserName(request.getParameter("username"));
        userDto.setPassword(request.getParameter("password"));
        userDto.setUpdatedBy(this.getUserName(request));
        return this.useApi.updateUser(userDto);
    }

	/**
	 * 修改用户详细信息
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/updateUserDetail.jhtml")
	@ResponseBody
	public void updateUserDetail(HttpServletRequest request) throws BusinessException{
		UserDto userDto = new UserDto();
		Map<String, Object> userDetail = new HashMap<String, Object>();
		userDetail.put("id",request.getParameter("id3"));
		userDetail.put("nickName", request.getParameter("nickName"));
		userDetail.put("tel", request.getParameter("tel"));
		userDetail.put("sex", request.getParameter("sex"));
		userDetail.put("idCode", request.getParameter("idCode"));
		userDetail.put("nationality", request.getParameter("nationality"));
		userDetail.put("maritalStatus", request.getParameter("maritalStatus"));
		userDetail.put("nativePlace", request.getParameter("nativePlace"));
		userDetail.put("mail", request.getParameter("mail"));
		userDetail.put("updatedBy", this.getUserName(request));
		userDto.setUserDetail(userDetail);
		this.useApi.updateUserDetail(userDto);
	}

	/**
	 * 根据id查询用户信息
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/getUserDetail.jhtml")
	@ResponseBody
	public UserDto getStaffDetial(HttpServletRequest request) {
		String id = request.getParameter("id");
		return useApi.getUser(id);
	}

	/**
	 * 查看用户详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "/showUserDetail.jhtml")
	public String showStaffDetail() {
		return "/user/userDetail";
	}

	@RequestMapping(value = "/updateUserPwd.jhtml")
	@ResponseBody
	public boolean updateUserPwd(HttpServletRequest request) {
		String id = request.getParameter("id");
		String pwd1 = request.getParameter("userPwdnew");
		String pwd2 = request.getParameter("userPwdnewA");
		if (pwd1.equals(pwd2)) {
			return useApi.updatePassword(id, pwd2);
		} else {
			return false;
		}

	}

	/**
	 * 保存新建的用户信息
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/addUser.jhtml")
	@ResponseBody
	public String addUser(UserDto userDto, HttpServletRequest request) throws BusinessException {
		userDto.setUserName(request.getParameter("username"));
		userDto.setPassword(request.getParameter("password"));
		userDto.setCreatedBy(this.getUserName(request));
		userDto.setUpdatedBy(this.getUserName(request));
		return this.useApi.insertUser(userDto);
	}

	@RequestMapping(value = "/addUserJsp.jhtml")
	public String addUser(HttpServletRequest request) {
		return "/user/addUser";
	}

	@RequestMapping(value = "/showAddUserDetail.jhtml")
	public String addUserDetail() {
		return "/user/addUserDetail";
	}

	/**
	 * 保存新建的用户信息
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/addUserDetail.jhtml")
	@ResponseBody
	public void addUserDetail(HttpServletRequest request) throws BusinessException {
		UserDto userDto = new UserDto();
		userDto.setUserName(request.getParameter("username"));
		userDto.setPassword(request.getParameter("password"));
		Map<String, Object> userDetail = new HashMap<String, Object>();
		userDetail.put("userName", request.getParameter("userId"));
		userDetail.put("nickName", request.getParameter("nickName"));
		userDetail.put("tel", request.getParameter("tel"));
		userDetail.put("age", request.getParameter("age"));
		userDetail.put("sex", request.getParameter("sex"));
		userDetail.put("idCode", request.getParameter("idCode"));
		userDetail.put("nationality", request.getParameter("nationality"));
		userDetail.put("maritalStatus", request.getParameter("maritalStatus"));
		userDetail.put("nativePlace", request.getParameter("nativePlace"));
		userDetail.put("mail", request.getParameter("mail"));
		userDto.setUserDetail(userDetail);
		this.userDetailApi.update(userDetail);
	}

	/**
	 * 根据id锁定用户
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/lockUser.jhtml")
	@ResponseBody
	public boolean lockUser(HttpServletRequest request) {
		String memberId = request.getParameter("id");
		return useApi.lock(memberId, this.getUserName(request));
	}
	
	/**
     * 根据id锁定用户
     * 
     * @param request
     * @return pageData
     * @throws ServiceException
     */
    @RequestMapping(value = "/unLock.jhtml")
    @ResponseBody
    public boolean unLock(HttpServletRequest request) {
        String memberId = request.getParameter("id");
        return useApi.unLock(memberId, this.getUserName(request));
    }
	
	
	/**
	 * 下拉框 性别
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getSexCodePageData.jhtml")
	@ResponseBody
	public List<CodeDto> getRoleGroupCodeDate() throws BusinessException {
		return codeApi.getCodeListByType("001");
}

	/* 婚姻状况 */
	@RequestMapping(value = "/getMarCodePageData.jhtml")
	@ResponseBody
	public List<CodeDto> getMarCodeDate() throws BusinessException {
		return codeApi.getCodeListByType("002"); 
}
	/* 民族 */
	@RequestMapping(value = "/getNatCodePageData.jhtml")
	@ResponseBody
	public List<CodeDto> getNatCodeDate() throws BusinessException {
		return codeApi.getCodeListByType("003");
}
	
	/* 祖籍 */
	@RequestMapping(value = "/getNCodePageData.jhtml")
	@ResponseBody
	public List<CodeDto> getNCodeDate() throws BusinessException {
		return codeApi.getCodeListByType("017");
}

	

}
