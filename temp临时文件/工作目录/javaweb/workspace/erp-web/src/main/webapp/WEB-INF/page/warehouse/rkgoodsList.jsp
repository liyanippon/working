<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>入库商品库存列表</title>
</head>
<body>
<script type="text/javascript" src="/js/print/print.js"></script>
	<div id="rukudan">
		<span style="margin-left: 5px; display: inline-block; width: 70px;">入库单编号</span>
		<input id="warehousenum" readonly="readonly" name="warehousenum"
			class="easyui-textbox" style="width: 300px; height: 25px;"> <span
			style="margin-left: 5px; display: inline-block; width: 60px;">创建人</span>
		<input id="creatBy" readonly="readonly" name="outboundPeople"
			class="easyui-textbox" style="width: 300px; height: 25px;">
			<a
			id="addSelect" class="easyui-linkbutton" style="width: 150px"
			onclick="addgoods()">添加入库商品</a> 
            <a id="pring"
			class="easyui-linkbutton" style="width: 100px" data-options="iconCls:'icon-print'"  onclick="printrud()">打印入库单</a>
			 <a id="return"
			class="easyui-linkbutton" style="width: 100px" onclick="returnlb()">返回</a>
			
	</div>
	<div>
		<table id="rkpertoryList">
		</table>
	</div>

	<!-- 打开修改页面 -->
	<div id="editRK" class="easyui-dialog"
		style="width: 1000px; height: 900px; padding: 10px 20px;"
		closed="true"></div>
	<script type="text/javascript">
//加载页面时判断是否显示供应商信息
 $("#creatBy").val($("#nickName").html());
var id = $("#id").val();
var option ='<%=request.getParameter("option")%>';//1编辑 2 只读
var warehousenumId ='<%=request.getParameter("warehousenum")%>';
		if (warehousenumId == "" || warehousenumId == "undefined"
				|| warehousenumId == "null") {
			//加载表单数据
			$.myAjax({
				url : '/warehouse/getRkdNumberByType.jhtml',
				success : function(data) {
					$("#warehousenum").textbox("setValue", data);
				}
			});
		} else {
			$("#warehousenum").val(warehousenumId);
		}

		//如果是编辑 1
		if (option == "2") {
			$("#addSelect").hide();
			$("#return").hide();
			$("#formcre input").attr("disabled", "disabled")
		} else if (option == "3") {
			$("#return").hide();
		}

		$('#rkpertoryList').datagrid({
			url : '/warehouse/getWarehouseListBywarehousenum.jhtml?warehouseId ='+id,
			width : '100%',
			rownumbers : true,
			fitColumns : true,
			rownumbers : true,
			pagination : true,
			striped : true,
			queryForm : 'selSupplierForm',
			columns : [ [

			{
				field : 'id',
				title : '供应商ID',
				hidden : true
			}, {
				field : 'goodsname',
				title : '货品名称',
				width : 70
			}, {
				field : 'specifications',
				title : '货品规格',
				width : 70
			}, {
				field : 'category',
				title : '货品种类',
				width : 70
			}, {
				field : 'varieties',
				title : '货品类别',
				width : 70
			}, {
				field : 'goodscount',
				title : '入库数量',
				width : 70
			}, {
				field : 'operate',
				title : '操作',
				width : 70,
				align : 'center',
				formatter : formatRKSP
			},

			] ],
			singleSelect : false,
			selectOnCheck : true,
			checkOnSelect : true

		});
		$('#rkpertoryList').datagrid('load', {
			"rkwarehousenum" : warehousenumId,
		});

		/*查看入库详情操作*/
		function formatRKSP(val, row, index) {
			var id = row.id;
			var deleted = row.deleted;
			var str = "";
			if (option == "2") {
				str = '<span><a href="#" onclick="xqRkgoods(\'' + id
						+ '\')" >详情</a></span>  ';
			} else {
				str = '<span><a href="#" onclick="xqRkgoods(\'' + id
						+ '\')" >详情</a></span>  '
						+ '<span><a href="#" onclick="xggoods(\'' + id
						+ '\')">修改</a></span>  '
						+ '<span><a href="#" onclick="delRkgoods(\'' + id
						+ '\')">删除</a></span>  ';
			}

			return str;
		}
		/*入库*/
		function addgoods() {
			var warehousenum = $("#warehousenum").textbox("getValue");
			
			$('#editRK').dialog({    
			    title: '添加入库商品',    
			    modal: true   
			});

			$("#editRK").dialog(
					'refresh',
					'/warehouse/showCreatNew.jhtml?warehousenumId='
							+ warehousenum);
			$("#editRK").dialog('open');

		}

		/*返回*/
		function returnlb() {
			$('#workspace')
					.panel('refresh', '/warehouse/showGodownEntry.jhtml');
		}
		/*打印入库单*/
		function printrud(){
			var warehousenum = $("#warehousenum").textbox("getValue");
			$.ajax({
				url : '/warehouse/showPrintRkd.jhtml',
				success : function() {
					CreateFormPage('入库单', $('#rkpertoryList'),warehousenum);
				}
			});
			
		}
		/* 根据id删除菜单  */
		function delRkgoods(id) {
			$.myMessager.confirm("提示！", "确实要删除吗？", function(r) {
				if (r) {
					$.ajax({
						url : '/warehouse/delWarehouseDtoById.jhtml',
						data : {
							"id" : id,
						},
						success : function() {
							$('#rkpertoryList').datagrid('reload');
							$.myMessager.info("删除用户成功!");
						}
					});
				}
			});
		}
		/*查看详情*/
		function xqRkgoods(id) {
			$('#editRK').dialog({    
			    title: '查看入库商品',    
			    modal: true   
			});

			$("#editRK").dialog(
					'refresh',
					'/warehouse/showCreatNew.jhtml?option=2&warehouseId=' + id);
			$("#editRK").dialog('open');

		}
		/* 修改操作*/
		function xggoods(id) {
			
			$('#editRK').dialog({    
			    title: '修改入库商品',    
			    modal: true   
			});

			$("#editRK").dialog('refresh',
					'/warehouse/showCreatNew.jhtml?option=1&warehouseId=' + id);//编辑
			$("#editRK").dialog('open');
		}
	</script>


</body>
</html>