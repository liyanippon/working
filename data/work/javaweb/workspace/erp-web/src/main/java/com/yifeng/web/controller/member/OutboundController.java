package com.yifeng.web.controller.member;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds.business.controller.BaseController;
import com.ds.business.pagination.PageData;
import com.ds.business.pagination.PageRequest;
import com.ds.fw.exception.BusinessException;
import com.yifeng.identify.api.NumberApi;
import com.yifeng.identify.api.OutboundApi;
import com.yifeng.identify.api.OutboundRepertoryApi;
import com.yifeng.identify.api.RepertoryApi;
import com.yifeng.identify.dto.OutboundDto;
import com.yifeng.identify.dto.OutboundRepertoryDto;
import com.yifeng.identify.dto.RepertoryDto;
import com.yifeng.web.Constants;
@Controller
@RequestMapping(value = "/outbound")
public class OutboundController extends BaseController{
	
	@Autowired
	private RepertoryApi repertoryApi;
	
	@Autowired
	private OutboundApi outboundApi;
	@Autowired
	private OutboundRepertoryApi outboundRepertoryApi;
	@Autowired
	private NumberApi numberApi;
	/**
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showRepertoryList.jhtml")
	public String showRepertoryList() {
		return "/outbound/repertory";
	}
	
	/**
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showoutboundDetail.jhtml")
	public String showOutList() {
		return "/outbound/repertory";
	}
	
	/**
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showOutboundList.jhtml")
	public String showOutboundList() {
		return "/outbound/outboundList";
	}
	
	/**
	 * 点击详情页面跳转
	 * */
	@RequestMapping(value = "/outboundDetail.jhtml")
	public String contractDetail(HttpServletRequest request) {
		return "/outbound/repertory";
	}
	
	
	/**
	 * 查询数据库中所有类别
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/loadRepertoryPageData.jhtml")
	@ResponseBody
	public PageData<RepertoryDto> loadRepertoryListByUser(HttpServletRequest request) throws BusinessException {
		PageRequest pageRequest = this.getPageRequest(request);
		return repertoryApi.getCategoryListByPage(pageRequest);
	}
	
	/**
	 * 查询数据库中所有类别
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/loadOutboundPageData.jhtml")
	@ResponseBody
	public PageData<OutboundDto> loadOutboundListByUser(HttpServletRequest request) throws BusinessException {
		PageRequest pageRequest = this.getPageRequest(request);
		return outboundApi.getCategoryListByPage(pageRequest);
	}
	
	/**
	 * 查询入库详情信息
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/showOutboundRepertoryList.jhtml")
	@ResponseBody
	public PageData<Map<String,Object>> showOutboundRepertoryList(HttpServletRequest request) throws BusinessException{
		PageRequest pageRequest = this.getPageRequest(request);
		PageData<Map<String,Object>> aa=outboundRepertoryApi.repertorysByobbh(pageRequest);
		return aa;
	}	
	/**
	 * 
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/addOutboundRepertory.jhtml")
	@ResponseBody
	public void addOutboundRepertory(HttpServletRequest request) throws BusinessException{		
		String outboundid=request.getParameter("outboundid");//出库单id
		String number=request.getParameter("number");//出库数量
		String repertoryProducts=request.getParameter("repertoryProducts");//货物id
		OutboundRepertoryDto obrd=new OutboundRepertoryDto();		
		obrd.setRepertoryId(repertoryProducts);
		obrd.setOutboundBh(outboundid);
		BigDecimal bd=new BigDecimal(number);
		obrd.setOutputQuantity(bd);
		outboundRepertoryApi.insertSelective(obrd);
	}
	/**
	 * 保存出库单
	 * @return String
	 */
	@RequestMapping(value = "/saveOutbound.jhtml")
	@ResponseBody
	public OutboundDto saveOutbound(HttpServletRequest request)throws BusinessException{
		String id=request.getParameter("id");		
		OutboundDto outboundDto=new OutboundDto();
		if(StringUtils.isBlank(id)||StringUtils.equals(id,"null")){
			outboundDto.setLsh(numberApi.getNumberByType("delivery"));
			outboundDto.setCreateBy(this.getUserName(request));
			outboundDto.setUpdateBy(this.getUserName(request));
			outboundDto.setStu(Constants.STATUS_NEWLY_REGISTER);
			id=outboundApi.saveOutbound(outboundDto);	
			String maxvalue =outboundDto.getLsh();
			//更新最大值
			numberApi.UpdateNumberMaxvalue("delivery", maxvalue);
		}else{
			
			outboundDto.setId(id);
			outboundApi.updateOutbound(outboundDto);	
		}
		outboundDto.setId(id);
		OutboundDto obd=outboundApi.selectByPrimaryKey(id);
		return obd;
	}
	
	/**
	 * 根据id删除出库单
	 * */
	@RequestMapping(value = "/deleteOutbound.jhtml")
	@ResponseBody
	public int deleteOutbound(HttpServletRequest request){
		String id = request.getParameter("id");
		outboundApi.deleteOutbound(id);//删除出库单信息
		return outboundRepertoryApi.deleteOutboundbylsh(id);//删除出库单关联的货物信息
	}
	
	/**
	 * 根据id删除库存 信息
	 * */
	@RequestMapping(value = "/deleteOut.jhtml")
	@ResponseBody
	public int deleteOut(HttpServletRequest request){
		String id = request.getParameter("id");
		return outboundRepertoryApi.deleteOutboundRepertory(id);
	}
	/**
	 * 提交审批人审批
	 * @param request
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/addOutboundPeople.jhtml")
	@ResponseBody
	public void addOutboundPeople(HttpServletRequest request) throws BusinessException{	
		String id=request.getParameter("id");//出库单id
		String outboundPeople=request.getParameter("outboundPeople");//获取提交人
		OutboundDto outboundDto=new OutboundDto();
		outboundDto.setId(id);
		outboundDto.setOutboundPeople(outboundPeople);
		outboundDto.setStu(Constants.STATUS_APPROVAL_PENDING);//状态为待审批
		outboundDto.setCreateBy(this.getUserName(request));
		outboundDto.setUpdateBy(this.getUserName(request));
		outboundApi.updateOutbound(outboundDto);
	}
	/**
	 * 驳回出库单
	 * @param request
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/backOutboundPeople.jhtml")
	@ResponseBody
	public void backOutboundPeople(HttpServletRequest request) throws BusinessException{	
		String id=request.getParameter("id");//出库单id
		OutboundDto outboundDto=new OutboundDto();
		outboundDto.setId(id);
		outboundDto.setStu(Constants.STATUS_FAILED_PSAA);//状态为未通过
		outboundApi.updateOutbound(outboundDto);
	}
	/**
	 * 通过出库单
	 * @param request
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/gogoOutboundBills.jhtml")
	@ResponseBody
	public Map<String,Object> gogoOutboundBills(HttpServletRequest request) throws BusinessException{	
		Map<String,Object> map=new HashMap<>();
		String id=request.getParameter("id");//出库单id
		String result=outboundApi.selectOutboundNumber(id);
        if(StringUtils.isNotBlank(result)){
        	map.put("result",result);
        	return map;
        }else{
        	OutboundDto outboundDto=new OutboundDto();
    		outboundDto.setId(id);
    		outboundDto.setStu(Constants.STATUS_BEEN_APPROVAL);//状态为已通过
    		outboundApi.updateOutbound(outboundDto);
    		outboundApi.updateRepertorygoodsnumben(id);
    		map.put("result","");
    		return map;
        }
		
	}
	/**
	 * 保存出库单
	 * @return String
	 */
	@RequestMapping(value = "/selectOutbound.jhtml")
	@ResponseBody
	public OutboundDto selectOutbound(HttpServletRequest request)throws BusinessException{
		String id=request.getParameter("id");	
		OutboundDto obd=outboundApi.selectByPrimaryKey(id);
		return obd;
	}
	/**
	 * 通过货品id查询货物的详细信息
	 * @param request
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/selectGoodsDetails.jhtml")
	@ResponseBody
	public Map<String,Object> selectGoodsDetails(HttpServletRequest request) throws BusinessException{	
		String id=request.getParameter("goodsId");
		Map<String,Object> map=new HashMap<>();
		map=repertoryApi.selectGoodsDetails(id);
    		return map;		
	}
}
