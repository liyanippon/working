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
<title>文档详情</title>
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
/*主体内容*/

.mainContext

{

border-bottom:solid 1px #BDBDBD;

border-left:solid 1px #BDBDBD;

border-right:solid 1px #BDBDBD;

width:1080px;

height:1000px;

background-color:#FFFCEC;

margin:0;

padding: 0;

position:relative;

margin-left: auto;

margin-right: auto;

}
/*左侧主体*/

.leftContext

{

float: left;

width: 750px;

border-bottom: 1px solid #BDBDBD;

}

/*左侧主体内容1*/

.leftContextdiv1

{

float: left;

width: 750px;

height:320px;

border-bottom: 1px solid #BDBDBD;

}
/*右侧主体*/

.rightContext

{

float: right;

width: 328px;

height:1000px;

border-left: 1px solid #BDBDBD;

}
.editorRecommend{
padding:2%;
}
.editorRecommends{
padding:1%;
}
.record{
margin-bottom:2px;
background:#EBEBEB
}
.textareacontext{
width:69%;
height:100%;
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
			<li class="active"><a href="#">${requestScope.dmsDocument.documentName}</a></li>	<!-- 文档名称 -->
			
		</ul>
		<ul class="nav">
		<li><a href="#"><img alt="" src="<%=request.getContextPath() %>/common/style/images/editor1.png" onclick="editor()"></a></li>
		</ul>
	</div>
	</div>
</nav>

<!-- 左面 文档基本信息-->
	<!--内容主体-->

<div class="mainContext">

<!--左侧内容-->

<div class="leftContext">

</div>

<!--右侧内容-->

<div class="rightContext ">

<!--编辑内容-->
<div id="recordview" style="" class="editorRecommend">

<div class="record">文档名称：<span id="redocumentName">${requestScope.dmsDocument.documentName}</span></div>
<div class="record">作&emsp;&emsp;者：<span id="reauthorName">${requestScope.dmsDocument.authorName}</span></div>
<div class="record">创建时间：<span id="recreateTime">${requestScope.createTime}</span></div>
<div class="record">修改时间：<span id="reupdateTime">${requestScope.updateTime}</span></div>
<div class="record">备&emsp;&emsp;注：<span id="reremark">${requestScope.dmsDocument.remark}</span></div>

</div>
<div id="recordeditor" style="display:none" class="editorRecommend">

<div class="record">文档名称：<input id="documentName" type="text" value="${requestScope.dmsDocument.documentName}"/></div>
<div class="record">作&emsp;&emsp;者：<input id="authorName" type="text" value="${requestScope.dmsDocument.authorName}"/></div>
<div class="record">创建时间：<span id="">${requestScope.createTime}</span></div>
<div class="record">修改时间：<span id="">${requestScope.updateTime}</span></div>
<div class="record">备&emsp;&emsp;注：<input id="remark" type="text" value="${requestScope.dmsDocument.remark}"/></div>

</div>

上传封面<br>


<!-- 正文内容 -->
<div id="contentview" style="height:100%;width:100%" class="editorRecommends">
<textarea id="recontext" class="textareacontext" disabled>${requestScope.dmsDocument.context}</textarea>
</div>
<div id="contexteditor" style="display:none;height:100%;width:100%" class="editorRecommends">
<textarea id="context" class="textareacontext">${requestScope.dmsDocument.context}</textarea>
</div>
</div>

    <script type="text/javascript">
    function editor(){
    	var recordview=document.getElementById("recordview");
    	var recordeditor=document.getElementById("recordeditor");
    	var contentview=document.getElementById("contentview");
    	var contexteditor=document.getElementById("contexteditor");
    	if(recordeditor.style.display=="none"){
    		recordview.style.display="none";
        	recordeditor.style.display="";
        	contentview.style.display="none";
    		contexteditor.style.display="";
    	}else{
    		update();
    	}
	}
	function update() {
		var documentName=$("#documentName").val();
		var authorName=$("#authorName").val();
		var createTime=$("#createTime").val();
		var updateTime=$("#updateTime").val();
		var remark=$("#remark").val();
		var context=$("#context").val();
		var documentSn='${requestScope.dmsDocument.documentSn}';
        $.ajax({
        //几个参数需要注意一下
            type: "POST",//方法类型
            url: "${pageContext.request.contextPath }/document/update",
            data: {documentName:documentName,authorName:authorName,createTime:createTime
            	,updateTime:updateTime,remark:remark,context:context,documentSn:documentSn},
            dataType:"json",
            complete: function (data) {
            	//更新内容
            	var json=data.responseText;            	
            	var obj =  JSON.parse(json);
            	document.getElementById("redocumentName").innerText = obj.DmsDocument.documentName;
            	document.getElementById("reauthorName").innerText = obj.DmsDocument.authorName;
            	document.getElementById("recreateTime").innerText = obj.createTime;
            	document.getElementById("reupdateTime").innerText = obj.updateTime;
            	document.getElementById("reremark").innerText = obj.DmsDocument.remark;
            	document.getElementById("recontext").innerHTML = obj.DmsDocument.context;
            	
            	recordview.style.display="";
            	recordeditor.style.display="none";
            	contentview.style.display="";
        		contexteditor.style.display="none";
            },
            error : function() {           	
                alert("异常！");
            }
        });
    }
    </script>
</body>
</html>