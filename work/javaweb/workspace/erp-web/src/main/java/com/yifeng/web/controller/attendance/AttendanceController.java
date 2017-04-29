package com.yifeng.web.controller.attendance;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
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
import com.ds.business.pagination.PageData;
import com.ds.business.pagination.PageRequest;
import com.ds.fw.exception.BusinessException;
import com.yifeng.dto.AttendanceDto;
import com.yifeng.dto.AttendanceSumDto;
import com.yifeng.dto.OffworkDto;
import com.yifeng.entity.Attendance;
import com.yifeng.entity.AttendanceSum;
import com.yifeng.service.AttendanceService;
import com.yifeng.service.AttendanceSumService;

/**
 * 我的考勤汇总
 * 
 * @author yangqili
 *
 */
@Controller
@RequestMapping(value = "/attendance")
public class AttendanceController extends BaseController {
	@Autowired
	private AttendanceSumService attendanceSumService;
	@Autowired
	private AttendanceService attendanceService;

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
		binder.registerCustomEditor(Date.class, dateEditor);
	}

	/**
	 * 新建我的考勤跳转
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showMyattendance.jhtml")
	public String showMyattendance() {
		return "/attendance/myattendance";
	}

	/**
	 * 跳转到考勤明细
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showMyattendanceDetail.jhtml")
	public String showMyattendanceDetail() {
		return "/attendance/attendanceDetail";
	}

	/**
	 * 部门考勤列表
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showCopAttendance.jhtml")
	public String showCopAttendance() {
		return "/attendance/copAttendance";
	}

	/**
	 * 根据userId获取我的考勤系统年，当月份汇总
	 * 
	 * @param userId
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/selectAttendanceMonthSum.jhtml")
	@ResponseBody
	public List<AttendanceSumDto> selectAttendanceMonthSum(HttpServletRequest request) throws BusinessException {
		PageRequest pageRequest = new PageRequest();
		String userName = this.getUserName(request);// 当前用户
		String dateYearstr = getNowYear();
		String dateMonthstr =this.getNowMonth();
		pageRequest.getConditions().put("deleted", "0");
		pageRequest.getConditions().put("year", dateYearstr);
		pageRequest.getConditions().put("month", dateMonthstr);
		pageRequest.getConditions().put("userId", userName);
		if (StringUtils.isEmpty(userName)) {
			return null;
		} else {

			return this.attendanceSumService.selectAttendanceMonthSum(pageRequest);
		}

	}

	// 获取当年
	private String getNowYear() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		return String.valueOf(year);
	}

	// 获取当月
	private String getNowMonth() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		return String.valueOf(month);
	}

	// 获取当天
	private String getNowDay() {
		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DATE);
		return String.valueOf(day);
	}

	/**
	 * 根据userId获取我的考勤月份汇总
	 * 
	 * @param userId
	 * @return
	 * @throws BusinessException
	 * @throws ParseException
	 */
	@RequestMapping(value = "/getAttendanceDetailBymonth.jhtml")
	@ResponseBody
	public List<AttendanceDto> getAttendanceDetailBymonth(HttpServletRequest request)
			throws BusinessException, ParseException {
		String userId = this.getUserName(request);// 当前用户
		if (StringUtils.isEmpty(userId)) {
			return null;
		} else {
			String month = request.getParameter("month");
			List<AttendanceDto> dto = attendanceService.selectAttendanceByMonthAnduserId(month, userId);
			if (dto != null && dto.size() > 0) {
				return dto;
			}
			return null;
		}

	}

	/**
	 * 单位考勤分页显示数据
	 * 
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/getAllAttendenceBycondition.jhtml")
	@ResponseBody
	public PageData<AttendanceDto> getAllAttendenceBycondition(HttpServletRequest request) throws BusinessException {
		PageRequest pageRequest = this.getPageRequest(request);
		//String userId = this.getUserName(request);// 当前用户
		String dateYearstr ="";
		String dateMonthstr ="";
		if(StringUtils.isEmpty(request.getParameter("year"))){
			dateYearstr= getNowYear();
			pageRequest.getConditions().put("year", dateYearstr);
		}
		if(StringUtils.isEmpty(request.getParameter("month"))){
			dateMonthstr= getNowMonth();
			pageRequest.getConditions().put("month", dateMonthstr);
		}else if(request.getParameter("month").length()==1){
			dateMonthstr="0"+request.getParameter("month");
			pageRequest.getConditions().put("month", dateMonthstr);
		}
		
		pageRequest.getConditions().put("delete", "0");// 是否删除
		
		return attendanceService.getAllAttendenceBycondition(pageRequest);
	}

	/**
	 * 获取年份
	 * 
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/getAllYears.jhtml")
	@ResponseBody
	public List<String> getAllYears(PageRequest pageRequest) throws BusinessException {
		return attendanceSumService.getAllYears(pageRequest);
	}

	/**
	 * 考勤添加数据
	 * 
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/insertOrupdateAttendance.jhtml")
	@ResponseBody
	public Map<String, Object> insertOrupdateAttendance(HttpServletRequest request) throws BusinessException {
		Map<String, Object> result = new HashMap<String, Object>();
		AttendanceDto attendanceDto = new AttendanceDto();
		String userId = this.getUserName(request);// 当前用户
		String attendanceId = request.getParameter("attendanceId");
		String normalHours = request.getParameter("normalHour");
		BigDecimal normalHour = new BigDecimal(normalHours);
		String regularOvertimeHoursstr = request.getParameter("regularOvertimeHours");
		BigDecimal regularOvertimeHours = new BigDecimal(regularOvertimeHoursstr);
		String overtimeWorkingHoursstr = request.getParameter("overtimeWorkingHours");
		BigDecimal overtimeWorkingHours = new BigDecimal(overtimeWorkingHoursstr);
		attendanceDto.setAttendanceId(attendanceId);
		attendanceDto.setNormalHour(normalHour);
		attendanceDto.setRegularOvertimeHours(regularOvertimeHours);
		attendanceDto.setOvertimeWorkingHours(overtimeWorkingHours);
		attendanceDto.setDelete("0");
		attendanceDto.setUpdateBy(userId);
		attendanceDto.setUpdateTime(new Timestamp(new java.util.Date().getTime()));// 更新时间
		attendanceDto.setStatus("016002");//待审核
		attendanceDto.setSubmitTime(new Timestamp(new java.util.Date().getTime()));
	
		
		//获取请假信息保存到请假表中
		

		String returnid = this.attendanceService.insertOrupdateAttendance(attendanceDto);
		AttendanceDto attendanceDtos = attendanceService.selectByPrimaryKey(attendanceDto.getAttendanceId());
		// 同时把数据插入到汇总表中
		this.attendanceService.updateAttendanceSum(attendanceDtos);

		if (StringUtils.isNotEmpty(returnid)) {
			result.put("succ", true);
			result.put("returnid", returnid);
		} else {
			result.put("succ", false);
		}
		return result;
	}

	/**
	 * 更新审核状态
	 * 
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/updateAttendanceStatus.jhtml")
	@ResponseBody
	public Map<String, Object> updateAttendanceStatus(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();

		// 主键
		String attendanceIdsArray = request.getParameter("attendanceIds");
		// 状态
		String status = request.getParameter("status");
		// 当前人
		String userId = this.getUserName(request);
		
		
		attendanceIdsArray = attendanceIdsArray.substring(1, attendanceIdsArray.length() - 1);// 去掉[]
		attendanceIdsArray = attendanceIdsArray.replaceAll("\"", "");
		String[] strArray = attendanceIdsArray.split(",");

		for (int i = 0; i < strArray.length; i++) {
			AttendanceDto attendanceDto = attendanceService.selectByPrimaryKey(strArray[i]);
			attendanceDto.setStatus(status);
			attendanceDto.setProcessTime(new Timestamp(new java.util.Date().getTime()));
			attendanceDto.setProcessBy(userId);
			attendanceDto.setUpdateBy(userId);
			attendanceDto.setUpdateTime(new Timestamp(new java.util.Date().getTime()));
			if (attendanceDto != null) {
				int flag = attendanceService.updateAttendanceStatus(attendanceDto);
				if (flag == 0) {
					result.put("succ", true);
				}else{
					result.put("succ", false);
					break;
				}
			}

		}

		return result;
	}
}
