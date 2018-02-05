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
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style>
div.menu ul  
{  
    list-style:none; /* 去掉ul前面的符号 */  
    margin: 0px; /* 与外界元素的距离为0 */  
    padding: 0px; /* 与内部元素的距离为0 */  
    width: auto; /* 宽度根据元素内容调整 */  
}  
/* 所有class为menu的div中的ul中的li样式 */  
div.menu ul li
{  
    float:left; /* 向左漂移，将竖排变为横排 */ 
    position:relative;
    left:50%; 
}  
.navbar-fixed-bottom {
    bottom: 0;
    border-width: 1px 0 0;
} 
.container{
	border-top: 2px solid #DDDDD9;
	padding-top:1%;
	padding-bottom:2%
}

.top{
padding-left:20%;
}
.pagination{
padding-left:15%
}
.col-md-3 {
    width: 10%;
}
.thumbnail {
    padding: 0px;
    margin-bottom: 5px;
}
.main{
padding:0% 10%
}
.navbar-nav {
    float: left;
    margin: 0;
    width: 50%;
}
.dropdown-menu {
    
}
</style>
</head>
<body>
	<%    
             if(session.getAttribute("user") == null) {      
   %>    
          <script type="text/javascript" language="javascript">        
            alert("您还没有登录，请登录...");   
           top.location.href="/maven-spring-mvc/user/login";  
           </script>   
   <%    
       }    
   %>
	<%-- <iframe name="content_iframe" marginwidth=0 marginheight=0 width=100% height=5% src="<%=request.getContextPath() %>/WEB-INF/jsp/top.jsp" frameborder=0></iframe> --%> 
<nav class="navbar navbar-default top" role="navigation">
	<div class="container-fluid">
	<div class="navbar-header">
		<a class="navbar-brand" href="${pageContext.request.contextPath }/document/list">文档管理</a>
	</div>
	<div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="#">首页</a></li>
			<li><a href="#">标签</a></li>
			<li><form id="formid"  name= "myform" method = 'post' action = '${pageContext.request.contextPath }/document/list'><div style="margin-top:3%" class="form-inline">
                   <input class="form-control" name="keyword" id="keyword" type="text" style="width: 230px;" placeholder="请输入书名或作者..." value="${requestScope.keyword}">
                   <input  class="btn btn-default" type="submit" value="查找" id="search"  />
                </div></form></li>
		</ul>
		<%-- <c:if test="${requestScope.login=='no'}">
		<ul class="nav navbar-nav navbar-right"> 
            <li><a href="#"><span class="glyphicon glyphicon-user"></span> 注册</a></li> 
            <li><a href="${pageContext.request.contextPath }/user/login"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li> 
        </ul>
        </c:if> --%>
        <c:if test="${requestScope.login=='yes'}">
        <ul class="nav navbar-nav">
			<li class="dropdown">
			<a href="#" class="dropdown-toggle" data-toggle="dropdown">liyanippon <b class="caret"></b>
			</a>
				<ul class="dropdown-menu">
					<li><a id="action-1" href="#">个人中心</a></li>
					<li class="divider"></li>
					<li><a href="#">用户管理</a></li>
					<li class="divider"></li>
					<li><a href="${pageContext.request.contextPath }/document/manager">文档管理</a></li>
					<li class="divider"></li>
					<li><a href="#">设置</a></li>
					<li class="divider"></li>
					<li><a href="#">退出登录</a></li>
				</ul>
			</li>
			</ul>
        </c:if>
	</div>
	</div>
</nav>
<!-- 正文内容 -->
<div class="main">
<div class="row">
<c:forEach items="${requestScope.documentList}" var="emp">
    <div class="col-sm-6 col-md-3" id="num">
        <a href="${pageContext.request.contextPath }/document/details?weight=${requestScope.weight}&documentName=${emp.documentName}" class="thumbnail">
            <img id="headimg" width="175px" height="230px" src="<%=request.getContextPath() %>${emp.headimgsrc}"
                 alt="通用的占位符缩略图">
        </a>
        <span id="docname">${emp.documentName}</span><br>
        <span id="aurname">作者-${emp.authorName}</span>
    </div>
    </c:forEach>
	<div class="col-sm-6 col-md-3" id="num">
		<a href="${pageContext.request.contextPath }/document/add" class="thumbnail">
            <img id="headimg" width="175px" height="230px" src="<%=request.getContextPath() %>/common/style/images/book.png"
                 alt="通用的占位符缩略图">
        </a>
        <span id="docname">添加图书</span><br>
        <span id="aurname"></span>
        </div>
</div>
</div>

<ul class="pagination">
	<li><a href="${pageContext.request.contextPath }/document/list?start=1">&laquo;</a></li>
	<c:forEach var="emp" begin="1" end="${requestScope.page.totalPages}" varStatus="emp">
	<li id="page${emp.index}" onclick="pageNum(id)"><a href="${pageContext.request.contextPath }/document/list?start=${emp.index}">${emp.index}</a></li>
	</c:forEach>
	<li><a href="${pageContext.request.contextPath }/document/list?start=${requestScope.page.totalPages+1}">&raquo;</a></li>
</ul>

<footer class="footer navbar-fixed-bottom ">
    <div class="container">
    <div class="row text-center menu">
            <ul>
                <li><a href="https://www.iminho.me">MinDoc</a></li>
                <li>&nbsp;·&nbsp;</li>
                <li><a href="https://github.com/lifei6671/mindoc/issues" target="_blank">意见反馈</a> </li>
                <li>&nbsp;·&nbsp;</li>
                <li><a href="https://github.com/lifei6671/mindoc">Github</a> </li>
            </ul>
        </div>
    </div>
</footer>
<script type="text/javascript">

	function pageNum(jkl){
		$("#"+jkl).addClass('active');
	}
	function search() {
		
		var keywords=$("#keyword").val();
        $.ajax({
        //几个参数需要注意一下
            type: "POST",//方法类型
            url: "${pageContext.request.contextPath }/document/list",
            data: {keyword:keywords},
            success: function (result) {        
            },
            error : function() {           	
                alert("异常！");
            }
        });
    }

	
</script>
</body>

</html>