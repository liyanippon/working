<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人信息</title>
</head>
<body>
<div id="addUserDetail" style="width: 100%; height: 100%; overflow: hidden;text-align: center;">
   <div>
   <div>个人信息</div>     
   <span style="margin-left: 5px; display:inline-block; width:60px;">账号</span>
   <input class="easyui-textbox" id="username2" name="userName" type="text" readonly="readonly"/>
   </div>
   <form id="form4" name="form4" method="post" class="easyui-form">
   <div style="display: none;">
   userid<input id="id2" name="userid" type="text" value="<%=request.getParameter("adduserId")%>">
   </div>
    <div style="display: none;">
   id<input id=id3 name="id" type="text" class="easyui-textbox">
   </div>
   <div style="display: none;">
        密码
   <input class="easyui-textbox" id="password2" name="password" type="password" readonly="readonly"/>
   </div>
   <div>
   <span style="margin-left: 5px; display:inline-block; width:60px;">用户名</span> 
   <input class="easyui-textbox" id="nickname2" name="nickName" type="text"/>
   </div>
   <div>
   <span style="margin-left: 5px; display:inline-block; width:60px;">性别</span>
   <input id="sex2" name="sex" class="easyui-combobox">
   </div>
   <div>
   <span style="margin-left: 5px; display:inline-block; width:60px;">民族</span>
   <input id="nationality2" name="nationality" class="easyui-combobox">
   </div>
   <div>
   <span style="margin-left: 5px; display:inline-block; width:60px;">婚姻状况</span>
   <input id="maritalStatus2" name="maritalStatus" class="easyui-combobox">
   </div>
   <div>
   <span style="margin-left: 5px; display:inline-block; width:60px;">祖籍</span>
   <input id="nativePlace2" class="easyui-combobox" name="nativePlace" type="text"/>
   </div>
   <div>
   <span style="margin-left: 5px; display:inline-block; width:60px;">身份证</span>
   <input id="idCode2" name="idCode" class="easyui-textbox" id="idCode2" name="idCode" type="text"/>
   </div>
   <div>
   <span style="margin-left: 5px; display:inline-block; width:60px;">手机号</span>
   <input class="easyui-textbox" id="tel2" name="tel" type="text"/>
   </div>
   <div>
   <span style="margin-left: 5px; display:inline-block; width:60px;">邮箱</span>
   <input class="easyui-textbox" id="mail2" name="mail" type="text"/>
   </div>
   </form>
   <div>
			<input id="saveD" type="button" name="saveD" value="保存" /> 
			<input id="resetD" type="reset" name="resetD" onclick="resertDetail();" value="重置" />
		</div>
</div>


<script type="text/javascript">

/* 进入该页面之后先查找账号 */
$(function() {
	$.ajax({
		type : 'get',
		url : '/user/getUserDetail.jhtml',
		data : {
			"id" : $("#id2").val()
		},
		dataType : 'json',
		success : function(data) {
			$("#username2").textbox('setValue', data.userName);
			$("#password2").textbox('setValue', data.password);
			$("#nickname2").textbox('setValue', data.userDetail.nickName);
			$("#tel2").textbox('setValue', data.userDetail.tel);
			$("#sex2").combobox('setValue', data.userDetail.sex);
			$("#nationality2").combobox('setValue', data.userDetail.nationality);
			$("#maritalStatus2").combobox('setValue', data.userDetail.maritalStatus);
			$("#nativePlace2").textbox('setValue', data.userDetail.nativePlace);
			$("#idCode2").textbox('setValue', data.userDetail.idCode);
			$("#mail2").textbox('setValue', data.userDetail.mail);
			$("#id3").textbox('setValue', data.userDetail.id);
		}
	});
});

/*性别下拉框*/
$('#sex2').combobox({
	url:"/user/getSexCodePageData.jhtml",
	valueField:'code',    
	textField:'label'
});

/*婚姻状况下拉框*/
$('#maritalStatus2').combobox({
	url:"/user/getMarCodePageData.jhtml",
	valueField:"code",
	textField:"label",
});

/*祖籍下拉框*/
$('#nativePlace2').combobox({
	url:"/user/getNCodePageData.jhtml",
	valueField:"code",
	textField:"label",
});

/*祖籍下拉框*/
$('#nationality2').combobox({
	url:"/user/getNatCodePageData.jhtml",
	valueField:"code",
	textField:"label",
});


/* 保存 */
$("#saveD").click(function(){
	var id = $("#id3").val();
	$.ajax({
		type : 'post',
		url : '/user/updateUserDetail.jhtml',
		data : {
			"userId" : $("#id2").val(),
			"id3" : $("#id3").val(),
			"username" : $("#username2").val(),
			"password" : $("#password2").val(),
			"nickName" : $("#nickname2").val(),
			"tel" : $("#tel2").val(),
			"mail" : $("#mail2").val(),
			"nationality" : $("#nationality2").combobox("getValue"),
			"sex" : $("#sex2").combobox("getValue"),
			"maritalStatus" : $("#maritalStatus2").combobox("getValue"),
			"nativePlace" : $("#nativePlace2").combobox("getValue"),
			"idCode" : $("#idCode2").val(),
		},
		success : function(data) {
			$.myMessager.info("修改成功");
			$('#workspace').panel('refresh', '/user/showStaffList.jhtml');
		}
	});
	
});

function resertDetail(){
	document.getElementById("form4").reset(); 
}
</script>
</body>
</html>