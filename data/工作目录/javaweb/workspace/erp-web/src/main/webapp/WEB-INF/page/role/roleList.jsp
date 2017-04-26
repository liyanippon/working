<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

</head>

<title>角色列表</title>
<body>
<form id="roleForm">
		<div id="tb" style="padding: 0px; width: 100%">
			<label for="name">角色名称</label> 
			<input id="txtRoleName" class="easyui-textbox" style="line-height: 25px; height: 25px; border: 1px solid #ccc">
			<span for="name">所在角色组:</span> 
			<input id="txtrolegroupName1" class="easyui-combobox" style="line-height: 25px; height: 25px; border: 1px solid #ccc">
			<a id="btnSelect" class="easyui-linkbutton" style="width: 50px">查询</a>
			<a id="btnReset" style="width: 50px" href="#" class="easyui-linkbutton">重置</a>
		    <a id="crtRole" class="easyui-linkbutton" style="width: 60px" onclick="crtRole();">添加</a>
		</div>
	</form>
	<!-- 查询角色信息 -->
	<table id="roleList" class="easyui-datagrid" style="width:100%;"
		data-options="url:'/role/loadRolePageData.jhtml',fitColumns:true,singleSelect:true,
		rownumbers:true,pagination:true,striped:true,queryForm:'roleForm'">
		<thead>
			<tr>
				<th data-options="field:'roleName',width:70,align:'center'">角色名称</th>
				<th data-options="field:'rolegroupName',width:100,align:'center'">所在角色组</th>
				<th data-options="field:'_operation',width:100,align:'center',formatter:formatOper">操作</th>
			</tr>
		</thead>
	</table>
	<!-- 修改角色信息 -->
	<div id="updateRole" class="easyui-dialog" 
		style="width: 300px; height: 350px; padding: 10px 20px;" closed="true" title="修改角色">
		<form id="form1" method="post" class="easyui-form">
			<div style="display: none">
				<label>角色ID </label> <input name="id" class="easyui-textbox"
					id="id2" />
			</div>
			<div>
<!-- 				<label>角色组ID </label> <input name="groupId" class="easyui-textbox" -->
<!-- 					id="groupId1" /> -->
			</div>
			<div>
				<label for="name">角色名称</label> 
				<input class="easyui-textbox" id="roleName1" type="text" name="roleName" />
			</div>
			<span style="margin-left: 5px;">角色组</span>
				<!-- <select id="txtrolegroupNamesad" class="easyui-combobox" style="width:150px; height: 25px;"
				         data-options="panelHeight:50"></select> -->
		    <input type="text" class="easyui-combobox" id="groupId1" name="groupId"/>
		</form>
	</div>
	
	<!-- 新建角色信息 -->
	<div id="createRole" class="easyui-dialog" title="创建角色"
		style="width: 300px; height: 350px; padding: 10px 20px;" closed="true">
		<form id="form2" method="post" class="easyui-form">
			<div>
				<label for="rolegroup_name">角色组</label>
				<input type="text" class="easyui-combobox" id="groupId2" name="groupId"/> 
			</div>
			<div>
				<label for="name">角色名称</label> 
				<input class="easyui-textbox" id="rolename2" type="text" name="roleName" maxlength="20"/>
			</div>
			<div>
				<label for="sortorder">显示顺序</label>
				<input class="easyui-textbox" id="sortorder2" type="text" name="sortorder" maxlength="20"/>
			</div>
			<div>
				 <input class="easyui-checkbox" id="creContinue" type="checkbox" name="createNext"/>继续创建下一条
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
			var groupId = $("#txtrolegroupName1").combobox("getValue");
			$('#roleList').datagrid('load', {
				"roleName" : name,
				"groupId":groupId,
			});
		}
	});
	/* 重置 */
	$('#btnReset').linkbutton({
		onClick : function() {
			var name1 = $("#txtRoleName").textbox("reset");
			var rename1 = $("#txtRoleName").textbox("getValue");
			var name2 = $("#txtrolegroupName1").textbox("reset");
			var rename2 = $("#txtrolegroupName1").textbox("getValue");
			var gender = $("#comboSelect1").combobox("reset");
			var state = $("#comboSelect2").combobox("reset");
			$("#roleList").datagrid('load', {
				roleName:rename1,
				rolegroupName : rename2,
			});
		}
	});
	/* 根据id删除角色 */
	function deletes(id) {
		$.messager.confirm("提示！","确实要删除该角色么！",function(r){
			if(r){
				$.ajax({
					url:'/role/deleteRolePageData.jhtml',
					data:{
						"id":id,
					},
					success:function(){
						$('#roleList').datagrid('reload'); 
						$.messager.info("删除角色成功");	
					}
				});
			}
		});
		
	}
	/* 修改用户信息 */
	function updaterow(id) {
	    $("#updateRole").dialog(
				{
					width : 400,
					height : 320,
					closed : false,
					onOpen : function() {
						$("#form1").form('load',
								'/role/getRoleDetail.jhtml?id=' + id);
					},
					buttons : [ {
						text : '保存',
						handler : function() {
							$.ajax({
								url : '/role/updateRole.jhtml',
								data : {
									"id" : $("#id2").textbox("getValue"),
									"groupId" : $("#groupId1").textbox("getValue"),
									"roleName" : $("#roleName1").textbox("getValue"),
								},
								success : function() {
									alert($("#groupId1").textbox("getValue"));
									$.myMessager.info("修改成功");
									$("#updateRole").dialog('close');
									$('#roleList').datagrid('load');
								}
							});
						}
					}, {
						text : '退出',
						handler : function() {
							$("#updateRole").dialog('close');
						}
					} ]
				});
	}

	/* 创建用户 */
	function crtRole() {
		$("#createRole").dialog({
			width : 400,
			height : 320,
			closed : false,
			buttons : [ {
				text : '保存',
				handler : function() {
					var nextData = $("#creContinue").is(":checked");
					$.ajax({
						url : '/role/addRole.jhtml',
						data : {
							"roleName" : $("#rolename2").textbox("getValue"),
							"groupId" : $("#groupId2").combobox("getValue"),
							"sortorder" : $("#sortorder2").textbox("getValue"),
						},
						success : function(data) {
							$.myMessager.info("创建成功");
							if(nextData){
					            $("#roleList").datagrid("load");
					            $("#form2").form('clear');
							}else{
								alert();
								$("#createRole").dialog("close");
					            $("#roleList").datagrid("load");
					            $("#form2").form('clear');
							}
								
						}
					});
				}
			}, {
				text : '关闭',
				handler : function() {
					$("#createRole").dialog('close');
				}
			} ]
		})
	}
	/* 修改下拉框 */
	$('#groupId2').combobox({    
	    url:'/role/getRoleGroupCodeDate.jhtml',    
	    valueField:'id',    
	    textField:'rolegroupName'   
	});
	$('#txtrolegroupName1').combobox({    
	    url:'/role/getRoleGroupCodeDate.jhtml',    
	    valueField:'id',    
	    textField:'rolegroupName'   
	});
	$('#groupId1').combobox({    
	    url:'/role/getRoleGroupCodeDate.jhtml',    
	    valueField:'id',    
	    textField:'rolegroupName'   
	});
	</script>
</body>
</html>