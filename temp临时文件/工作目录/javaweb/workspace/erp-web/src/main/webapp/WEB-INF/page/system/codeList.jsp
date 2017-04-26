<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>单值代码</title>
</head>
<body>
	<div id="selectCode">
		代码值： <input type="text" class="easyui-textbox" id="txtcodeValue" />
		代码内容： <input type="text" class="easyui-textbox" id="txtcodeLabel" />
		代码种类： <input type="text" class="easyui-combobox" id="txttypeName"/>

		<a id="crtCode" class="easyui-linkbutton" style="width: 60px"onclick="crtCode();">添加</a> 
		<a id="btnSelect" class="easyui-linkbutton" style="width: 50px">查询</a> 
		<a id="btnReset" style="width: 50px" href="#" class="easyui-linkbutton">重置</a>

	</div>
	<table id="codeList" class="easyui-datagrid" style="width: 100%;"
		data-options="url:'/system/loadCodePageData.jhtml',fitColumns:true,singleSelect:true,rownumbers:true,pagination:true">
		<thead>
			<tr>
				<th data-options="field:'codeValue',width:100" align="center" width="150">代码值</th>
				<th data-options="field:'codeLabel',width:100" align="center" width="150">代码内容</th>
				<th data-options="field:'typeName',width:100," align="center" width="150">代码种类</th>
				<th data-options="field:'operate',width:100,align:'center', formatter:formatter" width="150">操作</th>
			</tr>
		</thead>
	</table>
	<!-- 新建code -->
	<div id="createCode" class="easyui-dialog"
		style="width: 300px; height: 350px; padding: 10px 20px;" closed="true">
		<form id="form1" method="post" class="easyui-form">
            <div>
				<label for="name">代码种类：</label> 
				<select id="cretypeCode" class="easyui-validatebox"  data-options="required:true" 
				missingMessage="代码类型不能为空"  style="width: 150px; height: 25px; align: center" name="codeGroupCode"></select>
			</div>
			<div>
				<label for="name">代码值：</label> 
				<input class="easyui-validatebox"  missingMessage="只能输入1-3个整数"  id="crecodeValue" type="text" name="code">
			</div>
			<div>
				<label for="name">代码内容：</label> 
				<input class="easyui-validatebox"  missingMessage="代码内容不超过20个字符"  invalidMessage="不能超过20个字符"
				id="crecodeLabel" type="text" name="label" />
			</div>
			<div>
				<label for="name">显示顺序：</label> 
				<input class="easyui-validatebox" id="creorder" type="text" name="sortOrder" />
			</div>
			<div>
				<label for="name">备注：</label> 
				<input class="easyui-validatebox" id="crecomment" type="text" name="comment" />
			</div>
			<div>
				<input class="easyui-checkbox" id="nextData" type="checkbox"
					name="nextData" />继续创建下一条
			</div>
		</form>
	</div>
	
	
		<!-- 修改code -->
	<div id="updateCode" class="easyui-dialog"
		style="width: 300px; height: 350px; padding: 10px 20px;" closed="true">
		<form id="formup" method="post" class="easyui-form">
			<div style="display: none">
				<label>ID </label> <input name="id" class="easyui-validatebox"
					id="id" />
			</div>
            <div>
				<label for="name">代码种类：</label> 
				<select id="uptypeCode" class="easyui-combobox"
					style="width: 150px; height: 25px; align: center" name=codeGroupCode disabled="disabled"/></select>
			</div>
			<div>
				<label for="name">代码值：</label> 
				<input class="easyui-textbox" id="codeValue" type="text" name="code"  disabled="disabled"/>
			</div>
			<div>
				<label for="name">代码内容：</label> 
				<input class="easyui-validatebox" id="codeLabel" type="text" name="label" />
			</div>
			<div>
				<label for="name">显示顺序：</label> 
				<input class="easyui-validatebox" id="order" type="text" name="sortOrder" />
			</div>
			<div>
				<label for="name">备注：</label> 
				<input class="easyui-validatebox" id="comment" type="text" name="comment" />
			</div>
		</form>
	</div>
	<script type="text/javascript">
	
		/*操作*/
		function formatter(val, row, index) {
			var codeValue = row.codeValue;
			var deleted = row.deleted;
			var str = '<span><a href="#" onclick="edit(\'' + codeValue
					+ '\')" >修改</a></span>  '
					+ '<span><a href="#" onclick="del(\'' + codeValue
					+ '\')">删除</a></span>  ';
			return str;
		}
		 /*列表下拉列表：选择code类型名称*/
		 $('#txttypeName').combobox({    
		    url:'/system/getCodeTypeSelectData.jhtml',    
		    valueField:'codeGroupValue',    
		    textField:'codeGroupLabel'   
		}); 
		 /*新建下拉列表：选择code类型名称*/
		 $('#cretypeCode').combobox({    
		    url:'/system/getCodeTypeSelectData.jhtml',    
		    valueField:'codeGroupValue',    
		    textField:'codeGroupLabel',
		    onLoadSuccess: function (data) {
	             if (data) {
	                 $('#cretypeCode').combobox('setValue',data[0].codeGroupValue);
	             }
	         }
		}); 
		 /*更新下拉列表：选择code类型名称*/
		 $('#uptypeCode').combobox({    
		    url:'/system/getCodeTypeSelectData.jhtml',    
		    valueField:'codeGroupValue',    
		    textField:'codeGroupLabel'   
		}); 
		 
		/* 模糊查询代码值，代码内容，代码类型 */
		$('#btnSelect').linkbutton({
			onClick : function() {
				var codeValue = $("#txtcodeValue").textbox("getValue");
				var codeLabel = $("#txtcodeLabel").textbox("getValue");
				var typeCode = $("#txttypeName").combobox("getValue");
				$('#codeList').datagrid('load', {
					codeValue : codeValue,
					codeLabel : codeLabel,
					typeCode : typeCode,
				});
			}
		});
		
		/* 重置按钮  */
		$('#btnReset').linkbutton({
			onClick : function() {
				var codeValue = $("#txtcodeValue").textbox("reset");
				var codeLabel = $("#txtcodeLabel").textbox("reset");
				var typeCode = $("#txttypeName").combobox("reset");
				$("#codeList").datagrid('load',{});
			}
		});
		
		/* 新建code */
		$("#createCode").dialog({
			title : '新建单值代码',
			width : 400,
			height : 320,
			closed : true,
			modal : true,
		});
		function crtCode() {
			$("#createCode").dialog({
				width : 400,
				height : 320,
				closed : false,
				buttons : [ {
					text : '保存',
					handler : function() {
						var nextData = $("#nextData").is(":checked");
						var isValid = $("#form1").form('validate');
						if(isValid){
							$.ajax({
								url : '/system/addCode.jhtml',
								data : {
									"code" : $("#crecodeValue").val(),
									"label" : $("#crecodeLabel").val(),
									"codeGroupCode" : $("#cretypeCode").combobox("getValue"),
									"sortOrder" : $("#creorder").val(),
									"comment" : $("#crecomment").val(),
								},
								
								success : function(data) {
									if ("true"==data) {
										$.myMessager.info("保存成功");
										if(nextData){
											$("#codeList").datagrid("load");
											$("#form1").form('clear');
										}else{
											$("#createCode").dialog("close");
											$("#codeList").datagrid("load");
											$("#form1").form('clear');
										}
										
								    }else {
								       $.myMessager.info("保存失败！");
								       $("#createCode").dialog('close');
								    }
								}
							});
						}
					}
				}, {
					text : '退出',
					handler : function() {
						$("#createCode").dialog('close');
						$("#form1").form('clear');
					}
				} ]
			})
		}


		/* 根据codeValue删除菜单  */
		function del(codeValue) {
			$.myMessager.confirm("提示！", "确实要删除该用户么！", function(r) {
				if (r) {
					$.ajax({
						url : '/system/deleteCodePageData.jhtml',
						data : {
							"codeValue" : codeValue,
						},
						success : function() {
							$('#codeList').datagrid('reload');
							$.myMessager.info("删除用户成功!");
						}
					});
				}
			});
		}

		/* 修改code信息*/
		$("#updateCode").dialog({
			title : '修改单值代码信息',
			width : 400,
			height : 320,
			closed : true,
			modal : true,
		});
		function edit(codeValue) {
			$("#updateCode").dialog(
					{
						width : 400,
						height : 320,
						closed : false,
						onOpen : function() {
							$("#formup").form('load',
									'/system/getCodeDetail.jhtml?codeValue=' + codeValue);
						},
						buttons : [ {
							text : '保存',
							handler : function() {
								
								var isValid = $("#formup").form('validate');
								if(isValid){
									$.ajax({
										url : '/system/updateCode.jhtml',
										data : {
											"code" : $("#codeValue").val(),
											"label" : $("#codeLabel").val(),
											"codeGroupCode" : $("#cretypeCode").combobox("getValue"),
											"sortOrder" : $("#order").val(),
											"comment" : $("#comment").val(),
										},
										success : function(data) {
											if ("true"==data) {
												$.myMessager.info("修改成功");
												$("#updateCode").dialog("close");
												$("#codeList").datagrid("load");
												$("#formup").form('clear');
											} else { 
											   $.myMessager.info("修改失败");
											   $("#updateCode").dialog("close");
										    }
										}
									});
								}
							}
						}, {
							text : '退出',
							handler : function() {
								$("#updateCode").dialog('close');
								$("#formup").form('clear');
							}
						} ]
					});
		}
		//新建代码值校验 
		$('#crecodeValue').validatebox({    
		    required: true,  
		    validType: ['length[1,3]','integ'],
		}); 
		//新建代码内容校验
		$('#crecodeLabel').validatebox({    
		    required: false,    
		    validType: 'length[0,20]',
		    
		}); 
		//新建代码显示顺序校验
		$('#creorder').validatebox({    
		    required: false,    
		    validType:  ['length[0,9]','integ'],
		    
		}); 
		//新建代码备注长度校验
		$('#crecomment').validatebox({    
		    required: false,    
		    validType: 'length[0,20]',
		    
		}); 
		//修改代码内容校验
		$('#codeLabel').validatebox({    
		    required: false,    
		    validType: 'length[0,20]',
		    
		}); 
		//修改代码显示顺序校验
		$('#order').validatebox({    
		    required: false,    
		    validType:  ['length[0,9]','integ'],
		    
		}); 
		//修改代码备注长度校验
		$('#comment').validatebox({    
		    required: false,    
		    validType: 'length[0,20]',
		    
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