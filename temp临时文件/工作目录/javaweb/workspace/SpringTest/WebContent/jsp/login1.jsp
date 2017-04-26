<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js">
<script type="text/javascript">  
    //添加用户  
    function addUser() {  
    	alert("ssdf");
        var form = document.forms[0];  
        form.action = "/SpringTest/login1";
        
        //form.action = "${pageContext.request.contextPath}/user/addUser2";  
        //form.action = "${pageContext.request.contextPath}/user/addUser3";  
        form.method = "post";  
        form.submit();  
    }  
</script>  
</head>
<body>
<form>  
        <table>  
            <tr>  
                <td>username</td>  
                <td>  
                    <input type="text" name="userName">  
                </td>  
            </tr>  
            <tr>  
                <td>pass</td>  
                <td>  
                    <input type="password" name="password">  
                </td>  
            </tr>  
            <tr>  
                <td> </td>  
                <td>  
                    <input type="button" value="submit" onclick="addUser()">  
                </td>  
            </tr>  
        </table>  
    </form>  
</body>
</html>