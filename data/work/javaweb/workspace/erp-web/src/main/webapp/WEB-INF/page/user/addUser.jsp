<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新建用户</title>
</head>
<body>
	<div id="addUser" style="width: 100%; height: 100%; overflow: hidden;">
		<form id="form2" name="form2" method="post" class="easyui-form">
		<div style="display: none;">
				<span>id</span> <input class="easyui-textbox" id="userNameForUpdate"
					type="text" name="id" value="<%=request.getParameter("userId")==null?"":request.getParameter("userId")%>"/>
			</div> 
			<div>
				<span style="margin-left: 5px; display:inline-block; width:60px;">账号</span> <input class="easyui-textbox" id="username1"
					type="text" name="username" value=""/>
			</div>
			<div>
				<span style="margin-left: 5px; display:inline-block; width:60px;">密码</span> <input class="easyui-textbox" id="password1"
					type="password" name="password" value=""/>
			</div> 
			<div>
				<span style="margin-left: 5px; display:inline-block; width:60px;">确认密码</span> <input class="easyui-textbox" id="password2"
					type="password" name="passw" />
			</div>
			<div>
			<span style="margin-left: 5px; display:inline-block; width:60px;">所属部门:</span> <select id="dept" class="easyui-combobox"
				style="width: 140px; height: 25px; align: center"></select>
				</div>
		</form>
		<div>
			<input id="save" type="button" name="save" value="保存" />
			 <input id="sa" type="button" name="sa" value="保存并下一步" /> 
			 <input id="reset1" type="button" name="reset" value="重置" />
		</div>
		
	</div>

	<script type="text/javascript">
		$(function() {
			var userName = $("#userNameForUpdate").val();
			
			if(userName != "") {
				$.myAjax({
					type : 'get',
					url : '/user/getUserDetail.jhtml',
					data : {
						"id" : userName
					},
					dataType : 'json',
					success : function(data) {
						$("#username1").textbox('setValue', data.userName);
						$("#username1").textbox('readonly');
					}
				});
			}
			
			$("#save").click(function() {
				var id = userName;
				if(id == ""){
					var pwd1 = $("#password1").val();
					var pwd2 = $("#password2").val();
					if (pwd1==pwd2) {
						$.ajax({
							type : 'post',
							url : '/user/addUser.jhtml',
							data : {
								"username" : $("#username1").val(),
								"password" : $("#password1").val(),
							},
							success : function(data) {
								$.myMessager.info("保存成功");
								$('#workspace').panel('refresh',
										'/user/showStaffList.jhtml');
							}
						});
					} else {
						alert("密码不一致")
					}
				}
				if(id != ""){
					var pwd1 = $("#password1").val();
					var pwd2 = $("#password2").val();
					if (pwd1==pwd2) {
						$.ajax({
							type : 'post',
							url : '/user/updateUser.jhtml?',
							data : {
								"id" : $("#id1").val(),
								"username" : $("#username1").val(),
								"password" : $("#password1").val(),
							},
							success : function(data) {
								$.myMessager.info("保存成功");
								$('#workspace').panel('refresh',
										'/user/showStaffList.jhtml');
							}
						});
					} else {
						$.myMessager.error("密码不一致");
					}
				}
			});

			$("#reset1").click(function() {
				$("#form2").form("reset");
			});
			
			/* 保存并下一步 */
			$("#sa").click(function() {
				if(userName ==""){
					var pwd1 = $("#password1").val();
					var pwd2 = $("#password2").val();
					if (pwd1==pwd2) {
						$.ajax({
							type : 'post',
							url : '/user/addUser.jhtml',
							data : {
								"id" : userName,
								"username" : $("#username1").val(),
								"password" : $("#password1").val(),
							},
							success : function(data) {
								if(data != "") {
									$.myMessager.info("保存成功！");
									$('#workspace').panel('refresh', '/user/showAddUserDetail.jhtml?adduserId='+data);
								} else {
									$.myMessager.error("保存失败！");
								}
							}
						});
					} else {
						alert("密码不一致")
					}
				} else {
					var pwd1 = $("#password1").val();
					var pwd2 = $("#password2").val();
					if (pwd1 == pwd2) {
						$.ajax({
							type : 'post',
							url : '/user/updateUser.jhtml',
							data : {
								"id" : userName,
								"username" : $("#username1").val(),
								"password" : $("#password1").val(),
							},
							success : function(data) {
								if(data == true) {
									$.myMessager.info("修改成功!");
									$('#workspace').panel('refresh', '/user/showAddUserDetail.jhtml?adduserId='+userName);
								} else {
									$.myMessager.error("修改失败！");
								}
							}
						});
					} else {
						alert("密码不一致")
					}
				}
			});
		});
		
	</script>

</body>
</html>