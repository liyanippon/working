package com.yifeng.web.controller.offwork;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds.business.controller.BaseController;
import com.ds.business.dto.CodeDto;
import com.ds.business.pagination.PageData;
import com.ds.business.pagination.PageRequest;
import com.ds.fw.exception.BusinessException;
import com.ds.ump.system.api.CodeApi;
import com.yifeng.dto.OffworkDto;
import com.yifeng.service.OffworkService;
import com.yifeng.web.Constants;

@Controller
@RequestMapping(value = "/offwork")
public class showOffworkController extends BaseController {
	@Autowired
	private OffworkService offworkService;

	@Autowired
	private CodeApi codeApi;

	/**
	 * 我的请假页面跳转
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showMyOffwork.jhtml")
	public String showMyOffwork() {
		return "/offwork/showMyOffwork";
	}

	/**
	 * 单位的请假页面跳转
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showCropOffwork.jhtml")
	public String showCropOffwork() {
		return "/offwork/showCropOffwork";
	}

	/**
	 * 跳转到我要请假页面
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/addOffwork.jhtml")
	public String addOffwork() {
		return "/offwork/addOffwork";
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
		binder.registerCustomEditor(Date.class, dateEditor);
	}

	/**
	 * 新增请假
	 * 
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/addNewOffwork.jhtml")
	@ResponseBody
	public Map<String, Object> insertOrUpdateWarehouse(OffworkDto offworkDto, HttpServletRequest request)
			throws BusinessException {
		Map<String, Object> result = new HashMap<String, Object>();
		String userName = this.getUserName(request);// 当前用户
		String oldId = offworkDto.getOffworkId();
		if (StringUtils.isEmpty(oldId)) {// 新建时写入创建人，创建时间
			offworkDto.setCreateBy(userName);
			offworkDto.setCreateTime(new Timestamp(new java.util.Date().getTime()));// 创建时间
			offworkDto.setDelete("0");
		}
		offworkDto.setUpdateBy(userName);
		offworkDto.setUpdateTime(new Timestamp(new java.util.Date().getTime()));// 更新时间
		//待审批
		offworkDto.setProcessStatus(Constants.STATUS_APPROVAL_PENDING);
		// 开始时间加请假时间等于结束时间
		Date offerworkStarttime = offworkDto.getOfferworkStarttime();
		Calendar ca = Calendar.getInstance();
		ca.setTime(offerworkStarttime);
		ca.add(Calendar.MINUTE, offworkDto.getOffworkTime().multiply(new BigDecimal(60)).intValue());
		
		offworkDto.setOfferworkEndtime(ca.getTime());
		
		String returnid = this.offworkService.insertOrUpdate(offworkDto);

		if (StringUtils.isNotEmpty(returnid)) {
			result.put("succ", true);
			result.put("returnid", returnid);
		} else {
			result.put("succ", false);
		}
		return result;

	}
	/**
	 * 更新审批状态
	 * 
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/UpdateOffworkstatus.jhtml")
	@ResponseBody
	public Map<String, Object> UpdateOffworkstatus(HttpServletRequest request)throws BusinessException {
		Map<String, Object> result = new HashMap<String, Object>();
		OffworkDto offworkDto = new OffworkDto();
		String userName = this.getUserName(request);// 当前用户
		offworkDto.setUpdateBy(userName);
		offworkDto.setUpdateTime(new Timestamp(new java.util.Date().getTime()));// 更新时间
		//待审
        String processStatus = request.getParameter("processStatus");
        String offworkId = request.getParameter("offworkId");
        offworkDto.setOffworkId(offworkId);
        offworkDto.setProcessStatus(processStatus);
        offworkDto.setProcessBy(userName);
        offworkDto.setProcessTime(new Timestamp(new java.util.Date().getTime()));
		String returnid = this.offworkService.insertOrUpdate(offworkDto);
		if (StringUtils.isNotEmpty(returnid)) {
			result.put("succ", true);
			result.put("returnid", returnid);
		} else {
			result.put("succ", false);
		}
		return result;

	}


	/**
	 * 根据主键获取Offwork
	 * 
	 * @param id
	 * @return
	 * @throws BusinessException
	 */

	@RequestMapping(value = "/getOffworkDtoById.jhtml")
	@ResponseBody
	public OffworkDto getOffworkDtoById( HttpServletRequest request) throws BusinessException {
		String offworkid =request.getParameter("offworkId");
		OffworkDto offworkDto = this.offworkService.getOffworkDtoById(offworkid);
		if (null != offworkDto) {
			return offworkDto;
		}
		return null;

	}

	/**
	 * 获取请假类型
	 * 
	 * @param id
	 * @return
	 * @throws BusinessException
	 */

	@RequestMapping(value = "/getAlloffworkType.jhtml")
	@ResponseBody
	public List<CodeDto> getAlloffworkType() throws BusinessException {
		List<CodeDto> dtoList = codeApi.getCodeListByType(Constants.OFFWORK_TYPE);
		return dtoList;

	}

	/**
	 * 获取我的请假列表
	 * 
	 * @param id
	 * @return
	 * @throws BusinessException
	 */

	@RequestMapping(value = "/selectMyoffworkList.jhtml")
	@ResponseBody
	public PageData<OffworkDto> selectMyoffworkList(HttpServletRequest request) throws BusinessException {
		PageRequest pageRequest = this.getPageRequest(request);
		String userId = this.getUserName(request);// 当前用户
		pageRequest.getConditions().put("createBy", userId);
		pageRequest.getConditions().put("delete", "0");

		return offworkService.getOffworkByconditions(pageRequest);

	}
	/**
	 * 获取单位请假列表
	 * 
	 * @param id
	 * @return
	 * @throws BusinessException
	 */

	@RequestMapping(value = "/selectCopoffworkList.jhtml")
	@ResponseBody
	public PageData<OffworkDto> selectCopoffworkList(HttpServletRequest request) throws BusinessException {
		PageRequest pageRequest = this.getPageRequest(request);
		pageRequest.getConditions().put("delete", "0");
		return offworkService.getOffworkByconditions(pageRequest);

	}
	/**
	 * 根据id删除
	 * 
	 * @param request
	 * @return boolean
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/deleteOffworkByoffworkId.jhtml")
	@ResponseBody
	public boolean deleteOffworkByoffworkId(HttpServletRequest request) throws BusinessException {
		String offworkId = request.getParameter("offworkId");
		if (StringUtils.isEmpty(offworkId)) {
			return false;
		}
		return offworkService.delete(offworkId);
		
	}
}
