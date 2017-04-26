<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>类别管理</title>
</head>
<body>
<form id="varietiesForm">
		<div id="tb" style="padding: 0px; width: 100%">
			<label for="name">品种名称</label> 
			<input id="txtVarietiesName" class="easyui-textbox" style="line-height: 25px; height: 25px; border: 1px solid #ccc">
			<a id="btnSelect" class="easyui-linkbutton" style="width: 50px">查询</a>
			<a id="createSelect" onclick="createSelect();" class="easyui-linkbutton" style="width: 50px">新建</a>
		</div>
	</form>

<table id="varietiesList" class="easyui-datagrid" style="width:100%;"
		data-options="url:'/varieties/loadVarietiesPageData.jhtml',fitColumns:true,singleSelect:true,
		rownumbers:true,pagination:true,striped:true,queryForm:'varietiesForm'">
		<thead>
			<tr>
				<th data-options="field:'varietiesName',width:70,align:'center'">品种名称</th>
				<th data-options="field:'createTime',width:70,align:'center',formatter:time">创建时间</th>
				<th data-options="field:'operate',width:100,align:'center',formatter:formatOper">操作</th>
			</tr>
		</thead>
	</table>

<!-- 修改品种 -->
	<div id="updateVarieties" class="easyui-dialog"  title="修改品种" 
	     style="width: 300px; height: 350px; padding: 10px 20px;" closed="true">
		<form id="updateVarietiesForm" method="post" class="easyui-form">
		    <div style="display: none">
				<label>ID </label> <input name="id" class="easyui-validatebox" id="id" />
			</div>
			<div>
				<label for="name">品种名称</label> 
				<input class="easyui-textbox" id="varietiesName1" type="text" name="varietiesName" />
			</div>
		</form>
	</div>
	
<!-- 创建品种 -->
	<div id="createVarieties" class="easyui-dialog"  title="修改品种" 
	     style="width: 300px; height: 350px; padding: 10px 20px;" closed="true">
		<form id="createVarietiesForm" method="post" class="easyui-form">
			<div>
				<label for="name">品种名称</label> 
				<input class="easyui-textbox" id="varietiesName2" type="text" name="varietiesName" />
			</div>
		</form>
	</div>

<script type="text/javascript">

/* 操作 */
function formatOper(val, row, index) {
	var id=row.id;
	var deleted = row.deleted;
	var r = '<a href="#" onclick="updaterow(\''+id+'\')">修改</a>  '
			+ '<a href="#", onclick="deletes(\''+id+'\')">删除</a>';
	return r;
		}
		
/* 时间 */
function time(val, row, index) {
	var dateformate = new Date(val);
	return dateformate.toLocaleString();
}
	
/* 模糊查询 */
$('#btnSelect').linkbutton({
	onClick : function() {
		var name = $("#txtVarietiesName").textbox("getValue");
		$('#varietiesList').datagrid('load', {
			"varietiesName" : name,
		});
	}
});

/* 根据id删除用户 */
function deletes(id) {
	$.myMessager.confirm("提示！", "确实要删除该用户么！", function(r) {
		if (r) {
			$.ajax({
				url : '/varieties/deleteVarieties.jhtml',
				data : {
					"id" : id,
				},
				success : function() {
					$('#varietiesList').datagrid('reload');
					$.myMessager.info("删除用户成功！");
				}
			});
		}
	});
}


/* 修改类别信息 */
function updaterow(id) {
	$.ajax({
		url:'/varieties/getVarieties.jhtml?id=' + id,
		success:function(data) {
			$("#varietiesName1").textbox("setValue", data.varietiesName);
		}
	});
	$("#updateVarieties").dialog(
			{
				width : 400,
				height : 320,
				closed : false,
				buttons : [ {
					text : '保存',
					handler : function() {
						$.ajax({
							url : '/varieties/updateVarieties.jhtml',
							data : {
								"id" : id,
								"varietiesName" : $("#varietiesName1").val(),
							},
							success : function() {
								$.myMessager.info("修改成功");
								$("#updateVarieties").dialog('close');
								$('#varietiesList').datagrid('load');
							}
						});
					}
				}, {
					text : '退出',
					handler : function() {
						$("#updateVarieties").dialog('close');
					}
				} ]
			});
}


/* 创建类别 */
function createSelect() {
	$("#createVarieties").dialog({
		width : 400,
		height : 320,
		closed : false,
		buttons : [ {
			text : '保存',
			handler : function() {
				$.ajax({
					url : '/varieties/createVarieties.jhtml',
					data : {
						"varietiesName" : $("#varietiesName2").val(),
					},
					success : function() {
						$.myMessager.info("创建类别成功");
			            	$("#varietiesList").datagrid("load");
			            	$("#createVarietiesForm").form('clear');
			            	$("#createVarieties").dialog('close');
					}
				});
			}
		}, {
			text : '退出',
			handler : function() {
				$("#createVarieties").dialog('close');
			}
		} ]
	})
}


</script>




</body>
</html>