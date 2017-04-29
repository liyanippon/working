<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>绩效统计</title>
</head>
<body>
	<h1>项目工时统计</h1>
	<table id="statisticsList" class="easyui-datagrid" style="width: 100%;"
		data-options="url:'/statisticsList/loadStatisticsPageData.jhtml',fitColumns:true,singleSelect:true,
		rownumbers:true,pagination:true,striped:true">
		<thead>
			<tr>
				<th data-options="field:'user_id',width:70,align:'center'">员工名字</th>
				<th data-options="field:'he',width:70,align:'center',formatter:formatters">项目中工作工时</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
	function formatters(val, row, index) {
		return val+"小时";
	}

	</script>
</body>
</html>
