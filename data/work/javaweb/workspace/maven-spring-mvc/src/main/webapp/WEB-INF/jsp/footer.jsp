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
</style>
</head>
<body>
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
    
    </script>
</body>
</html>