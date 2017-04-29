<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<title>用户列表</title>
<body>
	<script type="text/javascript" src="/js/user/userList.js"></script>
	<div style="width: 100%; height: 100%; overflow: hidden;" id="mainDiv">
		<div id="queryDiv"
			style="width: 100%; vertical-align: middle; padding-bottom: 3px; padding-top: 3px; height: 25px;">
			<form id="queryUser" method="post">
				<span style="margin-left: 5px;">账号:</span> 
				<input id="txtStaffName" class="easyui-textbox"
					   style="line-height: 25px; height: 25px; border: 1px solid #ccc">
				<span style="margin-left: 5px;">姓名:</span> 
				<input id="txtStaffnickName" class="easyui-textbox"
					   style="line-height: 25px; height: 25px; border: 1px solid #ccc">
				<span style="margin-left: 5px;">性别:</span> 
				<select id="txtStaffsex" class="easyui-combobox"
					   style="width:150px; height: 25px;" data-options="panelHeight:50"></select>
				<span style="margin-left: 5px;">邮箱:</span>
				 <input id="txtStaffmail" class="easyui-textbox"
					    style="line-height: 25px; height: 25px; border: 1px solid #ccc">
				<span>手机号:</span> 
				<input id="txtStafftel" class="easyui-textbox"
					   style="line-height: 25px; height: 25px; border: 1px solid #ccc">
					
				<a id="btnSelect" class="easyui-linkbutton" style="width: 50px">查询</a>
				<a id="btnReset" style="width: 50px"  class="easyui-linkbutton">重置</a>
				<!--  <a id="crtUser" class="easyui-linkbutton" style="width: 60px;float: right;margin-right: 5px;" onclick="crtUser();">创建用户</a> -->
			</form>
		</div>

		<!-- 查询所有用户 -->
		<div style="width: 100%;" id="dataDiv">
			<table id="userList" style="width: 100%; height: 100%;">
				<thead>
					<tr>
						<!-- <th data-options="field:'id',width:70,align:'center'" >登录账号</th> -->
						<!--  <th data-options="field:'userName',width:70,align:'center'">姓名</th>
						<th data-options="field:'userDetail',width:70,align:'center',formatter:getNickName">昵称</th>
						<th data-options="field:'userDetail',width:70,align:'center',formatter:getmail">邮箱</th>
						<th data-options="field:'userDetail',width:70,align:'center',formatter:gettel">电话</th>
						<th data-options="field:'createTime',width:70,align:'center',formatter:datetime">创建时间</th>
						<th data-options="field:'operate',width:50,align:'center',formatter:formate">操作</th>-->
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<!-- 修改用户信息 -->
	<div id="detail"></div>

</body>
</html>