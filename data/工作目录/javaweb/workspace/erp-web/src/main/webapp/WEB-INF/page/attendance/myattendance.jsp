<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我的考勤列表</title>
</head>
<body>
    	<div id="button">
           <!--  <a id="pring"
			class="easyui-linkbutton" style="width: 100px" data-options="iconCls:'icon-print'"  onclick="printrud()">打印</a>
			 <a id="return"
			class="easyui-linkbutton" style="width: 100px" onclick="editAttendance()">导入</a> -->
			
	</div>
	<div id="myattendance">
	<table id="attendanceList" style="width: 100%;">
		<thead>
			<tr>
				<th data-options="field:'tAttendanceSumId',width:100,hidden:true" align="center"
					width="150">考勤Id</th>
				<th data-options="field:'tAttendanceSumMonth',width:100,formatter:formattergs" align="center"
					width="150">考勤月份</th>
				<th data-options="field:'projectId',width:100,hidden:true" align="center" 
					width="150">所在项目</th>
				<th data-options="field:'normalHourSum',width:100,formatter:formatterxs" align="center"
					width="150">正常工时合计</th>
				<th data-options="field:'regularOvertimeHoursSum',width:100,formatter:formatterxs" align="center"
					width="150">普通加班工时合计</th>
				<th data-options="field:'overtimeWorkingHours',width:100,formatter:formatterxs" align="center"
					width="150">节假日加班工时合计</th>
				<th data-options="field:'offworkHoursSum',width:100,formatter:formatterxs" align="center"
					width="150">请假合计</th>
				
				
			</tr>
		</thead>
	</table>
	</div>
	
	<!--跳转页面-->
	<div id="toAttendance" class="easyui-dialog" closed="true"></div>
	<script type="text/javascript">
	/* 月份 */
	function formattergs(val, row, index) {
		if(null!=val){
			return val+"月";
		}else{
			return "";
		}
		
	}
	/* 小时 */
	function formatterxs(val, row, index) {
		if(null!=val){
			return val+"小时";
		}else{
			return "";
		}
		
	}
	/* 时间 */
	function time(val, row, index) {
		if(null!=val&&""!=val&&undefined!=val){
			var dateformate = new Date(val);
			return dateformate.toLocaleString();
		}else{
			return "";
		}
	}
	$('#attendanceList').datagrid({
		url:'/attendance/selectAttendanceMonthSum.jhtml',
		fitColumns:true,
		singleSelect:true,
		rownumbers:true,
		pagination:false,
		onDblClickRow: function(index,rowData){
	    	var month =rowData.tAttendanceSumMonth;
			$("#workspace").panel(
					'refresh',
					'/attendance/showMyattendanceDetail.jhtml?month='+month+'&from=my'
			);
	    }
});
		
	</script>
</body>
</html>