<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>考勤明细</title>
</head>
<body>
<script type="text/javascript" src="/js/print/print.js"></script>
	<div>
	   <div id="button" style="align:center">
            <a id="saveAttendanceDetail"
			class="easyui-linkbutton" style="width: 100px" data-options="iconCls:'icon-save'"  onclick="saveAttendanceDetail()">保存</a>
			 <a id="cancel"
			class="easyui-linkbutton" style="width: 100px" data-options="iconCls:'icon-cancel'"  onclick="cancel()">取消</a>
			 <a id="return"
			class="easyui-linkbutton" style="width: 100px" data-options="iconCls:'icon-return'"  onclick="returnym()">返回</a>
			
	  </div>
			<table id="attendanceDetail">
			</table>
	</div>
	
	<script type="text/javascript">
    $('#attendanceDetail').datagrid({
			url : '/attendance/getAttendanceDetailBymonth.jhtml?month=<%=request.getParameter("month")%>',
			iconCls:'icon-edit',
			width : '100%',
			rownumbers : true,
			fitColumns : true,
			rownumbers : true,
			pagination : false,
			striped : true,
			columns : [ [

				{
					field : 'attendanceId',
					title : '考勤ID',
					hidden : true
				}, {
					field : 'attendanceDate',
					title : '考勤日期',
					width : 70,
					formatter:timedate
				}, {
					field : 'normalHour',
					title : '正常工时',
					width : 70,
					formatter:formatterxs,
					editor : {
					    type : 'validatebox',
					    options : {
					       required : true,
					       validType:  ['nunmber','maxNum'],
					    }
					}
				}, {
					field : 'regularOvertimeHours',
					title : '普通加班工时',
					width : 70,
					formatter:formatterxs,
					editor : {
					    type : 'validatebox',
					    options : {
					       required : true,
					       validType:  ['nunmber'],
					    }
					}
				},{
					field : 'overtimeWorkingHours',
					title : '节假日加工工时',
					width : 70,
					formatter:formatterxs,
					editor : {
					    type : 'validatebox',
					    options : {
					       required : true,
					       validType:  ['nunmber'],
					    }
					}
				},{
					field : 'offworkhours',
					title : '请假工时',
					width : 70,
					formatter:formatterxs,
				},{
					field : 'statusName',
					title : '审核状态',
					width : 70,
				},{
					field : 'status',
					title : '审核状态',
					width : 70,
					hidden : true
				},{
					field : 'submitTime',
					title : '提交日期',
					width : 70,
					formatter:time
				},{
					field : 'processName',
					title : '审核人',
					width : 70,
				},{
					field : 'processTime',
					title : '审核日期',
					width : 70,
					formatter:time
				},

				] ],
				singleSelect : false,
				selectOnCheck : true,
				checkOnSelect : true,
				onDblClickCell: function(index,field,value){
		            $(this).datagrid('beginEdit', index);
		            var ed = $(this).datagrid('getEditor', {index:index,field:field});
		            $(ed.target).focus();
	    		},
	    		onEndEdit: function (rowIndex, rowData, changes) {
	                   $.ajax({
	                	   url : '/attendance/insertOrupdateAttendance.jhtml',
	                       data: rowData, 
	                       type: 'POST', 
	                       success: function (data) {
	                    	   if(data.succ){
	                   				$.myMessager.show("保存成功");
	                    	   }else{
	                    		   $.myMessager.show("保存失败！");
	                    	   }
	                       }
	                   }); 
	                   
	    			
	                   editRow = undefined;
	               },
			});
	
		
	//保存入库信息
	function saveAttendanceDetail() {
// 		var updated = $("#attendanceDetail").datagrid('getChanges', "updated"); 
		var rows = $("#attendanceDetail").datagrid('getRows');
// 		var grid =$("#attendanceDetail").datagrid('option');
		
		var isValid = true;
		for ( var i = 0; i < rows.length; i++) {
			if(!$("#attendanceDetail").datagrid('validateRow', i)){
				isValid = false;
		   } 	
		}
	    if(isValid){
			for ( var i = 0; i < rows.length; i++) {
				$("#attendanceDetail").datagrid('endEdit', i);
			   } 
		}
	}
	//取消
	function cancel(){
		 $("#attendanceDetail").datagrid('load',{});
	}
	/*返回*/
	function returnym() {
		var from = '<%=request.getParameter("from")%>';
		if("my"==from){
			$('#workspace')
			.panel('refresh', '/attendance/showMyattendance.jhtml');
		}
		if("cop"==from){
			$('#workspace')
			.panel('refresh', '/attendance/showCopAttendance.jhtml');
		}
		
	}
	/* 时间 */
	function timedate(val, row, index) {
		if(null!=val){
			var dateformate = new Date(val);
			return dateformate.toLocaleDateString();
		}else{
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
	 /*请假类型下拉框*/
	 $("#offworkType").combobox({
		url:'/offwork/getAlloffworkType.jhtml',    
		valueField:'code',    
		textField:'label',
		editable:false,
	 });
	//校验
	$.extend($.fn.validatebox.defaults.rules,{  
	 nunmber:{
         validator:function(value,param){
           return /^\d{1,}$|^\d{1,}.\d{1,1}$/.test(value);
         },
         message: '请输入整数或者保留一位小数'
       },
       maxNum:{  
           validator:function(value,param){ 
        	  
        	   if(value <=8){
        		   return true;  
                   
               }else{  
                   return false;  
               }  
           },  
           message:'正常工时不得超过8小时'  
       }, 
})     
	</script>


</body>
</html>