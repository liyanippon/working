<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>货位管理</title>
</head>
<body>
	<div id="selectGoodsPlace">
		货位名称： <input type="text" class="easyui-textbox" id="txtgoodsPlaceName" />
		货位编码： <input type="text" class="easyui-textbox" id="txtgoodsPlaceNum" /> 
		所属仓库： <input type="text" class="easyui-combobox" id="txtstorehouseId" />
		所属库位： <input type="text" class="easyui-combobox" id="txtstorePositionId" /> 
		所属货盘： <input type="text" class="easyui-combobox" id="txtgoodsDishId" />  
		<a id="crtNew" class="easyui-linkbutton" style="width: 60px" onclick="crtGoodsPlace();">添加</a> 
		<a id="btnSelect" class="easyui-linkbutton" style="width: 50px">查询</a>
		<a id="btnReset" style="width: 50px" href="#" class="easyui-linkbutton">重置</a>
	</div>
	<table id="goodsPlaceList" class="easyui-datagrid" style="width: 100%;"
		data-options="url:'/store/loadGoodsPlacePageData.jhtml',fitColumns:true,singleSelect:true,rownumbers:true,pagination:true">
		<thead>
			<tr>
			    <th data-options="field:'id',width:100,hidden:true" align="center" width="150">Id</th>
				<th data-options="field:'goodsPlaceName',width:100" align="center" width="150">货位名称</th>
				<th data-options="field:'goodsPlaceNum',width:100" align="center" width="150">货位编码</th>
				<th data-options="field:'storehouseName',width:100" align="center" width="150">所属仓库</th>
				<th data-options="field:'storePositionName',width:100" align="center" width="150">所属库位</th>
				<th data-options="field:'goodsDishName',width:100" align="center" width="150">所属货盘</th>
				<th data-options="field:'memo',width:100" align="center" width="150">备注</th>
				<th data-options="field:'operate',width:100,align:'center', formatter:formatter" width="150">操作</th>
			</tr>
		</thead>
	</table>
	<!-- 新建库位 -->
	<div id="createGoodsPlace" class="easyui-dialog"
		style="width: 300px; height: 350px; padding: 10px 20px;" closed="true">
		<form id="formcre" method="post" class="easyui-form">
			<div style="display: none">
				<label>ID </label> <input name="id" class="easyui-textbox" id="id" />
			</div>
			<div>
				<label for="name">货位名称：</label> <input class="easyui-validatebox"
					id="goodsPlaceName" type="text" name="goodsPlaceName" />
			</div>
			<div>
				<label for="name">货位编码：</label> <input class="easyui-validatebox" 
					id="goodsPlaceNum" type="text" name="goodsPlaceNum" />
			</div>
			<div>
				<label for="name">所属仓库：</label> <input class="easyui-validatebox"
					id="storehouseId"" type="text" name="storehouseId"  />
			</div>
			<div>
				<label for="name">所属库位：</label> <input class="easyui-validatebox"
					id="storePositionId"" type="text" name="storePositionId"  />
			</div>
			<div>
				<label for="name">所属货盘：</label> <input class="easyui-validatebox"
					id="goodsDishId" type="text" name="goodsDishId"  />
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
		 /*查询下拉列表：选择所属货盘名称*/
		 $('#txtgoodsDishId').combobox({    
		    url:'/store/getAllGoodsDishDto.jhtml', 
		    valueField:'id', 
		    textField:'goodsDishName',
		    
		});
		 
		 /*新建修改下拉列表：选择所属仓库名称*/
		 $('#storehouseId').combobox({    
		    url:'/store/getAllStoreHouse.jhtml', 
		    valueField:'id', 
		    textField:'storehouseName',
		    //关联库位
            onSelect: function(rec){ 
	            var url = '/store/getAllStorePositionDto.jhtml?storeid='+rec.id;    
	            $('#storePositionId').combobox({    
				    url:url, 
				    valueField:'id', 
				    textField:'stortPosition',
				    //关联货盘
		            onSelect: function(rec2){ 
			            var url = '/store/getAllGoodsDishDto.jhtml?positionid='+rec2.id;    
			            $('#goodsDishId').combobox({    
						    url:url, 
						    valueField:'id', 
						    textField:'goodsDishName',
						}); 
			        }
				}); 
	        }
		    
		    
		}); 
		
		 /*新建修改下拉列表：选择所属仓库名称*/
		 $('#storehouseId').combobox({    
		    url:'/store/getAllStoreHouse.jhtml', 
		    valueField:'id', 
		    textField:'storehouseName',
		    
		}); 
		
		 /*新建下拉列表：选择所属库位名称*/
		 $('#storePositionId').combobox({    
		    url:'/store/getAllStorePositionDto.jhtml', 
		    valueField:'id', 
		    textField:'stortPosition',
		    
		});
		 /*新建下拉列表：选择所属货盘名称*/
		 $('#goodsDishId').combobox({    
		    url:'/store/getAllGoodsDishDto.jhtml', 
		    valueField:'id', 
		    textField:'goodsDishName',
		    
		});
		/* 模糊查询代码值，代码内容，代码类型 */
		$('#btnSelect').linkbutton({
			onClick : function() {
				var goodsPlaceName = $("#txtgoodsPlaceName").textbox("getValue");
				var goodsPlaceNum = $("#txtgoodsPlaceNum").textbox("getValue");
				var storehouseId = $("#txtstorehouseId").combotree("getValue");
				var storePositionId = $("#txtstorePositionId").combotree("getValue");
				var goodsDishId = $("#txtgoodsDishId").combotree("getValue");
				$('#goodsPlaceList').datagrid('load', {
					goodsPlaceName : goodsPlaceName,
					goodsPlaceNum : goodsPlaceNum,
					storehouseId : storehouseId,
					storePositionId : storePositionId,
					goodsDishId : goodsDishId,
				});
			}
		});
		
		/* 重置按钮  */
		$('#btnReset').linkbutton({
			onClick : function() {
				var goodsPlaceName = $("#txtgoodsPlaceName").textbox("reset");
				var goodsPlaceNum = $("#txtgoodsPlaceNum").textbox("reset");
				var storehouseId = $("#txtstorehouseId").textbox("reset");
				var storePositionId = $("#txtstorePositionId").textbox("reset");
				var goodsDishId = $("#txtgoodsDishId").textbox("reset");
				$("#goodsPlaceList").datagrid('load',{});
			}
		});
		
		/* 新建货位 */
		
		function crtGoodsPlace() {
			$("#createGoodsPlace").dialog({
				title : '新建货位信息',
				width : 400,
				height : 320,
				closed : false,
				modal : true,
				buttons : [ {
					text : '保存',
					handler : function() {
						var isValid = $("#formcre").form('validate');
						alert(isValid);
						if(isValid){
							$.myAjax({
								url : '/store/addOrUpdateGoodsPlace.jhtml',
								data : {
									"goodsPlaceName" : $("#goodsPlaceName").val(),
									"goodsPlaceNum" : $("#goodsPlaceNum").val(),
									"storehouseId" : $("#storehouseId").combobox("getValue"),
									"storePositionId" : $("#storePositionId").combobox("getValue"),
									"goodsDishId" : $("#goodsDishId").combobox("getValue"),
									"memo" : $("#memo").val(),//备注
								},
								
								success : function(data) {
									if ("true"==data) {
										    $.myMessager.info("保存成功");
											$("#createGoodsPlace").dialog("close");
											$("#goodsPlaceList").datagrid("load");
											$("#formcre").form('clear');
								    }else {
								       $.myMessager.info("保存失败！");
								       $("#createGoodsPlace").dialog('close');
								    }
								}
							});
						}
					}
				}, {
					text : '退出',
					handler : function() {
						$("#createGoodsPlace").dialog('close');
						$("#formcre").form('clear');
					}
				} ]
			})
		}
		
/* 修改货位信息*/
		function edit(id) {
			$.myAjax({
				url:'/store/getGoodsPlaceDetail.jhtml?id=' + id,		
				success:function(data) {
					$("#goodsPlaceName").val(data.goodsPlaceName);
					$("#goodsPlaceNum").val(data.goodsPlaceNum);
					//获取仓库
					$('#storehouseId').combobox({ 
					    url:'store/getAllStoreHouse.jhtml',
					    valueField:'id', 
					    textField:'storehouseName',
					    onLoadSuccess: function(rec){ 
					    	$("#storehouseId").combobox("setValue", data.storehouseId);
				            var url = '/store/getAllStorePositionDto.jhtml?storeid='+data.storehouseId;
				            //获取库位
				            $('#storePositionId').combobox({    
							    url:url, 
							    valueField:'id', 
							    textField:'stortPosition',
							    onLoadSuccess: function(rec){ 
							    	$("#storePositionId").combobox("setValue", data.storePositionId);
							    	//获取货盘
							    	$('#goodsDishId').combobox({    
									    url:'/store/getAllGoodsDishDto.jhtml?positionid='+data.storePositionId, 
									    valueField:'id', 
									    textField:'goodsDishName',
									    onLoadSuccess: function(rec){ 
									    	$("#goodsDishId").combobox("setValue", data.goodsDishId);
									    }
									    
									});
							    }
							}); 
				        },
				      
				        onSelsect: function(rec3){ 
				            //获取库位
				            $('#storePositionId').combobox({   
							    url:'/store/getAllStorePositionDto.jhtml?storeid='+rec3.id, 
							    valueField:'id', 
							    textField:'stortPosition',
							    onSelsect: function(rec2){ 
							    	//获取货盘
							    	$('#goodsDishId').combobox({    
									    url:'/store/getAllGoodsDishDto.jhtml?positionid='+rec2.id, 
									    valueField:'id', 
									    textField:'goodsDishName',
									});
							    	$("#goodsDishId").combobox("setValue", data.goodsDishId);
							    	
							    }
							}); 
				            $("#storePositionId").combobox("setValue", data.storePositionId);
				           
				        }
					});
					
					 
					 
					$("#memo").val(data.memo);
				}
			});
			$("#createGoodsPlace").dialog(
					{
						title : '修改货位信息',
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
										url : '/store/addOrUpdateGoodsPlace.jhtml',
										data : {
											"id" : id,
											"goodsPlaceName" : $("#goodsPlaceName").val(),
											"goodsPlaceNum" : $("#goodsPlaceNum").val(),
											"storehouseId" : $("#storehouseId").combobox("getValue"),
											"storePositionId" : $("#storePositionId").combobox("getValue"),
											"goodsDishId" : $("#goodsDishId").combobox("getValue"),
											"memo" : $("#memo").val(),//备注
										},
										
										success : function(data) {
											if ("true"==data) {
												    $.myMessager.info("保存成功");
													$("#createGoodsPlace").dialog("close");
													$("#goodsPlaceList").datagrid("load");
													$("#formcre").form('clear');
										    }else {
										       $.myMessager.info("保存失败！");
										       $("#createGoodsPlace").dialog('close');
										    }
										}
									});
								}
							}
						}, {
							text : '退出',
							handler : function() {
								$("#createGoodsPlace").dialog('close');
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
						url : '/store/deleteGoodsPlacePageData.jhtml',
						data : {
							"id" : id,
						},
						success : function() {
							$('#goodsPlaceList').datagrid('reload');
							$.myMessager.info("删除用户成功!");
						}
					});
				}
			});
		}
		//页面校验
		//名称校验 
		$("#goodsPlaceName").validatebox({    
		    required: true,  
		    validType: 'length[1,20]',
		}); 
		
		//编号校验 
		$("#goodsPlaceNum").validatebox({    
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