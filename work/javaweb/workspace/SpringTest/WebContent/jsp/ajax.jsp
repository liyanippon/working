<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js">
</script>
<script>
$(document).ready(function(){
	
  $("#submitId").click(
		  function(){
			  $.ajax({
				  type:'post',
				  url :'/SpringTest/ajax',
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
<body>
<div>

	username:<input type="text" name="username" id="username">  
    <br/>  
    password:<input type="text" name="password" id="password">  
    <br/>  
    <div id="sexid">
          性别:
    <input type="radio"  name="sex" value="男" checked="checked" />男
	<input type="radio" name="sex" value="女" />女
	</div>
    <br/>  
    mail:<input type="text" name="mail" id="mail">  
    <br/>  
    phone:<input type="text" name="phone" id="phone">  
    <br/>  
    remark:<input type="text" name="remark" id="remark">  
    <br/>
          居住地:
    <select  id="addressId">  
            <option>---请选择---</option>  
            <option>湖南</option>  
            <option>湖北</option>  
            <option>浙江</option>  
            <option>广东</option>  
    </select>  
    <br/>  
    
    <button id="submitId" class="ui button" type="submit">
	login
	</button>
</div>
</body>
</html>