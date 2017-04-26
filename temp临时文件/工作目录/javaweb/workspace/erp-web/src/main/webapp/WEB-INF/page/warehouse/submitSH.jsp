<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>提交审核</title>
</head>
<body>
 <!-- 提交审核 -->
    <form id="formSH" method="post" class="easyui-form" style="width: 300px; height: 10px; padding: 50px 20px;">
	         <div style="display: none " >
				<label>ID </label> <input name="id" class="easyui-textbox" id="id" />
			</div>
			<div style="height: 50px;">
				<label for="name">审核人：</label> 
				<input class="easyui-validatebox" id="shName" type="combobox" name="shName"  />
				<a id="submitsh" class="easyui-linkbutton" style="width: 80px;" onclick="submit();">提交</a>
			</div>

    </form>

<script type="text/javascript">
/*选择审核人下拉框*/
$("#shName").combotree({    
	url: '/org/getOrgTreeList.jhtml',     
	required: true
});
//提交页面
function submit(){
	var godownEntryId ='<%=request.getParameter("godownEntryId")%>';
	var isValid = $("#formSH").form('validate');
	var userTree = $('#shName').combotree('tree');	// 获取人员ID
	var userId = userTree.tree('getSelected').id;
	if(isValid){
	$.myAjax({
		url : '/warehouse/UpdateGodownEntry.jhtml',
		data : {
			"godownEntryId" :godownEntryId,
			"processBy" :userId,//处理人
			"status" :"016002",//待审核
		},
		success : function(data) {
			if (data.succ) {
				    $.myMessager.show("保存成功");
				  //刷新页面
					$('#godownentryList').datagrid('load'); 
					$("#submitSH").dialog("close");
		    }else {
		       $.myMessager.show("保存失败！");
		       $("#submitSH").dialog('close');
		    }
		}
	});	
}
}
//取消页面
function close(){
	 $("#submitSH").dialog('close');
}
</script>
</body>
</html>