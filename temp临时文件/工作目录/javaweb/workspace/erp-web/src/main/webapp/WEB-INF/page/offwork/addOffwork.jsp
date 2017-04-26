<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我的请假列表</title>
</head>
<body>
	<div id="addNew" class="easyui-form"
		style="width: 400px; height: 100px; padding: 10px 20px;" closed="true">
		<div id = "btn">
			<a id="btnSave" class="easyui-linkbutton" style="width: 90px"onclick="save();">保存并提交</a>  
			<a id="btnReset" class="easyui-linkbutton" style="width: 50px";>重置</a> 
		</div>
		<form id="formcre" method="post" class="easyui-form" style ="padding: 20px 0px;">
		<div style="height: 40px;display:none">
				<label for="name"style="margin-left: 5px; display:inline-block; width:100px;">主键id：</label> 
				<input class="easyui-validatebox" id="offworkId"  type="text" name="offworkId" style="width:200px"/>
			</div>
			<div style="height: 40px;">
				<label for="name"style="margin-left: 5px; display:inline-block; width:90px;">请假类型：</label> 
				<input class="easyui-combobox" id="offworkType"  type="text" name="offworkType" style="width:200px"/>
			</div>
			<div style="height: 40px;">
				<label for="name"style="margin-left: 5px; display:inline-block; width:90px;">请假时间：</label> 
				<input class="easyui-datetimebox" id="offworkStarttime"  type="text" name="offworkStarttime" style="width:200px"/>
			</div>
			<div style="height: 40px;">
				<label for="name"style="margin-left: 5px; display:inline-block; width:90px;">请假时长：</label> 
				<input class="easyui-textbox" id="offworkTime"  type="text" name="offworkTime" style="width:200px"/>
				小时
			</div>
			<div style="height: 40px;">
				<label for="name"style="margin-left: 5px; display:inline-block; width:90px;">请假事由：</label> 
				<input class="easyui-textbox" id="offerworkReason"  type="text" name="offerworkReason" style="width:200px;height:100px;" data-options="multiline:true" />
			</div>
		</form>
	 </div>

	<script type="text/javascript">
	//加载页面时判断是否显示供应商信息

	var option ='<%=request.getParameter("option")%>';//1编辑 2 只读
	var offworkId = '<%=request.getParameter("offworkId")%>';
	   if(option=="2"){
		 $("#btnSave").hide();
	     $("#btnReset").hide();
	     $("#formcre input").attr("disabled","disabled");
		getdetail(offworkId);	
		}else if(option=="1"){
		getdetail(offworkId);	
		}
	/* 重置按钮  */
	$('#btnReset').linkbutton({
		onClick : function() {
			$("#formcre").form("reset");
			$("#formcre").form('load',{});
		}
	});
	
	//保存入库信息
	function save(id) {
		var offworkId ="";
		if(""!=id&&undefined!=id){
			offworkId =id;	 
		}else{
			offworkId=$("#offworkId").val();
		}
		var isValid = $("#formcre").form('validate');
		if(isValid){
			$.myAjax({
				url : '/offwork/addNewOffwork.jhtml',
				data : {
					"offworkId" : offworkId,
					"offworkType" : $("#offworkType").combobox('getValue'),
					"offerworkStarttime" : $("#offworkStarttime").datetimebox('getValue'),
					"offworkTime" : $("#offworkTime").val(),
					"offerworkReason" : $("#offerworkReason").val(),//备注
				},
				
				success : function(data) {
					if (data.succ) {
						    $.myMessager.show("保存成功");
						  //返回数据
						    //getOffworkByreturnid(data.returnid);
						    $("#offworkId").val(data.returnid);
							//刷新页面
							$('#offworkList').datagrid('load'); 
							$('#newWindow').dialog('close'); 
							
				    }else {
				       $.myMessager.show("保存失败！");
				    }
				}
			});
		}
	}
	
	/* 返回数据库信息*/
	function getOffworkByreturnid(offworkid) {
		$.myAjax({
			url:'/offwork/getOffworkDtoById.jhtml?offworkid=' + offworkid,		
			success:function(data) {
				$("#offworkType").combobox("setValue", data.offworkType);
				$("#offworkStarttime").datetimebox("setValue", data.offerworkStarttime);
				$("#offworkTime").val(data.offworkTime);
				$("#offerworkReason").val(data.offerworkReason);
			}
		});
	}
	
	 /*请假类型下拉框*/
	 $("#offworkType").combobox({
		url:'/offwork/getAlloffworkType.jhtml',    
		valueField:'code',    
		textField:'label',
		editable:false,
	 });
	 function getdetail(offworkId){
		 $.myAjax({
			url:'/offwork/getOffworkDtoById.jhtml?offworkId=' + offworkId,		
			success:function(data) {
 				$("#offworkId").val(offworkId);//id
				$("#offworkType").combobox('setValue', data.offworkType);//类别
				$("#offworkStarttime").datetimebox('setValue', timeformat(data.offerworkStarttime));//时间
				$("#offworkTime").textbox('setValue',data.offworkTime);//时长
				$("#offerworkReason").textbox('setValue',data.offerworkReason);//原因
			}
		});
	 }
		function timeformat(mmsecond){
			var datetime = new Date();
		    datetime.setTime(mmsecond);
		    var year = datetime.getFullYear();
		    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
		    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
		    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
		    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
		    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
		    return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
		}
	
	</script>
</body>
</html>