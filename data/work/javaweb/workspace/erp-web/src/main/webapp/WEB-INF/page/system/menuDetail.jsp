<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>菜单详情</title>
</head>

<body>
<script type="text/javascript">
$(function(){
	$.ajax({
	    type: "GET",
	    url: "/system/getMenuDetail.jhtml?id=<%=request.getParameter("id")%>",
						dataType : 'json',
						success : function(data) {
							$("#name").html(data.menuName);
						    $("#type").html(data.nodeType); 
						}
					});
	
		});
</script>

<div id="display">
		<table>
			<tr>
				<td>菜单名称:</td>
				<td id="name"></td>
			</tr>
			<tr>
				<td>菜单类型:</td>
				<td id="type"></td>
			</tr>
			</table>
			</div>
</body>
</html>