<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户和角色</title>
</head>
<body>
<script type="text/javascript" src="/js/relation/relationList.js"></script>
<div style="width: 100%">
<div style="float: left;width:50%">
	<div id="queryDiv" style="width: 100%; vertical-align: middle; padding-bottom: 3px; padding-top: 3px; height: 25px;">
			<form id="queryRelation" method="post">
				<span style="margin-left: 5px;">公司:</span> 
				<input id="company" class="easyui-combobox" style="line-height: 25px; height: 25px; border: 1px solid #ccc">
				<span style="margin-left: 5px;">部门:</span> 
				<input id="dept" class="easyui-combobox" style="line-height: 25px; height: 25px; border: 1px solid #ccc">
				<a id="btnSelect" class="easyui-linkbutton" style="width: 50px">查询</a>
			</form>
		</div>
		
	    <table id="relationList" class="easyui-datagrid" style="width:50%;"> </table>
	   </div>
	   
	   
	        
<!-- 	easyui-tree  -->
<div style="float: right;width: 49%;">
    <ul id="tree" class="easyui-tree" 
 data-options="url:'/relation/loadrole.jhtml',checkbox:true" style="text-align: left;"></ul> 
    <input type="button" value="关联" style="width: 150px" id="save" onclick="save();">
	<input type="button" value="清空" style="width: 150px" id="reset" onclick="reset();">
</div>


</div>


</body>
</html>