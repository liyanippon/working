<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>单位管理</title>
</head>
<body>
<form id="unitForm">
		<div id="tb" style="padding: 0px; width: 100%">
			<label for="name">单位名称</label> 
			<input id="txtUnitName" class="easyui-textbox" style="line-height: 25px; height: 25px; border: 1px solid #ccc">
			<a id="btnSelect" class="easyui-linkbutton" style="width: 50px">查询</a>
		    <a id="createSelect" onclick="createSelect();" class="easyui-linkbutton" style="width: 50px">新建</a> 
		</div>
	</form>

<table id="unitList" class="easyui-datagrid" style="width:100%;"
		data-options="url:'/unit/loadUnitPageData.jhtml',fitColumns:true,singleSelect:true,
		rownumbers:true,pagination:true,striped:true,queryForm:'unitForm'">
		<thead>
			<tr>
				<th data-options="field:'unitName',width:70,align:'center'">单位名称</th>
				<th data-options="field:'createTime',width:70,align:'center',formatter:time">创建时间</th>
				<th data-options="field:'operate',width:100,align:'center',formatter:formatOper">操作</th>
			</tr>
		</thead>
	</table>
	
<!-- 修改单位 -->
	<div id="updateUnit" class="easyui-dialog"  title="修改单位" 
	     style="width: 300px; height: 350px; padding: 10px 20px;" closed="true">
		<form id="updateCategoryForm" method="post" class="easyui-form">
		<div style="display: none">
				<label>ID </label> <input name="id" class="easyui-validatebox" id="id" />
			</div>
			<div>
				<label for="name">单位名称</label> 
				<input class="easyui-textbox" id="unitName1" type="text" name="unitName" />
			</div>
		</form>
     </div>
     
<!-- 创建单位 -->
	<div id="createUnit" class="easyui-dialog"  title="创建单位" 
	     style="width: 300px; height: 350px; padding: 10px 20px;" closed="true">
		<form id="createUnitForm" method="post" class="easyui-form">
			<div>
				<label for="name">单位名称</label> 
				<input class="easyui-textbox" id="unitName2" type="text" name="unitName" />
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
		var name = $("#txtUnitName").textbox("getValue");
		$('#unitList').datagrid('load', {
			"unitName" : name,
		});
	}
});

/* 根据id删除用户 */
function deletes(id) {
	$.myMessager.confirm("提示！", "确实要删除该用户么！", function(r) {
		if (r) {
			$.ajax({
				url : '/unit/deleteUnit.jhtml',
				data : {
					"id" : id,
				},
				success : function() {
					$('#unitList').datagrid('reload');
					$.myMessager.info("删除用户成功！");
				}
			});
		}
	});
}

/* 修改类别信息 */
function updaterow(id) {
	$.ajax({
		url:'/unit/getUnit.jhtml?id=' + id,
		success:function(data) {
			$("#unitName1").textbox("setValue", data.unitName);
		}
		
	});
	$("#updateUnit").dialog(
			{
				width : 400,
				height : 320,
				closed : false,
				buttons : [ {
					text : '保存',
					handler : function() {
						$.ajax({
							url : '/unit/updateUnit.jhtml',
							data : {
								"id" : id,
								"unitName" : $("#unitName1").val(),
							},
							success : function() {
								$.myMessager.info("创建单位成功");
								$("#updateUnit").dialog('close');
								$('#unitList').datagrid('load');
							}
						});
					}
				}, {
					text : '退出',
					handler : function() {
						$("#updateUnit").dialog('close');
					}
				} ]
			});
}

/* 创建单位  */
function createSelect() {
	$("#createUnit").dialog({
		width : 400,
		height : 320,
		closed : false,
		buttons : [ {
			text : '保存',
			handler : function() {
				$.ajax({
					url : '/unit/createUnit.jhtml',
					data : {
						"unitName" : $("#unitName2").val(),
					},
					success : function() {
						$.myMessager.info("保存单位成功");
			            	$("#unitList").datagrid("load");
			            	$("#createUnitForm").form('clear');
			            	$("#createUnit").dialog('close');
					}
				});
			}
		}, {
			text : '退出',
			handler : function() {
				$("#createUnit").dialog('close');
			}
		} ]
	})
}

</script>





</body>
</html>