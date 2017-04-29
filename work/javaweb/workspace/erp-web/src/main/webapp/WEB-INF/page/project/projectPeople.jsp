<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>项目成员</title>
</head>
<body>

<form id="projectPeopleForm">
		<div id="tb" style="padding: 0px; width: 100%">
			<label for="name">人员名称</label> 
			<input id="projectName" class="easyui-textbox" style="line-height: 25px; height: 25px; border: 1px solid #ccc">
			<a id="btnSelect" class="easyui-linkbutton" style="width: 50px">查询</a>
			<a id="reSelect" class="easyui-linkbutton" style="width: 50px">重置</a>
		</div>
</form>

<table id="projectPeopleList" class="easyui-datagrid" style="width:100%;"
		data-options="url:'/project/loadProjectPeoplePageData.jhtml',fitColumns:true,singleSelect:true,
		rownumbers:true,pagination:true,striped:true,queryForm:'projectPeopleForm'">
		<thead>
			<tr>
				<th data-options="field:'projectUser',width:70,align:'center'">项目成员</th>
				<th data-options="field:'joinTime',width:70,align:'center',formatter:time">加入项目时间</th>
				<th data-options="field:'leaveTime',width:70,align:'center',formatter:ltime">加入项目时间</th>
				<th data-options="field:'operate',width:70,align:'center', formatter:oper">操作</th>
			</tr>
		</thead>
</table>



<script type="text/javascript">

/* 操作 */
function oper(val, row, index) {
			var id = row.id;
			var deleted = row.deleted;
			var str = '<span><a href="#" onclick="del(\'' + id + '\')">删除</a></span>  ';
			return str;
		}
		
/* 时间 */
function time(val, row, index) {
	var dateformate = new Date(val);
	return dateformate.toLocaleString();
}

function ltime(val, row, index) {
	var dateformate = new Date(val);
	return dateformate.toLocaleString();
}
		
/* 删除 */
function del(id) {
	$.myMessager.confirm("提示！", "确实要删除该人员吗 ？", function(r) {
		if (r) {
			$.ajax({
				url : '/project/deleteProjectPeople.jhtml',
				data : {
					"id" : id,
				},
				success : function() {
					$('#projectPeopleList').datagrid('reload');
					$.myMessager.info("删除成功！");
				}
			});
		}
	});
}		
		
		
</script>


</body>
</html>