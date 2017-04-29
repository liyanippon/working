<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>供应商</title>
</head>
<body>

<form id="supplierForm">
		<div id="tb" style="padding: 0px; width: 100%">
		<a id="btnSelect" class="easyui-linkbutton" style="width: 80px" data-options="plain:true,iconCls:'icon-search'">查询</a>
		<a id="btnReset" style="width: 80px"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">重置</a>
		</div>
		<div>
		    <div style="float:left; width: 30%; height:35px;">
			<label for="name">企业名称: </label> 
			<input id="txtSupplierName" class="easyui-textbox" style="line-height: 25px; height: 25px; border: 1px solid #ccc">
			</div>
			<div style="float:left; width: 30%; height:35px;">
			<label for="name">联系人: </label> 
			<input id="txtContacts" class="easyui-textbox" style="line-height: 25px; height: 25px; border: 1px solid #ccc">
			</div>
			<div style="float:left; width: 30%; height:35px;">
			<label for="name">联系电话: </label> 
			<input id="txtTel" class="easyui-textbox" style="line-height: 25px; height: 25px; border: 1px solid #ccc">
			</div>
			<div style="float:left; width: 30%; height:35px;">
			<label for="name">联系地址: </label> 
			<input id="txtAddress" class="easyui-textbox" style="line-height: 25px; height: 25px; border: 1px solid #ccc">
		    </div>
		</div>
	</form>

<table id="supplierList" class="easyui-datagrid" style="width:100%;"
		data-options="url:'/supplier/loadSupplierPageData.jhtml',fitColumns:true,singleSelect:true,
		rownumbers:true,pagination:true,striped:true,queryForm:'supplierForm'">
		<thead>
			<tr>
				<th data-options="field:'supplierName',width:70,align:'center'">供应商名称</th>
				<th data-options="field:'people',width:70,align:'center'">联系人</th>
				<th data-options="field:'tel',width:70,align:'center'">联系电话</th>
				<th data-options="field:'adress',width:70,align:'center'">联系地址</th>
				<th data-options="field:'createTime',width:70,align:'center',formatter:time">创建时间</th>
				<th data-options="field:'operate',width:100,align:'center',formatter:formatOper">操作</th>
			</tr>
		</thead>
	</table>



<script type="text/javascript">

/* 操作 */
function formatOper(val, row, index) {
	var id=row.id;
	var deleted = row.deleted;
	var r = '<a href="#" onclick="detail(\''+id+'\')">详情 </a>  '
			+   '<a href="#", onclick="deletes(\''+id+'\')">删除</a>';
	return r;
		}
	
/* 详情页面  */
function detail(id) {
			$('#workspace').panel('refresh',
			'/supplier/supplierDetail.jhtml?supplierId=' + id+"&&operated=2");
}
	
/* 时间 */
function time(val, row, index) {
	var dateformate = new Date(val);
	return dateformate.toLocaleString();
}

/* 模糊查询 */
$('#btnSelect').linkbutton({
	onClick : function() {
		var name = $("#txtSupplierName").textbox("getValue");
		var txtpeople = $("#txtContacts").textbox("getValue");
		var txttel = $("#txtTel").textbox("getValue");
		var txtAddress = $("#txtAddress").textbox("getValue");
		$('#supplierList').datagrid('load', {
			"supplierName" : name,
			"people" : txtpeople,
			"tel" : txttel,
			"adress" : txtAddress,
		});
	}
});

/* 重置 */
$('#btnReset').linkbutton({
	onClick : function() {
		$("#supplierForm").form("reset");
		$("#supplierList").datagrid('load', {});
	}

});

/* 根据id删除用户 */
function deletes(id) {
	$.myMessager.confirm("提示！", "确实要删除该供应商么？", function(r) {
		if (r) {
			$.ajax({
				url : '/supplier/deleteSupplier.jhtml',
				data : {
					"id" : id,
				},
				success : function() {
					$('#supplierList').datagrid('reload');
					$.myMessager.info("删除供应商成功！");
				}
			});
		}
	});
}
</script>
</body>
</html>