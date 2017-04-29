<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>库盘管理</title>
</head>
<body>
	<div id="selectGoodsDish">
		货盘名称： <input type="text" class="easyui-textbox" id="txtgoodsDishName" />
		货盘编码： <input type="text" class="easyui-textbox" id="txtgoodsDishNum" /> 
		所属仓库： <input type="text" class="easyui-combobox" id="txtstorehouseId" /> 
		所属库位： <input type="text" class="easyui-combobox" id="txtstorePositionId" /> 
		<a id="crtNew" class="easyui-linkbutton" style="width: 60px" onclick="crtDish();">添加</a> 
		<a id="btnSelect" class="easyui-linkbutton" style="width: 50px">查询</a>
		<a id="btnReset" style="width: 50px" href="#" class="easyui-linkbutton">重置</a>
	</div>
	<table id="goodsDishList" class="easyui-datagrid" style="width: 100%;"
		data-options="url:'/store/loadGoodsDishPageData.jhtml',fitColumns:true,singleSelect:true,rownumbers:true,pagination:true">
		<thead>
			<tr>
			    <th data-options="field:'id',width:100,hidden:true" align="center" width="150">Id</th>
				<th data-options="field:'goodsDishName',width:100" align="center" width="150">货盘名称</th>
				<th data-options="field:'goodsDishNum',width:100" align="center" width="150">货盘编码</th>
				<th data-options="field:'storehouseName',width:100" align="center" width="150">所属仓库</th>
				<th data-options="field:'storePositionName',width:100" align="center" width="150">所属库位</th>
				<th data-options="field:'memo',width:100" align="center" width="150">备注</th>
				<th data-options="field:'operate',width:100,align:'center', formatter:formatter" width="150">操作</th>
			</tr>
		</thead>
	</table>
	<!-- 新建库位 -->
	<div id="createGoodsDish" class="easyui-dialog"
		style="width: 300px; height: 350px; padding: 10px 20px;" closed="true">
		<form id="formcre" method="post" class="easyui-form">
			<div style="display: none">
				<label>ID </label> <input name="id" class="easyui-textbox" id="id" />
			</div>
			<div>
				<label for="name">货盘名称：</label> <input class="easyui-validatebox"
					id="goodsDishName" type="text" name="goodsDishName" />
			</div>
			<div>
				<label for="name">货盘编码：</label> <input class="easyui-validatebox" 
					id="goodsDishNum" type="text" name="goodsDishNum" />
			</div>
			<div>
				<label for="name">所属仓库：</label> <input class="easyui-validatebox"
					id="storehouseId" type="text" name="storehouseId"  />
			</div>
			<div>
				<label for="name">所属库位：</label> <input class="easyui-validatebox"
					id="storePositionId" type="text" name="storePositionId"  />
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
		 /*查询下拉列表：选择所属库位名称*/
		 $('#txtstorePositionId').combobox({    
		    url:'/store/getAllStorePositionDto.jhtml', 
		    valueField:'id', 
		    textField:'stortPosition',
		    
		});
		 /*新建修改下拉列表：选择所属仓库名称*/
		 $('#storehouseId').combobox({    
		    url:'/store/getAllStoreHouse.jhtml', 
		    valueField:'id', 
		    textField:'storehouseName',
            onSelect: function(rec){ 
	            var url = '/store/getAllStorePositionDto.jhtml?storeid='+rec.id;    
	            $('#storePositionId').combobox({    
				    url:url, 
				    valueField:'id', 
				    textField:'stortPosition',
				}); 
	        }
		    
		    
		}); 
		 /*查询下拉列表：选择所属库位名称*/
		 $('#storePositionId').combobox({    
		    url:'/store/getAllStorePositionDto.jhtml', 
		    valueField:'id', 
		    textField:'stortPosition',
		    
		});
		/* 模糊查询 */
		$('#btnSelect').linkbutton({
			onClick : function() {
				var goodsDishName = $("#txtgoodsDishName").textbox("getValue");
				var goodsDishNum = $("#txtgoodsDishNum").textbox("getValue");
				var storehouseId = $("#txtstorehouseId").combotree("getValue");
				var storePositionId = $("#txtstorePositionId").combotree("getValue");
				$('#goodsDishList').datagrid('load', {
					goodsDishName : goodsDishName,
					goodsDishNum : goodsDishNum,
					storehouseId : storehouseId,
					storePositionId : storePositionId,
				});
			}
		});
		
		/* 重置按钮  */
		$('#btnReset').linkbutton({
			onClick : function() {
				var goodsDishName = $("#txtgoodsDishName").textbox("reset");
				var goodsDishNum = $("#txtgoodsDishNum").textbox("reset");
				var storehouseId = $("#txtstorehouseId").textbox("reset");
				var storePositionId = $("#txtstorePositionId").textbox("reset");
				$("#goodsDishList").datagrid('load',{});
			}
		});
		
		/* 新建库位 */
		
		function crtDish() {
			$("#createGoodsDish").dialog({
				title : '新建货盘信息',
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
								url : '/store/addOrUpdateGoodsDish.jhtml',
								data : {
									"goodsDishName" : $("#goodsDishName").val(),
									"goodsDishNum" : $("#goodsDishNum").val(),
									"storehouseId" : $("#storehouseId").combobox("getValue"),
									"storePositionId" : $("#storePositionId").combobox("getValue"),
									"memo" : $("#memo").val(),//备注
								},
								
								success : function(data) {
									if ("true"==data) {
										    $.myMessager.info("保存成功");
											$("#createGoodsDish").dialog("close");
											$("#goodsDishList").datagrid("load");
											$("#formcre").form('clear');
								    }else {
								       $.myMessager.info("保存失败！");
								       $("#createGoodsDish").dialog('close');
								    }
								}
							});
						}
					}
				}, {
					text : '退出',
					handler : function() {
						$("#createGoodsDish").dialog('close');
						$("#formcre").form('clear');
					}
				} ]
			})
		}
		
/* 修改仓库信息*/
		function edit(id) {
			$.myAjax({
				url:'/store/getGoodsDishDetail.jhtml?id=' + id,		
				success:function(data) {
					$("#goodsDishName").val(data.goodsDishName);
					$("#goodsDishNum").val(data.goodsDishNum);
					$('#storehouseId').combobox({ 
					    url:'store/getAllStoreHouse.jhtml',
					    valueField:'id', 
					    textField:'storehouseName',
					    onLoadSuccess: function(rec){ 
					    	$("#storehouseId").combobox("setValue", data.storehouseId);
				            var url = '/store/getAllStorePositionDto.jhtml?storeid='+data.storehouseId;    
				            $('#storePositionId').combobox({    
							    url:url, 
							    valueField:'id', 
							    textField:'stortPosition',
							    onLoadSuccess: function(rec){ 
							    	$("#storePositionId").combobox("setValue", data.storePositionId);
							    }
							}); 
				        }
					});
					
					//$("#storePositionId").combobox("setValue", data.storePositionId);
					$("#memo").val(data.memo);
				}
			});
			$("#createGoodsDish").dialog(
					{
						title : '修改货盘信息',
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
										url : '/store/addOrUpdateGoodsDish.jhtml',
										data : {
											"id" : id,
											"goodsDishName" : $("#goodsDishName").val(),
											"goodsDishNum" : $("#goodsDishNum").val(),
											"storehouseId" : $("#storehouseId").combobox("getValue"),
											"storePositionId" : $("#storePositionId").combobox("getValue"),
											"memo" : $("#memo").val(),//备注
										},
										
										success : function(data) {
											if ("true"==data) {
												 $.myMessager.info("修改成功");
													$("#createGoodsDish").dialog("close");
													$("#goodsDishList").datagrid("load");
													$("#formcre").form('clear');
										    }else {
										       $.myMessager.info("修改失败！");
										       $("#createGoodsDish").dialog('close');
										    }
										}
									});
								}
							}
						}, {
							text : '退出',
							handler : function() {
								$("#createGoodsDish").dialog('close');
								$("#formcre").form('clear');
							}
						} ]
					})
				}
							
							
		
		/* 根据id删除菜单  */
		function del(id) {
			$.myMessager.confirm("提示！", "确实要删除该货盘么！", function(r) {
				if (r) {
					$.myAjax({
						url : '/store/deleteGoodsDishPageData.jhtml',
						data : {
							"id" : id,
						},
						success : function(data) {
							alert(data);
							if("ture"==data){
								$('#goodsDishList').datagrid('reload');
							  	$.myMessager.info("删除成功!");
							    }else if("position"==data){
								  	$.myMessager.info("货位有值，不能删除该货盘!");
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
		$("#goodsDishName").validatebox({    
		    required: true,  
		    validType: 'length[1,20]',
		}); 
		
		//编号校验 
		$("#goodsDishNum").validatebox({    
		    required: true,  
		    validType:  'length[1,32]',
		}); 
		//备注校验
		$("#memo").validatebox({    
		    required: false,  
		    validType: 'length[0,100]',
		});
	</script>
</body>
</html>