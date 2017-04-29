<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>消息管理</title>
</head>
<body>
	<div id="selectMessage">
		消息值： <input type="text" class="easyui-textbox" id="txtcode" /> 消息内容：
		<input type="text" class="easyui-textbox" id="txtcontent" /> <a
			id="crtMessage" class="easyui-linkbutton" style="width: 60px"
			onclick="crtCode();">添加</a> <a id="btnSelect"
			class="easyui-linkbutton" style="width: 50px">查询</a> <a id="btnReset"
			style="width: 50px" href="#" class="easyui-linkbutton">重置</a>

	</div>
	<table id="messageList" class="easyui-datagrid" style="width: 100%;"
		data-options="url:'/system/loadMessagePageData.jhtml',fitColumns:true,singleSelect:true,rownumbers:true,pagination:true">
		<thead>
			<tr>
				<th data-options="field:'code',width:100" align="center" width="150">消息值</th>
				<th data-options="field:'content',width:100" align="center"
					width="150">消息内容</th>
				<th
					data-options="field:'operate',width:100,align:'center', formatter:formatter"
					width="150">操作</th>
			</tr>
		</thead>
	</table>
	<!-- 新建消息 -->
	<div id="createMessage" class="easyui-dialog"
		style="width: 300px; height: 350px; padding: 10px 20px;" closed="true">
		<form id="formMessage" method="post" class="easyui-form">
			<div>
				<label for="name">消息值：</label> <input class="easyui-validatebox"
					id="code" type="text" name="code" />
			</div>
			<div>
				<label for="name">消息内容：</label> <input class="easyui-validatebox"
					id="content" type="text" name="content" />
			</div>
			<div>
				<label for="name">消息类别：</label> 
				<select class="easyui-validatebox" style="width: 140px; height: 25px; align: center" id="level" type="text" name="level" >
				<option>error</option>   
                <option>info</option>   
                <option>warning</option>    
				</select>
			</div>
			<div>
				<input class="easyui-checkbox" id="nextData" type="checkbox"
					name="nextData" />继续创建下一条
			</div>
		</form>
	</div>
	<!-- 更新消息 -->
	<div id="updateMessage" class="easyui-dialog"
		style="width: 300px; height: 350px; padding: 10px 20px;" closed="true">
		<form id="formupMessage" method="post" class="easyui-form">
			<div style="display: none">
				<label>ID </label> <input name="id" class="easyui-validatebox"
					id="id" />
			</div>

			<div>
				<label for="name">消息值：</label> <input class="easyui-validatebox"
					id="upcode" type="text" name="code" disabled="disabled" />
			</div>
			<div>
				<label for="name">消息内容：</label> <input class="easyui-validatebox"
					id="upcontent" type="text" name="content" />
			</div>
			<div>
				<label for="name">消息类别：</label> 
				<select class="easyui-validatebox" id="uplevel" type="text" name="level" >
					<option>error</option>   
                    <option>info</option>   
                    <option>warning</option>   
                </select>
			</div>
		</form>
	</div>
	<script type="text/javascript">
	 
	
		/*操作*/
		function formatter(val, row, index) {
			var code = row.code;
			var deleted = row.deleted;
			var str = '<span><a href="#" onclick="edit(\'' +code
					+ '\')" >修改</a></span>  '
					+ '<span><a href="#" onclick="del(\'' + code
					+ '\')">删除</a></span>  ';
			return str;
		}
		 
		/* 模糊查询代码值，代码内容，代码类型 */
		$('#btnSelect').linkbutton({
			onClick : function() {
				var code = $("#txtcode").textbox("getValue");
				var content = $("#txtcontent").textbox("getValue");
				$('#messageList').datagrid('load', {
					code : code,
					content : content,
				});
			}
		});
		
		/* 重置按钮  */
		$('#btnReset').linkbutton({
			onClick : function() {
				var code = $("#txtcode").textbox("reset");
				var content = $("#txtcontent").textbox("reset");
				$("#messageList").datagrid('load',{});
			}
		});
		
		/* 新建消息*/
		$("#createMessage").dialog({
			title : '新建消息',
			width : 400,
			height : 320,
			closed : true,
			modal : true,
		});
		function crtCode() {
			$("#createMessage").dialog({
				width : 400,
				height : 320,
				closed : false,
				buttons : [ {
					text : '保存',
					handler : function() {
						var isValid = $("#formMessage").form('validate');
						var nextData = $("#nextData").is(":checked");
						if(isValid){
							$.ajax({
								url : '/system/addMessage.jhtml',
								data : {
									"code" : $("#code").val(),
									"content" : $("#content").val(),
									"level" : $("#level").val(),
								},
								success : function(data) {
									if ("true"==data) {
										$.myMessager.info("保存成功");
										if(nextData){
											$("#messageList").datagrid("load");
											$("#formMessage").form('clear');
										}else{
											$("#createMessage").dialog("close");
											$("#messageList").datagrid("load");
											$("#formMessage").form('clear');
										}
									} else { 
									   $.myMessager.info("保存失败");
									   $("#createMessage").dialog("close");
								}
								}
							});
						}
					}
				}, {
					text : '关闭',
					handler : function() {
						$("#createMessage").dialog('close');
						$("#formMessage").form('clear');
					}
				} ]
			})
		}


		/* 根据code删除消息*/
		function del(code) {
			$.myMessager.confirm("提示！", "确实要删除该用户么！", function(r) {
				if (r) {
					$.ajax({
						url : '/system/deleteMessagePageData.jhtml',
						data : {
							"code" : code,
						},
						success : function(date) {
							if(date){
								 $('#messageList').datagrid('reload');
								 $.myMessager.info("删除成功");
							}else{
								$.myMessager.info("删除失败");
							}
							 
							 
						}
					});
				}
			});
		}

		/* 修改消息*/
		$("#updateMessage").dialog({
			title : '修改单值代码信息',
			width : 400,
			height : 320,
			closed : true,
			modal : true,
		});
		function edit(code) {
			$("#updateMessage").dialog(
					{
						width : 400,
						height : 320,
						closed : false,
						onOpen : function() {
							$("#formupMessage").form('load',
									'/system/getMessageDetail.jhtml?code=' + code);
						},
						buttons : [ {
							text : '保存',
							handler : function() {
								var isValid = $("#formupMessage").form('validate');
								if(isValid){
									$.ajax({
										url : '/system/updateMessage.jhtml',
										data : {
											"code" : $("#upcode").val(),
											"content" : $("#upcontent").val(),
											"level" : $("#uplevel").val(),
										},
										success : function(data) {
											if("true"==data){
											$.myMessager.info("修改成功");
											$("#updateMessage").dialog("close");
											$("#messageList").datagrid("load");
											$("#formupMessage").form('clear');
										    }else{
										    $.myMessager.info("修改失败");
										    $("#updateMessage").dialog("close");
										    }
										}
									});
								}
							}
						}, {
							text : '退出',
							handler : function() {
								$("#updateMessage").dialog('close');
								$("#formupMessage").form('clear');
							}
						} ]
					});
		}
		//新建消息代码值校验 
		$('#code').validatebox({    
		    required: true,  
		    validType: ['length[1,8]','integ'],
		}); 
		//新建消息内容校验
		$('#content').validatebox({    
		    required: false,    
		    validType: 'length[0,20]',
		    
		}); 
		//新建消息级别
		$('#level').validatebox({    
		    required: true,    
		    validType: 'length[0,10]',
		    
		}); 
	
	
		//更新消息内容校验
		$('#upcontent').validatebox({    
		    required: false,    
		    validType: 'length[0,20]',
		    
		}); 
		//更新消息级别
		$('#uplevel').validatebox({    
		    required: true,    
		    validType: 'length[1,10]',
		    
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