<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我的请假列表</title>
</head>
<body>
		<div id="selectOffwork">
	<form id="selectCondition">
	<div>
	<a id="btnSelect" class="easyui-linkbutton" style="width: 80px" data-options="plain:true,iconCls:'icon-search'">查询</a> 
	<a id="btnResetcop" style="width: 80px" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">重置</a>
	</div>
	<div>
	<div style="float:left; width: 30%; height:35px;">
	 开始时间： <input type="text" class="easyui-datebox" id="txtsssbeginDate" />
	</div>
	<div style="float:left; width: 30%; height:35px;">
	 结束时间： <input type="text" class="easyui-datebox" id="txtendDate" />
	</div>
	<div style="float:left; width: 30%; height:35px;">
		状态： <input type="text" class="easyui-combobox" id="txtstatus"/>
		</div>
	<div style="float:left; width: 30%; height:35px;padding-left:14px">
		请假人： <input type="text" class="easyui-combotree" id="txtuserName"/>
		</div>
	<div style="float:left; width: 30%; height:35px;padding-left:0px">
		审核人： <input type="text" class="easyui-combotree" id="txtprocessBy"/>
		</div>
	</div>
</form>
	</div>
	<div id="showOffwork">
		<table id="offworkList" class="easyui-datagrid" data-options="url:'/offwork/selectCopoffworkList.jhtml',fitColumns:true,singleSelect:true,rownumbers:true,pagination:true"style="width: 100%;">
			<thead>
				<tr>
					<th data-options="field:'offworkId',width:70,hidden:true"
						align="center" width="150">请假Id</th>
					<th data-options="field:'createByName',width:70" align="center"
						width="110">请假人员</th>
					<th data-options="field:'offworkTypeName',width:70" align="center"
						width="130">请假类型</th>
					<th data-options="field:'offerworkStarttime',width:70,formatter: time" align="center" 
					width="130">开始时间</th>
					<th data-options="field:'offerworkEndtime',width:70,formatter: time" align="center"
						width="130">结束时间</th>
					<th data-options="field:'offworkTime',width:70,formatter:formatterxs," align="center"
						width="130">请假时间</th>
					<th data-options="field:'processStatus',width:70,hidden:true" align="center"
						width="110">审核状态</th>
					<th data-options="field:'processStatusName',width:70," align="center"
						width="110">审核状态</th>
					<th data-options="field:'processName',width:70," align="center"
						width="110">审核人</th>
					<th data-options="field:'processTime',width:70,formatter: time"
						align="center" width="130">审核时间</th>
                    <th data-options="field:'operate',width:100,align:'center', formatter:formatter"
					width="150",>操作</th>
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
		var processStatus = row.processStatus;
		var deleted = row.deleted;
		var dsp = "016002";
		var ysp = "016003";
		var wtg = "016004";
		
		var str = "";
		if (dsp == row.processStatus) {
		   str = '<span><a href="#" onclick="detailygqjd(\'' + offworkId
			+ '\')" >查看</a></span>  '
			+ '<span><a href="#" onclick="agreeqjd(\'' + offworkId
			+ '\')">通过审批</a></span>  '
			+ '<span><a href="#" onclick="returnqjd(\'' + offworkId
			+ '\')">驳回</a></span>  ';
		} else if (ysp == row.processStatus) {
			 str = '<span><a href="#" onclick="detailqjd(\'' + offworkId
				+ '\')" >查看</a></span>  ';
		}else if (wtg== row.processStatus) {
			
			str = '<span><a href="#" onclick="detailygqjd(\'' + offworkId
			+ '\')" >查看</a></span>  '
			+ '<span><a href="#" onclick="agreeqjd(\'' + offworkId
			+ '\')">通过审批</a></span>  ';
		}

		return str;
	}
	/*查看详情*/
	function detailygqjd(offworkId) {
		$('#newWindow').dialog({    
		    title: '查看请假单',    
		    modal: true ,
		});
		$('#newWindow').dialog('refresh',
				'/offwork/addOffwork.jhtml?offworkId='+offworkId+'&option=2');
		$("#newWindow").dialog('open');
		
	}
	
	/*审批通过*/
	function agreeqjd(offworkId) {
		$.myAjax({
			url : '/offwork/UpdateOffworkstatus.jhtml',
			data : {
				"offworkId" : offworkId,
				"processStatus" : "016003",//已审核
			},
			success : function(data) {
				if (data.succ) {
					$.myMessager.show("保存成功");
					//刷新页面
					$('#offworkList').datagrid('load');

				} else {
					$.myMessager.show("保存失败！");
				}
			}
		});
	}
	/*审批不通过*/
	function returnqjd(offworkId) {
		$.myAjax({
			url : '/offwork/UpdateOffworkstatus.jhtml',
			data : {
				"offworkId" : offworkId,
				"processStatus" : "016004",//审批不通过
			},
			success : function(data) {
				if (data.succ) {
					$.myMessager.show("保存成功");
					//刷新页面
					$('#offworkList').datagrid('load');

				} else {
					$.myMessager.show("保存失败！");
				}
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
		 /* 小时 */
	    function formatterxs(val, row, index) {
					if(null!=val){
						return val+"小时";
					}else{
						return "";
					}
					
				} 
		
		/* 重置按钮  */
		$('#btnResetcop').linkbutton({
			onClick : function() {
				$("#selectCondition").form("reset");
				$("#offworkList").datagrid('load', {});
			}
		});
		$("#txtuserName").combotree({
			url: "/org/getOrgTreeList.ajax?pId=-1",
			lines: true,
			onBeforeExpand: function(node) {
				if(node.nodeType == "1") {
					$('#txtuserName').combotree("tree").tree('options').url = "/org/getOrgTreeList.ajax?compId=" + node.id + "&pId=" + node.id;
				} else if(node.nodeType == "2") {
					$('#txtuserName').combotree("tree").tree('options').url = "/org/getDepartTreeList.ajax?pId=" + node.id;
				}
			},
			onClick: function(node){
				if(node.nodeType=="1" || node.nodeType == "2") {
					$('#txtuserName').combotree("clear");
				}
			}
		
		});
		$("#txtprocessBy").combotree({
			url: "/org/getOrgTreeList.ajax?pId=-1",
			lines: true,
			onBeforeExpand: function(node) {
				if(node.nodeType == "1") {
					$('#txtprocessBy').combotree("tree").tree('options').url = "/org/getOrgTreeList.ajax?compId=" + node.id + "&pId=" + node.id;
				} else if(node.nodeType == "2") {
					$('#txtprocessBy').combotree("tree").tree('options').url = "/org/getDepartTreeList.ajax?pId=" + node.id;
				}
			},
			onClick: function(node){
				if(node.nodeType=="1" || node.nodeType == "2") {
					$('#txtprocessBy').combotree("clear");
				}
			}
		
		});
		/* 模糊查询代码值 */
		$('#btnSelect').linkbutton({
			onClick : function() {
				var userId = "";
				var processBy = "";
				//开始时间
				var txtsssbeginDate = $("#txtsssbeginDate").datetimebox("getValue");
				//结束时间
				var txtendDate = $("#txtendDate").datetimebox("getValue");
				
				var txtstatus = $("#txtstatus").combobox("getValue");
				
				var txtuserName = $('#txtuserName').combotree('tree');
				if(txtuserName.tree('getSelected')!=null){
					userId = txtuserName.tree('getSelected').id;
				}
		        var txtprocessBy = $('#txtprocessBy').combotree('tree');
		        if(txtprocessBy.tree('getSelected')!=null){
		        	processBy = txtprocessBy.tree('getSelected').id;
		        }
		        
				$('#offworkList').datagrid('load', {
					offerworkStarttime : txtsssbeginDate,
					offerworkEndtime : txtendDate,
					processStatus: txtstatus,
					userId:userId,
					processBy:processBy,
					
				});
			}
		});
		
		 /*审核状态下拉框*/
		 $("#txtstatus").combobox({
			url : '/warehouse/getStatusCode.jhtml',
			valueField : 'code',
			textField : 'label',
			editable : false,
			/* data: [{
				label: '待审批',
				value: '016002'
			},{
				label: '已审批',
				value: '016003'
			},{
				label: '未通过',
				value: '016004'
			}], */
		});
	</script>
</body>
</html>