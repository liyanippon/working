<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我的请假列表</title>
</head>
<body>
	
	</div style="padding-bottom:10px">
		<a id="btnOut" style="width: 150px;float: left;"  data-options="iconCls:'icon-add'" class="easyui-linkbutton" onclick="toRest();">我要请假</a>
	</div>

	<div id="showOffwork">
		<table id="offworkList" class="easyui-datagrid" style="width: 100%;">
			<thead>
				<tr>
					<th data-options="field:'offworkId',width:10,hidden:true"
						align="center" width="150">请假Id</th>
					<th data-options="field:'offworkTypeName',width:70" align="center"
						width="130">请假类型</th>
					<th data-options="field:'offerworkStarttime',width:70,formatter:time" align="center" 
					width="130">开始时间</th>
					<th data-options="field:'offerworkEndtime',width:70,formatter:time" align="center"
						width="130">结束时间</th>
					<th data-options="field:'strOffworkTime',width:70," align="center"
						width="100">请假时间</th>
					<th data-options="field:'processStatusName',width:70," align="center"
						width="100">审核状态</th>
					<th data-options="field:'processStatus',width:70,hidden:true" align="center"
						width="100">审核状态</th>
					<th data-options="field:'processName',width:70," align="center"
						width="100">审核人</th>
					<th data-options="field:'processTime',width:70,formatter: time"
						align="center" width="130">审核时间</th>
					<th
					data-options="field:'operate',width:100,align:'center', formatter:formatter"
					width="150">操作</th>
			</tr>

				</tr>
			</thead>
		</table>
	</div>
<!-- 打开修改页面 -->
	<div id="newWindow" class="easyui-dialog"
		style="width: 500px; height: 400px; padding: 10px 20px;"
		closed="true"></div>

	<script type="text/javascript">
	/*操作*/
	function formatter(val, row, index) {
		var offworkId = row.offworkId;
		var status = row.status;
		var deleted = row.deleted;
		var dsp = "016002";
		var ysp = "016003";
		var wtg = "016004";
		
		var str = "";
		if (dsp == row.processStatus||wtg==row.processStatus) {
		   str = '<span><a href="#" onclick="detailqjd(\'' + offworkId
			+ '\')" >查看</a></span>  '
			+ '<span><a href="#" onclick="updateqjd(\'' + offworkId
			+ '\')">修改</a></span>  '
			+ '<span><a href="#" onclick="delqjd(\'' + offworkId
			+ '\')">删除</a></span>  ';
		} else if (ysp == row.processStatus) {
			 str = '<span><a href="#" onclick="detailqjd(\'' + offworkId
				+ '\')" >查看</a></span>  ';
		}

		return str;
	}
	/*查看详情*/
	function detailqjd(offworkId) {
		$('#newWindow').dialog({    
		    title: '查看请假单',    
		    modal: true ,
		});
		$('#newWindow').dialog('refresh',
				'/offwork/addOffwork.jhtml?offworkId='+offworkId+'&option=2');
		$("#newWindow").dialog('open');
		
	}
	/*编辑*/
	function updateqjd(offworkId) {
		$('#newWindow').dialog({    
		    title: '修改请假单',    
		    modal: true ,
		});
		$('#newWindow').dialog('refresh',
				'/offwork/addOffwork.jhtml?offworkId='+offworkId+'&option=1');
		$("#newWindow").dialog('open');
		
	}
	 /* 根据id删除菜单  */
	function delqjd(offworkId) {
		$.myMessager.confirm("提示！", "确实要删除吗？", function(r) {
			if (r) {
				$.ajax({
					url : '/offwork/deleteOffworkByoffworkId.jhtml',
					data : {
						"offworkId" : offworkId,
					},
					success : function() {
						$('#offworkList').datagrid('reload');
						$.myMessager.show("删除信息成功!");
					}
				});
			}
		});
	}
		/* 时间 */
		function time(val, row, index) {
			if (null != val && "" != val && undefined != val) {
				var dateformate = new Date(val);
				return dateformate.toLocaleString();
			} else {
				return "";
			}
		}
		
		/*我要请假按钮*/
		function toRest() {
			$('#newWindow').dialog({    
			    title: '我要请假',    
			    modal: true   
			});
			$('#newWindow').dialog('refresh',
					'/offwork/addOffwork.jhtml');
			$("#newWindow").dialog('open');
		}
		
		$('#offworkList').datagrid({
			url:'/offwork/selectMyoffworkList.jhtml',
			fitColumns:true,
			singleSelect:true,
			rownumbers:true,
			pagination:true
			
		});
		
		/* 时间 */
		function time(val, row, index) {
			if(null!=val){
				var dateformate = new Date(val);
				return dateformate.toLocaleString();
			}else{
				return "";
			}
			
		}
	</script>
</body>
</html>