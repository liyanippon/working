<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>合同管理</title>
</head>
<body>
<form id="contractForm">
		<div id="tb" style="padding: 0px; width: 100%">
		<div style="padding: 0px; width: 100%">
		<a id="btnSelect" class="easyui-linkbutton" style="width: 80px" data-options="plain:true,iconCls:'icon-search'">查询</a>
		<a id="btnReset" style="width: 80px"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">重置</a>
		</div>
	    <div>
	        <div style="float:left; width: 30%; height:35px;">
	        <label for="name">合同编号:</label> 
			<input id="txtLsh" class="easyui-textbox" style="line-height: 25px; height: 25px; border: 1px solid #ccc">
			</div>
			<div style="float:left; width: 30%; height:35px;">
			<label for="name">甲方:</label> 
			<input id="txtFirst" class="easyui-textbox" style="line-height: 25px; height: 25px; border: 1px solid #ccc">
			</div>
			<div style="float:left; width: 30%; height:35px;">
			<label for="name">乙方:</label> 
			<input id="txtSecond" class="easyui-textbox" style="line-height: 25px; height: 25px; border: 1px solid #ccc">
			</div>
	    </div>
		</div>
	</form>

<table id="contractList" class="easyui-datagrid" style="width:100%;"
		data-options="url:'/contract/loadContractPageData.jhtml',fitColumns:true,singleSelect:true,
		rownumbers:true,pagination:true,striped:true,queryForm:'contractForm'">
		<thead>
			<tr>
				<th data-options="field:'lsh',width:70,align:'center'">合同编号</th>
				<th data-options="field:'firstPeople',width:70,align:'center'">甲方</th>
				<th data-options="field:'secondPeople',width:70,align:'center'">乙方</th>
				<th data-options="field:'relevantPeople',width:70,align:'center'">审批相关人</th>
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
	var r = '<a href="#" onclick="detail(\''+id+'\')">详情 </a>    '
	        +   '<a href="#", onclick="updates(\''+id+'\')">修改</a>   '
			+   '<a href="#", onclick="deletes(\''+id+'\')">删除</a>';
	       return r;
		}

/* 时间 */
function time(val, row, index) {
	var dateformate = new Date(val);
	return dateformate.toLocaleString();
}

/* 模糊查询 */
$('#btnSelect').linkbutton({
	onClick : function() {
		var lsh = $("#txtLsh").textbox("getValue");
		var first = $("#txtFirst").textbox("getValue");
		var second = $("#txtSecond").textbox("getValue");
		$('#contractList').datagrid('load', {
			"lsh" : lsh,
			"firstPeople" : first,
			"secondPeople" : second,
		});
	}
});

/* 重置 */
$('#btnReset').linkbutton({
	onClick : function() {
		$("#contractForm").form("reset");
		$("#contractList").datagrid('load', {});
	}

});

/* 根据id删除合同 */
function deletes(id) {
	$.myMessager.confirm("提示！", "确实要删除合同吗 ？", function(r) {
		if (r) {
			$.ajax({
				url : '/contract/deleteContract.jhtml',
				data : {
					"id" : id,
				},
				success : function() {
					$('#contractList').datagrid('reload');
					$.myMessager.info("删除合同成功！");
				}
			});
		}
	});
}

/* 详情页面  */
function detail(id) {
			$('#workspace').panel('refresh',
			'/contract/contractDetail.jhtml?contractId=' + id+"&&operated=2");
}

/* 修改页面  */
function updates(id) {
			$('#workspace').panel('refresh',
			'/contract/contractDetail.jhtml?contractId=' + id);
}


</script>
</body>
</html>