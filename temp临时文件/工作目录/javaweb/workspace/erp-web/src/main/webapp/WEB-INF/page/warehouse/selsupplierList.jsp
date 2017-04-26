<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>选择供应商</title>
</head>
<body>
    <form id="selSupplierForm">
		<div id="tb" style="padding: 0px; width: 100%">
			供应商名称：<input type="text" class="easyui-textbox" id="txtSupplierName" style="line-height: 50px; height: 25px; border: 1px solid #ccc">
			<a id="btnSelectSupplier" class="easyui-linkbutton" style="width: 50px">查询</a>
		    <a id="btnResetsSupplier"  class="easyui-linkbutton" style="width: 50px">重置</a>
		</div>
	</form>
    <table id="xzgyList">
    </table>
<div style="margin:40px 0px">
   <a id="btnSave" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width: 100px" onclick="saveGysgx();">保存</a>  
   <a id="btnReset" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" style="width: 100px" onclick="qxGysgx();">取消</a> 
</div>

<script type="text/javascript">
var warehouseId = '<%=request.getParameter("warehouseId")%>';
var warehousenumHid ='<%=request.getParameter("warehousenumHideeneen")%>';
$('#xzgyList').datagrid({
	url : '/warehouse/getNoneSelectSupplier.jhtml?warehouseId='+warehouseId,
	width : '100%',
	rownumbers : true,
	fitColumns : true,
	rownumbers : true,
	pagination : true,
	striped : true,
	queryForm : 'selSupplierForm',
	columns : [ [
	{
		field : 'xz',
		checkbox:true
	},{
		field : 'id',
		title : '供应商ID',
		hidden : true
	}, {
		field : 'supplierName',
		title : '供应商名称',
		width : 70
	}, 
	] ],
	singleSelect : false,
	selectOnCheck : true,
	checkOnSelect : true

});

function saveGysgx(){
	var checkedItems = $('#xzgyList').datagrid('getChecked');
	var supplierstr = [];
	var supplier =""
	$.each(checkedItems, function(index, item){
		supplierstr.push(item.id);
	});               
	console.log(supplierstr.join(","));
	supplier=JSON.stringify(supplierstr);
	
		$.myAjax({
			url : '/warehouse/creatNewGoodsSupplierMappling.jhtml',
			data : {
				"warehouseId" :warehouseId,
				"warehousenum" :warehousenumHid,
				"supplier" :supplier,
			},
			success : function(data) {
				if (data.succ) {
					    $.myMessager.show("保存成功");
						//刷新页面
						forwarehouseId = data.warehouseId;
						$('#yxzgysList').datagrid("options").url = '/warehouse/getGoodsSupplierMapplingList.jhtml?warehouseId='+forwarehouseId; 
						$('#yxzgysList').datagrid("reload");
						$("#selectSupplier").dialog("close");
						
			    }else {
			       $.myMessager.show("保存失败！");
			       $("#selectSupplier").dialog('close');
			    }
			}
		});
}

/* 模糊查询 */
$('#btnSelectSupplier').linkbutton({
	onClick : function() {
		var name = $("#txtSupplierName").textbox("getValue");
		$('#xzgyList').datagrid('load', {
			"supplierName" : name,
		});
	}
});

/* 重置 */
function qxGysgx(){
	$("#selSupplierForm").form("reset");
	$("#xzgyList").datagrid('load', {});

};
</script>
</body>
</html>