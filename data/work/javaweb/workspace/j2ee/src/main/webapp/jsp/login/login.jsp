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

	var flag1 = false;
    var flag2 = false;
    $("#username")
            .blur(
                    function() {
                        var userName = $("#username").val();
                        if ($.trim(userName) == ''
                                || $.trim(userName).length < 5
                                || $.trim(userName).length > 15) {
                            $("#userspan")
                                    .html(
                                            "<font color='red'>用户名不能为空，且用户名长度为5-15</font>");
                            flag1 = false;
                        }else{
                        	$("#userspan")
                            .html(
                                    "");
                        }
                    });
	
    $("#password").blur(
            function() {
                var inputPassword = $("#password").val();
                if ($.trim(inputPassword) == "") {
                    $("#pswspan").html("<font color='red'>密码不能为空</font>");
                } else if ($.trim(inputPassword).length > 15
                        || $.trim(inputPassword).length < 5) {
                    $("#pswspan").html("密码长度必须为5-15").css({
                        color : "red"
                    });
                } else {
                    $("#pswspan").html("");
                }

            });
	
	
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
						"password" : $("#password").val()
					},
					success : function(data) {
						
						 
					},
					error: function(request) {  
		                alert("KKJ");
		                
		            }
				});
			 
		  });
});

</script>
</head>
<style type="text/css">
    #wrapper {display:table;width:1000px;height:200px;background:#000;margin:200px auto;color:red;}
	#cell{display:table-cell; vertical-align:middle;}
	#userpass{margin:0 0 0 300px}
	#title{margin:0 0 0 300px}
	#submitId{margin:0 0 0 70px}
</style>
<body>


<div id="wrapper">
    <div id="cell">
        
        <div class="text" id="title"><h2>用户登录</h2></div>
		<div class="text" id="userpass">
		用户名<input type="text" name="username" id="username"><span id="userspan"></span>   
    	<br/>  
          	密&nbsp;&nbsp;&nbsp;码<input type="password" name="password" id="password"><span id="pswspan"></span>  
    	<br/>  
    	<div>
    	<div class="text" >
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