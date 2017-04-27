<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js">
</script>
<script>
$(document).ready(function(){

  $("#resetId").click(function(){
	  $("input[name='username']").val("").focus();
	  $("#password").val("");
  });
  
  $("#submitId").click(
		  function(){
			  $.ajax({
				  type:'post',
				  url :'/SpringMvcUserManager/login',
				  data : {
						"username" : $("#username").val(),
						"password" : $("#password").val(),
						"val_payPlatform" : $('#sexid input[name="sex"]:checked ').val(),
						"address": $("#addressId").val()
					},
					success : function(data) {
						alert(data);
					},
					error: function(request) {  
		                alert(request);
		                
		            }
				});
			 
		  });
});

</script>
</head>
<style type="text/css">
    #wrapper {display:table;width:300px;height:300px;background:#000;margin:200px auto;color:red;}
	#cell{display:table-cell; vertical-align:middle;}
</style>
<body>


<div id="wrapper">
    <div id="cell">
        
        <div class="text" style=" text-align:center;"><h2>用户登录</h2></div>
		<div class="text" style=" text-align:center;">
		用户名<input type="text" name="username" id="username">  
    	<br/>  
          	密&nbsp;&nbsp;&nbsp;码<input type="password" name="password" id="password">  
    	<br/>  
    	<div>
    	<div class="text" style=" text-align:center;">
    	<button id="submitId" class="ui button" type="submit">
		登录
		</button>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 	<button id="resetId" class="ui button" type="submit">
		重置
		</button>
		</div>
		</div>
		</div>

    </div>
</div>




</body>
</html>