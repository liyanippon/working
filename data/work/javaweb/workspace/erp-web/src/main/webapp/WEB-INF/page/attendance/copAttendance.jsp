<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>公司考勤</title>
</head>
<body>
<script type="text/javascript" src="/js/print/print.js"></script>
<div id="cxcopAttendance">
<form id="copAttendanceform">
	<div>
	<a id="btnSelect" class="easyui-linkbutton" style="width: 80px" data-options="plain:true,iconCls:'icon-search'">查询</a>
	<a id="btnReset" style="width: 80px" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">重置</a> 
	</div>
	
	<div style="float:left; width: 30%; height:35px;">
	              年： <input type="text" class="easyui-combobox" id="txtyear" />
	</div>
	<div style="float:left; width: 30%; height:35px;">
		月： <input type="text" class="easyui-combobox" id="txtmonth"/>
	</div>
	<div style="float:left; width: 30%; height:35px;display:none">
		项目： <input type="text" class="easyui-combotree" id="txtproject"/>
	</div>
	<div style="float:left; width: 30%; height:35px;">
		员工姓名： <input type="text" class="easyui-combotree" id="txtuserid" style="width:280px;"/>
	</div>
	
</form>
    <div style= "display:block;float:right;">
		<a id="btnSHTG" class="easyui-linkbutton" style="width: 80px" onclick="saveSHTG()">审核通过</a>
	<a id="btnBH" style="width: 80px"  class="easyui-linkbutton" onclick="saveSHBH()">驳回考勤</a>
	</div>
</div>
	<div>
		<table id="copAttendance">
		</table>
	</div>
	<!--打开审核页面-->
	<div id="submitSHKQ" class="easyui-dialog" closed="true"></div>

	<script type="text/javascript">
		$('#copAttendance').datagrid({
			url : '/attendance/getAllAttendenceBycondition.jhtml',
			iconCls:'icon-edit',
			width : '100%',
			rownumbers : true,
			fitColumns : true,
			rownumbers : true,
			pagination : true,
			striped : true,
			columns : [ [
				 {
					title:'',
					field:'picturePath',
					align:'center',
				    formatter:function(value,row,index){
				    	if(row.status=="016002"){
				    		return '<img src="'+row.picturePath+'" />';
				    		} 
				    	}
				}, 
				{
					field : 'xz',
					checkbox:true
				},{
					field : 'attendanceId',
					title : '考勤ID',
					hidden : true
				}, {
					field : 'attendanceDate',
					title : '考勤日期',
					width : 70,
					formatter:timedate
				}, {
					field : 'userName',
					title : '员工姓名',
					width : 70
				}, {
					field : 'projectId',
					title : '所在项目',
					width : 70,
					hidden:true
				}, {
					field : 'normalHour',
					title : '正常工时',
					width : 50,
					formatter:formatterxs,
				}, {
					field : 'regularOvertimeHours',
					title : '普通加班工时',
					width : 50,
					formatter:formatterxs,
				}, {
					field : 'overtimeWorkingHours',
					title : '节假日加班工时',
					width : 50,
					formatter:formatterxs,
				},{
					field : 'offworkhours',
					title : '请假工时',
					width : 50,
					formatter:formatterxs,
				},{
					field : 'statusName',
					title : '审核状态',
					width : 50
				},{
					field : 'status',
					title : '状态code',
					width : 70,
					hidden:true
				}, {
					field : 'submitTime',
					title : '提交时间',
					width : 90,
					formatter:time
				},{
					field : 'processName',
					title : '审核人',
					width : 70
				},{
					field : 'processTime',
					title : '审核时间',
					width : 90,
					formatter:time
				}

				] ],
				singleSelect : false,
				selectOnCheck : true,
				checkOnSelect : true,
			    onCheck: function(rowIndex,rowData){
			    	if(rowData.status == "016004"||rowData.status=="016003") {
			    		$('#copAttendance').datagrid("uncheckRow", rowIndex);
			    		//$.myMessager.info("该状态不能审核");
			    	}
			    }

			});
		
		
		/*选择类别下拉框*/
		 $("#txtyear").combobox({    
		    url:'/attendance/getAllYears.jhtml',    
		    valueField:'id',    
		    textField:'tAttendanceSumYear',
		    editable:true,
		    onLoadSuccess:function(){
		    	var oDate = new Date(); //实例一个时间对象； 
		    	var year=oDate.getFullYear();   
		        $("#txtyear").combobox("select",year);
		    }
		}); 
		 $('#txtmonth').combobox({    
			    valueField: 'label',
				textField: 'value',
				data: [{
					label: '1',
					value: '一月'
				},{
					label: '2',
					value: '二月'
				},{
					label: '3',
					value: '三月'
				},{
					label: '4',
					value: '四月'
				},{
					label: '5',
					value: '五月'
				},{
					label: '6',
					value: '六月'
				},{
					label: '7',
					value: '七月'
				},{
					label: '8',
					value: '八月'
				},{
					label: '9',
					value: '九月'
				},{
					label: '10',
					value: '十月'
				},{
					label: '11',
					value: '十一月'
				},{
					label: '12',
					value: '十二月'
				}],
				onLoadSuccess:function(){
			    	var oDate = new Date(); //实例一个时间对象； 
			    	var month=oDate.getMonth()+1;   
			        $("#txtmonth").combobox("select",month);
				}
		    	
			});
    /* 小时 */
    function formatterxs(val, row, index) {
				if(null!=val){
					return val+"小时";
				}else{
					return "";
				}
				
			} 
	 /* 时间 */
	function time(val, row, index) {
				if(null!=val&&""!=val&&undefined!=val){
					var dateformate = new Date(val);
					return dateformate.toLocaleString();
				}else{
					return "";
				}
			}
	 /* 时间只返回日期 */
	function timedate(val, row, index) {
				if(null!=val&&""!=val&&undefined!=val){
					var dateformate = new Date(val);
					return dateformate.toLocaleDateString();
				}else{
					return "";
				}
			}
	$("#txtuserid").combotree({
		url: "/org/getOrgTreeList.ajax?pId=-1",
		lines: true,
		onBeforeExpand: function(node) {
			if(node.nodeType == "1") {
				$('#txtuserid').combotree("tree").tree('options').url = "/org/getOrgTreeList.ajax?compId=" + node.id + "&pId=" + node.id;
			} else if(node.nodeType == "2") {
				$('#txtuserid').combotree("tree").tree('options').url = "/org/getDepartTreeList.ajax?pId=" + node.id;
			}
		},
		onClick: function(node){
			if(node.nodeType=="1" || node.nodeType == "2") {
				$('#txtuserid').combotree("clear");
			}
		}
	
	});
	
    /* 模糊查询代码值 */
	$('#btnSelect').linkbutton({
		onClick : function() {
			//年
			var txtyear = $("#txtyear").combobox("getValue");
			//月
			var txtmonth = $("#txtmonth").combobox("getValue");
			
			var userTree = $('#txtuserid').combotree('tree');	// 获取人员ID
			var userId = "";
			if(undefined!=userTree&&null!=userTree.tree('getSelected')){
				 userId = userTree.tree('getSelected').id;
			}

			$('#copAttendance').datagrid('load', {
				year : txtyear,
				month : txtmonth,
				userId : userId,
			});
		}
	});
    
	/* 重置按钮  */
	$('#btnReset').linkbutton({
		onClick : function() {
			var oDate = new Date(); //实例一个时间对象； 
	    	var year=oDate.getFullYear();   
	        $("#txtyear").combobox("select",year);
	    	var month=oDate.getMonth()+1;   
	        $("#txtmonth").combobox("select",month);
	        $("#txtuserid").combotree("reset");
			$("#copAttendance").datagrid('load',{});
		}
	});
		 //审核通过 
		 function saveSHTG(){
				var checkedItems = $('#copAttendance').datagrid('getChecked');
				if(undefined ==checkedItems||""==checkedItems){
					$.myMessager.info("请选择要审核的考勤 ");
				}else{
				var attendanceIdstr = [];
				var attendanceIds =""
				$.each(checkedItems, function(index, item){
					attendanceIdstr.push(item.attendanceId);
				});               
				console.log(attendanceIdstr.join(","));
				attendanceIds=JSON.stringify(attendanceIdstr);
				
					$.myAjax({
						url : '/attendance/updateAttendanceStatus.jhtml',
						data : {
							"attendanceIds" :attendanceIds ,
							"status" :"016003",
						},
						success : function(data) {
							if (data.succ) {
								    $.myMessager.show("保存成功");
								  //刷新页面
									$('#copAttendance').datagrid('load');
									//刷新页面
									
						    }else {
						       $.myMessager.show("保存失败！");
						    }
						}
					});
				}
			}
		//审核驳回
		 function saveSHBH(){
				var checkedItems = $('#copAttendance').datagrid('getChecked');
				if(undefined ==checkedItems||""==checkedItems){
					$.myMessager.info("请选择要审核的考勤 ");
				}else{
					var attendanceIdstr = [];
					var attendanceIds =""
					$.each(checkedItems, function(index, item){
						attendanceIdstr.push(item.attendanceId);
					});               
					console.log(attendanceIdstr.join(","));
					attendanceIds=JSON.stringify(attendanceIdstr);
				
					$.myAjax({
						url : '/attendance/updateAttendanceStatus.jhtml',
						data : {
							"attendanceIds" :attendanceIds ,
							"status" :"016004",
						},
						success : function(data) {
							if (data.succ) {
								    $.myMessager.show("保存成功");
								    $('#copAttendance').datagrid('load');
									//刷新页面
									
						    }else {
						       $.myMessager.show("保存失败！");
						    }
						}
					});
				}
			}
	</script>


</body>
</html>