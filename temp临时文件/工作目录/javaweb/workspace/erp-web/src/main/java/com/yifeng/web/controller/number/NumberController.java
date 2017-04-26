package com.yifeng.web.controller.number;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds.business.controller.BaseController;
import com.ds.business.dto.CodeDto;
import com.ds.fw.exception.BusinessException;
import com.ds.system.api.CodeApi;
import com.yifeng.identify.api.NumberApi;
import com.yifeng.identify.dto.NumberDto;

/**
 * 编码
 * 
 * @author yangqili
 *
 */
@Controller
@RequestMapping(value = "/number")
public class NumberController extends BaseController {
	@Autowired
	private CodeApi codeApi;
	@Autowired
	private NumberApi numberApi;
	//编码方式
	private static final String NUMBER_WAYS ="009";
	//编码时间规则
	private static final String NUMBER_TIMEWAYS ="010";
	//时间字符类型
	private static final String TIME_Y ="010001";
	private static final String TIME_YM="010002";
	private static final String TIME_YMD ="010003";
	private static final String TIME_STR_Y ="yyyy";
	private static final String TIME_STR_YM="yyyyMM";
	private static final String TIME_STR_YMD ="yyyyMMdd";
	/**
	 * 批次编码页面跳转
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showBatchList.jhtml")
	public String showBatchList() {
		return "/number/batchList";
	}
	/**
	 * 品类编码页面跳转
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showCategoryList.jhtml")
	public String showCategoryList() {
		return "/number/categoryList";
	}
	/**
	 * 入库单编码页面跳转
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showWarehouseList.jhtml")
	public String showWarehouseList() {
		return "/number/warehouseList";
	}
	/**
	 * 出库单编码页面跳转
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showDeliveryList.jhtml")
	public String showDeliveryList() {
		return "/number/deliveryList";
	}
	/**
	 * 合同编码页面跳转
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showContractList.jhtml")
	public String showContractList() {
		return "/number/contractList";
	}
	/**
	 * 核算单编码页面跳转
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showCheckingList.jhtml")
	public String showCheckingList() {
		return "/number/checkingList";
	}
	/**
	 * 领用单编码页面跳转
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showReciveList.jhtml")
	public String showReciveList() {
		return "/number/reciveList";
	}
	/**
	 * 获取编码方式
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/getNumberWaysCode.jhtml")
	@ResponseBody
	public List<CodeDto> getNumberWaysCode(HttpServletRequest request) {
		String type =  request.getParameter("type");
		String codetype = "";
		if("one".equals(type)){
			codetype = NUMBER_WAYS;
		}else if("two".equals(type)){
			codetype = NUMBER_TIMEWAYS;
		}
		try {
			return codeApi.getCodeListByType(codetype);
		} catch (BusinessException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 新建编码规则
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/addOrUpdateNumber.jhtml")
	@ResponseBody
	public Map<String,String> addOrUpdateNumber(NumberDto numberDto, HttpServletRequest request) throws BusinessException {
		Map<String,String> result = new HashMap<String,String>();
		String numType =  request.getParameter("numType");//编码种类
		String userName = this.getUserName(request);// 当前用户
		String oldId = numberDto.getId();
		if (StringUtils.isEmpty(oldId)) {// 只有新建的时候添加创建人和创建时间
			numberDto.setCreateBy(userName);
			numberDto.setCreateTime(new Date());// 创建时间
			numberDto.setDelete("0");
		} else {
			NumberDto oldDto = numberApi.getDtoById(oldId);// 获取数据库中的值
			numberDto.setCreateBy(oldDto.getCreateBy());
			numberDto.setCreateTime(oldDto.getCreateTime());
			numberDto.setDelete(oldDto.getDelete());// 有效无效
		}
		//赋值时间字符
		String timeType = numberDto.getPrefixTwo();
		if(TIME_Y.equals(timeType)){
			numberDto.setRuleTwo(TIME_STR_Y);
		}else if(TIME_YM.equals(timeType)){
			numberDto.setRuleTwo(TIME_STR_YM);
		}else{
			numberDto.setRuleTwo(TIME_STR_YMD);
		}
		numberDto.setType(numType);
		numberDto.setUpdateBy(userName);
		numberDto.setUpdateTime(new Date());// 更新时间

		String returnid = this.numberApi.insertOrUpdateNumber(numberDto);
		if (StringUtils.isNotEmpty(returnid)) {
			result.put("recid", returnid);
			result.put("sucess", "true");
		}
		return result;

	}
	
	/**
	 * 根据类型查询信息
	 * 
	 * @param request
	 * @return NumberDto
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/getNumberDetailBytype.jhtml")
	@ResponseBody
	public NumberDto getNumberDetailBytype(HttpServletRequest request) throws BusinessException {
		String numType =  request.getParameter("numType");//编码种类
		List<NumberDto> dtoList = numberApi.getDtoByType(numType);
		if(null!=dtoList&&dtoList.size()>0){
			return dtoList.get(0);
		}
		return null;
	}
}
