<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>菜单</title>
</head>
<body>
<script type="text/javascript" src="/js/menu/menuList.js"></script>
<div id="MenuSelect">
<form id="selectMenu" method="post">
菜单名称 : <input type="text" class="easyui-textbox" id="txtmenuName"/>    
菜单种别 : <input type="text" class="easyui-combobox" id="txtmenuType"/> 
URL: <input type="text" class="easyui-textbox" id="txtUrl"/>            
<a id="btnSelect" class="easyui-linkbutton" style="width: 50px">查询</a>
<a id="btnReset" style="width: 50px" href="#" class="easyui-linkbutton">重置</a>
<a id="crtMenu" class="easyui-linkbutton" style="width: 60px" onclick="crtMenu();">添加</a>
</form>
</div>
<table id="menuList" class="easyui-datagrid" style="width:100%;">
    </table>
    
    <!-- 修改 -->
    <div id="updateMenu"  title="菜单修改" class="easyui-dialog" 
		 style="width: 300px; height: 350px; padding: 10px 20px;" closed="true">
		<form id="form1" method="post" class="easyui-form">
			<div style="display: none">
				<label>ID </label> <input name="id" class="easyui-validatebox" id="id" />
			</div>
			<div>
				<label for="name">菜单名称</label> 
				<input class="easyui-textbox" id="menuName1" type="text" name="menuName" />
			</div>
			<div>
				<label for="name">连接名称</label> 
				<input class="easyui-textbox" id="url1" type="text" name="url" />
			</div>
			<div>
				<label for="name">菜单种别</label> 
				<input class="easyui-combobox" id="menuType1" type="text" name="menuType" />
			</div>
			</form>
			</div>
			<!-- 新建菜单 -->
	<div id="createMenu" class="easyui-dialog" 
		style="width: 300px; height: 350px; padding: 10px 20px;" closed="true">
		<form id="form2" method="post" class="easyui-form">
			<div>
				<label for="name">菜单种别</label>
				<input id="menuType2" class="easyui-combobox" name="menuType" >
			</div>
			<div>
				<label for="name">上级菜单</label>
				<input class="easyui-combobox" id="parentId2" name="parentId" />
			</div>
			<div>
				<label for="name">菜单名称</label> 
				<input class="easyui-textbox" id="menuname2" type="text" name="menuName" maxlength="10"/>
			</div>
			<div>
				<label for="name">URL</label>
				 <input class="easyui-textbox" id="url2" type="text" name="url" maxlength="255"/>
			</div>
			
			<div>
				<label for="name">显示顺序</label>
				 <input class="easyui-textbox" id="sortorder2" type="text" name="sortorder" maxlength="16"/>
			</div>
			<div>
				 <input class="easyui-checkbox" id="creContinue" type="checkbox" name="sortorder"/>继续创建下一条
			</div>
			</form>
			</div>
    <script type="text/javascript">
  
    
    </script>
</body>
</html>