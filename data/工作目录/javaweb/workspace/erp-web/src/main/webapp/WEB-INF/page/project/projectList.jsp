<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>项目一览表</title>
</head>
<body>
<form id="projectForm">
		<div id="tb" style="padding: 0px; width: 100%">
			<div style="padding: 0px; width: 100%">
			<a id="btnSelect" class="easyui-linkbutton" style="width: 80px" data-options="plain:true,iconCls:'icon-search'">查询</a>
			<a id="reSelect" class="easyui-linkbutton" style="width: 80px" data-options="plain:true,iconCls:'icon-reload'">重置</a>
			</div>
			<div style="float:left; width: 30%; height:35px;">
			<label for="name">项目名称</label> 
			<input id="txtprojectName" class="easyui-textbox" style="line-height: 25px; height: 25px; border: 1px solid #ccc">
			</div>
		</div>
</form>

<table id="projectList" class="easyui-datagrid" style="width:100%;"
		data-options="url:'/project/loadProjectPageData.jhtml',fitColumns:true,singleSelect:true,
		rownumbers:true,pagination:true,striped:true,queryForm:'projectForm'">
		<thead>
			<tr>
				<th data-options="field:'projectName',width:70,align:'center'">项目名称</th>
				<th data-options="field:'projectManager',width:70,align:'center'">项目经理</th>
				<th data-options="field:'yjstartTime',width:70,align:'center',formatter:yjtime,sortable:'true'">预计开始时间</th>
				<th data-options="field:'startTime',width:70,align:'center',formatter:time">实际开始时间</th>
				<th data-options="field:'stu',width:70,align:'center',formatter: function(value){
																if (value=='017001'||value==017001){
																	return '项目进行中';
																} else {
																	return '项目已结束';
																}
																}"
				>合同状态</th>
				<th data-options="field:'operate',width:70,align:'center', formatter:oper">操作</th>
			</tr>
		</thead>
</table>



<script type="text/javascript">

/* 操作 */
function oper(val, row, index) {
			var id = row.id;
			var deleted = row.deleted;
			var str = '<span><a href="#" onclick="detail(\'' + id + '\')" >详情</a></span>  '
					  +'<span><a href="#" onclick="update(\'' + id + '\')" >修改</a></span>  '
					  +'<span><a href="#" onclick="del(\'' + id + '\')">删除</a></span>  ';
			return str;
		}

/* 模糊查询 */
$('#btnSelect').linkbutton({
	onClick : function() {
		var projectName = $("#txtprojectName").textbox("getValue");
		$('#projectList').datagrid('load', {
			"projectName" : projectName,
		});
	}
});

/* 重置 */
$('#reSelect').linkbutton({
	onClick : function() {
		$("#projectForm").form("reset");
		$("#projectList").datagrid('load', {});
	}
});

/* 时间 */
function time(val, row, index) {
	var dateformate = new Date(val);
	return dateformate.toLocaleString();
}

function yjtime(val, row, index) {
	var dateformate = new Date(val);
	return dateformate.toLocaleString();
}

/* 详情  */
function detail(id) {
			$('#workspace').panel('refresh',
			'/project/showNewProject.jhtml?projectId=' + id+"&&operated=2");
}
/* 修改  */
function update(id) {
			$('#workspace').panel('refresh',
			'/project/showNewProject.jhtml?projectId=' + id+"&&operated=1");
}

/* 删除 */
function del(id) {
	$.myMessager.confirm("提示！", "确实要删除项目吗 ？", function(r) {
		if (r) {
			$.ajax({
				url : '/project/deleteProject.jhtml',
				data : {
					"id" : id,
				},
				success : function() {
					$('#projectList').datagrid('reload');
					$.myMessager.info("删除成功！");
				}
			});
		}
	});
}



</script>

</body>
</html>