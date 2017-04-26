package com.yifeng.web.controller.system;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds.business.controller.BaseController;
import com.ds.business.dto.MessageDto;
import com.ds.business.pagination.PageData;
import com.ds.business.pagination.PageRequest;
import com.ds.fw.exception.BusinessException;
import com.ds.system.api.MessageApi;

/**
 * 消息管理
 * 
 * @author yangqili
 *
 */
@Controller
@RequestMapping(value = "/system")
public class MessageController extends BaseController {
	@Autowired
	private MessageApi messageApi;

	/**
	 * 消息维护页面跳转
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showMessageList.jhtml")
	public String showMessageList() {
		return "/system/messageList";
	}
	
	/**
	 * 根据查询条件查询数据库中消息信息
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/loadMessagePageData.jhtml")
	@ResponseBody
	public PageData<MessageDto> loadMessageListBycondition(HttpServletRequest request) throws BusinessException {
		PageRequest pageRequest = this.getPageRequest(request);
		return messageApi.getMessageList(pageRequest);
	}
	
	/**
	 * 新建消息
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/addMessage.jhtml")
	@ResponseBody
	public String addCode(MessageDto messageDto, HttpServletRequest request) throws BusinessException {
		String userName = this.getUserName(request);//当前用户
		messageDto.setCreatedBy(userName);
		messageDto.setUpdatedBy(userName);
		messageDto.setCreateTime(new Date());//创建时间
		messageDto.setUpdateTime(new Date());//更新时间
		 String code =this.messageApi.insertMessage(messageDto);
		 if(StringUtils.isNotEmpty(code)){
			 return "true";
		 }
		 return "false";
		
	}
	/**
	 * 根据code删除该单值代码
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/deleteMessagePageData.jhtml")
	@ResponseBody
	public boolean deleteMessagePageData(HttpServletRequest request)throws BusinessException {
		String code = request.getParameter("code");
		if(StringUtils.isEmpty(code)){
			return false;
		}
		return messageApi.delete(code);
	}
	/**
	    * 根据code查询Message信息
	    * 
	    * @param request
	    * @return pageData
	    * @throws ServiceException
	    */
	   @RequestMapping(value = "/getMessageDetail.jhtml")
	   @ResponseBody
	   public MessageDto getMessageDetial(HttpServletRequest request) {
	       String code = request.getParameter("code");
	       MessageDto messagedto = messageApi.getMessage(code);
	       return messagedto;
	   }
	/**
	 * 修改Message
	 * 
	 * @param request
	 * @return pageData
	 * @throws BusinessException 
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/updateMessage.jhtml")
	@ResponseBody
	public String updateMessage(MessageDto messageDto,HttpServletRequest request) throws BusinessException{
		String userName = this.getUserName(request);//当前用户
		messageDto.setUpdatedBy(userName);
		messageDto.setUpdateTime(new Date());//更新时间
		String code = this.messageApi.updateMessage(messageDto);
		if(StringUtils.isNotEmpty(code)){
			return "true";
		}
		return "false";
	}

	
}
