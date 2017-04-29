<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>品类编码</title>
</head>
<body>
	<!-- 新建规则 -->
	<div id="create" class="easyui-form"
		style="width: 600px; height: 100px; padding: 20px 20px;" closed="true">
		<form id="formcre" method="post" class="easyui-form"  >
			<div style="display: none " >
				<label>ID </label> <input name="id" class="easyui-textbox" id="id" />
			</div>
			<div style="height: 50px;">
				<label for="name">前缀1：</label> 
				<input class="easyui-combobox" id="prefixOne" type="combobox" name="prefixOne" />
				<label for="name">规则</label> 
				<input class="easyui-validatebox" id="ruleOne" type="text" name="ruleOne" />
				
			</div>
			<div style="height: 50px;">
				<label for="name">前缀2：</label> 
				<input class="easyui-combobox" id="prefixTwo" type="combobox" name="prefixTwo" />
				
			</div>
			<div style="height: 50px;">
				<label for="name">流水号：</label> 
				<label for="name">长度</label> 
				<input style="width: 100px;"class="easyui-validatebox" id="serialLength"" type="text" name="serialLength"  />
				<label for="name">初始值</label> 
				<input class="easyui-validatebox" id="initvalue" type="text" name="initvalue" />
			</div>
		</form>
		
		<a id="btnSave" class="easyui-linkbutton" style="width: 50px"onclick="saveBatch();">保存</a> 
		<a id="btnUpdate" class="easyui-linkbutton" style="width: 50px hide:true "onclick="updateBatch();">修改</a> 
		<a id="btnReset" class="easyui-linkbutton" style="width: 50px">重置</a> 
	    </div>


	<script type="text/javascript">
	 /*选择设置编发方式*/
	 $("#prefixOne").combobox({    
	    url:'/number/getNumberWaysCode.jhtml?type='+"one",    
	    valueField:'code',    
	    textField:'label'   
	}); 
	 /*编码时间规则*/
	 $("#prefixTwo").combobox({
		url:'/number/getNumberWaysCode.jhtml?type='+"two",    
		valueField:'code',    
		textField:'label' 
	 });  
	 //加载表单数据
  $.myAjax({
	  url:'/number/getNumberDetailBytype.jhtml?numType='+"category",	
		success:function(data) {
			if(null==data.id){
			   $("#btnUpdate").hide();
			   $("#btnSave").show();
			}else{
			   $("#btnSave").hide();
			   $("#btnUpdate").show();
			}
			$("#id").val(data.id);
			$('#prefixOne').combobox({ 
				url:'/number/getNumberWaysCode.jhtml?type='+"one",    
			    valueField:'code',    
			    textField:'label',   
			    onLoadSuccess: function(rec){ 
			    	$("#prefixOne").combobox("setValue", data.prefixOne);
		        }
			});
			$("#ruleOne").val(data.ruleOne);
			$('#prefixTwo').combobox({ 
				url:'/number/getNumberWaysCode.jhtml?type='+"two",    
			    valueField:'code',    
			    textField:'label',   
			    onLoadSuccess: function(rec2){ 
			    	$("#prefixTwo").combobox("setValue", data.prefixTwo);
		        }
			});
			$("#serialLength").val(data.serialLength);
			$("#initvalue").val(data.initvalue);
		}
					
  });


	/*新建批次编号规则*/
function saveBatch() {
	var isValid = $("#formcre").form('validate');
	if(isValid){
	$.myAjax({
			url : '/number/addOrUpdateNumber.jhtml?numType='+"category",
			data : {
				"prefixOne" : $("#prefixOne").combobox("getValue"),
				"ruleOne" : $("#ruleOne").val(),
				"prefixTwo" : $("#prefixTwo").combobox("getValue"),
				"serialLength" : $("#serialLength").val(),
				"initvalue" : $("#initvalue").val(),
			},						
		    success : function(data) {
				if ("true"==data.sucess) {
					    $.myMessager.info("保存成功");
						$("#btnSave").hide();
						$("#btnUpdate").show();
						$("#formcre").form('load',{
							"id" : data.id,
						});
			    }else {
			       $.myMessager.info("保存失败！");
			    }
		     }
          });
	}
}
/*修改批次编号规则*/
function updateBatch() {
	var isValid = $("#formcre").form('validate');
	if(isValid){
	$.myAjax({
			url : '/number/addOrUpdateNumber.jhtml?numType='+"category",
			data : {
				"id" : $("#id").val(),
				"prefixOne" : $("#prefixOne").combobox("getValue"),
				"ruleOne" : $("#ruleOne").val(),
				"prefixTwo" : $("#prefixTwo").combobox("getValue"),
				"serialLength" : $("#serialLength").val(),
				"initvalue" : $("#initvalue").val(),
			},						
		    success : function(data) {
				if ("true"==data.sucess) {
					    $.myMessager.info("修改成功");
						$("#formcre").form("load");
						$("#btnSave").hide();
			    }else {
			       $.myMessager.info("修改失败！");
			    }
		     }
          });
	}
}

/* 重置按钮  */
$('#btnReset').linkbutton({
	onClick : function() {
		$("#formcre").form("reset");
		$("#formcre").form('load',{});
	}
});
	</script>
</body>
</html>