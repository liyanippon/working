<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="<%=request.getContextPath() %>/common/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/common/css/date_picker.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/common/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/common/css/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/common/css/jquery-ui-timepicker-addon.css" rel="stylesheet" type="text/css" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/common/js/jquery.min.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/common/js/date.js" charset="utf-8"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/common/js/jquery.dataTables.min.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/common/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/common/js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.3/i18n/jquery-ui-timepicker-addon-i18n.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/common/js/jquery-ui-timepicker-zh-CN.js"></script>
<!-- 引入 Bootstrap -->
      <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
 
      <!-- HTML5 Shiv 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
      <!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
      <!--[if lt IE 9]>
         <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
         <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
      <![endif]-->
      <%-- <!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
      <script src="https://code.jquery.com/jquery.js"></script>
      <!-- 包括所有已编译的插件 -->
      <script src="<%=request.getContextPath() %>/common/js/bootstrap.min.js"></script> --%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人信息展示</title>
<style>
.tabless{
padding:0% 4%;
}

</style>
</head>
<body>
	<div class="tabless">
    <table class="table">
    <caption>个人信息展示</caption>
    
  <thead>
    <tr>
      <th>姓名</th>
      <th>性别</th>
      <th>电话</th>
      <th>地址</th>
      <th>操作</th>
    </tr>
  </thead>
  	<tbody>
  		<c:forEach items="${requestScope.userList}" var="emp">
    		<tr>
      		<td>${emp.username}</td>
      		<td>${emp.sex}</td>
      		<td>${emp.phone}</td>
      		<td>${emp.address}</td>
      		<td>
      		<a href="${pageContext.request.contextPath }/user/addandupdate?username=${emp.username}">修改</a>
      		<a href="${pageContext.request.contextPath }/user/delete?username=${emp.username}" style="margin-left:1%">删除</a>
      		</td>
    		</tr>
  		</c:forEach>
  	</tbody>
     	</table>
   </div>
    <script type="text/javascript">
    
    </script>
</body>
</html>