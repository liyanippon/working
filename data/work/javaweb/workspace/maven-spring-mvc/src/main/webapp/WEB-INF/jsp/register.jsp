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
<title>注册</title>
<style>
.form1{
margin-left:40%;
margin-right:30%
}
dl{width:100%;}
dt{float:left;width:20%}
dd{width:100%}
dd input.textinput{padding:0px 0px;width:50%;margin-right:30%}
dd textarea{padding:0px 0px;width:50%;margin-right:30%}
#register{float:right;width:20%;background:#0000ff;color:#ffffff}
</style>
</head>
<body>
	<center>
        <h2>注册</h2>
    </center>
    <form id="formid"  name= "myform" method = 'post' action = '${pageContext.request.contextPath }/user/save' onsubmit = "return checkUser();">
            <div class="form1">
              <dl>
                <dt>姓名</dt>
                <dd>
                	<input type="text" value='${requestScope.user.username}'  name = "username" class="textinput" id = "username"/>
                </dd>
              </dl>
              <dl>
                <dt>性别</dt>
                <dd>
                	<input name="sex" id="sex" type="radio" checked="checked" value="男" />男<input name="sex" style="margin-left:10%" type="radio" value="女" />女
				</dd>
              </dl>
              <dl>
                <dt>电话号码</dt>
                <dd><input type="text" value='${requestScope.user.phone}'  name = "phone" class="textinput"  id = "phone"/></dd>
              </dl>
              <dl>
                <dt>住址</dt>
                <dd><textarea class="address" name="address" rows="3" cols="">${requestScope.user.address}</textarea></dd>
              </dl>
              <dl>
                <dt>登录密码</dt>
                <dd><input type="password" value="${requestScope.user.password}" name = "password" class="textinput" id = "password"/></dd>
              </dl>
              <dl>
                <dt>确认密码</dt>
                <dd><input type="password" value="${requestScope.user.password}" name = "repassword" class="textinput" id = "repassword"/></dd>
              </dl>
              <dl>
                <dt><input type=text style="visibility: hidden;"/></dt>
                <dd>
                    <input  class="textinput" type="submit" value="注册" id="register"  />
                </dd>
                </dl>
             </div>
    </form>
    <script type="text/javascript">
    var selects = document.getElementsByName("sex");
    for (var i=0; i<selects.length; i++){
      if (selects[i].value=="${requestScope.sex}") {
    		selects[i].checked= true;
     			break;
     }
    }
 
    
    	function checkUser(){
    	   var username = document.getElementById("username").value;
    	   var phone =document.getElementById("phone").value;
    	   var password =document.getElementById("password").value;
    	   var repassword=document.getElementById("repassword").value;
    	    
    	   if(username == ""  ){
    	     alert("用户名不能为空");
    	     
    	     return false;
    	   }
    	   if(phone == ""  ){
       	    alert("电话号码不能为空");
       	     return false;
       	   }
    	   if(!(/^1\d{10}$/.test(phone))){//11位数字以1开头
    	   		alert("请输入正确的手机号码");
    	   		return false;
    	   }
    	   if(password == "" || repassword ==""){
          	    alert("密码不能为空");
          	     return false;
          	   }
    	   if(password!=repassword){
    	    alert("两次密码输入不同！");
    	     return false;
    	   }
    	   else{
    		   if(!window.localStorage){
    	            alert("浏览器支持localstorage");
    	            return false;
    	        }else{
    	            var storage=window.localStorage;
    	            //保存数据到缓存中
    	            storage.setItem("username",username);
    	            storage.setItem("sex",document.getElementById("sex").value);
    	            storage.setItem("address",document.getElementById("address").value);
    	            storage.setItem("phone",phone);
    	            storage.setItem("password",password);
    	        }   
    	   return true;
    	   }
    	   
    	}
    	
    </script>
</body>
</html>