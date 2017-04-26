<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>仓库管理</title>
</head>
<body>
	<div id="selectStorePosition">
		库位名称： <input type="text" class="easyui-textbox" id="txtstortPosition" />
		库位编码： <input type="text" class="easyui-textbox" id="txtstortPositionNum" /> 
		所属仓库： <input type="text" class="easyui-combobox" id="txtstorehouseId" /> 
		<a id="crtStorePosition" class="easyui-linkbutton" style="width: 60px" onclick="crtStorePosition();">添加</a> 
		<a id="btnSelect" class="easyui-linkbutton" style="width: 50px">查询</a>
		<a id="btnReset" style="width: 50px" href="#" class="easyui-linkbutton">重置</a>
	</div>
	<table id="storePositionList" class="easyui-datagrid" style="width: 100%;"
		data-options="url:'/store/loadStorePositionPageData.jhtml',fitColumns:true,singleSelect:true,rownumbers:true,pagination:true">
		<thead>
			<tr>
			    <th data-options="field:'id',width:100,hidden:true" align="center" width="150">Id</th>
				<th data-options="field:'stortPosition',width:100" align="center" width="150">库位名称</th>
				<th data-options="field:'stortPositionNum',width:100" align="center" width="150">库位编码</th>
				<th data-options="field:'storehouseName',width:100" align="center" width="150">所属仓库</th>
				<th data-options="field:'memo',width:100" align="center" width="150">备注</th>
				<th data-options="field:'operate',width:100,align:'center', formatter:formatter" width="150">操作</th>
			</tr>
		</thead>
	</table>
	<!-- 新建库位 -->
	<div id="createStorePosition" class="easyui-dialog"
		style="width: 300px; height: 350px; padding: 10px 20px;" closed="true">
		<form id="formcre" method="post" class="easyui-form">
			<div style="display: none">
				<label>ID </label> <input name="id" class="easyui-textbox" id="id" />
			</div>
			<div>
				<label for="name">库位名称：</label> <input class="easyui-validatebox"
					id="stortPosition" type="text" name="stortPosition" />
			</div>
			<div>
				<label for="name">库位编码：</label> <input class="easyui-validatebox" 
					id="stortPositionNum" type="text" name="stortPositionNum" />
			</div>
			<div>
				<label for="name">所属仓库：</label> <input class="easyui-validatebox"
					id="storehouseId" type="text" name="storehouseId"  />
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
		/*查询下拉列表：选择所属仓库名称*/
		 $('#txtstorehouseId').combobox({    
		    url:'/store/getAllStoreHouse.jhtml', 
		    valueField:'id', 
		    textField:'storehouseName',
		    
		}); 
		 /*新建修改下拉列表：选择所属仓库名称*/
		 $('#storehouseId').combobox({    
		    url:'/store/getAllStoreHouse.jhtml', 
		    valueField:'id', 
		    textField:'storehouseName',
		    
		}); 
		/* 模糊查询代码值，代码内容，代码类型 */
		$('#btnSelect').linkbutton({
			onClick : function() {
				var stortPosition = $("#txtstortPosition").textbox("getValue");
				var stortPositionNum = $("#txtstortPositionNum").textbox("getValue");
				var storehouseId = $("#txtstorehouseId").combotree("getValue");
				$('#storePositionList').datagrid('load', {
					stortPosition : stortPosition,
					stortPositionNum : stortPositionNum,
					storehouseId : storehouseId,
				});
			}
		});
		
		/* 重置按钮  */
		$('#btnReset').linkbutton({
			onClick : function() {
				var stortPosition = $("#txtstortPosition").textbox("reset");
				var stortPositionNum = $("#txtstortPositionNum").textbox("reset");
				var storehouseId = $("#txtstorehouseId").textbox("reset");
				$("#storePositionList").datagrid('load',{});
			}
		});
		
		/* 新建库位 */
		$("#createPosition").dialog({
			title : '新建库位信息',
			width : 400,
			height : 320,
			closed : true,
			modal : true,
		});
		function crtStorePosition() {
			$("#createStorePosition").dialog({
				title : '新建库位信息',
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
								url : '/store/addOrUpdateStorePosition.jhtml',
								data : {
									"id" : $("#id").val(),
									"stortPosition" : $("#stortPosition").val(),
									"stortPositionNum" : $("#stortPositionNum").val(),
									"storehouseId" : $("#storehouseId").combobox("getValue"),
									"memo" : $("#memo").val(),//备注
								},
								
								success : function(data) {
									if ("true"==data) {
										    $.myMessager.info("保存成功");
											$("#createStorePosition").dialog("close");
											$("#storePositionList").datagrid("load");
											$("#formcre").form('clear');
								    }else {
								       $.myMessager.info("保存失败！");
								       $("#createStorePosition").dialog('close');
								    }
								}
							});
						}
					}
				}, {
					text : '退出',
					handler : function() {
						$("#createStorePosition").dialog('close');
						$("#formcre").form('clear');
					}
				} ]
			})
		}
		
/* 修改仓库信息*/
		function edit(id) {
			$.myAjax({
				url:'/store/getStorePositionDetail.jhtml?id=' + id,		
				success:function(data) {
					$("#stortPosition").val(data.stortPosition);
					$("#stortPositionNum").val(data.stortPositionNum);
					$('#storehouseId').combobox({ 
					    url:'store/getAllStoreHouse.jhtml',
					    valueField:'id', 
					    textField:'storehouseName',
					});
					$("#storehouseId").combobox("setValue", data.storehouseId);
					$("#memo").val(data.memo);
				}
			});
			$("#createStorePosition").dialog(
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
										url : '/store/addOrUpdateStorePosition.jhtml',
										data : {
											"id" : id,
											"stortPosition" : $("#stortPosition").val(),
											"stortPositionNum" : $("#stortPositionNum").val(),
											"storehouseId" : $("#storehouseId").combobox("getValue"),
											"memo" : $("#memo").val(),//备注
										},
										
										success : function(data) {
											if ("true"==data) {
												    $.myMessager.info("修改成功");
													$("#createStorePosition").dialog("close");
													$("#storePositionList").datagrid("load");
													$("#formcre").form('clear');
										    }else {
										       $.myMessager.info("修改失败！");
										       $("#createStorePosition").dialog('close');
										    }
										}
									});
								}
							}
						}, {
							text : '退出',
							handler : function() {
								$("#createStorePosition").dialog('close');
								$("#formcre").form('clear');
							}
						} ]
					})
				}
							
							
		
		/* 根据id删除菜单  */
		function del(id) {
			$.myMessager.confirm("提示！", "确实要删除该库位么！", function(r) {
				if (r) {
					$.myAjax({
						url : '/store/deleteStorePositionPageData.jhtml',
						data : {
							"id" : id,
						},
						success : function(data) {
							if("ture"==data){
								$('#storePositionList').datagrid('reload');
							  	$.myMessager.info("删除成功!");
							    }else if("position"==data){
								  	$.myMessager.info("货盘有值，不能删除该库位!");
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
		$("#stortPosition").validatebox({    
		    required: true,  
		    validType: 'length[1,20]',
		}); 
		
		//编号校验 
		$("#stortPositionNum").validatebox({    
		    required: true,  
		    validType:  'length[1,32]',
		}); 
		//位置校验
		$("#storehouseId").validatebox({    
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