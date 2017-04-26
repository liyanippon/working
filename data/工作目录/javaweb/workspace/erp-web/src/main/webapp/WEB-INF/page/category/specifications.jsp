<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>规格管理</title>
</head>
<body>
<form id="specificationsForm">
		<div id="tb" style="padding: 0px; width: 100%">
			<label for="name">规格名称</label> 
			<input id="txtSpecificationsName" class="easyui-textbox" style="line-height: 25px; height: 25px; border: 1px solid #ccc">
			<a id="btnSelect" class="easyui-linkbutton" style="width: 50px">查询</a>
		    <a id="createSelect" onclick="createSelect();" class="easyui-linkbutton" style="width: 50px">新建</a>
		</div>
	</form>

<table id="specificationsList" class="easyui-datagrid" style="width:100%;"
		data-options="url:'/specifications/loadSpecificationsPageData.jhtml',fitColumns:true,singleSelect:true,
		rownumbers:true,pagination:true,striped:true,queryForm:'specificationsForm'">
		<thead>
			<tr>
				<th data-options="field:'specificationsName',width:70,align:'center'">规格名称</th>
				<th data-options="field:'createTime',width:70,align:'center',formatter:time">操作时间</th>
				<th data-options="field:'operate',width:100,align:'center',formatter:formatOper">操作</th>
			</tr>
		</thead>
	</table>


<!-- 修改规格 -->
	<div id="updateSpecifications" class="easyui-dialog"  title="修改规格" 
	     style="width: 300px; height: 350px; padding: 10px 20px;" closed="true">
		<form id="updateCategoryForm" method="post" class="easyui-form">
		<div style="display: none">
				<label>ID </label> <input name="id" class="easyui-validatebox" id="id" />
			</div>
			<div>
				<label for="name">角色组名称</label> 
				<input class="easyui-textbox" id="specificationsName1" type="text" name="specificationsName" />
			</div>
		</form>
	</div>
	
<!-- 创建规格 -->	
<div id="createSpecifications" class="easyui-dialog"  title="创建规格" 
	     style="width: 300px; height: 350px; padding: 10px 20px;" closed="true">
		<form id="createSpecificationsForm" method="post" class="easyui-form">
			<div>
				<label for="name">角色组名称</label> 
				<input class="easyui-textbox" id="specificationsName2" type="text" name="specificationsName" />
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
		var name = $("#txtSpecificationsName").textbox("getValue");
		$('#specificationsList').datagrid('load', {
			"specificationsName" : name,
		});
	}
});
		
/* 根据id删除规格 */
function deletes(id) {
	$.myMessager.confirm("提示！", "确实要删除该用户么！", function(r) {
		if (r) {
			$.ajax({
				url : '/specifications/deleteSpecifications.jhtml',
				data : {
					"id" : id,
				},
				success : function() {
					$('#specificationsList').datagrid('reload');
					$.myMessager.info("删除用户成功！");
				}
			});
		}
	});
}
	
/* 修改规格信息 */
function updaterow(id) {
	$.ajax({
		url:'/specifications/getSpecifications.jhtml?id=' + id,
		success:function(data) {
			$("#specificationsName1").textbox("setValue", data.specificationsName);
		}
		
	});
	$("#updateSpecifications").dialog(
			{
				width : 400,
				height : 320,
				closed : false,
				buttons : [ {
					text : '保存',
					handler : function() {
						$.ajax({
							url : '/specifications/updateSpecifications.jhtml',
							data : {
								"id" : id,
								"specificationsName" : $("#specificationsName1").val(),
							},
							success : function() {
								$.myMessager.info("修改成功");
								$("#updateSpecifications").dialog('close');
								$('#specificationsList').datagrid('load');
							}
						});
					}
				}, {
					text : '退出',
					handler : function() {
						$("#updateSpecifications").dialog('close');
					}
				} ]
			});
}

/* 创建规格  */
function createSelect() {
	$("#createSpecifications").dialog({
		width : 400,
		height : 320,
		closed : false,
		buttons : [ {
			text : '保存',
			handler : function() {
				$.ajax({
					url : '/specifications/createSpecifications.jhtml',
					data : {
						"specificationsName" : $("#specificationsName2").val(),
					},
					success : function() {
						    $.myMessager.info("创建规格成功");
			            	$("#specificationsList").datagrid("load");
			            	$("#createSpecificationsForm").form('clear');
			            	$("#createSpecifications").dialog('close');
					}
				});
			}
		}, {
			text : '退出',
			handler : function() {
				$("#createSpecifications").dialog('close');
			}
		} ]
	})
}
</script>


</body>
</html>