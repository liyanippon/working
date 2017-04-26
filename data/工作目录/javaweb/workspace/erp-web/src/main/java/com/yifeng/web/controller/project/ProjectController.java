package com.yifeng.web.controller.project;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds.business.controller.BaseController;
import com.ds.business.pagination.PageData;
import com.ds.business.pagination.PageRequest;
import com.ds.fw.exception.BusinessException;
import com.yifeng.dto.ProjectDto;
import com.yifeng.dto.ProjectPeopleDto;
import com.yifeng.service.ProjectPeopleService;
import com.yifeng.service.ProjectService;

@Controller
@RequestMapping(value = "/project")
public class ProjectController extends BaseController {

	@Autowired
	private ProjectService projectService;
	@Autowired
	private ProjectPeopleService projectPeopleService;
	
	@InitBinder
    protected void initBinder(HttpServletRequest request,
        ServletRequestDataBinder binder) throws Exception {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }

	@RequestMapping(value = "/showProjectList.jhtml")
	public String showProjectList() {
		return "/project/projectList";
	}

	@RequestMapping(value = "/showNewProject.jhtml")
	public String showDepartList() {
		return "/project/newProject";
	}
	
	@RequestMapping(value = "/showPeopleList.jhtml")
	public String showPeopleList() {
		return "/project/projectPeople";
	}

	/**
	 * 查询数据库中所有项目
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/loadProjectPageData.jhtml")
	@ResponseBody
	public PageData<ProjectDto> loadProjectPageData(HttpServletRequest request) throws BusinessException {
		PageRequest pageRequest = this.getPageRequest(request);
		return projectService.getProjectListByPage(pageRequest);
	}

	/**
	 * 查询数据库中所有人员
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/loadProjectPeoplePageData.jhtml")
	@ResponseBody
	public PageData<Map<String, Object>> loadProjectPeoplePageData(HttpServletRequest request) throws BusinessException {
		PageRequest pageRequest = this.getPageRequest(request);
		pageRequest.getConditions().put("pid", request.getParameter("projectId"));
		return projectPeopleService.getProjectPeopleListByPage(pageRequest); 
	}
	
	/**
     * 删除项目
     */
    @RequestMapping(value = "/deleteProject.jhtml")
    @ResponseBody
    public void deleteProject(HttpServletRequest request) {
        String id = request.getParameter("id");
        projectService.deleteProject(id);
        projectPeopleService.delPopBypid(id); 
    }
    
    /**
     * 删除人员
     */
    @RequestMapping(value = "/deleteProjectPeople.jhtml")
    @ResponseBody
    public void deleteProjectPeople(HttpServletRequest request) {
        String id = request.getParameter("id");
        projectPeopleService.deletePop(id);
    }
    
    /**
     * 根据id查询项目
     */
    @RequestMapping(value = "/getProject.jhtml")
    @ResponseBody
    public ProjectDto getProject(HttpServletRequest request){
    	String id = request.getParameter("id");
    	ProjectDto projectDto = projectService.getProjectByid(id);
    	return projectDto;
    }
    
    /**
	 * 修改合同
	 * */
	@RequestMapping(value = "/updateProject.jhtml")
    @ResponseBody
    public void updateContract(HttpServletRequest request){
		ProjectDto projectDto = new ProjectDto();
		projectDto.setId(request.getParameter("id"));
		projectDto.setProjectName(request.getParameter("projectName"));
		projectDto.setProjectManager(request.getParameter("projectManager"));
		projectDto.setProjectPeople(request.getParameter("projectPeople"));
		projectDto.setMome(request.getParameter("mome"));
		String statrtime = request.getParameter("startTime");
		String endtime = request.getParameter("endTime");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = format.parse(statrtime);
			date2 = format.parse(endtime);
			projectDto.setStartTime(date1);
			projectDto.setEndTime(date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		projectDto.setStartTime(date1);
		projectDto.setEndTime(date2);
		projectService.updateProject(projectDto); 
	}
	
	/**
	 * 新建项目
	 * */
	@RequestMapping(value = "/createProject.jhtml")
    @ResponseBody
    public String createContract(HttpServletRequest request,ProjectDto projectDto){		
		return this.projectService.insertProject(projectDto);
	}
	
	/**
	 * 添加人员
	 * */
	@RequestMapping(value = "/addProjectPeople.jhtml")
    @ResponseBody
    public void addProjectPeople(HttpServletRequest request,ProjectPeopleDto rojectPeopleDto){		
		rojectPeopleDto.setProjectId(request.getParameter("projectId"));
		rojectPeopleDto.setProjectUser(request.getParameter("projectPeople"));
		this.projectPeopleService.insertPop(rojectPeopleDto);
	}
}
