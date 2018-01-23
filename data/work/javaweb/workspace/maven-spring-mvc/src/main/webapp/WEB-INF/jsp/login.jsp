<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="<%=request.getContextPath() %>/common/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/common/css/date_picker.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/common/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/common/css/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/common/css/jquery-ui-timepicker-addon.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath() %>/common/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/common/js/date.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/common/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/common/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/common/js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.3/i18n/jquery-ui-timepicker-addon-i18n.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/common/js/jquery-ui-timepicker-zh-CN.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<title>登录</title>
<style>
.form-group{
width:30%;
}
.title{
margin:1% 0 1% 5%;
}
.form{
margin:1% 0 1% 5%;
}
.col-sm-2{
    position: relative;
    min-height: 1px;
    padding-right: 0px;
    padding-left: 15px;
}
.form-control {
    width: 50%;
}
</style>
</head>
<body>
<form class="form-horizontal" method ="post" action = '${pageContext.request.contextPath }/user/loginsystem' onsubmit = "return checkUser();">
	<div class="title">用户登录</div>
	<div class="form-group form">
		<label for="firstname" class="col-sm-2 control-label">姓名</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="name" name="name" value="${requestScope.name}"
				   placeholder="请输入姓名">
			<c:if test="${requestScope.message=='nouser'}">   
			<span form-control><font color="red">*用户名不存在</font></span>
			</c:if>
		</div>
	</div>
	<div class="form-group">
		<label for="lastname" class="col-sm-2 control-label">密码</label>
		<div class="col-sm-10">
			<input type="password" class="form-control" id="password" name="password" value="${requestScope.password}"
				   placeholder="请输入密码">
			<c:if test="${requestScope.message=='passerror'}">   
		    <span form-control><font color="red">*密码错误</font></span>
		    </c:if>		    
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<div class="checkbox">
				<label>
					<input type="checkbox"> 请记住我
				</label>
			</div>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-default">登录</button>
		</div>
	</div>
</form>
	<script type="text/javascript">
	function checkUser(){
		
 	   var name = $("#name").val();
 	   var password = $("#password").val();
 	   if(name == ""  ){
 	     alert("用户名不能为空");
 	     
 	     return false;
 	   }
 	   
 	   if(password == ""){
       	    alert("密码不能为空");
       	     return false;
       	   }
 	   else{
 		   if(!window.localStorage){
 	            alert("浏览器支持localstorage");
 	            return false;
 	        }else{
 	            var storage=window.localStorage;
 	            //保存数据到缓存中
 	            storage.setItem("name",name);
 	            storage.setItem("password",password);
 	        }
 	   return true;
 	   }
 	   
 	}
	javascript:window.history.forward(1); 
	</script>
</body>
</html>