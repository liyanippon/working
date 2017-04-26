<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>仓库管理</title>
</head>
<body>
	<div id="selectStore">
		仓库名称： <input type="text" class="easyui-textbox" id="txtstorehouseName" />
		仓库编码： <input type="text" class="easyui-textbox" id="txtstorehouseNum" /> 
		<a id="crtStore" class="easyui-linkbutton" style="width: 60px" onclick="crtStore();">添加</a> 
		<a id="btnSelect" class="easyui-linkbutton" style="width: 50px">查询</a>
		<a id="btnReset" style="width: 50px" href="#" class="easyui-linkbutton">重置</a>
	</div>
	<table id="storeHouseList" class="easyui-datagrid" style="width: 100%;"
		data-options="url:'/store/loadStorePageData.jhtml',fitColumns:true,singleSelect:true,rownumbers:true,pagination:true">
		<thead>
			<tr>
			    <th data-options="field:'id',width:100,hidden:true" align="center" width="150">Id</th>
				<th data-options="field:'storehouseName',width:100" align="center" width="150">仓库名称</th>
				<th data-options="field:'storehouseNum',width:100" align="center" width="150">仓库编码</th>
				<th data-options="field:'position',width:100" align="center" width="150">物理位置</th>
				<th data-options="field:'memo',width:100" align="center" width="150">备注</th>
				<th data-options="field:'operate',width:100,align:'center', formatter:formatter" width="150">操作</th>
			</tr>
		</thead>
	</table>
	<!-- 新建仓库 -->
	<div id="createStore" class="easyui-dialog"
		style="width: 300px; height: 350px; padding: 10px 20px;" closed="true">
		<form id="formcre" method="post" class="easyui-form">
			<div style="display: none">
				<label>ID </label> <input name="id" class="easyui-textbox" id="id" />
			</div>
			<div>
				<label for="name">仓库名称：</label> <input class="easyui-validatebox"
					id="storehouseName" type="text" name="storehouseName" />
			</div>
			<div>
				<label for="name">仓库编码：</label> <input class="easyui-validatebox" 
					id="storehouseNum" type="text" name="storehouseNum" />
			</div>
			<div>
				<label for="name">物理位置：</label> <input class="easyui-validatebox"
					id="position" type="text" name="position"  />
			</div>

			<div>
				<label for="name">备 注：</label> <input class="easyui-validatebox"
					id="memo" type="text" name="memo" />
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
		/* 模糊查询代码值，代码内容，代码类型 */
		$('#btnSelect').linkbutton({
			onClick : function() {
				var storehouseName = $("#txtstorehouseName").textbox("getValue");
				var storehouseNum = $("#txtstorehouseNum").textbox("getValue");
				$('#storeHouseList').datagrid('load', {
					storehouseName : storehouseName,
					storehouseNum : storehouseNum,
				});
			}
		});
		
		/* 重置按钮  */
		$('#btnReset').linkbutton({
			onClick : function() {
				var txtstorehouseName = $("#txtstorehouseName").textbox("reset");
				var txtstorehouseNum = $("#txtstorehouseNum").textbox("reset");
				$("#storeHouseList").datagrid('load',{});
			}
		});
		
		/* 新建部门 */
		$("#createDepart").dialog({
			title : '新建仓库信息',
			width : 400,
			height : 320,
			closed : true,
			modal : true,
		});
		function crtStore() {
			//自动生成编码
			$.myAjax({
				url:'/store/getNumberByType.jhtml',	
						
				success:function(data) {
					$("#storehouseNum").val(data);
				}
				
			});
			$("#createStore").dialog({
				title : '新建仓库信息',
				width : 400,
				height : 320,
				closed : false,
				modal : true,
				buttons : [ {
					text : '保存',
					handler : function() {
						
						var isValid = $("#formcre").form('validate');
						if(isValid){
							$.myAjax({
								url : '/store/addOrUpdateStoreHouse.jhtml',
								data : {
									"id" : $("#id").val(),
									"storehouseName" : $("#storehouseName").val(),
									"storehouseNum" : $("#storehouseNum").val(),
									"position" : $("#position").val(),
									"memo" : $("#memo").val(),//备注
								},
								
								success : function(data) {
									if ("true"==data) {
										    $.myMessager.info("保存成功");
											$("#createStore").dialog("close");
											$("#storeHouseList").datagrid("load");
											$("#formcre").form('clear');
								    }else {
								       $.myMessager.info("保存失败！");
								       $("#createStore").dialog('close');
								    }
								}
							});
						}
					}
				}, {
					text : '退出',
					handler : function() {
						$("#createStore").dialog('close');
						$("#formcre").form('clear');
					}
				} ]
			})
		}
		
/* 修改仓库信息*/
		function edit(id) {
			$.myAjax({
				url:'/store/getStoreDetail.jhtml?id=' + id,	
						
				success:function(data) {
					$("#storehouseName").val(data.storehouseName);
					$("#storehouseNum").val(data.storehouseNum);
					$("#position").val(data.position);
					$("#memo").val(data.memo);
					
				}
				
			});
			$("#createStore").dialog(
					{
						title : '修改仓库信息',
						width : 400,
						height : 320,
						closed : false,
						modal : true,
						buttons : [ {
							text : '保存',
							handler : function() {
								
								var isValid = $("#formcre").form('validate');
								if(isValid){
									$.myAjax({
										url : '/store/addOrUpdateStoreHouse.jhtml',
										data : {
											"id" :id,
											"storehouseName" : $("#storehouseName").val(),
											"storehouseNum" : $("#storehouseNum").val(),
											"position" : $("#position").val(),
											"memo" : $("#memo").val(),//备注
										},
										
										success : function(data) {
											if ("true"==data) {
												    $.myMessager.info("修改成功");
													$("#createStore").dialog("close");
													$("#storeHouseList").datagrid("load");
													$("#formcre").form('clear');
										    }else {
										       $.myMessager.info("修改失败！");
										       $("#createStore").dialog('close');
										    }
										}
									});
								}
							}
						}, {
							text : '退出',
							handler : function() {
								$("#createStore").dialog('close');
								$("#formcre").form('clear');
							}
						} ]
					})
				}
		
		/* 根据id删除菜单  */
		function del(id) {
			$.myMessager.confirm("提示！", "确实要删除该仓库么！", function(r) {
				if (r) {
					$.myAjax({
						url : '/store/deleteStorePageData.jhtml',
						data : {
							"id" : id,
						},
						success : function(data) {
							if("ture"==data){
							$('#storeHouseList').datagrid('reload');
						  	$.myMessager.info("删除成功!");
						    }else if("position"==data){
							  	$.myMessager.info("库位有值，不能删除该仓库!");
						    }else{
							  	$.myMessager.info("删除失败!");
						    }
						}
					});
				}
			});
		}
		//页面校验
		//名称校验
		$("#storehouseName").validatebox({    
		    required: true,  
		    validType: 'length[1,20]',
		}); 
		
		//编号校验 
		$("#storehouseNum").validatebox({    
		    required: true,  
		    validType:  'length[1,32]',
		}); 
		//位置校验
		$("#position").validatebox({    
		    required: false,  
		    validType: 'length[0,20]',
		});
		//备注校验
		$("#memo").validatebox({    
		    required: false,  
		    validType: 'length[0,100]',
		});
	</script>
</body>
</html>