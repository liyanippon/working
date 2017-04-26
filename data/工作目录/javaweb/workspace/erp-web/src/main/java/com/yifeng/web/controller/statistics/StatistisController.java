package com.yifeng.web.controller.statistics;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds.business.controller.BaseController;
import com.ds.business.pagination.PageData;
import com.ds.business.pagination.PageRequest;
import com.ds.fw.exception.BusinessException;
import com.yifeng.dto.ProjectDto;
import com.yifeng.service.StatisticsService;

@Controller
@RequestMapping(value = "/statisticsList")
public class StatistisController extends BaseController  {

	@Autowired
	private StatisticsService statisticsService;
	
	@RequestMapping(value="/showStatisticsList.jhtml")
	public String showProjectList() {
		return "/statistics/statisticsList";
	}
	/**
	 * 查询数据库中所有人员
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/loadStatisticsPageData.jhtml")
	@ResponseBody
	public PageData<Map<String, Object>> loadStatisticsPageData(HttpServletRequest request) throws BusinessException {
		PageRequest pageRequest = this.getPageRequest(request);
		return statisticsService.getStatisticsListByPage(pageRequest);
		

	}
	
}
