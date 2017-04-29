<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

	<script type="text/javascript">
$(function(){  
	$.ajax({
	    type: "GET",
	    url: "/user/getUserDetail.jhtml?id=<%=request.getParameter("id")%>",
						dataType : 'json',
						success : function(data) {
							$("#name").html(data.userName);
							$("#usernickName").html(data.userDetail.nickName);
							$("#usermail").html(data.userDetail.mail);
							$("#usermobile").html(data.userDetail.tel);
							$("#usercreateTime").html(new Date(data.createTime).toLocaleString());
						}
					});
		});
	</script>

	<div id="display">
		<table>
			<tr>
				<td>账号:</td>
				<td id="name"></td>
			</tr>
			<tr>
				<td>姓名:</td>
				<td id="usernickName"></td>
			</tr>
			<tr>
				<td>邮箱:</td>
				<td id="usermail"></td>
			</tr>
			<tr>
				<td>电话:</td>
				<td id="usermobile"></td>
			</tr>
			<tr>
				<td>创建时间:</td>
				<td id="usercreateTime"></td>
			</tr>
			</table>
			</div>
</body>
</html>