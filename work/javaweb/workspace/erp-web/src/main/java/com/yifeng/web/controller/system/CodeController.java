package com.yifeng.web.controller.system;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.ds.business.controller.BaseController;
import com.ds.business.dto.CodeDto;
import com.ds.business.dto.CodeGroupDto;
import com.ds.business.pagination.PageData;
import com.ds.business.pagination.PageRequest;
import com.ds.fw.exception.BusinessException;
import com.ds.system.api.CodeApi;
import com.ds.system.api.CodeGroupApi;

/**
 * 单值代码维护
 * 
 * @author yangqili
 *
 */
@Controller
@RequestMapping(value = "/system")
public class CodeController extends BaseController {
	@Autowired
	private CodeApi codeApi;
	@Autowired
	private CodeGroupApi codeGroupApi;

	/**
	 * code维护页面跳转
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showCodeList.jhtml")
	public String showCodeList() {
		return "/system/codeList";
	}

	/**
	 * 根据查询条件查询数据库中Code信息
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/loadCodePageData.jhtml")
	@ResponseBody
	public PageData<Map<String,String>> loadCodeListBycondition(HttpServletRequest request) throws BusinessException {
		PageRequest pageRequest = this.getPageRequest(request);
		String codeValue = request.getParameter("codeValue");
		String codeLabel = request.getParameter("codeLabel");
		String typeCode = request.getParameter("typeCode");

		pageRequest.getConditions().put("codeValue", codeValue);// 代码值
		pageRequest.getConditions().put("codeLabel", codeLabel);// 代码内容
		pageRequest.getConditions().put("typeCode", typeCode);// 代码类型
		pageRequest.getConditions().put("deleted", "0");// 是否删除
		return codeApi.getCodeList(pageRequest);
	}

	/**
	 * 代码类型下拉框
	 * 
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping(value = "/getCodeTypeSelectData.jhtml")
	@ResponseBody
	public List<CodeGroupDto> getCodeTypeSelectData() throws BusinessException {
			return codeGroupApi.getAllCodeTypeData();
	}

	/**
	 * 保存新建的用户信息
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/addCode.jhtml")
	@ResponseBody
	public String addCode(CodeDto codeDto, HttpServletRequest request) throws BusinessException {
		String userName = this.getUserName(request);//当前用户
		if(StringUtils.isEmpty(codeDto.getCode())||StringUtils.isEmpty(codeDto.getCodeGroupCode())){
			 return "false";
		}
		codeDto.setCreatedBy(userName);
		codeDto.setUpdatedBy(userName);
		codeDto.setCreateTime(new Timestamp(System.currentTimeMillis()));//创建时间
		codeDto.setUpdateTime(new Timestamp(System.currentTimeMillis()));//更新时间
		 String codeValue =this.codeApi.insertCode(codeDto);
		 if(StringUtils.isNotEmpty(codeValue)){
			 return "true";
		 }
		 return "false";
		
	}
	
	/**
	 * 根据codeValue删除该单值代码
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/deleteCodePageData.jhtml")
	@ResponseBody
	public boolean deleteCodePageData(HttpServletRequest request) {
		String codeValue = request.getParameter("codeValue");
		return codeApi.delete(codeValue);
	}
	 /**
	    * 根据codeValue查询code信息
	    * 
	    * @param request
	    * @return pageData
	    * @throws ServiceException
	    */
	   @RequestMapping(value = "/getCodeDetail.jhtml")
	   @ResponseBody
	   public CodeDto getCodeDetial(HttpServletRequest request) {
	       String codeValue = request.getParameter("codeValue");
	       CodeDto codedto = codeApi.getCode(codeValue);
	       return codedto;
	   }
	/**
	 * 修改Code
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/updateCode.jhtml")
	@ResponseBody
	public String updateCode(CodeDto codeDto,HttpServletRequest request) throws BusinessException{
		String userName = this.getUserName(request);//当前用户
		codeDto.setDeleted("0");
		codeDto.setUpdatedBy(userName);
		codeDto.setUpdateTime(new Timestamp(System.currentTimeMillis()));//更新时间
		String codeValue =this.codeApi.updateCode(codeDto);
		 if(StringUtils.isNotEmpty(codeValue)){
			 return "true";
		 }
		 return "false";
		
	}

}
