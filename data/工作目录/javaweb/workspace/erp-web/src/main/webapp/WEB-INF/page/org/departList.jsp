<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>部门维护</title>
</head>
<body>
	<div id="selectDepart">
		部门名称： <input type="text" class="easyui-textbox" id="txtdepartName" />
		所在公司： <input type="text" class="easyui-combobox" id="txtorgId"editable="false" /> 
		<a id="crtDepart" class="easyui-linkbutton" style="width: 60px" onclick="crtDepart();">添加</a> 
		<a id="btnSelect" class="easyui-linkbutton" style="width: 50px">查询</a>
		<a id="btnReset" style="width: 50px" href="#" class="easyui-linkbutton">重置</a>

	</div>
	<table id="departList" class="easyui-datagrid" style="width: 100%;"
		data-options="url:'/org/loadDepartPageData.jhtml',fitColumns:true,singleSelect:true,rownumbers:true,pagination:true">
		<thead>
			<tr>
				<th data-options="field:'departName',width:100" align="center"
					width="150">部门名称</th>
				<th data-options="field:'orgId',width:100,hidden:true"
					align="center" width="150">所在公司ID</th>
				<th data-options="field:'orgName',width:100" align="center"
					width="150">所在公司名称</th>
				<th
					data-options="field:'operate',width:100,align:'center', formatter:formatter"
					width="150">操作</th>
			</tr>
		</thead>
	</table>
	<!-- 新建部门 -->
	<div id="createDepart" class="easyui-dialog"
		style="width: 300px; height: 350px; padding: 10px 20px;" closed="true">
		<form id="formcre" method="post" class="easyui-form">
			<div style="display: none">
				<label>ID </label> <input name="id" class="easyui-textbox" id="id" />
			</div>
			<div>
				<label for="name">部门名称：</label> <input class="easyui-validatebox"
					id="credepartName" type="text" name="departName" />
			</div>
			<div>
				<label for="name">所在公司：</label> <input class="easyui-validatebox" 
					id="creorgId" type="text" name="orgId" editable="false" />
			</div>
			<div>
				<label for="name">上级部门：</label> <input class="easyui-validatebox"
					id="crepId" type="text" name="pId"  />
			</div>

			<div>
				<label for="name">显示顺序：</label> <input class="easyui-validatebox"
					id="cresortOrder" type="text" name="sortOrder" />
			</div>
			<div>
				<label for="name">备 注：</label> <input class="easyui-validatebox"
					id="crememo" type="text" name="memo" />
			</div>

			<div>
				<input class="easyui-checkbox" id="nextData" type="checkbox"
					name="nextData" />继续创建下一条
			</div>
		</form>
	</div>


	<!-- 修改部门 -->
	<div id="updateDepart" class="easyui-dialog"
		style="width: 300px; height: 350px; padding: 10px 20px;" closed="true">
		<form id="formup" method="post" class="easyui-form">
			<div style="display: none">
				<label>ID </label> <input name="id" class="easyui-textbox" id="id" />
			</div>
			<div>
				<label for="name">部门名称：</label> <input class="easyui-textbox"
					id="updepartName" type="text" name="departName" />
			</div>
			<div>
				<label for="name">所在公司：</label> <input class="easyui-validatebox"
					id="uporgId" type="text" name="orgId" editable="false" />
			</div>
			<div>
				<label for="name">上级部门：</label> <input class="easyui-validatebox"
					id="uppId" type="text" name="pId1"  />
			</div>

			<div>
				<label for="name">显示顺序：</label> <input class="easyui-validatebox"
					id="upsortOrder" type="text" name="sortOrder" />
			</div>
			<div>
				<label for="name">备 注：</label> <input class="easyui-validatebox"
					id="upmemo" type="text" name="memo" />
			</div>
		</form>
	</div>
	<script type="text/javascript">
	
		/*操作*/
		function formatter(val, row, index) {
			var id = row.id;
			var orgId =row.orgId;
			var deleted = row.deleted;
			var str = '<span><a href="#" onclick="edit(\'' + id
					+ '\',\''+ orgId + '\')" >修改</a></span>  '
					+ '<span><a href="#" onclick="del(\'' + id
					+ '\')">删除</a></span>  ';
			return str;
		}
		 /*查询下拉列表：选择所在公司名称*/
		 $('#txtorgId').combobox({    
		    url:'/org/getAllComp.jhtml', 
		    valueField:'id', 
		    textField:'orgName',
		    
		}); 
		 //新建部门
		 $('#crepId').combobox({
			    url:'/org/getAllDepartByOrgId.jhtml',
			    valueField:'id', 
			    textField:'departName',
			    required: false,  
			    validType: ['selectValid'],
			});
		 /*新建下拉列表：选择所在部门名称 联动*/
		 $('#creorgId').combobox({    
		    url:'/org/getAllComp.jhtml', 
		    valueField:'id',    
		    textField:'orgName',
		    required: true,  
		    onSelect: function(rec){    
		    	
	            var url2 = '/org/getAllDepartByOrgId.jhtml?orgId='+rec.id;    
	            $('#crepId').combobox({    
				    url:url2, 
				    valueField:'id', 
				    textField:'departName',
				}); 
	        }
		    
		});
		 //更新部门下拉表
		 $('#uppId').combobox({ 
			    url:'/org/getAllDepartByOrgId.jhtml',
			    valueField:'pId', 
			    textField:'departName',
			});
		 
		 /*修改下拉列表：选择所在部门名称 联动*/
		 $('#uporgId').combobox({    
		    url:'/org/getAllComp.jhtml', 
		    valueField:'id',    
		    textField:'orgName',
		    required: true,  
		    onSelect: function(rec){  
	            var url2 = '/org/getAllDepartByOrgId.jhtml?orgId='+rec.id;    
	            $('#uppId').combobox({    
				    url:url2, 
				    valueField:'pId', 
				    textField:'departName',
				    onLoadSuccess:function(){
				    	if($("#pId").val() != '-1'){
				    		$("#uppId").combobox("select",$("#pId").val());
				    	}else{
				    		$("#uppId").combobox("clear");
				    	}
				    	
				    }
				}); 
	            
	        }
		    
		}); 
		 
		/* 模糊查询代码值，代码内容，代码类型 */
		$('#btnSelect').linkbutton({
			onClick : function() {
				var departName = $("#txtdepartName").textbox("getValue");
				var orgId = $("#txtorgId").combobox("getValue");
				$('#departList').datagrid('load', {
					departName : departName,
					orgId : orgId,
				});
			}
		});
		
		/* 重置按钮  */
		$('#btnReset').linkbutton({
			onClick : function() {
				var departName = $("#txtdepartName").textbox("reset");
				var orgId = $("#txtorgId").combobox("reset");
				$("#departList").datagrid('load',{});
			}
		});
		
		/* 新建部门 */
		$("#createDepart").dialog({
			title : '新建部门信息',
			width : 400,
			height : 320,
			closed : true,
			modal : true,
		});
		function crtDepart() {
			$("#createDepart").dialog({
				width : 400,
				height : 320,
				closed : false,
				buttons : [ {
					text : '保存',
					handler : function() {
						var nextData = $("#nextData").is(":checked");
						var isValid = $("#formcre").form('validate');
						if(isValid){
							$.myAjax({
								url : '/org/addOrUpdateDepart.jhtml',
								data : {
									"id" : $("#id").val(),
									"departName" : $("#credepartName").val(),
									"pId" : $("#crepId").combobox("getValue"),
									"sortOrder" : $("#cresortOrder").val(),
									"orgId" : $("#creorgId").combobox("getValue"),//所在公司
									"memo" : $("#crememo").val(),//备注
								},
								
								success : function(data) {
									if ("true"==data) {
										$.myMessager.info("保存成功");
										if(nextData){
											$("#departList").datagrid("load");
											$("#formcre").form('clear');
										}else{
											$("#createDepart").dialog("close");
											$("#departList").datagrid("load");
											$("#formcre").form('clear');
										}
										
								    }else {
								       $.myMessager.info("保存失败！");
								       $("#createDepart").dialog('close');
								    }
								}
							});
						}
					}
				}, {
					text : '退出',
					handler : function() {
						$("#createDepart").dialog('close');
						$("#formcre").form('clear');
					}
				} ]
			})
		}


		/* 根据id删除菜单  */
		function del(id) {
			$.myMessager.confirm("提示！", "确实要删除该用户么！", function(r) {
				if (r) {
					$.myAjax({
						url : '/org/deleteDepartPageData.jhtml',
						data : {
							"id" : id,
						},
						success : function() {
							$('#departList').datagrid('reload');
							$.myMessager.info("删除用户成功!");
						}
					});
				}
			});
		}

		/* 修改部门信息*/
		
		function edit(id,orgId) {
			$.myAjax({
				url:'/org/getDepartDetail.jhtml?id=' + id +"&orgId=" + orgId,
				success:function(data) {
					$("#updepartName").textbox("setValue", data.departName);
					$("#uporgId").combobox("setValue", data.orgId);
					$('#uppId').combobox({ 
					    url:'org/getAllDepartByOrgId.jhtml?orgId=' + data.orgId,
					    valueField:'id', 
					    textField:'departName',
					});
					if("-1"!=data.pId){
					$("#uppId").combobox("setValue", data.pId);
					}
					$("#upsortOrder").textbox("setValue", data.sortOrder);
					$("#upmemo").textbox("setValue", data.memo);
					
				}
				
			});
			$("#updateDepart").dialog(
					{
						width : 400,
						height : 320,
						closed : false,
						buttons : [ {
							text : '保存',
							handler : function() {
								var isValid = $("#formup").form('validate');
								var departName =  $("#updepartName").val();
								if(""==departName){
									$.myMessager.info("部门名称不能为空！");
								}
								if(isValid){
									$.myAjax({
										url : '/org/addOrUpdateDepart.jhtml',
										data : {
											"id" :id,
											"departName" : $("#updepartName").val(),
											"pId" : $("#uppId").combobox("getValue"),
											"sortOrder" : $("#upsortOrder").val(),
											"orgId" : $("#uporgId").combobox("getValue"),//所在公司
											"memo" : $("#upmemo").val(),//备注
										},
										success : function(data) {
											if ("true"==data) {
												$.myMessager.info("修改成功");
												$("#updateDepart").dialog("close");
												$("#departList").datagrid("load");
												$("#formup").form('clear');
											} else { 
											   $.myMessager.info("修改失败");
											   $("#updateDepart").dialog("close");
										    }
										}
									});
								}
							}
						}, {
							text : '退出',
							handler : function() {
								$("#updateDepart").dialog('close');
								$("#formup").form('clear');
							}
						} ]
					});
		}
		//部门名称校验 
		$("input[name='departName']").validatebox({    
		    required: true,  
		    validType: 'length[0,20]',
		}); 
		
		//显示顺序校验 
		$("input[name='sortOrder']").validatebox({    
		    required: false,  
		    validType:  ['length[0,9]','integ'],
		}); 
		//备注校验 
		$("input[name='memo']").validatebox({    
		    required: false,  
		    validType: 'length[0,100]',
		});

		$.extend($.fn.validatebox.defaults.rules, {
		 integ:{
	          validator:function(value,param){
	            return /^[+]?[0-9]\d*$/.test(value);
	          },
	          message: '请输入整数'
	        },
	        //选择框的验证
		selectValid:{  
			   validator:function(value,param){
				 var fa = $("#creorgId").combobox("getValue");
			     if(fa == ""){
			      var pId = $("#crepId").combobox("reset");
			      return false;  
			      }else{  
			       return true ;  
			            }  
			    },  
			      message:'请先选择所在公司'  
			 },  

		}); 
		
		
 
	
	</script>
</body>
</html>