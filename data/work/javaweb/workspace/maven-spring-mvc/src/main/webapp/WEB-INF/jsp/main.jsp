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

.navbar-nav {
    float: left;
    margin: 0;
    width:70%
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

</style>
</head>
<body>
	<nav class="navbar navbar-default top" role="navigation">
	<div class="container-fluid">
	<div class="navbar-header">
		<a class="navbar-brand" href="#">文档管理</a>
	</div>
	<div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="#">首页</a></li>
			<li><a href="#">标签</a></li>
			<li><div style="margin-top:3%" class="form-inline">
                   <input class="form-control" name="keyword" id="keyword" type="text" style="width: 230px;" placeholder="请输入书名或作者..." value="">
                   <input  class="btn btn-default" type="button" value="查找" id="search" onclick="search()" />
                </div></li>
           <li style="margin-left:10%"><a href="#">登录</a></li>     
		</ul>
	</div>
	</div>
</nav>

<!-- 正文内容 -->
<div class="main">
<div class="row">
<c:forEach items="${requestScope.documentList}" var="emp">
    <div class="col-sm-6 col-md-3">
        <a href="#" class="thumbnail">
            <img src="<%=request.getContextPath() %>/common/style/images/book.jpg"
                 alt="通用的占位符缩略图">
        </a>
        ${emp.documentName}<br>
                   ${emp.authorName}
    </div>
    </c:forEach>

</div>
</div>

<ul class="pagination">
	<li><a href="${pageContext.request.contextPath }/document/list?start=1">&laquo;</a></li>
	<c:forEach var="emp" begin="1" end="${requestScope.page.totalPages}" varStatus="emp">
	<li id="page${emp.index}" onclick="pageNum(id)"><a href="${pageContext.request.contextPath }/document/list?start=${emp.index}">${emp.index}</a></li>
	</c:forEach>
	<li><a href="${pageContext.request.contextPath }/document/list?start=${requestScope.page.totalPages}">&raquo;</a></li>
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
		alert("sd");
        $.ajax({
        //几个参数需要注意一下
            type: "POST",//方法类型
            url: "${pageContext.request.contextPath }/document/list" ,
            data: {keyword:keyword},
            success: function (result) {
                if (result.resultCode == 200) {
                    alert("SUCCESS");
                }
                ;
            },
            error : function() {
                alert("异常！");
            }
        });
    }
</script>
</body>

</html>