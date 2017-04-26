<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>品种管理</title>
</head>
<body>
<form id="categoryForm">
		<div id="tb" style="padding: 0px; width: 100%">
			<label for="name">类别名称</label> 
			<input id="txtCategoryName" class="easyui-textbox" style="line-height: 25px; height: 25px; border: 1px solid #ccc">
			<a id="btnSelect" class="easyui-linkbutton" style="width: 50px">查询</a>
			<a id="createSelect" onclick="createSelect();" class="easyui-linkbutton" style="width: 50px">新建</a>
		</div>
	</form>

<table id="categoryList" class="easyui-datagrid" style="width:100%;"
		data-options="url:'/category/loadCategoryPageData.jhtml',fitColumns:true,singleSelect:true,
		rownumbers:true,pagination:true,striped:true,queryForm:'categoryForm'">
		<thead>
			<tr>
				<th data-options="field:'categoryName',width:70,align:'center'">类别名称</th>
				<th data-options="field:'createTime',width:70,align:'center',formatter:time">创建时间</th>
				<th data-options="field:'operate',width:70,align:'center', formatter:oper">操作</th>
			</tr>
		</thead>
	</table>
	
<!-- 修改品种 -->
	<div id="updateCategory" class="easyui-dialog"  title="修改类别" 
	     style="width: 300px; height: 350px; padding: 10px 20px;" closed="true">
		<form id="updateCategoryForm" method="post" class="easyui-form">
		<div style="display: none">
				<label>ID </label> <input name="id" class="easyui-validatebox" id="id" />
			</div>
			<div>
				<label for="name">角色组名称</label> 
				<input class="easyui-textbox" id="categoryName1" type="text" name="rolegroupName" />
			</div>
		</form>
	</div>
	
<!-- 创建品种 -->	
<div id="createCategory" class="easyui-dialog"  title="创建品种" 
	     style="width: 300px; height: 350px; padding: 10px 20px;" closed="true">
		<form id="createCategoryForm" method="post" class="easyui-form">
			<div>
				<label for="name">角色组名称</label> 
				<input class="easyui-textbox" id="categoryName2" type="text" name="rolegroupName" />
			</div>
		</form>
</div>
	
<script type="text/javascript">

/* 操作 */
function oper(val, row, index) {
			var id = row.id;
			var deleted = row.deleted;
			var str = '<span><a href="#" onclick="edit(\'' + id
					+ '\')" >修改</a></span>  '
					+ '<span><a href="#" onclick="del(\'' + id
					+ '\')">删除</a></span>  ';
			return str;
		}
		
/* 时间 */
function time(val, row, index) {
	var dateformate = new Date(val);
	return dateformate.toLocaleString();
}	

/* 模糊查询 */
$('#btnSelect').linkbutton({
	onClick : function() {
		var name = $("#txtCategoryName").textbox("getValue");
		$('#categoryList').datagrid('load', {
			"categoryName" : name,
		});
	}
});

/* 根据id删除品种  */
function del(id) {
	$.myMessager.confirm("提示！", "确实要删除该用户么！", function(r) {
		if (r) {
			$.ajax({
				url : '/category/deleteCategory.jhtml',
				data : {
					"id" : id,
				},
				success : function() {
					$('#categoryList').datagrid('reload');
					$.myMessager.info("删除用户成功！");
				}
			});
		}
	});
}

/* 修改品种信息 */
function edit(id) {
	$.ajax({
		url:'/category/getCategory.jhtml?id=' + id,
		success:function(data) {
			$("#categoryName1").textbox("setValue", data.categoryName);
		}
		
	});
	$("#updateCategory").dialog(
			{
				width : 400,
				height : 320,
				closed : false,
				buttons : [ {
					text : '保存',
					handler : function() {
						$.ajax({
							url : '/category/updateCategory.jhtml',
							data : {
								"id" : id,
								"categoryName" : $("#categoryName1").val(),
							},
							success : function() {
								$.myMessager.info("修改成功");
								$("#updateCategory").dialog('close');
								$('#categoryList').datagrid('load');
							}
						});
					}
				}, {
					text : '退出',
					handler : function() {
						$("#updateCategory").dialog('close');
					}
				} ]
			});
}


/* 创建品种  */
function createSelect() {
	$("#createCategory").dialog({
		width : 400,
		height : 320,
		closed : false,
		buttons : [ {
			text : '保存',
			handler : function() {
				$.ajax({
					url : '/category/createCategory.jhtml',
					data : {
						"categoryName" : $("#categoryName2").val(),
					},
					success : function() {
						    $.myMessager.info("你成功创建品种啦 ~");
			            	$("#categoryList").datagrid("load");
			            	$("#createCategoryForm").form('clear');
			            	$("#createCategory").dialog('close');
					}
				});
			}
		}, {
			text : '退出',
			handler : function() {
				$("#createCategory").dialog('close');
			}
		} ]
	})
}
</script>




</body>

</html>