<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>入库信息列表</title>
</head>
<body>

	<div id="selectWarehouse">
	<form id="selectCondition">
	<div>
	<a id="btnSelect" class="easyui-linkbutton" style="width: 80px" data-options="plain:true,iconCls:'icon-search'">查询</a> 
	<a id="btnReset" style="width: 80px" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">重置</a>
	</div>
	<div>
	<div style="float:left; width: 30%; height:35px;">
	 入库单编号： <input type="text" class="easyui-textbox" id="txtwarehousenum" />
	</div>
	<div style="float:left; width: 30%; height:35px;">
		状态： <input type="text" class="easyui-combobox" id="txtstatus"/>
		</div>
	<div style="float:left; width: 30%; height:35px;">
		创建人： <input type="text" class="easyui-combotree" id="txtcreateBy"/>
		</div>
	<div style="float:left; width: 30%; height:35px;padding-left:24px">
		处理人： <input type="text" class="easyui-combotree" id="txtprocessBy"/>
		</div>
		<a id="btnOut" style="width: 150px;float: right;padding-right:20px"  data-options="iconCls:'icon-add'" class="easyui-linkbutton" onclick="rubutton();">入库</a>
	</div>
</form>
	</div>
	<table id="godownentryList" class="easyui-datagrid" style="width: 100%;"
		data-options="url:'/warehouse/getGodownEntryListBycondition.jhtml',fitColumns:true,singleSelect:true,rownumbers:true,pagination:true">
		<thead>
			<tr>
				<th data-options="field:'godownEntryId',width:100,hidden:true" align="center"
					width="150">Id</th>
				<th data-options="field:'warehousenum',width:100" align="center"
					width="150">入库单编号</th>
				<th data-options="field:'createName',width:100," align="center"
					width="150">创建人</th>
				<th data-options="field:'createTime',width:100, formatter: time" align="center" , width="150" >创建时间</th>
				<th data-options="field:'statusName',width:100," align="center"
					width="150">状态</th>
				<th data-options="field:'status',width:100,hidden:true"
					align="center" width="150">状态</th>
				<th data-options="field:'submitName',width:100," align="center"
					width="150">提交人</th>
				<th data-options="field:'processName',width:100," align="center"
					width="150">处理人</th>
				<th
					data-options="field:'operate',width:100,align:'center', formatter:formatter"
					width="250">操作</th>
			</tr>
		</thead>
	</table>
	<!-- 打开修改入库单页面 -->
	<div id="editWarehouse" class="easyui-dialog"
		style="width: 900px; height: 600px; padding: 10px 20px;"
		closed="true">
		<table id="supplierList" class="easyui-datagrid" style="width: 100%;"
			data-options="fitColumns:true,singleSelect:true,rownumbers:true,pagination:true">
		</table>
	</div>
	<!--打开审核页面-->
	<div id="submitSH" class="easyui-dialog" closed="true"></div>
	<script type="text/javascript">
	
	   
		/*操作*/
		function formatter(val, row, index) {
			var godownEntryId = row.godownEntryId;
			var status = row.status;
			var deleted = row.deleted;
			var warehousenum = row.warehousenum;
			var xdj = "016001";
			var dsp = "016002";
			var ysp = "016003";
			var wtg = "016004";
			
			var str = "";
			if (xdj == row.status || wtg == row.status) {
				str = '<span><a href="#" onclick="editRKD(\'' + godownEntryId + '\',\''
						+ warehousenum + '\')" >修改</a></span>  '
						+ '<span><a href="#" onclick="detailRKD(\'' + godownEntryId
						+ '\',\'' + warehousenum + '\')" >详情</a></span>  '
						+ '<span><a href="#" onclick = "submit(\'' + godownEntryId
						+ '\')">提交审核</a></span> '
						+ '<span><a href="#" onclick="delRKD(\'' + godownEntryId
						+ '\',\'' + warehousenum + '\')" >删除</a></span>  '
			} else if (dsp == row.status) {
				str = '<span><a href="#" onclick="detailRKD(\'' + godownEntryId + '\',\''
						+ warehousenum + '\')" >详情</a></span>  '
						+ '<span><a href="#" onclick = "opinionYes(\''  + godownEntryId
						+ '\',\'' + warehousenum + '\')" >审批通过</a></span>  '
						+ '<span><a href="#" onclick = "opinionNo(\'' + godownEntryId
						+ '\',\'' + warehousenum + '\')" >审批不通过</a></span>  '
			} else if (ysp == row.status) {
				str = '<span><a href="#" onclick="detailRKD(\'' + godownEntryId + '\',\''
						+ warehousenum + '\')" >详情</a></span>  ';
			}

			return str;
		}
		
		/* 时间 */
		function time(val, row, index) {
			var dateformate = new Date(val);
			return dateformate.toLocaleString();
		}
		
		/*入库单状态*/
		$("#txtstatus").combobox({
			url : '/warehouse/getStatusCode.jhtml',
			valueField : 'code',
			textField : 'label',
			editable : false
		});
		//创建人
		$("#txtcreateBy").combotree({    
			url: '/org/getOrgTreeList.jhtml',     
		});
		//创建人
		$("#txtprocessBy").combotree({    
			url: '/org/getOrgTreeList.jhtml',     
		});

		/* 模糊查询代码值 */
		$('#btnSelect')
				.linkbutton(
						{
							onClick : function() {
								//入库单编号
								var txtwarehousenum = $("#txtwarehousenum")
										.textbox("getValue");
								//状态
								var txtstatus = $("#txtstatus").combobox(
										"getValue");
								//创建人
								var txtcreateBy = $("#txtcreateBy").textbox(
										"getValue");
								//处理人
								var txtprocessBy = $("#txtprocessBy").textbox(
										"getValue");

								$('#godownentryList').datagrid('load', {
									warehousenum : txtwarehousenum,
									status : txtstatus,
									createBy : txtcreateBy,
									processBy : txtprocessBy,
								});
							}
						});

		/* 重置按钮  */
		$('#btnReset').linkbutton({
			onClick : function() {
				$("#selectCondition").form("reset");
				$("#godownentryList").datagrid('load', {});
			}
		});

		/*入库*/
		function rubutton() {
			$('#workspace').panel('refresh',
					'/warehouse/showRkgoodsDetail.jhtml?');
		}

		/* 修改操作*/
		function editRKD(godownEntryId, warehousenum) {
			$('#editWarehouse').dialog({    
			    title: '修改入库单',    
			    modal: true   
			});
			$("#editWarehouse").dialog(
							'refresh',
							'/warehouse/showRkgoodsDetail.jhtml?option=3&warehousenum='
							+ warehousenum);//编辑
							$("#editWarehouse").dialog('open');
		}
		/*查看详情*/
		function detailRKD(godownEntryId, warehousenum) {
			$('#editWarehouse').dialog({    
			    title: '查看入库单',    
			    modal: true   
			});
			$("#editWarehouse").dialog(
								'refresh',
								'/warehouse/showRkgoodsDetail.jhtml?option=2&warehouseId='
								+ godownEntryId + '&warehousenum=' + warehousenum);//只读
								$("#editWarehouse").dialog('open');
		}
		/* 根据godownEntryId删除菜单  */
		function delRKD(godownEntryId,warehousenum) {
			$.myMessager.confirm("提示！", "确实要删除吗？", function(r) {
				if (r) {
					$.ajax({
						url : '/warehouse/delGodownEntryListByById.jhtml',
						data : {
							"godownEntryId" :godownEntryId,
							"warehousenum" :warehousenum,
						},
						success : function() {
							$('#godownentryList').datagrid('reload');
							$.myMessager.show("删除用户成功!");
						}
					});
				}
			});
		}
		/*提交审核*/
		function submit(godownEntryId) {
			$("#submitSH").dialog({
				title : "提交审核",
				width : 400,
				height : 200,
				closed : false,
				href : '/warehouse/showSubmitSh.jhtml?godownEntryId=' + godownEntryId,
			})
		}
		/*审批通过*/
		function opinionYes(godownEntryId,warehousenum) {
			$.myAjax({
				url : '/warehouse/UpdateGodownEntry.jhtml',
				data : {
					"godownEntryId" : godownEntryId,
					"warehousenum" : warehousenum,
					"status" : "016003",//已审核
				},
				success : function(data) {
					if (data.succ) {
						$.myMessager.show("保存成功");
						//刷新页面
						$('#godownentryList').datagrid('load');

					} else {
						$.myMessager.show("保存失败！");
					}
				}
			});
		}
		/*审批不通过*/
		function opinionNo(godownEntryId) {
			$.myAjax({
				url : '/warehouse/UpdateGodownEntry.jhtml',
				data : {
					"godownEntryId" : godownEntryId,
					"status" : "016004",//审批不通过
				},
				success : function(data) {
					if (data.succ) {
						$.myMessager.show("保存成功");
						//刷新页面
						$('#godownentryList').datagrid('load');

					} else {
						$.myMessager.show("保存失败！");
					}
				}
			});
		}
	</script>
</body>
</html>