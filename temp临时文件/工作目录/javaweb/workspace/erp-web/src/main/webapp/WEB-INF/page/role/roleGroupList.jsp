<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>角色组维护</title>
</head>
<body>
<form id="roleGroupForm">
		<div id="tb" style="padding: 0px; width: 100%">
			<label for="name">角色组名称</label> 
			<input id="txtRoleName" class="easyui-textbox" style="line-height: 25px; height: 25px; border: 1px solid #ccc">
			<a id="btnSelect" class="easyui-linkbutton" style="width: 50px">查询</a>
			<a id="btnReset" style="width: 50px" href="#"
				class="easyui-linkbutton">重置</a>
		    <a id="crtRole" class="easyui-linkbutton" style="width: 60px" onclick="crtRoleGroup();">添加</a>
		</div>
	</form>

<table id="rolegroupList" class="easyui-datagrid" style="width:100%;"
		data-options="url:'/roleGroup/loadRoleGroupPageData.jhtml',fitColumns:true,singleSelect:true,
		rownumbers:true,pagination:true,striped:true,queryForm:'roleGroupForm'">
		<thead>
			<tr>
				<th data-options="field:'rolegroupName',width:70,align:'center'">角色组</th>
				<th data-options="field:'_operation',width:100,align:'center',formatter:formatOper">操作</th>
			</tr>
		</thead>
	</table>
	
	<!-- 新建角色组信息 -->
	<div id="createRoleGroup" class="easyui-dialog" 
		style="width: 300px; height: 350px; padding: 10px 20px;" closed="true" title="创建角色组"> 
		<form id="createRoleGroupForm" method="post" class="easyui-form">
			<div>
				<label for="name">角色组名称</label> 
				<input class="easyui-textbox" id="rolegroupName1" type="text" name="rolegroupName" maxlength="20"/>
			</div>
			<div>
				<label for="name">显示顺序</label> 
				<input class="easyui-textbox" id="sortorder1" type="text" name="sortorder" maxlength="16"/>
			</div>
			<div>
				 <input class="easyui-checkbox" id="continueCreate" type="checkbox" name="createNext" onclick="continued();"/>继续创建下一条
			</div> 
		</form>
	</div>
	
	<!-- 修改角色组信息 -->
	<div id="updateRoleGroup" class="easyui-dialog"  title="修改角色组" 
	     style="width: 300px; height: 350px; padding: 10px 20px;" closed="true">
		<form id="updateRoleGroupForm" method="post" class="easyui-form">
		<div style="display: none">
				<label>角色ID </label> <input name="id" class="easyui-validatebox" id="id" />
			</div>
			<div>
				<label for="name">角色组名称</label> 
				<input class="easyui-textbox" id="rolegroupName2" type="text" name="rolegroupName" />
			</div>
		</form>
	</div>
	<script type="text/javascript">
	/* 操作 */
	function formatOper(val, row, index) {
		var id=row.id;
		var deleted=row.deleted;
		var r = '<a href="#" onclick="updaterow(\''+id+'\')">修改</a>  '
				+ '<a href="#", onclick="deletes(\''+id+'\')">删除</a>';
		return r;
			}
	
	/* 模糊查询  */
	$('#btnSelect').linkbutton({
		onClick : function() {
			var name = $("#txtRoleName").textbox("getValue");
			$('#rolegroupList').datagrid('load', {
				rolegroupName : name,
			});
		}
	});
	/* 重置 */
	$('#btnReset').linkbutton({
		onClick : function() {
			var name = $("#txtRoleName").textbox("reset");
			var rename = $("#txtRoleName").textbox("getValue");
			var gender = $("#comboSelect1").combobox("reset");
			var state = $("#comboSelect2").combobox("reset");
			$("#rolegroupList").datagrid('load', {
				rolegroupName : rename,
			});
		}
	});
	/* 创建角色组 */
	function crtRoleGroup() {
		$("#createRoleGroup").dialog({
			width : 400,
			height : 320,
			closed : false,
			buttons : [ {
				text : '保存',
				handler : function() {
					var nextData = $("#continueCreate").is(":checked");
					$.ajax({
						url : '/roleGroup/addRoleGroup.jhtml',
						data : {
							"rolegroupName" : $("#rolegroupName1").val(),
							"sortorder" : $("#sortorder1").val(),
						},
						success : function() {
							$.myMessager.info("提示信息", "保存成功");
							if(nextData){
								$("#rolegroupList").datagrid("load");
								$("#createRoleGroupForm").form('clear');
							}else{
								$("#createRoleGroup").dialog("close");
					            $("#rolegroupList").datagrid("load");
					            $("#createRoleGroupForm").form('clear');
							}
						}
					});
				}
			}, {
				text : '关闭',
				handler : function() {
					$("#createRoleGroup").dialog('close');
				}
			} ]
		})
	}
	/* 修改用户组信息 */
	function updaterow(id) {
		$("#updateRoleGroup").dialog(
				{
					width : 400,
					height : 320,
					closed : false,
					onOpen : function() {
						$("#updateRoleGroupForm").form('load',
								'/roleGroup/getRoleGroup.jhtml?id=' + id);
					},
					buttons : [ {
						text : '保存',
						handler : function() {
							$.ajax({
								url : '/roleGroup/updateRoleGroup.jhtml',
								data : {
									"id" : id,
									"rolegroupName" : $("#rolegroupName2").val(),
								},
								success : function() {
									$.myMessager.info("修改成功");
									$("#updateRoleGroup").dialog('close');
									$('#rolegroupList').datagrid('load');
								}
							});
						}
					}, {
						text : '退出',
						handler : function() {
							$("#updateRoleGroup").dialog('close');
						}
					} ]
				});
	}
	
	/* 根据id删除角色组 */
	function deletes(id) {
		$.messager.confirm("提示！","确实要删除该角色组信息吗！",function(r){
			if(r){
				$.ajax({
					url:'/roleGroup/deleteRoleGroup.jhtml',
					data:{
						"id":id,
					},
					success:function(){
						$('#rolegroupList').datagrid('reload'); 
						$.messager.info("删除成功");	
					}
				});
			}
		});
		
	}
	
	</script>
</body>
</html>
