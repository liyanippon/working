<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>公司维护</title>
</head>
<body>
	<div id="selectOrg">
		公司名称： <input type="text" class="easyui-textbox" id="txtorgName" />

		<a id="btncreat" class="easyui-linkbutton" style="width: 60px"onclick="crtOrg();">添加</a> 
		<a id="btnSelect" class="easyui-linkbutton" style="width: 50px">查询</a> 
		<a id="btnReset" style="width: 50px" href="#" class="easyui-linkbutton">重置</a>

	</div>
	<table id="orgList" class="easyui-datagrid" style="width: 100%;"
		data-options="url:'/org/loadOrgPageData.jhtml',fitColumns:true,singleSelect:true,rownumbers:true,pagination:true">
		<thead>
			<tr>
				<th data-options="field:'orgName',width:100" align="center" width="150">公司名称</th>
				<th data-options="field:'operate',width:100,align:'center', formatter:formatter" width="150">操作</th>
			</tr>
		</thead>
	</table>
	<!-- 新建公司 -->
	<div id="createOrg" class="easyui-dialog"
		style="width: 300px; height: 350px; padding: 10px 20px;" closed="true">
		<form id="formcre" method="post" class="easyui-form">
		     <div style="display: none">
				<label>ID </label> <input name="id" class="easyui-textbox"
					id="id" />
			</div>
			<div>
				<label for="name">公司名称：</label> 
				<input class="easyui-validatebox" id="creorgName" type="text" name="orgName" />
			</div>
			<div>
				<label for="name">显示顺序：</label> 
				<input class="easyui-validatebox" id="cresortOrder" type="text" name="sortOrder" />
			</div>
			<div>
				<label for="name">备注：</label> 
				<input class="easyui-validatebox" id="crememo" type="text" name="memo" />
			</div>
			<div>
				<input class="easyui-checkbox" id="nextData" type="checkbox"
					name="nextData" />继续创建下一条
			</div>
		</form>
	</div>
	
	
		<!-- 修改公司 -->
	<div id="updateOrg" class="easyui-dialog"
		style="width: 300px; height: 350px; padding: 10px 20px;" closed="true">
		<form id="formup" method="post" class="easyui-form">
			<div style="display: none">
				<label>ID </label> <input name="id" class="easyui-textbox"
					id="id" />
			</div>
			<div>
				<label for="name">公司名称：</label> 
				<input class="easyui-validatebox" id="uporgName" type="text" name="orgName" />
			</div>
			<div>
				<label for="name">显示顺序：</label> 
				<input class="easyui-validatebox" id="upsortOrder" type="text" name="sortOrder" />
			</div>
			<div>
				<label for="name">备注：</label> 
				<input class="easyui-validatebox" id="upmemo" type="text" name="memo" />
			</div>
		</form>
	</div>
	<script type="text/javascript">
	
		/*操作*/
		function formatter(val, row, index) {
			var id = row.id;
			var deleted = row.deleted;
			var str = '<span><a href="#" onclick="edit(\'' + id
					+ '\')" >修改</a></span>  '
					+ '<span><a href="#" onclick="del(\'' + id
					+ '\')">删除</a></span>  ';
			return str;
		}
		 
		/* 模糊查询公司名称，公司级别*/
		$('#btnSelect').linkbutton({
			onClick : function() {
				var orgName = $("#txtorgName").textbox("getValue");
				$('#orgList').datagrid('load', {
					orgName : orgName,
				});
			}
		});
		
		/* 重置按钮  */
		$('#btnReset').linkbutton({
			onClick : function() {
				var codeValue = $("#txtorgName").textbox("reset");
				$("#orgList").datagrid('load',{});
			}
		});
		
		 //列表下拉列表：新建上级公司名称
		 $('#crepid').combobox({    
		    url:'org/getAllComp.jhtml', 
		    valueField:'id',    
		    textField:'orgName' 
		}); 
		 
		/* 新建公司*/
		$("#createOrg").dialog({
			title : '新建公司',
			width : 400,
			height : 320,
			closed : true,
			modal : true,
		});
		function crtOrg() {
			
			$("#createOrg").dialog({
				width : 400,
				height : 320,
				closed : false,
				buttons : [ {
					text : '保存',
					handler : function() {
						var nextData = $("#nextData").is(":checked");
						var isValid = $("#formcre").form('validate');
						if(isValid){
							$.ajax({
								url : '/org/addOrUpdateOrg.jhtml',
								data : {
									"id" : $("#id").val(),
									"orgName" : $("#creorgName").val(),
									"sortOrder" : $("#cresortOrder").val(),
									"memo" : $("#crememo").val(),//备注
								},
								success : function(data) {
									if ("true"==data) {
										$.myMessager.info("保存成功");
										if(nextData){
											$("#orgList").datagrid("load");
											$("#formcre").form('clear');
										}else{
											$("#createOrg").dialog("close");
											$("#orgList").datagrid("load");
											$("#formcre").form('clear');
										}
										
								    }else {
								       $.myMessager.info("保存失败！");
								       $("#createOrg").dialog('close');
								    }
								}
							});
						}
					}
				}, {
					text : '退出',
					handler : function() {
						$("#createOrg").dialog('close');
						$("#formcre").form('clear');
					}
				} ]
			})
		}


		/* 根据id删除菜单  */
		function del(id) {
			$.myMessager.confirm("提示！", "确实要删除该用户么！", function(r) {
				if (r) {
					$.ajax({
						url : '/org/deleteOrgPageData.jhtml',
						data : {
							"id" : id,
						},
						success : function() {
							$('#orgList').datagrid('reload');
							$.myMessager.info("删除用户成功!");
						}
					});
				}
			});
		}

		/* 修改公司信息*/
		$("#updateOrg").dialog({
			title : '修改公司信息',
			width : 400,
			height : 320,
			closed : true,
			modal : true,
		});
		function edit(id) {
			$("#updateOrg").dialog(
					{
						width : 400,
						height : 320,
						closed : false,
						onOpen : function() {
							$("#formup").form('load',
									'/org/getOrgDetail.jhtml?id=' + id);
						},
						buttons : [ {
							text : '保存',
							handler : function() {
								
								var isValid = $("#formup").form('validate');
								if(isValid){
									$.ajax({
										url : '/org/addOrUpdateOrg.jhtml',
										data : {
											"id" : id,
											"orgName" : $("#uporgName").val(),
											//"pId" : $("#uppid").combobox("getValue"),
											"sortOrder" : $("#upsortOrder").val(),
											"memo" : $("#upmemo").val(),//备注
										},
										success : function(data) {
											if ("true"==data) {
												$.myMessager.info("修改成功");
												$("#updateOrg").dialog("close");
												$("#orgList").datagrid("load");
												$("#formup").form('clear');
											} else { 
											   $.myMessager.info("修改失败");
											   $("#updateOrg").dialog("close");
										    }
										}
									});
								}
							}
						}, {
							text : '退出',
							handler : function() {
								$("#updateOrg").dialog('close');
								$("#formup").form('clear');
							}
						} ]
					});
		}
		
		//新建公司名称校验
		$('#creorgName').validatebox({    
		    required: true,    
		    validType: 'length[1,20]',
		    
		}); 
		//新建显示顺序校验
		$('#cresortOrder').validatebox({    
		    required: false,    
		    validType:  ['length[0,16]','integ'],
		    
		}); 
		//新建备注长度校验
		$('#crememo').validatebox({    
		    required: false,    
		    validType: 'length[0,150]',
		    
		}); 
		//修改公司名称校验
		$('#uporgName').validatebox({    
		    required: true,    
		    validType: 'length[1,20]',
		    
		}); 
		//修改显示顺序校验
		$('#upsortOrder').validatebox({    
		    required: false,    
		    validType:  ['length[0,16]','integ'],
		    
		}); 
		//修改备注长度校验
		$('#upmemo').validatebox({    
		    required: false,    
		    validType: 'length[0,150]',
		    
		}); 


		$.extend($.fn.validatebox.defaults.rules, {
		 integ:{
	          validator:function(value,param){
	            return /^[+]?[0-9]\d*$/.test(value);
	          },
	          message: '请输入整数'
	        },
		}); 
		
		
 
	
	</script>
</body>
</html>